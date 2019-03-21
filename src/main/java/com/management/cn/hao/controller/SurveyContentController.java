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
@Slf4j
public class SurveyContentController {

    @Autowired
    private SurveyContentService surveyContentService;

    @RequestMapping("/getContentBySurveyTypeId")
    public List<SurveyContentDTO> getContentBySurveyTypeId(Integer surveyTypeId) {
        log.info("------------------------surveyTypeId:{}", surveyTypeId);
        List<SurveyContentDTO> contentList = surveyContentService.getContentBySurveyTypeId(surveyTypeId);
        return contentList;
    }


    @RequestMapping("/addSurveyContent")
    public Map<String, Object> addSurveyContent(@RequestBody SurveyContentDTO surveyContentDTO) {
        Map<String, Object> map = new HashMap<>();


        List<Map<String, Object>> list = surveyContentDTO.getSurveyContentList();
        for (Map<String, Object> m : list) {
            SurveyContent surveyContent = new SurveyContent();
            //调查问卷id
            surveyContent.setSurveyType(surveyContentDTO.getSurveyType());
            //问题
            surveyContent.setContent(m.get("content").toString());
            //问题选项
            surveyContent.setOptions(JSON.toJSONString(m.get("options")));
            //保存
            surveyContentService.addSurveyContent(surveyContent);
        }
        map.put("success", true);
        map.put("message", "添加成功！");
        return map;
    }

}
