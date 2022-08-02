package com.num.training.auth;

import com.num.training.domain.entity.UserDO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/8/2 19:42
 */
public class LoginUtil {

    public static MyUserDetails getLoginUser(){
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return (MyUserDetails) authenticationToken.getPrincipal();

    }
    public static UserDO getUser(){
        return getLoginUser().getUserDO();
    }

    public static Long  getUserId(){
        return getUser().getId();
    }

    public static List<String> getUserRoles(){
        return getLoginUser().getRoles();
    }

    public static List<String> getUserPermissions(){
        return getLoginUser().getPermissions();
    }
}
