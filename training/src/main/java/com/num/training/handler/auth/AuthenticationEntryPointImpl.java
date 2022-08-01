package com.num.training.handler.auth;


import com.alibaba.fastjson.JSON;
import com.num.training.common.ResponseResult;
import com.num.training.common.ResultInfo;
import com.num.training.util.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/4/10 3:21 下午=
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //ResponseResult result=new ResponseResult(HttpStatus.UNAUTHORIZED+"","用户认证失败请重新登陆",null);

        ResponseResult<Boolean> error=ResponseResult.error(ResultInfo.Authentication_ERROR);
        String s = JSON.toJSONString(error);
        WebUtils.renderString(response,s);
    }
}
