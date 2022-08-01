package com.num.training.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.num.training.domain.entity.SamplingInformationDO;
import com.num.training.mapper.SamplingInformationMapper;
import com.num.training.service.ISamplingInformationService;
import org.springframework.stereotype.Service;

/**
 * (SamplingInformation)表服务实现类
 *
 * @author makejava
 * @since 2022-07-22 14:37:30
 */
@Service("samplingInformationService")
public class SamplingInformationServiceImpl extends ServiceImpl<SamplingInformationMapper, SamplingInformationDO> implements ISamplingInformationService {

}
