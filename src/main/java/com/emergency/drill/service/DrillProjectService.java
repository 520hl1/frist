package com.emergency.drill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.emergency.drill.entity.DrillProject;

/**
 * 演练项目 Service 接口
 */
public interface DrillProjectService extends IService<DrillProject> {
    // 可扩展自定义业务方法
    boolean toggleActiveStatus(Integer id, boolean active);
}