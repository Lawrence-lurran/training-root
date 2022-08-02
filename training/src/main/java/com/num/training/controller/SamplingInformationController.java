package com.num.training.controller;


import com.num.training.annotation.Log;
import com.num.training.auth.LoginUtil;
import com.num.training.common.ResponseResult;
import com.num.training.common.ResultInfo;
import com.num.training.domain.entity.SamplingInformationDO;
import com.num.training.enums.BusinessType;
import com.num.training.enums.OperatorType;
import com.num.training.handler.exception.MyRuntimeException;
import com.num.training.mapper.SamplingInformationMapper;
import com.num.training.service.ISamplingInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @Autowired
    private SamplingInformationMapper samplingInformationMapper;

    @PostMapping("/add")
    @Log(title = "提交抽样表单",operatorType = OperatorType.MOBILE,businessType = BusinessType.INSERT)
    public ResponseResult<Boolean> add(@RequestBody SamplingInformationDO samplingInformationDO){
        boolean save = samplingInformationService.updateById(samplingInformationDO);
        if (!save){
            throw new MyRuntimeException(ResultInfo.SAVE_SAMPLING_INFORMATION_ERROR);
        }
        return ResponseResult.success();
    }

    @GetMapping("/getId")
    @Log(title = "获取抽样表单id",operatorType = OperatorType.MOBILE,businessType = BusinessType.QUERY)
    public ResponseResult<Long> getId(){
        Long userId = LoginUtil.getUserId();
        SamplingInformationDO samplingInformationDO = new SamplingInformationDO().setUserId(userId);
        samplingInformationMapper.insertNull(samplingInformationDO);
        return ResponseResult.success(samplingInformationDO.getId());
    }



}

