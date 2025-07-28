package com.emergency.drill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emergency.drill.entity.EmergencyPlan;
import com.emergency.drill.mapper.EmergencyPlanMapper;
import com.emergency.drill.service.EmergencyPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyPlanServiceImpl extends ServiceImpl<EmergencyPlanMapper, EmergencyPlan> implements EmergencyPlanService {

    @Override
    public List<EmergencyPlan> listByType(String planType) {
        return list(new LambdaQueryWrapper<EmergencyPlan>()
                .eq(EmergencyPlan::getPlanType, planType));
    }

    @Override
    public List<EmergencyPlan> listActivePlans() {
        return list(new LambdaQueryWrapper<EmergencyPlan>()
                .eq(EmergencyPlan::getStatus, 1)); // 假设状态1为激活状态
    }
}