package com.num.training.service;


import com.num.training.common.ResponseResult;
import com.num.training.domain.model.LoginBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/4/8 3:36 下午
 */

public interface LoginService {
    ResponseResult<Map<String ,String >> login(LoginBody user);


    ResponseResult<Boolean> logout();

    ResponseResult<Map<String, String>> register(LoginBody user);

    ResponseResult<Map<String ,Object >> getInfo();

    ResponseResult<Boolean> registerBatch(MultipartFile file);
}
