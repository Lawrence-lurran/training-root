package com.num.training.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.num.training.auth.LoginUtil;
import com.num.training.common.ResponseResult;
import com.num.training.common.ResultInfo;
import com.num.training.domain.entity.SamplingInformationDO;
import com.num.training.handler.exception.MyRuntimeException;
import com.num.training.mapper.SamplingInformationMapper;
import com.num.training.service.ISamplingInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * (SamplingInformation)表服务实现类
 *
 * @author makejava
 * @since 2022-07-22 14:37:30
 */
@Service("samplingInformationService")
public class SamplingInformationServiceImpl extends ServiceImpl<SamplingInformationMapper, SamplingInformationDO> implements ISamplingInformationService {

    @Autowired
    private SamplingInformationMapper samplingInformationMapper;

    @Override
    public ResponseResult<Boolean> add(SamplingInformationDO samplingInformationDO) {
        samplingInformationDO.setCreatDate(new Date()).setUseFlag(1);
        boolean save =updateById(samplingInformationDO);
        if (!save){
            throw new MyRuntimeException(ResultInfo.SAVE_SAMPLING_INFORMATION_ERROR);
        }
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Long> getId() {
        Long userId = LoginUtil.getUserId();
        List<SamplingInformationDO> samplingInformationDOS = samplingInformationMapper.selectList(new LambdaQueryWrapper<SamplingInformationDO>().eq(SamplingInformationDO::getUserId, userId).eq(SamplingInformationDO::getUseFlag, 0));
        if (samplingInformationDOS.size()!=0){
            return ResponseResult.success(samplingInformationDOS.get(0).getId());
        }
        SamplingInformationDO samplingInformationDO = new SamplingInformationDO().setUserId(userId);
        samplingInformationMapper.insertNull(samplingInformationDO);
        return ResponseResult.success(samplingInformationDO.getId());
    }
}
