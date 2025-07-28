package com.emergency.drill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("drill_plan")
public class DrillPlan {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String planCode;
    private Integer deptId;
    private String drillReason;
    private Integer projectId;
    private String drillContent;
    private Date drillTime;
    private String drillPersons;
    private String impactScope;
    private String cooperateDepts;
    private String drillPlanFile;
    private Integer status;
    private String createBy;
    private Date createTime;
    private Date updateTime;
}