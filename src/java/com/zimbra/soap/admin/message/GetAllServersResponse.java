/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra Collaboration Suite Server
 * Copyright (C) 2010, 2011, 2012, 2013 Zimbra, Inc.
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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.zimbra.common.soap.AdminConstants;

import com.zimbra.soap.admin.type.ServerInfo;

@XmlRootElement(name=AdminConstants.E_GET_ALL_SERVERS_RESPONSE)
public class GetAllServersResponse {

    /**
     * @zm-api-field-description Information about servers
     */
    @XmlElement(name=AdminConstants.E_SERVER)
    private List <ServerInfo> serverList = new ArrayList<ServerInfo>();

    public GetAllServersResponse() {
    }

    public void addServer(ServerInfo server ) {
        this.getServerList().add(server);
    }

    public List <ServerInfo> getServerList() { return serverList; }
}
