/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra Collaboration Suite Server
 * Copyright (C) 2011, 2012, 2013 Zimbra, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software Foundation,
 * version 2 of the License.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 * ***** END LICENSE BLOCK *****
 */

package com.zimbra.soap.mail.message;

import com.google.common.base.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.zimbra.common.soap.MailConstants;
import com.zimbra.soap.mail.type.MailCalDataSource;
import com.zimbra.soap.mail.type.MailCaldavDataSource;
import com.zimbra.soap.mail.type.MailGalDataSource;
import com.zimbra.soap.mail.type.MailImapDataSource;
import com.zimbra.soap.mail.type.MailPop3DataSource;
import com.zimbra.soap.mail.type.MailRssDataSource;
import com.zimbra.soap.mail.type.MailUnknownDataSource;
import com.zimbra.soap.mail.type.MailYabDataSource;
import com.zimbra.soap.type.DataSource;

/**
 * @zm-api-command-auth-required true
 * @zm-api-command-admin-auth-required false
 * @zm-api-command-description Creates a data source that imports mail items into the specified folder, for example
 * via the POP3 or IMAP protocols.  Only one data source is allowed per request.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name=MailConstants.E_CREATE_DATA_SOURCE_REQUEST)
public class CreateDataSourceRequest {

    /**
     * @zm-api-field-description The data source
     */
    @XmlElements({
        @XmlElement(name=MailConstants.E_DS_IMAP /* imap */, type=MailImapDataSource.class),
        @XmlElement(name=MailConstants.E_DS_POP3 /* pop3 */, type=MailPop3DataSource.class),
        @XmlElement(name=MailConstants.E_DS_CALDAV /* caldav */, type=MailCaldavDataSource.class),
        @XmlElement(name=MailConstants.E_DS_YAB /* yab */, type=MailYabDataSource.class),
        @XmlElement(name=MailConstants.E_DS_RSS /* rss */, type=MailRssDataSource.class),
        @XmlElement(name=MailConstants.E_DS_GAL /* gal */, type=MailGalDataSource.class),
        @XmlElement(name=MailConstants.E_DS_CAL /* cal */, type=MailCalDataSource.class),
        @XmlElement(name=MailConstants.E_DS_UNKNOWN /* unknown */, type=MailUnknownDataSource.class)
    })
    private DataSource dataSource;

    public CreateDataSourceRequest() {
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public DataSource getDataSource() { return dataSource; }

    public Objects.ToStringHelper addToStringInfo(Objects.ToStringHelper helper) {
        return helper
            .add("dataSource", dataSource);
    }

    @Override
    public String toString() {
        return addToStringInfo(Objects.toStringHelper(this)).toString();
    }
}
