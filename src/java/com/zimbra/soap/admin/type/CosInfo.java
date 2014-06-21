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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.zimbra.common.soap.AdminConstants;
import com.zimbra.soap.type.ZmBoolean;

@XmlAccessorType(XmlAccessType.NONE)
public class CosInfo implements AdminObjectInterface {

    /**
     * @zm-api-field-tag id
     * @zm-api-field-description ID
     */
    @XmlAttribute(name=AdminConstants.A_ID /* id */, required=true)
    private final String id;

    /**
     * @zm-api-field-tag name
     * @zm-api-field-description Name
     */
    @XmlAttribute(name=AdminConstants.A_NAME /* name */, required=true)
    private final String name;

    /**
     * @zm-api-field-tag is-default-cos
     * @zm-api-field-description Flag whether is the default Class Of Service (COS)
     */
    @XmlAttribute(name=AdminConstants.A_IS_DEFAULT_COS /* isDefaultCos */, required=false)
    private final ZmBoolean isDefaultCos;

    /**
     * @zm-api-field-description Attributes
     */
    @XmlElement(name=AdminConstants.E_A /* a */, required=false)
    private final List <CosInfoAttr> attrs;

    /** no-argument constructor wanted by JAXB */
    protected CosInfo() {
        this((String) null,(String) null, false, (Collection <CosInfoAttr>) null);
    }

    protected CosInfo(String id, String name, boolean isDefaultCos, Collection <CosInfoAttr> attrs) {
        this.name = name;
        this.id = id;
        this.attrs = new ArrayList<CosInfoAttr>();
        if (attrs != null) {
            this.attrs.addAll(attrs);
        }
        this.isDefaultCos = ZmBoolean.fromBool(isDefaultCos);
    }

    public static CosInfo createForIdAndName(String id, String name) {
        return new CosInfo(id, name, false, (Collection <CosInfoAttr>) null);
    }

    public static CosInfo createForIdNameAndAttrs(String id, String name, Collection <CosInfoAttr> attrs) {
        return new CosInfo(id, name, false, attrs);
    }

    public static CosInfo createDefaultCosForIdNameAndAttrs(String id, String name, Collection <CosInfoAttr> attrs) {
        return new CosInfo(id, name, true, attrs);
    }

    public static CosInfo createNonDefaultCosForIdNameAndAttrs(String id, String name, Collection <CosInfoAttr> attrs) {
        return new CosInfo(id, name, false, attrs);
    }

    @Override
    public String getId() { return id; }
    @Override
    public String getName() { return name; }
    public Boolean getIsDefaultCos() { return ZmBoolean.toBool(isDefaultCos); }
    public List<CosInfoAttr> getAttrs() { return Collections.unmodifiableList(attrs); }

    @Override
    public List<Attr> getAttrList() {
        return toAttrsList(attrs);
    }
    public static List <Attr> toAttrsList(Iterable <CosInfoAttr> params) {
        if (params == null)
            return null;
        List <Attr> newList = Lists.newArrayList();
        Iterables.addAll(newList, params);
        return Collections.unmodifiableList(newList);
    }
}
