package com.num.training.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.num.training.auth.MyUserDetails;
import com.num.training.common.ResultInfo;
import com.num.training.domain.entity.RoleDO;
import com.num.training.domain.entity.UserDO;
import com.num.training.domain.entity.UserRoleDO;
import com.num.training.handler.exception.MyRuntimeException;
import com.num.training.mapper.MenuMapper;
import com.num.training.mapper.RoleMapper;
import com.num.training.mapper.UserMapper;
import com.num.training.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/4/7 12:22 下午
 */
@Slf4j
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetails myUserDetails = new MyUserDetails();
        UserDO userDO = userMapper.selectOne(new LambdaQueryWrapper<UserDO>().eq(UserDO::getUsername,username));
        if (Objects.isNull(userDO)) {
            log.error("用户名不存在:{}",username);
            throw new MyRuntimeException(ResultInfo.NAME_NOT_EXIT_ERROR);
        }
        myUserDetails.setUserDO(userDO);
        List<String> list = menuMapper.selectPermsByUserId(userDO.getId());
        List<UserRoleDO> userRoleDOS = userRoleMapper.selectList(new LambdaQueryWrapper<UserRoleDO>().eq(UserRoleDO::getUserId, userDO.getId()));
        List<String> roles = userRoleDOS.stream().map(userRoleDO -> {
            RoleDO roleDO = roleMapper.selectById(userRoleDO.getRoleId());
            return roleDO.getRoleKey();
        }).collect(Collectors.toList());
        myUserDetails.setPermissions(list);
        myUserDetails.setRoles(roles);
        return myUserDetails;
    }

}
