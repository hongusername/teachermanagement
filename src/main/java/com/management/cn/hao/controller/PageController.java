package com.management.cn.hao.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: GengHao
 * @date: 2019-03-20 13:57
 */
@Controller
public class PageController {


    @ApiOperation(value = "进入学生评测老师页面")
    @ApiImplicitParam(name = "surveyTypeId",value = "调查问卷类型")
    @RequestMapping("/pingce")
    public String page(Integer surveyTypeId, Model model) {
        model.addAttribute("surveyTypeId", surveyTypeId);
        return "survey_content";
    }
    @ApiOperation(value = "进入添加调查问卷内容页面")
    @RequestMapping("/addSurveyContentPage")
    public String addSurveyContentPage() {
        return "add_survey_content";
    }

    @ApiOperation(value = "进入调查问卷类型列表页面")
    @RequestMapping("/survey_type_list")
    public String surveyTypeListPage(){
        return "survey_type_list";
    }

    @ApiOperation(value = "查看调查问卷内容")
    @ApiImplicitParam(name = "surveyTypeId",value = "调查问卷类型")
    @RequestMapping("/survey_content_list_page")
    public String surveyContentListPage(Integer surveyTypeId, Model model){
        model.addAttribute("surveyTypeId", surveyTypeId);
        return "survey_content_list";
    }
}
