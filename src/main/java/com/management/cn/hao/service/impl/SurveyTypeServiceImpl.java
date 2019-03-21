package com.management.cn.hao.service.impl;

import com.management.cn.entity.SurveyType;
import com.management.cn.hao.dao.SurveyTypeMapper;
import com.management.cn.hao.service.SurveyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-20 16:40
 */
@Service
public class SurveyTypeServiceImpl implements SurveyTypeService {

    @Autowired
    private SurveyTypeMapper surveyTypeMapper;

    @Override
    public List<SurveyType> getSurveyTypeList() {
        return surveyTypeMapper.selectSurveyTypeList();
    }

    @Override
    public SurveyType getSurveyTypeById(Integer id) {
        return surveyTypeMapper.selectSurveyTypeById(id);
    }
}
