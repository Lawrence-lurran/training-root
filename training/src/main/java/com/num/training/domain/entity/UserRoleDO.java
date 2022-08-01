package com.num.training.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (UserRole)表实体类
 *
 * @author makejava
 * @since 2022-07-22 16:18:52
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role")
public class UserRoleDO  {
    /**
    *用户id
    */

    private Long userId;
    /**
    *角色id
    */

    private Long roleId;




}
