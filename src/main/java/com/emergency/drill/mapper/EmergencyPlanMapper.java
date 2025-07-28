package com.emergency.drill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emergency.drill.entity.EmergencyPlan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmergencyPlanMapper extends BaseMapper<EmergencyPlan> {
    // 可添加自定义SQL方法
}