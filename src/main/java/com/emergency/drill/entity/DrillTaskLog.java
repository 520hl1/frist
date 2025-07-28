package com.emergency.drill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("drill_task_log")
public class DrillTaskLog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer taskId;
    private String logContent;
    private Date logTime;
    private String operator;
    private Date createTime;
    private Date updateTime;
}