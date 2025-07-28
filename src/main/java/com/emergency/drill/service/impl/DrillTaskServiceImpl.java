package com.emergency.drill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emergency.drill.entity.DrillTask;
import com.emergency.drill.mapper.DrillTaskMapper;
import com.emergency.drill.service.DrillTaskService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DrillTaskServiceImpl extends ServiceImpl<DrillTaskMapper, DrillTask> implements DrillTaskService {

    @Override
    public List<DrillTask> listByPlanId(Integer planId) {
        return list(new LambdaQueryWrapper<DrillTask>()
                .eq(DrillTask::getPlanId, planId));
    }

    @Override
    public List<DrillTask> listByAssignee(String assignTo) {
        return list(new LambdaQueryWrapper<DrillTask>()
                .eq(DrillTask::getAssignTo, assignTo));
    }

    @Override
    public List<DrillTask> listOverdueTasks() {
        return list(new LambdaQueryWrapper<DrillTask>()
                .lt(DrillTask::getDueTime, new Date())
                .ne(DrillTask::getStatus, 2)); // 状态不为已完成(2)
    }
}