package com.management.cn.chen.controller;

import com.management.cn.chen.service.IClassesService;
import com.management.cn.chen.service.ITeacherService;
import com.management.cn.entity.Classes;
import com.management.cn.entity.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IClassesController {


    @Resource
    private IClassesService iClassesService;
    @Resource
    private ITeacherService iTeacherService;

    @RequestMapping("getClasses")
    public String getClasses(Model model){
        List<Teacher> jy=iTeacherService.getTea(1);
        List<Teacher> bzr=iTeacherService.getTea(2);
        List<Classes> list=iClassesService.getClasses();
        model.addAttribute("classes",list);
        return "Long_senate";
    }

}
