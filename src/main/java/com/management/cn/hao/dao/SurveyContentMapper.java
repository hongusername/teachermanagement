package com.management.cn.hao.dao;

import com.management.cn.entity.SurveyContent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 调查问卷内容mapper
 * @author: GengHao
 * @date: 2019-03-20 12:01
 */
@Component
public interface SurveyContentMapper {
    /**
     * 根据调查问卷类型id查询 问题
     * @param surveyType
     * @return
     */
    List<SurveyContent> selectContentBySurveyTypeId(Integer surveyType);

    /**
     * 添加问题
     * @param surveyContent
     * @return
     */
    Boolean insertSurveyContent(SurveyContent surveyContent);

    /**
     * 根据调查问卷id 删除问题
     * @param surveyType
     * @return
     */
    int deleteSurveyContentBySurveyTypeId(Integer surveyType);
}
