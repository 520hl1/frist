package com.emergency.drill.controller;

import com.emergency.drill.entity.EmergencyPlan;
import com.emergency.drill.service.EmergencyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/emergencyPlan")
public class EmergencyPlanController {

    @Autowired
    private EmergencyPlanService emergencyPlanService;

    @PostMapping("/add")
    public String addEmergencyPlan(@RequestBody EmergencyPlan emergencyPlan) {
        emergencyPlanService.save(emergencyPlan);
        return "添加成功";
    }

    @PutMapping("/update")
    public String updateEmergencyPlan(@RequestBody EmergencyPlan emergencyPlan) {
        emergencyPlanService.updateById(emergencyPlan);
        return "更新成功";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmergencyPlan(@PathVariable Integer id) {
        emergencyPlanService.removeById(id);
        return "删除成功";
    }

    @GetMapping("/get/{id}")
    public EmergencyPlan getEmergencyPlan(@PathVariable Integer id) {
        return emergencyPlanService.getById(id);
    }

    @GetMapping("/list")
    public List<EmergencyPlan> listEmergencyPlans() {
        return emergencyPlanService.list();
    }
}