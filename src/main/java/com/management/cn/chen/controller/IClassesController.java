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
import java.util.ArrayList;
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
        List<Classes> list = iClassesService.getClasses();
        List<Teacher> jy = iTeacherService.getTea(1);
        List<Teacher> bzr = iTeacherService.getTea(2);
        List<Grade> classType = longIGradeService.getGrade();
        model.addAttribute("classes", list);
        model.addAttribute("jy", jy);
        model.addAttribute("bzr", bzr);
        model.addAttribute("classType", classType);

        return "Long_classes";
    }

    @RequestMapping("l_addClass")
    public String l_addClass(String classesName, int classesType, int classesBzr, int classesJy) {

        System.out.println(classesName + "-" + classesType + "-" + classesBzr + "-" + classesJy);
        Classes c = new Classes();
        c.setClass_name(classesName);
        c.setClass_type(classesType);
        c.setClass_bzr(classesBzr);
        c.setClass_jy(classesJy);
        iClassesService.addClass(c);
        return "redirect:/getClasses";
    }

    @RequestMapping("updClasses")
    public String updClasses(int id, String classesName, int classesType, int classesBzr, int classesJy) {

        Classes c = new Classes();
        c.setClass_id(id);
        c.setClass_name(classesName);
        c.setClass_bzr(classesBzr);
        c.setClass_type(classesType);
        c.setClass_jy(classesJy);
        int i = iClassesService.updClass(c);
        return "redirect:/getClasses";
    }

    @RequestMapping("delClasses")
    public String delClasses(int id) {
        int i = iClassesService.delClass(id);
        return "redirect:/getClasses";
    }

    @RequestMapping("Long_sel")
    public String sel(String key, Model model) {

        if(key.trim()==null||key.trim()==""){
            return "redirect:/getClasses";
        }
        List<Teacher> jy = iTeacherService.getTea(1);
        List<Teacher> bzr = iTeacherService.getTea(2);
        List<Grade> classType = longIGradeService.getGrade();
        model.addAttribute("jy", jy);
        model.addAttribute("bzr", bzr);
        model.addAttribute("classType", classType);
        model.addAttribute("key", key);

        List<Classes> c1 = iClassesService.selClassBzr(key);
        List<Classes> c2 = iClassesService.selClassJy(key);
        List<Classes> c3 = iClassesService.selClassType(key);
        List<Classes> c4 = iClassesService.selClassName(key);

        List<Classes> list = new ArrayList<Classes>();
        for(Classes c : c1){
            list.add(c);
        }
        for(Classes c : c2){
            list.add(c);
        }
        for(Classes c : c3){
            list.add(c);
        }
        for(Classes c : c4){
            list.add(c);
        }

        for (Classes c : list) {
            Teacher t1 = new Teacher();
            Teacher t2 = new Teacher();
            Grade g = new Grade();
            c.setClass_name(c.getClass_name().replace(key, "<span style='color:red;'>" + key + "</span>"));

            t1.setName(c.getTeacher1().getName().replace(key, "<span style='color:red;'>" + key + "</span>"));
            t1.setTeacherid(c.getTeacher2().getTeacherid());
            c.setTeacher1(t1);


            t2.setName(c.getTeacher2().getName().replace(key, "<span style='color:red;'>" + key + "</span>"));
            t2.setTeacherid(c.getTeacher1().getTeacherid());
            c.setTeacher2(t2);


            g.setSemester(c.getGrade().getSemester().replace(key, "<span style='color:red;'>" + key + "</span>"));
            g.setId(c.getGrade().getId());
            c.setGrade(g);


            c.setClass_name(c.getClass_name().replace(key, "<span style='color:red;'>" + key + "</span>"));
            System.out.println(c.getClass_name());
        }

             model.addAttribute("classes", list);
        return "Long_classes";
    }
}
