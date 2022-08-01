package com.num.training.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.num.training.domain.entity.RoleDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 角色表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-22 17:02:15
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {

}

