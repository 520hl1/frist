package com.emergency.drill.controller;

import com.emergency.drill.entity.DrillTask;
import com.emergency.drill.service.DrillTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/drillTask")
public class DrillTaskController {

    @Autowired
    private DrillTaskService drillTaskService;

    @PostMapping("/add")
    public String addDrillTask(@RequestBody DrillTask drillTask) {
        drillTaskService.save(drillTask);
        return "添加成功";
    }

    @PutMapping("/update")
    public String updateDrillTask(@RequestBody DrillTask drillTask) {
        drillTaskService.updateById(drillTask);
        return "更新成功";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDrillTask(@PathVariable Integer id) {
        drillTaskService.removeById(id);
        return "删除成功";
    }

    @GetMapping("/get/{id}")
    public DrillTask getDrillTask(@PathVariable Integer id) {
        return drillTaskService.getById(id);
    }

    @GetMapping("/list")
    public List<DrillTask> listDrillTasks() {
        return drillTaskService.list();
    }
}