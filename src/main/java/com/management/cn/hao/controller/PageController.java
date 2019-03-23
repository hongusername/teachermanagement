package com.management.cn.hao.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: GengHao
 * @date: 2019-03-20 13:57
 */
@Controller
public class PageController {


    @RequestMapping("/pingce")
    public String page(Integer surveyTypeId, Model model) {
        model.addAttribute("surveyTypeId", surveyTypeId);
        return "survey_content";
    }

    @RequestMapping("/addSurveyContentPage")
    public String addSurveyContentPage() {
        return "add_survey_content";
    }

    @RequestMapping("/survey_type_list")
    public String surveyTypeListPage() {
        return "survey_type_list";
    }

    @RequestMapping("/survey_content_list_page")
    public String surveyContentListPage(Integer surveyTypeId, Model model) {
        model.addAttribute("surveyTypeId", surveyTypeId);
        return "survey_content_list";
    }

    @RequestMapping("/evaluating")
    public String evaluating() {
        return "evaluating";
    }
}
