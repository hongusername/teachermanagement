package com.management.cn.hao.controller;

import com.management.cn.entity.SurveyType;
import com.management.cn.hao.service.SurveyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public SurveyType getSurveyTypeById(Integer id) {
        SurveyType surveyType = surveyTypeService.getSurveyTypeById(id);
        return surveyType;
    }

    @RequestMapping("/addSurveyType")
    public Map<String, Object> addSurveyType(@RequestBody SurveyType surveyType) {
        Map<String, Object> map = new HashMap<>();
        Boolean result = surveyTypeService.addSurveyType(surveyType);
        if (result) {
            map.put("success", true);
            map.put("message", "添加成功！");
        } else {
            map.put("success", false);
            map.put("message", "添加失败！");
        }
        return map;
    }

}
