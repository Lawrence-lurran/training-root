package com.num.training.handler.exception;


import com.num.training.common.ResultInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/4/6 8:49 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyRuntimeException extends RuntimeException{

    private String code;

    private String message;

    public MyRuntimeException(ResultInfo resultInfo){
        this.code=resultInfo.getCode();
        this.message=resultInfo.getMassage();
    }
    public MyRuntimeException(String  message){
        this.message=message;
    }
}
