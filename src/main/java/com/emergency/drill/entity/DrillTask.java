package com.emergency.drill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("drill_task")
public class DrillTask {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String taskCode;
    private Integer planId;
    private String taskContent;
    private String assignTo;
    private Date dueTime;
    private Integer status;
    private String createBy;
    private Date createTime;
    private Date updateTime;
}