/*
package com.itt.jmtemplate.jwt;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.ApplicationContext;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itt.ApplicationContextProvider;


/**
 * MyRealm class is mainly used to authenticate and authorize the user with permission matrix.
 */
/*public class MyRealm extends AuthorizingRealm {

    /*
     * (non-Javadoc)
     *
     * @see org.apache.shiro.realm.AuthenticatingRealm#supports(org.apache.shiro.
     * authc.AuthenticationToken)
     */
   /* @Override
    public boolean supports(final AuthenticationToken token) {

        return token instanceof JWTToken;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache
     * .shiro.subject.PrincipalCollection)
     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
//        ApplicationContext ctx = ApplicationContextProvider.getContext();
//        UserService userService = ctx.getBean(UserService.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        Set<String> permissions = new HashSet<String>();
//
//        String email = JWTUtil.getemail(principals.toString());
//
//        User user = userService.getUserByEmail(email);
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        String role = user.getUserRole();
//
//
//        try {
//            List<PermissionMatrix> permissionMatrix =
//                    objectMapper.readValue(userService.getResourceLoader().getResource
//                    ("classpath:permissionsMatrix.json").
//                            getInputStream(), new TypeReference<List<PermissionMatrix>>() {
//                    });
//            for (PermissionMatrix permission : permissionMatrix) {
//                if (role.equals(permission.getRole())) {
//                    permissions.addAll(permission.getPermissions());
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        simpleAuthorizationInfo.addRole(role);
//        simpleAuthorizationInfo.addStringPermissions(permissions);
//
//        return simpleAuthorizationInfo;
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.
//     * apache.shiro.authc.AuthenticationToken)
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken auth)
//    throws AuthenticationException {
//
//        ApplicationContext ctx = ApplicationContextProvider.getContext();
//        UserService userService = ctx.getBean(UserService.class);
//        String token = (String) auth.getCredentials();
//
//        String email = JWTUtil.getemail(token);
//        if (email == null) {
//            throw new AuthenticationException("token invalid");
//        }
//
//        User user = userService.getUserByEmail(email);
//        if (user == null) {
//            throw new AuthenticationException("User didn't existed!");
//        }
//
//        if (!JWTUtil.verify(token, email, user.getPassword())) {
//            throw new AuthenticationException("Invalid Token");
//        }
//
//
//        AuthenticationInfo authenticationInfo = null;
//
//        authenticationInfo = new SimpleAuthenticationInfo(token, token, "my_realm");
//
//        return authenticationInfo;
//    }
//}
//*/