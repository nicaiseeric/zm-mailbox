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
import com.zimbra.soap.admin.type.InheritedFlaggedValue;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name=AdminConstants.E_GET_ADMIN_CONSOLE_UI_COMP_RESPONSE)
public class GetAdminConsoleUICompResponse {

    /**
     * @zm-api-field-description  zimbraAdminConsoleUIComponents values
     */
    @XmlElement(name=AdminConstants.E_A, required=false)
    private List<InheritedFlaggedValue> values = Lists.newArrayList();

    public GetAdminConsoleUICompResponse() {
    }

    public void setValues(Iterable <InheritedFlaggedValue> values) {
        this.values.clear();
        if (values != null) {
            Iterables.addAll(this.values,values);
        }
    }

    public GetAdminConsoleUICompResponse addValue(InheritedFlaggedValue value) {
        this.values.add(value);
        return this;
    }


    public List<InheritedFlaggedValue> getValues() {
        return Collections.unmodifiableList(values);
    }
}
