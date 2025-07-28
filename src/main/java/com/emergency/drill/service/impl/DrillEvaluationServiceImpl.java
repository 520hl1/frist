package com.emergency.drill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emergency.drill.entity.DrillEvaluation;
import com.emergency.drill.mapper.DrillEvaluationMapper;
import com.emergency.drill.service.DrillEvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 演练评估 Service 实现类
 * 实现业务逻辑方法
 */
@Service
public class DrillEvaluationServiceImpl extends ServiceImpl<DrillEvaluationMapper, DrillEvaluation>
        implements DrillEvaluationService {

    /**
     * 分页查询评估（带筛选条件）
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @param planId 预案ID（可选筛选条件）
     * @return 分页结果
     */
    @Override
    public Page<DrillEvaluation> pageWithCondition(
            Integer pageNum,
            Integer pageSize,
            Integer planId
    ) {
        LambdaQueryWrapper<DrillEvaluation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(planId != null, DrillEvaluation::getPlanId, planId); // 按预案ID筛选
        queryWrapper.orderByDesc(DrillEvaluation::getEvaluationTime); // 按评估时间倒序排序

        return this.page(new Page<>(pageNum, pageSize), queryWrapper);
    }

    /**
     * 批量删除评估
     * @param ids 评估ID列表
     * @return 删除结果
     */
    @Override
    public boolean deleteBatch(List<Integer> ids) {
        return this.removeByIds(ids);
    }

    /**
     * 新增/修改时自动填充公共字段
     * @param entity 评估实体
     */
    @Override
    public boolean save(DrillEvaluation entity) {
        entity.setCreateTime(new Date()); // 自动填充创建时间
        entity.setUpdateTime(new Date()); // 自动填充更新时间
        return super.save(entity);
    }

    @Override
    public boolean updateById(DrillEvaluation entity) {
        entity.setUpdateTime(new Date()); // 修改时更新时间
        return super.updateById(entity);
    }
}