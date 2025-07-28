package com.emergency.drill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emergency.drill.entity.DrillSafetyNotice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DrillSafetyNoticeMapper extends BaseMapper<DrillSafetyNotice> {
    // 可添加自定义SQL方法
}