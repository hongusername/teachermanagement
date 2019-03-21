package com.management.cn.yang.controller;

import com.management.cn.entity.Student;
import com.management.cn.yang.services.StudentServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class LoginController {


    @Resource
    private StudentServices studentServices;

    @RequestMapping("/login")
    public String login(){
        return "yang_login";
    }

    @RequestMapping("/toEvaluation")
    public String toEvaluation(Model model, String grade, String stuname){
        Student student =studentServices.queryStudentLogin(grade,stuname);
        if(student!=null){
            model.addAttribute("student",student);
            return "yang_toEvaluation";
        }else{
            return "yang_login";
        }
    }

    @RequestMapping("/Evaluation")
    public String Evaluation(Model model,String key,String grade){
        System.out.println(studentServices.queryTeacherById(key,grade));
        model.addAttribute("teacher",studentServices.queryTeacherById(key,grade));
        model.addAttribute("grade",studentServices.queryClassesById(Integer.parseInt(grade)));
        return "yang_Evaluation";
    }

}
