package com.num.training.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.num.training.common.ResponseResult;
import com.num.training.domain.entity.SamplingInformationDO;


/**
 * (SamplingInformation)表服务接口
 *
 * @author makejava
 * @since 2022-07-22 14:37:30
 */
public interface ISamplingInformationService extends IService<SamplingInformationDO> {


    /**
     * 添加抽样信息
     * @param samplingInformationDO
     * @return
     */
    public ResponseResult<Boolean> add(SamplingInformationDO samplingInformationDO);

    /**
     * 获取抽样表id
     * @return
     */
    public ResponseResult<Long> getId();

}
