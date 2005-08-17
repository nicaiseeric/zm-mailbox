/*
 * Created on Sep 23, 2004
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.zimbra.cs.account.ldap;

import java.util.Set;

import javax.naming.directory.Attributes;

import com.zimbra.cs.account.Config;
import com.zimbra.cs.account.Provisioning;

/**
 * @author schemers
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LdapConfig extends LdapEntry implements Config {

    private Set mInheritableAccountAttrs;
    private Set mInheritableDomainAttrs;
    private Set mInheritableServerAttrs;
    
    LdapConfig(String dn, Attributes attrs) {
        super(dn, attrs);
        mInheritableAccountAttrs = getMultiAttrSet(Provisioning.A_zimbraCOSInheritedAttr);
        mInheritableDomainAttrs = getMultiAttrSet(Provisioning.A_zimbraDomainInheritedAttr);
        mInheritableServerAttrs = getMultiAttrSet(Provisioning.A_zimbraServerInheritedAttr);
    }

    /* (non-Javadoc)
     * @see com.zimbra.cs.account.Config#isInheritedAccountAttr(java.lang.String)
     */
    public boolean isInheritedAccountAttr(String name) {
        return mInheritableAccountAttrs.contains(name);
    }

    /* (non-Javadoc)
     * @see com.zimbra.cs.account.Config#isInheritedDomainAttr(java.lang.String)
     */
    public boolean isInheritedDomainAttr(String name) {
        return mInheritableDomainAttrs.contains(name);        
    }

    /* (non-Javadoc)
     * @see com.zimbra.cs.account.Config#isInheritedServerAttr(java.lang.String)
     */
    public boolean isInheritedServerAttr(String name) {
        return mInheritableServerAttrs.contains(name);
    }
}
