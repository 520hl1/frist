package com.emergency.drill.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.emergency.drill.entity.DrillProject;
import com.emergency.drill.service.DrillProjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 演练项目 Controller
 */
@RestController
@RequestMapping("/api/drillProject")
public class DrillProjectController {

    @Resource
    private DrillProjectService projectService;

    /**
     * 新增演练项目
     */
    @PostMapping("/add")
    public String add(@RequestBody DrillProject project) {
        project.setCreateTime(new Date());
        project.setIsActive(1); // 默认启用
        boolean result = projectService.save(project);
        return result ? "success" : "fail";
    }

    /**
     * 更新演练项目
     */
    @PutMapping("/update")
    public String update(@RequestBody DrillProject project) {
        boolean result = projectService.updateById(project);
        return result ? "success" : "fail";
    }

    /**
     * 切换项目状态（启用/禁用）
     */
    @PutMapping("/toggleStatus/{id}/{active}")
    public String toggleStatus(@PathVariable Integer id, @PathVariable Boolean active) {
        boolean result = projectService.toggleActiveStatus(id, active);
        return result ? "success" : "fail";
    }

    /**
     * 删除演练项目（逻辑删除）
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return projectService.removeById(id) ? "success" : "fail";
    }

    /**
     * 根据ID查询项目
     */
    @GetMapping("/get/{id}")
    public DrillProject get(@PathVariable Integer id) {
        return projectService.getById(id);
    }

    /**
     * 分页查询项目列表
     */
    @GetMapping("/list")
    public Page<DrillProject> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String projectName,
            @RequestParam(required = false) Integer isActive
    ) {
        LambdaQueryWrapper<DrillProject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(projectName != null, DrillProject::getProjectName, projectName);
        queryWrapper.eq(isActive != null, DrillProject::getIsActive, isActive);

        return projectService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }

    /**
     * 获取所有启用的项目列表（用于下拉选择）
     */
    @GetMapping("/activeList")
    public List<DrillProject> activeList() {
        LambdaQueryWrapper<DrillProject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DrillProject::getIsActive, 1);
        return projectService.list(queryWrapper);
    }
}