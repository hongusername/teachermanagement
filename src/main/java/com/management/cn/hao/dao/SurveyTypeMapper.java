package com.management.cn.hao.dao;

import com.management.cn.entity.SurveyType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 调查问卷类型mapper
 * @author: GengHao
 * @date: 2019-03-20 11:58
 */
@Component
public interface SurveyTypeMapper {
    /**
     * 查询所有调查问卷类型
     */
    List<SurveyType> selectSurveyTypeList();

    /**
     * 添加调查问卷类型
     * @param surveyType
     * @return
     */
    Boolean insertSurveyType(SurveyType surveyType);

    /**
     * 根据ID查询调查问卷类型
     * @param id
     * @return
     */
    SurveyType selectSurveyTypeById(Integer id);
}
