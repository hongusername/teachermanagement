package com.management.cn.hao.controller;

import com.management.cn.entity.SurveyType;
import com.management.cn.hao.service.SurveyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-20 16:56
 */
@RestController
@RequestMapping("/survey_type")
public class SurveyTypeController {
    @Autowired
    private SurveyTypeService surveyTypeService;

    @RequestMapping("/getSurveyTypeList")
    public List<SurveyType> getSurveyTypeList() {
        List<SurveyType> surveyTypeList = surveyTypeService.getSurveyTypeList();
        return surveyTypeList;
    }

    @RequestMapping("/getSurveyTypeById")
    public SurveyType getSurveyTypeById(Integer id){
        SurveyType surveyType = surveyTypeService.getSurveyTypeById(id);
        return surveyType;
    }

}
