package com.num.training.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户登录对象
 *
 * @author ruoyi
 */
public class LoginBody
{
    /**
     * 用户名
     */
    @NotNull(message = "用户名为空")
    @NotBlank(message = "用户名为空")
    private String username;

    /**
     * 用户密码
     */
    @NotNull(message = "密码为空")
    @NotBlank(message = "密码为空")
    private String password;

    /**
     * 验证码
     */
    @NotNull(message = "验证码为空")
    @NotBlank(message = "验证码为空")
    private String code;

    /**
     * 唯一标识
     */
    @NotNull(message = "uuid验证码唯一标识为空")
    @NotBlank(message = "uuid验证码唯一标识为空")
    private String uuid;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }
}
