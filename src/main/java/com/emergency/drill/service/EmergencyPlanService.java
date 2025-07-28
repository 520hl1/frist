package com.emergency.drill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.emergency.drill.entity.EmergencyPlan;

import java.util.List;

public interface EmergencyPlanService extends IService<EmergencyPlan> {
    // 自定义业务方法
    List<EmergencyPlan> listByType(String planType);
    List<EmergencyPlan> listActivePlans();
}