package com.emergency.drill.controller;

import com.emergency.drill.entity.SysDepartment;
import com.emergency.drill.service.SysDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/departments") // 使用复数形式更符合REST规范
public class SysDepartmentController {

    @Autowired
    private SysDepartmentService departmentService;

    /**
     * 获取部门列表
     */
    @GetMapping
    public ResponseEntity<?> list() {
        List<SysDepartment> list = departmentService.list();
        return ResponseEntity.ok(mapResult(200, "success", list));
    }

    /**
     * 获取部门树
     */
    @GetMapping("/tree")
    public ResponseEntity<?> tree() {
        List<SysDepartment> tree = departmentService.getDeptTree();
        return ResponseEntity.ok(mapResult(200, "success", tree));
    }

    /**
     * 获取单个部门
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        SysDepartment dept = departmentService.getById(id);
        if (dept != null) {
            return ResponseEntity.ok(mapResult(200, "success", dept));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 添加部门
     */
    @PostMapping
    public ResponseEntity<?> add(@RequestBody SysDepartment department) {
        boolean savedDept = departmentService.save(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapResult(200, "添加成功", savedDept));
    }

    /**
     * 修改部门
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SysDepartment department) {
        department.setId(Math.toIntExact(id)); // 确保ID一致
        boolean success = departmentService.updateById(department);
        if (success) {
            return ResponseEntity.ok(mapResult(200, "修改成功", null));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapResult(500, "修改失败", null));
        }
    }

    /**
     * 删除部门
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean success = departmentService.removeById(id);
        if (success) {
            return ResponseEntity.ok(mapResult(200, "删除成功", null));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapResult(500, "删除失败", null));
        }
    }

    // 统一响应格式封装
    private Map<String, Object> mapResult(int code, String message, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("message", message);
        result.put("data", data);
        return result;
    }
}