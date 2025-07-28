package com.emergency.drill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.emergency.drill.entity.SysDepartment;

import java.util.List;

public interface SysDepartmentService extends IService<SysDepartment> {
    // 自定义业务方法
    List<SysDepartment> getDeptTree();
}