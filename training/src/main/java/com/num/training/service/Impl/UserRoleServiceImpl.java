package com.num.training.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.num.training.domain.entity.UserRoleDO;
import com.num.training.mapper.UserRoleMapper;
import com.num.training.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * (UserRole)表服务实现类
 *
 * @author makejava
 * @since 2022-07-22 16:18:53
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleDO> implements IUserRoleService {

}
