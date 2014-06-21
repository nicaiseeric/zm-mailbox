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
import javax.xml.bind.annotation.XmlRootElement;

import com.zimbra.common.soap.MailConstants;
import com.zimbra.soap.mail.type.ActionSelector;
import com.zimbra.soap.json.jackson.annotate.ZimbraUniqueElement;

/**
 * @zm-api-command-auth-required true
 * @zm-api-command-admin-auth-required false
 * @zm-api-command-description Perform an action on a message
 * <br />
 * For op="update", caller can specify any or all of: l="{folder}", name="{name}", color="{color}", tn="{tag-names}",
 * f="{flags}".
 * <br />
 * <br />
 * For op="!spam", can optionally specify a destination folder
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name=MailConstants.E_MSG_ACTION_REQUEST)
public class MsgActionRequest {

    /**
     * @zm-api-field-description Specify the action to perform
     */
    @ZimbraUniqueElement
    @XmlElement(name=MailConstants.E_ACTION /* action */, required=true)
    private final ActionSelector action;

    /**
     * no-argument constructor wanted by JAXB
     */
    @SuppressWarnings("unused")
    private MsgActionRequest() {
        this((ActionSelector) null);
    }

    public MsgActionRequest(ActionSelector action) {
        this.action = action;
    }

    public ActionSelector getAction() { return action; }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("action", action)
            .toString();
    }
}
