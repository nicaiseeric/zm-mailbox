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

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.zimbra.common.soap.AdminConstants;
import com.zimbra.soap.admin.type.WaitSetInfo;

// note: soap-waitset.txt differs significantly but the SOAP handler only adds E_WAITSET elements to the response
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name=AdminConstants.E_QUERY_WAIT_SET_RESPONSE)
public class QueryWaitSetResponse {

    /**
     * @zm-api-field-description Information about WaitSets
     */
    @XmlElement(name=AdminConstants.E_WAITSET /* waitSet */, required=false)
    private List<WaitSetInfo> waitsets = Lists.newArrayList();

    public QueryWaitSetResponse() {
    }

    public void setWaitsets(Iterable <WaitSetInfo> waitsets) {
        this.waitsets.clear();
        if (waitsets != null) {
            Iterables.addAll(this.waitsets,waitsets);
        }
    }

    public QueryWaitSetResponse addWaitset(WaitSetInfo waitset) {
        this.waitsets.add(waitset);
        return this;
    }

    public List<WaitSetInfo> getWaitsets() {
        return Collections.unmodifiableList(waitsets);
    }
}
