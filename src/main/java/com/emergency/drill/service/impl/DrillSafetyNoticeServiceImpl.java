package com.emergency.drill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emergency.drill.entity.DrillSafetyNotice;
import com.emergency.drill.mapper.DrillSafetyNoticeMapper;
import com.emergency.drill.service.DrillSafetyNoticeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrillSafetyNoticeServiceImpl extends ServiceImpl<DrillSafetyNoticeMapper, DrillSafetyNotice> implements DrillSafetyNoticeService {

    @Override
    public List<DrillSafetyNotice> listActiveNotices() {
        return list(new LambdaQueryWrapper<DrillSafetyNotice>()
                .eq(DrillSafetyNotice::getIsActive, 1));
    }
}