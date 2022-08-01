package com.num.training.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.num.training.domain.entity.RoleDO;
import com.num.training.mapper.RoleMapper;
import com.num.training.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表(Role)表服务实现类
 *
 * @author makejava
 * @since 2022-07-22 17:02:15
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements IRoleService {

}
