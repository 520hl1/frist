package com.emergency.drill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.emergency.drill.entity.DrillPlan;

import java.util.List;

public interface DrillPlanService extends IService<DrillPlan> {

    /**
     * 根据部门ID查询演练计划列表
     * @param deptId 部门ID
     * @return 演练计划列表
     */
    List<DrillPlan> listByDeptId(Integer deptId);
}