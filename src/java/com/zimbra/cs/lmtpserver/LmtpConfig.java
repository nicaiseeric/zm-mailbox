/*
 * Created on Dec 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.zimbra.cs.lmtpserver;

import com.zimbra.cs.account.Provisioning;
import com.zimbra.cs.account.Server;
import com.zimbra.cs.service.ServiceException;
import com.zimbra.cs.util.Config;

/**
 * @author schemers
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LmtpConfig {

    private int mNumThreads;
    private int mPort;
    private String mBindAddress;
    private String mAdvertisedName;
    
    public LmtpConfig() throws ServiceException {
        reload();
    }
    
    public void reload() throws ServiceException {
        Server config = Provisioning.getInstance().getLocalServer();
        mNumThreads = config.getIntAttr(Provisioning.A_zimbraLmtpNumThreads, Config.D_LMTP_THREADS);
        mPort = config.getIntAttr(Provisioning.A_zimbraLmtpBindPort, Config.D_LMTP_BIND_PORT);
        mBindAddress = config.getAttr(Provisioning.A_zimbraLmtpBindAddress, Config.D_LMTP_BIND_ADDRESS);
        mAdvertisedName = config.getAttr(Provisioning.A_zimbraLmtpAdvertisedName, Config.D_LMTP_ANNOUNCE_NAME);
    }
    /**
     * @return Returns the advertisedName.
     */
    public String getAdvertisedName() {
        return mAdvertisedName;
    }
    /**
     * @return Returns the bindAddress.
     */
    public String getBindAddress() {
        return mBindAddress;
    }
    /**
     * @return Returns the numThreads.
     */
    public int getNumThreads() {
        return mNumThreads;
    }
    /**
     * @return Returns the port.
     */
    public int getPort() {
        return mPort;
    }
}
