package com.emergency.drill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.emergency.drill.entity.DrillEvaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 演练评估 Service 接口
 * 定义业务逻辑方法
 */
public interface DrillEvaluationService extends IService<DrillEvaluation> {

    // 分页查询评估（带筛选条件）
    Page<DrillEvaluation> pageWithCondition(
            Integer pageNum,
            Integer pageSize,
            Integer planId
    );

    // 批量删除评估
    boolean deleteBatch(@Param("ids") List<Integer> ids);
}