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
    List<SurveyContent> selectContentBySurveyTypeId(Integer surveyType);
    Boolean insertSurveyContent(SurveyContent surveyContent);
}
