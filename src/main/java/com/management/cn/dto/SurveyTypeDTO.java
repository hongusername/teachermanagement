package com.management.cn.dto;

import com.management.cn.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 问卷类型表
 * @author: GengHao
 * @date: 2019-03-20 11:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyTypeDTO {
    /**问卷类型表id*/
    private Integer id;
    /**问卷类型名称*/
    private String name;
    /**问卷问题选项模板*/
    private String options;
    /**教师类型*/
    private Integer teacherType;
    /**学期*/
    private Grade grade;
    /** 开始时间*/
    private Date startTime;
    /** 结束时间*/
    private Date endTime;
}
