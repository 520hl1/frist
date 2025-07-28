package com.emergency.drill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("drill_project")
public class DrillProject {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String projectName;
    private String projectDesc;
    private Integer isActive;
    private Date createTime;
}