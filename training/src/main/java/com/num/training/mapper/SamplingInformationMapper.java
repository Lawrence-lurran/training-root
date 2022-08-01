package com.num.training.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.num.training.domain.entity.SamplingInformationDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * (SamplingInformation)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-22 14:37:17
 */
@Mapper
public interface SamplingInformationMapper extends BaseMapper<SamplingInformationDO> {

}

