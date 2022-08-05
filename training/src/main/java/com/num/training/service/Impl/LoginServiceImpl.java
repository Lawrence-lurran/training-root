package com.num.training.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.num.training.auth.LoginUtil;
import com.num.training.auth.MyUserDetails;
import com.num.training.common.ResponseResult;
import com.num.training.common.ResultInfo;
import com.num.training.constant.Constants;
import com.num.training.domain.entity.UserDO;
import com.num.training.domain.entity.UserRoleDO;
import com.num.training.domain.model.LoginBody;
import com.num.training.handler.exception.MyRuntimeException;
import com.num.training.mapper.MenuMapper;
import com.num.training.service.IUserRoleService;
import com.num.training.service.LoginService;
import com.num.training.service.UserService;
import com.num.training.util.JwtUtil;
import com.num.training.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/4/8 3:36 下午
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public ResponseResult<Map<String ,String >> login(LoginBody user) {
        validateCaptcha(user.getCode(), user.getUuid());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)){
            throw new MyRuntimeException(ResultInfo.LOGIN_ERROR);
        }
        MyUserDetails loginUser = (MyUserDetails) authenticate.getPrincipal();
        String userId = loginUser.getUserDO().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        redisCache.setCacheObject(Constants.LOGIN_USER_KEY+userId,loginUser,Constants.TOKEN_EXPIRATION, TimeUnit.MINUTES);
        Map<String,String> map=new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.success("登录成功",map);
    }

    @Override
    public ResponseResult<Boolean> logout() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails loginUser = (MyUserDetails) authenticationToken.getPrincipal();
        String userId = loginUser.getUserDO().getId().toString();
        String redisKey=Constants.LOGIN_USER_KEY+userId;
        redisCache.deleteObject(redisKey);
        return ResponseResult.success("注销成功");
    }

    @Override
    public ResponseResult<Map<String, String>> register(LoginBody user) {
        validateCaptcha(user.getCode(), user.getUuid());
        UserDO hasUserDO = userService.getOne(new LambdaQueryWrapper<UserDO>().eq(UserDO::getUsername, user.getUsername()));
        if (hasUserDO !=null){
            throw new MyRuntimeException(ResultInfo.REPEAT_NAME_ERROR);
        }
        UserDO userDO1 = new UserDO();
        userDO1.setUsername(user.getUsername());
        userDO1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        boolean save = userService.save(userDO1);
        if (!save){
            throw new MyRuntimeException(ResultInfo.REGISTER_ERROR);
        }
        MyUserDetails myUserDetails = new MyUserDetails();
        UserDO loginUserDO = userService.getOne(new LambdaQueryWrapper<UserDO>().eq(UserDO::getUsername, user.getUsername()));
        String userId = loginUserDO.getId().toString();
        UserRoleDO userRoleDO = new UserRoleDO();
        userRoleDO.setUserId(loginUserDO.getId());
        userRoleDO.setRoleId(2L);
        userRoleService.save(userRoleDO);
        String jwt = JwtUtil.createJWT(userId);
        myUserDetails.setUserDO(loginUserDO);
        List<String> list = menuMapper.selectPermsByUserId(loginUserDO.getId());
        myUserDetails.setPermissions(list);
        redisCache.setCacheObject(Constants.LOGIN_USER_KEY+userId,myUserDetails,Constants.TOKEN_EXPIRATION, TimeUnit.MINUTES);
        Map<String,String> map=new HashMap<>();
        map.put(Constants.TOKEN,jwt);
        return ResponseResult.success("注册成功",map);
    }

    @Override
    public ResponseResult<Map<String, Object>> getInfo() {
        MyUserDetails loginUser = LoginUtil.getLoginUser();
        Map<String, Object> data = new HashMap<>();
        data.put("user",loginUser.getUserDO());
        data.put("roles",loginUser.getRoles());
        data.put("permissions",loginUser.getPermissions());
        return ResponseResult.success(data);
    }

    /**
     * 校验验证码
     *
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String code, String uuid)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY +(uuid==null?"":uuid);
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new MyRuntimeException(ResultInfo.CAPTCHA_EXP_ERROR);
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new MyRuntimeException(ResultInfo.CAPTCHA_WRONG_ERROR);
        }
    }
}
