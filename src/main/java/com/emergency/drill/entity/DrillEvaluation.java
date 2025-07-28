package com.emergency.drill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("drill_evaluation")
public class DrillEvaluation {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer planId;
    private String evaluationContent;
    private String problems;
    private String improvementSuggestions;
    private String evaluator;
    private Date evaluationTime;
    private Date createTime;
    private Date updateTime;
}