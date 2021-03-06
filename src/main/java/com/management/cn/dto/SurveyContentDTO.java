package com.management.cn.dto;

import com.management.cn.entity.Option;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-20 13:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyContentDTO {
    /**问卷内容id*/
    private Integer id;
    /**问卷内容*/
    private String content;
    /**问卷所属分类*/
    private Integer surveyType;
    /**问卷选项*/
    private List<Option> options;
    /** 这个题的平均分*/
    private Integer avg;

}
