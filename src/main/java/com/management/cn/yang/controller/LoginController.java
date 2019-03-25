package com.management.cn.yang.controller;

import com.management.cn.chen.service.IClassesService;
import com.management.cn.entity.Classes;
import com.management.cn.entity.Student;
import com.management.cn.entity.Teacher;
import com.management.cn.yang.services.StudentServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {


    @Resource
    private StudentServices studentServices;

    @Resource
    private IClassesService classesService;
    @RequestMapping("/user/login")
    public String login(Model model){
        model.addAttribute("classes",classesService.getClasses());
        return "yang_login";
    }

    @RequestMapping("/toEvaluation")
    public String toEvaluation(Model model, Integer grade){
        Classes student =studentServices.queryStudentLogin(grade);
        System.out.println(student);
        if(student!=null){
            System.out.println("----------1111");
            model.addAttribute("grade",student);
            return "yang_toEvaluation";
        }else{
            model.addAttribute("classes",classesService.getClasses());
            return "yang_login";
        }
    }

    @RequestMapping("/evaluation")
    public  String Evaluation(Model model,String key,String grade){
        Teacher teacher=studentServices.queryTeacherById(key,grade);
        Classes classes=studentServices.queryClassesById(Integer.parseInt(grade));
        Integer surveyTypeId = studentServices.querySurveyTypeById(teacher.getType(),classes.getClass_type()).getId();
        model.addAttribute("teacher",teacher);
        model.addAttribute("grade",classes);
        model.addAttribute("surveyTypeId",surveyTypeId);
        model.addAttribute("date",new Date());
        return "evaluating";
    }



}
