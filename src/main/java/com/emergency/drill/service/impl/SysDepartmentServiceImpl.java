package com.emergency.drill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emergency.drill.entity.SysDepartment;
import com.emergency.drill.mapper.SysDepartmentMapper;
import com.emergency.drill.service.SysDepartmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentMapper, SysDepartment> implements SysDepartmentService {

    @Override
    public List<SysDepartment> getDeptTree() {
        List<SysDepartment> allDepts = baseMapper.selectList(null);
        Map<Integer, SysDepartment> deptMap = new HashMap<>();
        List<SysDepartment> rootDepts = new ArrayList<>();

        // 构建部门Map和根部门列表
        for (SysDepartment dept : allDepts) {
            deptMap.put(dept.getId(), dept);
            if (dept.getParentId() == null || dept.getParentId() == 0) {
                rootDepts.add(dept);
            }
        }

        // 构建部门树
        for (SysDepartment dept : allDepts) {
            SysDepartment parent = deptMap.get(dept.getParentId());
            if (parent != null) {
                // 这里需要在SysDepartment类中添加children字段
                // parent.getChildren().add(dept);
            }
        }

        return rootDepts;
    }
}