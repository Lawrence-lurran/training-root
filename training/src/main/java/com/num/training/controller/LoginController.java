package com.num.training.controller;


import com.num.training.annotation.Log;
import com.num.training.common.ResponseResult;
import com.num.training.domain.model.LoginBody;
import com.num.training.enums.BusinessType;
import com.num.training.enums.OperatorType;
import com.num.training.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/4/8 3:33 下午
 */
@Api(value = "登录 注册 退出", tags="{登录 注册 退出 接口}")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;


    @ApiOperation(value = "登录")
    @Log(title = "登录",operatorType = OperatorType.MOBILE,isAuthentication = false)
    @PostMapping("/user/login")
    public ResponseResult<Map<String ,String >> login(@RequestBody LoginBody user){
        return loginService.login(user);
    }
    @ApiOperation(value = "登出")
    @GetMapping ("/user/logout")
    @Log(title = "登出",operatorType = OperatorType.MOBILE)
    public ResponseResult<Boolean> logout(){
        return loginService.logout();
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "注册")
    @Log(title = "注册",businessType = BusinessType.INSERT,operatorType = OperatorType.MOBILE,isAuthentication = false)
    public ResponseResult<Map<String ,String >> register(@RequestBody LoginBody user){
        return loginService.register(user);
    }
    @GetMapping("/user/getInfo")
    @Log(title = "获取用户信息",businessType = BusinessType.QUERY,operatorType = OperatorType.MOBILE)
    @ApiOperation(value = "获取用户信息")
    public ResponseResult<Map<String ,Object >> getInfo() {
        return loginService.getInfo();
    }

}
