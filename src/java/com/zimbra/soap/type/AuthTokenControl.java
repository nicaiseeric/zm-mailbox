/*
 * ***** BEGIN LICENSE BLOCK *****
 * 
 * Zimbra Collaboration Suite Server
 * Copyright (C) 2014 Zimbra, Inc.
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
 * 
 * ***** END LICENSE BLOCK *****
 */
package com.zimbra.soap.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import com.zimbra.common.soap.HeaderConstants;

@XmlAccessorType(XmlAccessType.NONE)
public final class AuthTokenControl {

    /**
     * @zm-api-field-tag voidOnExpired
     * @zm-api-field-description if set to true, expired authToken in the header will be ignored
     */
    @XmlAttribute(name=HeaderConstants.A_VOID_ON_EXPIRED/* voidOnExpired */, required=false)
    private Boolean voidOnExpired;

    public AuthTokenControl() {
        voidOnExpired = false;
    }

    public AuthTokenControl(boolean voidExpired) {
        voidOnExpired = voidExpired;
    }

    public void setVoidOnExpired(boolean voidExpired) {
        voidOnExpired = voidExpired;
    }

    public boolean isVoidOnExpired() {
        return voidOnExpired;
    }
}
