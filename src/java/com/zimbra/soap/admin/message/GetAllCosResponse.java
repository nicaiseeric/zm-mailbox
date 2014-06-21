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

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.zimbra.common.soap.AdminConstants;
import com.zimbra.soap.admin.type.CosInfo;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name=AdminConstants.E_GET_ALL_COS_RESPONSE)
@XmlType(propOrder = {})
public class GetAllCosResponse {

    /**
     * @zm-api-field-description Information on Classes of Service (COS)
     */
    @XmlElement(name=AdminConstants.E_COS)
    private List<CosInfo> cosList = Lists.newArrayList();

    public GetAllCosResponse() {
    }

    public List<CosInfo> getCosList() {
        return Collections.unmodifiableList(cosList);
    }

    public GetAllCosResponse setCosList(Iterable<CosInfo> cosList) {
        this.cosList.clear();
        if (cosList != null) {
            Iterables.addAll(this.cosList,cosList);
        }
        return this;
    }

    public GetAllCosResponse addCos(CosInfo cos) {
        cosList.add(cos);
        return this;
    }
}
