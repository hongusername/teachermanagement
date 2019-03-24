package com.management.cn.chen.controller;

import com.management.cn.chen.service.IClassesService;
import com.management.cn.chen.service.ITeacherService;
import com.management.cn.chen.service.LongIGradeService;
import com.management.cn.entity.Classes;
import com.management.cn.entity.Grade;
import com.management.cn.entity.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IClassesController {


    @Resource
    private IClassesService iClassesService;
    @Resource
    private ITeacherService iTeacherService;
    @Resource
    private LongIGradeService longIGradeService;

    @RequestMapping("getClasses")
    public String getClasses(Model model) {
        List<Teacher> jy = iTeacherService.getTea(1);
        List<Teacher> bzr = iTeacherService.getTea(2);
        List<Classes> list = iClassesService.getClasses();
        List<Grade> classType=longIGradeService.getGrade();
        model.addAttribute("classes", list);
        model.addAttribute("jy", jy);
        model.addAttribute("bzr", bzr);
        model.addAttribute("classType", classType);

        for (Classes g : list){}
        return "Long_classes";
    }

    @RequestMapping("l_addClass")
    public String l_addClass(String classesName,int classesType,int classesBzr,int classesJy){

        System.out.println( classesName+"-"+classesType+"-"+classesBzr+"-"+classesJy);
        Classes c=new Classes();
        c.setClass_name(classesName);
        c.setClass_type(classesType);
        c.setClass_bzr(classesBzr);
        c.setClass_jy(classesJy);
        iClassesService.addClass(c);
        return "redirect:/getClasses";
    }
}
