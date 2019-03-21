package com.management.cn.hao.service;

import com.management.cn.dto.SurveyContentDTO;
import com.management.cn.entity.SurveyContent;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-20 12:40
 */
public interface SurveyContentService {
    List<SurveyContentDTO> getContentBySurveyTypeId(Integer surveyType);
    Boolean addSurveyContent(SurveyContent surveyContent);
}
