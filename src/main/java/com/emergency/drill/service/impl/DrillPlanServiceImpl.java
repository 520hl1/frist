package com.emergency.drill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emergency.drill.entity.DrillPlan;
import com.emergency.drill.mapper.DrillPlanMapper;
import com.emergency.drill.service.DrillPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrillPlanServiceImpl extends ServiceImpl<DrillPlanMapper, DrillPlan> implements DrillPlanService {

    @Override
    public List<DrillPlan> listByDeptId(Integer deptId) {
        return list(new LambdaQueryWrapper<DrillPlan>()
                .eq(DrillPlan::getDeptId, deptId));
    }
}