package com.emergency.drill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emergency.drill.entity.DrillProject;
import org.apache.ibatis.annotations.Mapper;

/**
 * 演练项目 Mapper 接口
 */
@Mapper
public interface DrillProjectMapper extends BaseMapper<DrillProject> {
    // 可扩展自定义 SQL 方法（默认已包含 CRUD）
}