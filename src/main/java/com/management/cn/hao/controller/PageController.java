package com.management.cn.hao.controller;

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
}
