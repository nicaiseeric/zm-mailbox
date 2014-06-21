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

import com.google.common.base.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.zimbra.common.soap.CertMgrConstants;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name=CertMgrConstants.E_UPLOAD_DOMCERT_RESPONSE)
public class UploadDomCertResponse {

    /**
     * @zm-api-field-tag certificate-content
     * @zm-api-field-description Certificate content
     */
    @XmlAttribute(name=CertMgrConstants.A_cert_content /* cert_content */, required=false)
    private String certificateContent;

    /**
     * @zm-api-field-tag key-content
     * @zm-api-field-description Key content
     */
    @XmlAttribute(name=CertMgrConstants.A_key_content /* key_content */, required=false)
    private String keyContent;

    private UploadDomCertResponse() {
    }

    private UploadDomCertResponse(String certificateContent, String keyContent) {
        setCertificateContent(certificateContent);
        setKeyContent(keyContent);
    }

    public static UploadDomCertResponse createForCertificateAndKey(String certificateContent, String keyContent) {
        return new UploadDomCertResponse(certificateContent, keyContent);
    }

    public void setCertificateContent(String certificateContent) { this.certificateContent = certificateContent; }
    public void setKeyContent(String keyContent) { this.keyContent = keyContent; }
    public String getCertificateContent() { return certificateContent; }
    public String getKeyContent() { return keyContent; }

    public Objects.ToStringHelper addToStringInfo(
                Objects.ToStringHelper helper) {
        return helper
            .add("certificateContent", certificateContent)
            .add("keyContent", keyContent);
    }

    @Override
    public String toString() {
        return addToStringInfo(Objects.toStringHelper(this))
                .toString();
    }
}
