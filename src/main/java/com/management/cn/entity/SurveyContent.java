package com.management.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 问卷内容
 * @author: GengHao
 * @date: 2019-03-20 11:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyContent {
    /**问卷内容id*/
    private Integer id;
    /**问卷内容*/
    private String content;
    /**问卷所属分类*/
    private Integer surveyType;
    /**问卷选项*/
    private String options;
}
