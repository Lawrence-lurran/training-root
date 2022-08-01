package com.num.training.handler.exception;


import com.num.training.common.ResponseResult;
import com.num.training.common.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/4/6 8:43 下午
 */
@Slf4j
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(MyRuntimeException.class)
    @ResponseBody
    public ResponseResult<Boolean> myRuntimeException(MyRuntimeException e){
        log.error("自定义异常抛出:{}",e.getMessage());
        return ResponseResult.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult<Boolean> exception(Exception e){
        log.error("全局异常处理:{}",e.getMessage());
        return ResponseResult.error(ResultInfo.GlOBAL_ERROR);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseBody
    public ResponseResult<Boolean> internalAuthenticationServiceException(InternalAuthenticationServiceException e){
        log.error("认证异常:{}",e.getMessage());
        return ResponseResult.error(e.getMessage());
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public ResponseResult<Boolean> badCredentialsException(BadCredentialsException e){
        log.error("登录异常:{}",e.getMessage());
        return ResponseResult.error(ResultInfo.PASSWORD_ERROR);
    }


}
