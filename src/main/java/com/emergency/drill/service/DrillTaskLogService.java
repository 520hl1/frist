package com.emergency.drill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.emergency.drill.entity.DrillTaskLog;

import java.util.List;

/**
 * 演练任务日志 Service 接口
 */
public interface DrillTaskLogService extends IService<DrillTaskLog> {
    // 可扩展自定义业务方法
    List<DrillTaskLog> getLogsByTaskId(Integer taskId);
}