package com.management.cn.chen.controller;

import com.management.cn.chen.service.ITeacherService;
import com.management.cn.entity.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ITeacherController {

    @Resource
    private ITeacherService iTeacherService;

    @RequestMapping("/Longs")
    public String index() {
        System.out.println(1);
        return "Long_index";
    }

    @RequestMapping("tiao_Long_senate")
    public String tiao_Long_senate() {
        return "Long_senate";
    }



    @RequestMapping("doUpdatePassword")
    public String doupdatePassword(Model model,Teacher teacher){
        model.addAttribute("teacher",this.iTeacherService.updatePassword(teacher));
        return "admin/index";
    }


}
