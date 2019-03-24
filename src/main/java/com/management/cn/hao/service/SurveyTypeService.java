package com.management.cn.hao.service;

import com.management.cn.dto.SurveyTypeDTO;
import com.management.cn.entity.SurveyType;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-20 16:20
 */
public interface SurveyTypeService {

    /**
     * 查询所有调查问卷类型
     */
    List<SurveyTypeDTO> getSurveyTypeList();

    /**
     * 根据ID查询调查问卷类型
     * @param id
     * @return
     */
    SurveyTypeDTO getSurveyTypeById(Integer id);

    /**
     * 添加调查问卷类型
     * @param surveyType
     * @return
     */
    Boolean addSurveyType(SurveyType surveyType);

    /**
     * 修改调查问卷类型
     * @param surveyType
     * @return
     */
    int updateSurveyTypebId(SurveyType surveyType);

    /**
     * 删除调查问卷
     * @param id
     * @return
     */
    int deleteSurveyTypeId(Integer id);
}
