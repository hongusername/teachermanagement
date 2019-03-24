package com.management.cn.hao.service;

import com.management.cn.dto.SurveyContentDTO;
import com.management.cn.entity.SurveyContent;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-20 12:40
 */
public interface SurveyContentService {
    /**
     * 根据调查问卷类型id查询 问题
     * @param surveyType
     * @return
     */
    List<SurveyContentDTO> getContentBySurveyTypeId(Integer surveyType);

    /**
     * 添加问题
     * @param surveyContentDTOList
     * @return
     */
    Boolean addSurveyContent(List<SurveyContentDTO> surveyContentDTOList);

    /**
     * 根据调查问卷id 删除问题
     * @param surveyTypeId
     * @return
     */
    int deleteSurveyContentBySurveyTypeId(Integer surveyTypeId);
}
