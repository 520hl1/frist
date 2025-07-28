package com.emergency.drill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("drill_safety_notice")
public class DrillSafetyNotice {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String noticeTitle;
    private String noticeContent;
    private String voiceFile;
    private Integer duration;
    private Integer isActive;
    private String createBy;
    private Date createTime;
    private Date updateTime;
}