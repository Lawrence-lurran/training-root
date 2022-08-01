package com.num.training.common;

import lombok.AllArgsConstructor;

/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/4/6 7:08 下午
 */

@AllArgsConstructor
public enum ResultInfo {
    /**
     * 页面没有找到
     */
    NOT_FOUND("404","页面没有找到"),
    SUCCESS("200","操作成功"),
    GlOBAL_ERROR("101","系统繁忙"),
    Authentication_ERROR("401008","认证失败"),
    LOGIN_ERROR("401001","登录失败"),
    PASSWORD_ERROR("401009","用户名或密码错误"),
    NAME_NOT_EXIT_ERROR("401010","用户名不存在"),
    REGISTER_ERROR("401002","注册失败"),
    REPEAT_NAME_ERROR("401006","用户名已存在"),
    CAPTCHA_EXP_ERROR("401003","验证码过期"),
    CAPTCHA_WRONG_ERROR("401004","验证码错误"),
    CAPTCHA_ERROR("401005","获取验证码失败"),
    TOKEN_ILLEGAL_ERROR("401007","token非法"),
    TOKEN_EXP_ERROR("401008","用户未登录，token已经过期"),
    AccessDenied_ERROR("403","权限不足"),
    FILE_UPLOAD_ERROR("405001","文件上传失败"),
    SAVE_SAMPLING_INFORMATION_ERROR("402001","保存SamplingInformation错误"),
    MY_ERROR("102","自定义异常");


    /**
     * 状态码
     */
    private String code;
    /**
     * 状态信息
     */
    private String massage;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
