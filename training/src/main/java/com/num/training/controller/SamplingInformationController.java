package com.num.training.controller;


import com.num.training.annotation.Log;
import com.num.training.auth.MyUserDetails;
import com.num.training.common.ResponseResult;
import com.num.training.common.ResultInfo;
import com.num.training.domain.entity.SamplingInformationDO;
import com.num.training.enums.BusinessType;
import com.num.training.enums.OperatorType;
import com.num.training.handler.exception.MyRuntimeException;
import com.num.training.service.ISamplingInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * (SamplingInformation)表控制层
 *
 * @author makejava
 * @since 2022-07-22 14:37:16
 */
@RestController
@RequestMapping("/samplingInformation")
public class SamplingInformationController {

    @Autowired
    private ISamplingInformationService samplingInformationService;

    @PostMapping("/add")
    @Log(title = "提交抽样表单",operatorType = OperatorType.MOBILE,businessType = BusinessType.INSERT)
    public ResponseResult<Boolean> add(@RequestBody SamplingInformationDO samplingInformationDO){
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails loginUser = (MyUserDetails) authenticationToken.getPrincipal();
        samplingInformationDO.setUserId(loginUser.getUserDO().getId());
        boolean save = samplingInformationService.save(samplingInformationDO);
        if (!save){
            throw new MyRuntimeException(ResultInfo.SAVE_SAMPLING_INFORMATION_ERROR);
        }
        return ResponseResult.success();
    }

}

