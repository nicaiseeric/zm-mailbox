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

package com.zimbra.soap.admin.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.zimbra.common.soap.AccountConstants;
import com.zimbra.common.soap.AdminConstants;
import com.zimbra.soap.admin.type.AdminAttrsImpl;
import com.zimbra.soap.type.Id;

/**
 * @zm-api-command-auth-required true
 * @zm-api-command-admin-auth-required true
 * @zm-api-command-description Changes attributes of the given data source.
 * Only the attributes specified in the request are modified. To change the name, specify 
 * <b>"zimbraDataSourceName"</b> as an attribute.
 * <br />
 * <br />
 * note: this request is by default proxied to the account's home server
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name=AdminConstants.E_MODIFY_DATA_SOURCE_REQUEST)
public class ModifyDataSourceRequest extends AdminAttrsImpl {

    // Id for existing Account
    /**
     * @zm-api-field-tag account-id
     * @zm-api-field-description Existing account ID
     */
    @XmlAttribute(name=AdminConstants.E_ID, required=true)
    private final String id;

    /**
     * @zm-api-field-description Data source specification
     */
    @XmlElement(name=AccountConstants.E_DATA_SOURCE, required=true)
    private final Id dataSource;

    /**
     * no-argument constructor wanted by JAXB
     */
    @SuppressWarnings("unused")
    private ModifyDataSourceRequest() {
        this((String) null, (Id) null);
    }

    public ModifyDataSourceRequest(String id, Id dataSource) {
        this.id = id;
        this.dataSource = dataSource;
    }

    public String getId() { return id; }
    public Id getDataSource() { return dataSource; }
}
