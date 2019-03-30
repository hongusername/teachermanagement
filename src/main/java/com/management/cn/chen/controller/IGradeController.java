package com.management.cn.chen.controller;

import com.management.cn.chen.service.LongIGradeService;
import com.management.cn.entity.Grade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class IGradeController {
    @Resource
    private LongIGradeService longIGradeService;

    @RequestMapping("deleteGradeController")
    public String delete(int id, Model model) {
        model.addAttribute("rows",this.longIGradeService.deleteGrade(id));
        return "redirect:/listGradeController";
    }

    @RequestMapping("doUpdateGradeController")
    public String doUpdate(Grade grade, Model model) {
        model.addAttribute("rows",this.longIGradeService.updateGrade(grade));
        return "redirect:/listGradeController";
    }




    @RequestMapping("detailGradeController")
    @ResponseBody
    public Grade detail(@RequestParam(value="id")int id) {
        return this.longIGradeService.queryGradeById(id);
    }

    @RequestMapping("doAddGradeController")
    public String doAdd(Grade grade,Model model) {
       Integer rows = this.longIGradeService.addGrade(grade);
        model.addAttribute("rows", rows);
        System.out.println(grade);
        return "redirect:/listGradeController";
    }

    @RequestMapping(value="listGradeController")
    public String queryAll(Model model) {
        model.addAttribute("listGrade", this.longIGradeService.getGrade());
        return "admin/GradeList";


    }
}
