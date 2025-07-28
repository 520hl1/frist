package com.emergency.drill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emergency.drill.entity.DrillTaskLog;
import com.emergency.drill.mapper.DrillTaskLogMapper;
import com.emergency.drill.service.DrillTaskLogService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 演练任务日志 Service 实现类
 */
@Service
public class DrillTaskLogServiceImpl extends ServiceImpl<DrillTaskLogMapper, DrillTaskLog>
        implements DrillTaskLogService {

    @Override
    public List<DrillTaskLog> getLogsByTaskId(Integer taskId) {
        LambdaQueryWrapper<DrillTaskLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DrillTaskLog::getTaskId, taskId)
                .orderByDesc(DrillTaskLog::getLogTime);
        return list(queryWrapper);
    }

    @Override
    public boolean save(DrillTaskLog entity) {
        // 自动填充创建时间和日志时间
        if (entity.getLogTime() == null) {
            entity.setLogTime(new Date());
        }
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        return super.save(entity);
    }
}