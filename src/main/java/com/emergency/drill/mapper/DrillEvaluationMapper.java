package com.emergency.drill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emergency.drill.entity.DrillEvaluation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 演练评估 Mapper 接口
 * 继承 BaseMapper 获得通用 CRUD 方法
 * 可扩展自定义 SQL 方法
 */
@Mapper
public interface DrillEvaluationMapper extends BaseMapper<DrillEvaluation> {

    // 示例：根据预案ID查询评估列表（自定义方法，可按需扩展）
    List<DrillEvaluation> selectByPlanId(@Param("planId") Integer planId);
}