package com.management.cn.hao.controller;

import com.alibaba.fastjson.JSON;
import com.management.cn.dto.SurveyContentDTO;
import com.management.cn.entity.SurveyContent;
import com.management.cn.hao.service.SurveyContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: GengHao
 * @date: 2019-03-20 12:50
 */
@RestController
@RequestMapping("/survey_content")
public class SurveyContentController {

    @Autowired
    private SurveyContentService surveyContentService;

    @RequestMapping("/getContentBySurveyTypeId")
    public List<SurveyContentDTO> getContentBySurveyTypeId(Integer surveyTypeId) {
        List<SurveyContentDTO> contentList = surveyContentService.getContentBySurveyTypeId(surveyTypeId);
        return contentList;
    }


    @RequestMapping("/addSurveyContent")
    public Map<String, Object> addSurveyContent(@RequestBody List<SurveyContentDTO> surveyContentDTOList) {
        Map<String, Object> map = new HashMap<>();
        Boolean result = surveyContentService.addSurveyContent(surveyContentDTOList);
        if (result) {
            map.put("success", true);
            map.put("message", "添加成功！");
        } else {
            map.put("success", true);
            map.put("message", "添加失败！");
        }
        return map;
    }

}
