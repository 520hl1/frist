package com.emergency.drill.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.emergency.drill.entity.DrillEvaluation;
import com.emergency.drill.service.DrillEvaluationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/drillEvaluation")
public class DrillEvaluationController {

    @Resource
    private DrillEvaluationService evaluationService;

    // 新增评估
    @PostMapping("/add")
    public String add(@RequestBody DrillEvaluation evaluation) {
        evaluation.setCreateTime(new Date());
        evaluation.setUpdateTime(new Date());
        boolean result = evaluationService.save(evaluation);
        return result ? "success" : "fail";
    }

    // 修改评估
    @PutMapping("/update")
    public String update(@RequestBody DrillEvaluation evaluation) {
        evaluation.setUpdateTime(new Date());
        boolean result = evaluationService.updateById(evaluation);
        return result ? "success" : "fail";
    }

    // 删除评估
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return evaluationService.removeById(id) ? "success" : "fail";
    }

    // 根据ID查询
    @GetMapping("/get/{id}")
    public DrillEvaluation get(@PathVariable Integer id) {
        return evaluationService.getById(id);
    }

    // 分页查询（按预案ID筛选）
    @GetMapping("/list")
    public Page<DrillEvaluation> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer planId
    ) {
        LambdaQueryWrapper<DrillEvaluation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(planId != null, DrillEvaluation::getPlanId, planId);
        return evaluationService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }
}