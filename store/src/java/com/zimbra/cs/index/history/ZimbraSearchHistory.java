package com.zimbra.cs.index.history;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.account.Account;

/**
 * Store that uses a helper SearchHistoryMetadata class for storing search history
 * and a SearchHistoryIndex class for indexing it.
 * Most operations are delegated to SearchHistoryMetadata, but searching search history
 * first uses the index to look up the IDs of matching search history entries.
 */
public class ZimbraSearchHistory extends SearchHistory {

    private SearchHistoryMetadata mdStore;
    private SearchHistoryIndex index;
    private SearchHistoryConfig config;
    private SavedSearchPromptLog promptLog;


    public ZimbraSearchHistory(SearchHistoryMetadata mdStore, SearchHistoryIndex index,
           SearchHistoryConfig config, SavedSearchPromptLog promptLog) {
       this.mdStore = mdStore;
       this.index = index;
       this.config = config;
       this.promptLog = promptLog;
    }

    @Override
    public boolean contains(String searchString) throws ServiceException {
        return mdStore.contains(searchString);
    }

    @Override
    public void registerSearch(int id, String searchString) throws ServiceException {
        mdStore.initNewSearch(id, searchString);
        index.add(id, searchString);
    }

    @Override
    public void logSearch(String searchString, long millis) throws ServiceException {
        mdStore.logSearch(searchString, millis);
    }

    @Override
    public void purgeHistory(long maxAgeMillis) throws ServiceException {
        Collection<Integer> ids = mdStore.deleteByAge(maxAgeMillis);
        index.delete(ids);
    }

    @Override
    public void deleteHistory() throws ServiceException {
        index.deleteAll();
        mdStore.deleteAll();
    }

    @Override
    public List<String> search(SearchHistoryParams params) throws ServiceException {
        SearchHistoryMetadataParams mdParams = new SearchHistoryMetadataParams(params);
        long maxAge = config.getMaxAge();
        mdParams.setMaxAge(maxAge);
        if(params.hasPrefix()) {
            String prefix = params.getPrefix();
            Collection<Integer> ids = index.search(prefix);
            if (ids.isEmpty()) {
                return Collections.emptyList();
            } else {
                mdParams.setIds(ids);
            }
        }
        return mdStore.search(mdParams);
    }

    @Override
    public int getCount(String searchString) throws ServiceException {
        return mdStore.getCount(searchString, config.getMaxAge());
    }

    @Override
    public SavedSearchPromptLog getPromptLog() throws ServiceException {
        return promptLog;
    }

    public static abstract class Factory implements SearchHistory.Factory {
        public abstract SearchHistoryIndex getIndex(Account acct) throws ServiceException;
        public abstract SearchHistoryMetadata getMetadataStore(Account acct) throws ServiceException;
        public abstract SearchHistoryConfig getConfig(Account acct) throws ServiceException;
        public abstract SavedSearchPromptLog getPromptLog(Account acct) throws ServiceException;

        @Override
        public SearchHistory getSearchHistory(Account acct) throws ServiceException {
            SearchHistoryIndex index = getIndex(acct);
            SearchHistoryMetadata mdStore = getMetadataStore(acct);
            SearchHistoryConfig config = getConfig(acct);
            SavedSearchPromptLog promptLog = getPromptLog(acct);
            return new ZimbraSearchHistory(mdStore, index, config, promptLog);
        }
    }

    public static class SearchHistoryMetadataParams extends SearchHistoryParams {
        private Collection<Integer> ids;

        public SearchHistoryMetadataParams() {
            this(0, 0L);
        }

        public SearchHistoryMetadataParams(int numResults) {
            this(numResults, 0L);
        }

        public SearchHistoryMetadataParams(int numResults, long maxAge) {
            super(numResults, maxAge, null);
        }

        public SearchHistoryMetadataParams(SearchHistoryParams params) {
            this(params.getNumResults(), params.getMaxAge());
        }
        public void setIds(Collection<Integer> ids) {
            this.ids = ids;
        }

        public Collection<Integer> getIds() {
            return ids;
        }

        public boolean hasIds() {
            return ids != null && !ids.isEmpty();
        }
    }

    /**
     * Searchable index of search history entries.
     * The index only returns IDs of entries, the actual string must be looked up in
     * the metadata store.
     */
    public static interface SearchHistoryIndex {

        /**
         * Add a search history entry to the index with the given ID and timestamp
         */
        public void add(int id, String entry) throws ServiceException;

        /**
         * Search the index for IDs of matching entries
         */
        public Collection<Integer> search(String searchString) throws ServiceException;

        /**
         * Delete entries with the given ids from the index
         */
        public void delete(Collection<Integer> ids) throws ServiceException;

        /**
         * Delete all search history data from the index
         */
        public void deleteAll() throws ServiceException;
    }

    /**
     * Metadata store for search history entries. Does not support text searches;
     * this must be done by the HistoryIndex.
     */
    public static interface SearchHistoryMetadata {

        /**
         * Store a search history entry in the metadata store.
         */
        public void initNewSearch(int id, String searchString) throws ServiceException;

        /**
         * Search the metadata store for matching entries
         */
        public List<String> search(SearchHistoryMetadataParams params) throws ServiceException;

        /**
         * Determine whether this entry already exists in the store
         */
        public boolean contains(String searchString) throws ServiceException;

        /**
         * Add a new history entry for a known search string
         */
        public void logSearch(String searchString, long millis) throws ServiceException;

        /**
         * Delete entries with the given parameters from the metadata store and
         * return the IDs to be deleted from the index
         */
        public Collection<Integer> deleteByAge(long maxAgeMillis) throws ServiceException;

        /**
         * Delete all search history
         */
        public void deleteAll() throws ServiceException;

        /**
         * Get the number of times the given term was searched within the given timeframe
         */
        public int getCount(String searchString, long maxAgeMillis)  throws ServiceException;
    }


    public static interface SearchHistoryConfig {

        /**
         * Search queries older than this will not be included
         * in results, and will eventually be deleted
         */
        public long getMaxAge() throws ServiceException;
    }
}