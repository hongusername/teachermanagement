package com.management.cn.hao.controller;

import com.management.cn.hao.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: GengHao
 * @date: 2019-03-20 13:57
 */
@Controller
public class PageController {

    @Autowired
    private ResultService resultService;

    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        System.out.println("-----------------------page------------------------");
        return page;
    }

    @RequestMapping("/admin/{page}")
    public String adminPage(@PathVariable String page) {


        return "admin/" + page;
    }


    /**
     * 调查问卷内容列表
     */
    @RequestMapping("/survey_content_list_page")
    public String surveyContentListPage(Integer surveyTypeId, Model model) {
        model.addAttribute("surveyTypeId", surveyTypeId);
        return "admin/survey_content_list";
    }


}
