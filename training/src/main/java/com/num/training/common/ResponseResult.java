package com.num.training.common;

/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/4/8 1:37 下午
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.num.training.constant.Constants;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private String code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    public ResponseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResponseResult(ResultInfo resultInfo) {
        this.code = resultInfo.getCode();
        this.msg = resultInfo.getMassage();
    }

    public ResponseResult(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> ResponseResult<T> success(String msg, T data) {
        return new ResponseResult<T>(Constants.SUCCESS, msg, data);
    }
    public static <T> ResponseResult<Boolean> success(String msg) {
        return ResponseResult.success(msg, true);
    }
    public static <T> ResponseResult<Boolean> success() {
        return ResponseResult.success("操作成功");
    }

    public static <T> ResponseResult<T> success(T data) {
        return ResponseResult.success("操作成功", data);
    }
    public static <T> ResponseResult<T> error(String code,String msg, T data) {
        return new ResponseResult<T>(code, msg, data);
    }
    public static <T> ResponseResult<T> error(String msg, T data) {
        return ResponseResult.error(Constants.FAIL, msg, data);
    }
    public static <T> ResponseResult<Boolean> error(String msg) {
        return ResponseResult.error(msg, false);
    }
    public static <T> ResponseResult<Boolean> error(String code,String msg) {
        return ResponseResult.error(code, msg,false);
    }
    public static <T> ResponseResult<Boolean> error() {
        return ResponseResult.error("操作成功");
    }

    public static <T> ResponseResult<T> error(T data) {
        return ResponseResult.error("操作失败", data);
    }
    public static <T> ResponseResult<Boolean> error(ResultInfo resultInfo) {
        return ResponseResult.error(resultInfo.getCode(),resultInfo.getMassage());
    }
}
