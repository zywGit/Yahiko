package com.inmobi.adinterface.config.shiro;


import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.inmobi.adinterface.base.util.EncryptPassword.encryptPassword;

/**
 * @author SPPan
 */
@Component
/*@Import(ShiroManager.class)*/
public class MyRealm extends AuthorizingRealm {


    public MyRealm() {
        super(new AllowAllCredentialsMatcher());

        //FIXME: 暂时禁用Cache
        setCachingEnabled(false);
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
     return null;
    }

}
