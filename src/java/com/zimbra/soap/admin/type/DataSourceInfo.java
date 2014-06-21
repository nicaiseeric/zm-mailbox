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

package com.zimbra.soap.admin.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import com.zimbra.common.soap.AccountConstants;
import com.zimbra.soap.admin.type.AdminAttrsImpl;

@XmlAccessorType(XmlAccessType.NONE)
public class DataSourceInfo extends AdminAttrsImpl {

    /**
     * @zm-api-field-tag data-source-name
     * @zm-api-field-description Data source name
     */
    @XmlAttribute(name=AccountConstants.A_NAME, required=true)
    private final String name;

    /**
     * @zm-api-field-tag data-source-id
     * @zm-api-field-description Data source id
     */
    @XmlAttribute(name=AccountConstants.A_ID, required=true)
    private final String id;

    /**
     * @zm-api-field-tag data-source-type
     * @zm-api-field-description Data source type
     */
    @XmlAttribute(name=AccountConstants.A_TYPE, required=true)
    private final DataSourceType type;

    /**
     * no-argument constructor wanted by JAXB
     */
    @SuppressWarnings("unused")
    private DataSourceInfo() {
        this((String) null, (String) null, (DataSourceType) null);
    }

    public DataSourceInfo(String name, String id, DataSourceType type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public String getName() { return name; }
    public String getId() { return id; }
    public DataSourceType getType() { return type; }
}
