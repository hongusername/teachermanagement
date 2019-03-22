package com.management.cn.yang.controller;

import com.management.cn.entity.Classes;
import com.management.cn.entity.Student;
import com.management.cn.entity.Teacher;
import com.management.cn.yang.services.StudentServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;

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
        Teacher teacher=studentServices.queryTeacherById(key,grade);
        Classes classes=studentServices.queryClassesById(Integer.parseInt(grade));
        System.out.println(teacher.getType());
        System.out.println(classes.getClass_type());
        Integer surveyTypeId = studentServices.querySurveyTypeById(teacher.getType(),classes.getClass_type()).getId();
        model.addAttribute("teacher",teacher);
        model.addAttribute("grade",classes);
        model.addAttribute("surveyTypeId",surveyTypeId);
        model.addAttribute("date",new Date());
        return "survey_content";
    }

}
