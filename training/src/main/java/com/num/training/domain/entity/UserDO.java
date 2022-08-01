package com.num.training.domain.entity;




import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;

/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2022-07-26 19:59:27
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
@Accessors(chain = true)
public class UserDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("real_name")
    private String realName;


    private String password;

    private String status;
    @TableField("email")
    private String email;

    @TableField("phone_number")
    private String phoneNumber;

    @TableField("grade")
    private String grade;

    @TableField("series")
    private String series;

    private String userType;

    @TableField("major")
    private String major;

    @TableField("school")
    private String school;

    @TableField("id_card")
    private String idCard;

    @TableField("bank_num")
    private String bankNum;


    private Integer delFlag;



}


