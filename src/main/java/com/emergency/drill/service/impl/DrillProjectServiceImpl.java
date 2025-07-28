package com.emergency.drill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emergency.drill.entity.DrillProject;
import com.emergency.drill.mapper.DrillProjectMapper;
import com.emergency.drill.service.DrillProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 演练项目 Service 实现类
 */
@Service
public class DrillProjectServiceImpl extends ServiceImpl<DrillProjectMapper, DrillProject>
        implements DrillProjectService {

    @Override
    @Transactional
    public boolean toggleActiveStatus(Integer id, boolean active) {
        DrillProject project = getById(id);
        if (project == null) {
            return false;
        }
        project.setIsActive(active ? 1 : 0);
        return updateById(project);
    }
}