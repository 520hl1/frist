package com.emergency.drill.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.emergency.drill.entity.DrillTaskLog;
import com.emergency.drill.service.DrillTaskLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 演练任务日志 Controller
 */
@RestController
@RequestMapping("/api/drillTaskLog")
public class DrillTaskLogController {

    @Resource
    private DrillTaskLogService logService;

    /**
     * 新增日志
     */
    @PostMapping("/add")
    public String add(@RequestBody DrillTaskLog log) {
        boolean result = logService.save(log);
        return result ? "success" : "fail";
    }

    /**
     * 根据任务ID获取日志列表
     */
    @GetMapping("/listByTaskId/{taskId}")
    public List<DrillTaskLog> listByTaskId(@PathVariable Integer taskId) {
        return logService.getLogsByTaskId(taskId);
    }

    /**
     * 分页查询日志
     */
    @GetMapping("/list")
    public Page<DrillTaskLog> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer taskId,
            @RequestParam(required = false) String operator
    ) {
        LambdaQueryWrapper<DrillTaskLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(taskId != null, DrillTaskLog::getTaskId, taskId);
        queryWrapper.like(operator != null, DrillTaskLog::getOperator, operator);
        queryWrapper.orderByDesc(DrillTaskLog::getLogTime);

        return logService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }

    /**
     * 根据ID查询日志
     */
    @GetMapping("/get/{id}")
    public DrillTaskLog get(@PathVariable Integer id) {
        return logService.getById(id);
    }

    /**
     * 删除日志
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return logService.removeById(id) ? "success" : "fail";
    }
}