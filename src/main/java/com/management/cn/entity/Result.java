package com.management.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 结果表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 所属班级
     */
    private Integer classid;
    /**
     * 分数
     */
    private Integer totalScore;
    /**
     * 老师id
     */
    private Integer teacherId;
    /**
     * 调查问卷类型
     */
    private Integer surveyType;
    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    private String date;
    /**
     * 选项
     */
    private String options;
    /**
     * 意见、建议
     */
    private String opinion;
}
