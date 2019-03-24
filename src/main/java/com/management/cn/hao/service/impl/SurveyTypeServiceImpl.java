package com.management.cn.hao.service.impl;

import com.management.cn.dto.SurveyTypeDTO;
import com.management.cn.entity.Grade;
import com.management.cn.entity.SurveyType;
import com.management.cn.hao.dao.GradeMapper;
import com.management.cn.hao.dao.SurveyContentMapper;
import com.management.cn.hao.dao.SurveyTypeMapper;
import com.management.cn.hao.service.SurveyTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-20 16:40
 */
@Service
public class SurveyTypeServiceImpl implements SurveyTypeService {

    @Autowired
    private SurveyTypeMapper surveyTypeMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private SurveyContentMapper surveyContentMapper;

    @Override
    public List<SurveyTypeDTO> getSurveyTypeList() {

        List<SurveyTypeDTO> surveyTypeDTOList = new ArrayList<>();

        List<SurveyType> surveyTypeList = surveyTypeMapper.selectSurveyTypeList();

        for (SurveyType surveyType : surveyTypeList) {
            SurveyTypeDTO surveyTypeDTO = new SurveyTypeDTO();
            BeanUtils.copyProperties(surveyType, surveyTypeDTO);
            Grade grade = gradeMapper.selectGradeBId(surveyType.getGradeId());
            surveyTypeDTO.setGrade(grade);
            surveyTypeDTOList.add(surveyTypeDTO);
        }


        return surveyTypeDTOList;
    }

    @Override
    public SurveyTypeDTO getSurveyTypeById(Integer id) {
        SurveyTypeDTO surveyTypeDTO = new SurveyTypeDTO();
        SurveyType surveyType = surveyTypeMapper.selectSurveyTypeById(id);
        BeanUtils.copyProperties(surveyType, surveyTypeDTO);
        Grade grade = gradeMapper.selectGradeBId(surveyType.getGradeId());
        surveyTypeDTO.setGrade(grade);
        return surveyTypeDTO;
    }

    @Override
    public Boolean addSurveyType(SurveyType surveyType) {
        return surveyTypeMapper.insertSurveyType(surveyType);
    }

    @Override
    public int updateSurveyTypebId(SurveyType surveyType) {
        return surveyTypeMapper.updateSurveyTypebId(surveyType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteSurveyTypeId(Integer id) {
        //删除调查问卷同时删除该调查问卷的 题
        surveyContentMapper.deleteSurveyContentBySurveyTypeId(id);
        return surveyTypeMapper.deleteSurveyTypeId(id);
    }
}
