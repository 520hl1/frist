package com.emergency.drill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("emergency_plan")
public class EmergencyPlan {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String planCode;
    private String planName;
    private String planType;
    private String planContent;
    private String planFile;
    private Integer status;
    private String createBy;
    private Date createTime;
    private Date updateTime;
}