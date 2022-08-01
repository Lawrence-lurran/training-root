package com.num.training.filter;


import com.num.training.auth.MyUserDetails;
import com.num.training.common.ResultInfo;
import com.num.training.constant.Constants;
import com.num.training.handler.exception.MyRuntimeException;
import com.num.training.util.JwtUtil;
import com.num.training.util.RedisCache;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/4/8 4:52 下午
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException,MyRuntimeException {
        String token = request.getHeader(Constants.TOKEN);
        if (!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            log.error("用户token异常:{}",token);
            throw new MyRuntimeException(ResultInfo.TOKEN_ILLEGAL_ERROR);
        }
        String redisKey= Constants.LOGIN_USER_KEY+userId;
        MyUserDetails loginUser =  redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)){
            log.error("用户token过期:{}",token);
            throw new MyRuntimeException(ResultInfo.TOKEN_EXP_ERROR);
        }
        redisCache.expire(redisKey,Constants.TOKEN_EXPIRATION, TimeUnit.MINUTES);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request,response);
    }
}
