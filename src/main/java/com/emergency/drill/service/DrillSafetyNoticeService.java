package com.emergency.drill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.emergency.drill.entity.DrillSafetyNotice;

import java.util.List;

public interface DrillSafetyNoticeService extends IService<DrillSafetyNotice> {
    // 自定义业务方法
    List<DrillSafetyNotice> listActiveNotices();
}