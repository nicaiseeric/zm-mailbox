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
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.zimbra.common.soap.MailConstants;
import com.zimbra.soap.mail.type.ExpandedRecurrenceInstance;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="ExpandRecurResponse")
public class ExpandRecurResponse {

    /**
     * @zm-api-field-description Expanded recurrence instances
     */
    @XmlElement(name=MailConstants.E_INSTANCE /* inst */, required=false)
    private List<ExpandedRecurrenceInstance> instances = Lists.newArrayList();

    public ExpandRecurResponse() {
    }

    public void setInstances(Iterable <ExpandedRecurrenceInstance> instances) {
        this.instances.clear();
        if (instances != null) {
            Iterables.addAll(this.instances,instances);
        }
    }

    public ExpandRecurResponse addInstance(ExpandedRecurrenceInstance instance) {
        this.instances.add(instance);
        return this;
    }

    public List<ExpandedRecurrenceInstance> getInstances() {
        return Collections.unmodifiableList(instances);
    }

    public Objects.ToStringHelper addToStringInfo(Objects.ToStringHelper helper) {
        return helper
            .add("instances", instances);
    }

    @Override
    public String toString() {
        return addToStringInfo(Objects.toStringHelper(this)).toString();
    }
}
