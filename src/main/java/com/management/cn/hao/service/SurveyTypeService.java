package com.management.cn.hao.service;

import com.management.cn.entity.SurveyType;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-20 16:20
 */
public interface SurveyTypeService {

    /**
     * 查询所有调查问卷类型
     */
    List<SurveyType> getSurveyTypeList();

    /**
     * 根据ID查询调查问卷类型
     * @param id
     * @return
     */
    SurveyType getSurveyTypeById(Integer id);
}
