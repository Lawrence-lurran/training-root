package com.num.training.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/4/10 4:40 下午
 */
@Component
public class SExpressionRoot {

    public boolean hasAuth(String authority){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        MyUserDetails loginUser = (MyUserDetails) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        return permissions.contains(authority);
    }
}
