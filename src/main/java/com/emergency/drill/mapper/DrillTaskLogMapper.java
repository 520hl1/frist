package com.emergency.drill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emergency.drill.entity.DrillTaskLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 演练任务日志 Mapper 接口
 */
@Mapper
public interface DrillTaskLogMapper extends BaseMapper<DrillTaskLog> {
    // 可扩展自定义 SQL 方法（默认已包含 CRUD）
}