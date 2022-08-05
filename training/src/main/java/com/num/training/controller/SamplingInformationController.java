package com.num.training.controller;


import com.num.training.annotation.Log;
import com.num.training.common.ResponseResult;
import com.num.training.domain.entity.SamplingInformationDO;
import com.num.training.enums.BusinessType;
import com.num.training.enums.OperatorType;
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

    @PostMapping("/add")
    @Log(title = "提交抽样表单",operatorType = OperatorType.MOBILE,businessType = BusinessType.INSERT)
    public ResponseResult<Boolean> add(@RequestBody SamplingInformationDO samplingInformationDO){
        return samplingInformationService.add(samplingInformationDO);
    }

    @GetMapping("/getId")
    @Log(title = "获取抽样表单id",operatorType = OperatorType.MOBILE,businessType = BusinessType.QUERY)
    public ResponseResult<Long> getId(){
        return samplingInformationService.getId();
    }



}

