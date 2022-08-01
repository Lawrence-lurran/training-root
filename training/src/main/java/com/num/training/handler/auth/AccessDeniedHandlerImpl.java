package com.num.training.handler.auth;


import com.alibaba.fastjson.JSON;
import com.num.training.common.ResponseResult;
import com.num.training.common.ResultInfo;
import com.num.training.util.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/4/10 3:25 下午
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //ResponseResult result=new ResponseResult(HttpStatus.FORBIDDEN+"", "权限不足",null);
        ResponseResult<Boolean> error=ResponseResult.error(ResultInfo.AccessDenied_ERROR);
        String s = JSON.toJSONString(error);
        WebUtils.renderString(response,s);
    }
}
