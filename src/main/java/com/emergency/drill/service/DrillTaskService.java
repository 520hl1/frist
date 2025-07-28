package com.emergency.drill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.emergency.drill.entity.DrillTask;

import java.util.List;

public interface DrillTaskService extends IService<DrillTask> {

    /**
     * 根据计划ID查询任务列表
     * @param planId 计划ID
     * @return 任务列表
     */
    List<DrillTask> listByPlanId(Integer planId);

    /**
     * 根据负责人查询任务列表
     * @param assignTo 负责人
     * @return 任务列表
     */
    List<DrillTask> listByAssignee(String assignTo);

    /**
     * 查询已逾期的任务列表
     * @return 逾期任务列表
     */
    List<DrillTask> listOverdueTasks();
}