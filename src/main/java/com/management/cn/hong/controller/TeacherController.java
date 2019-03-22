package com.management.cn.hong.controller;

import com.management.cn.entity.Teacher;
import com.management.cn.hong.services.TeacherServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TeacherController {
    @Resource
    private TeacherServices teacherServices;

    @RequestMapping("/index")
    @ResponseBody
    public Object index(Teacher teacher, @RequestParam(defaultValue = "1", required = false) Integer pageNumber, @RequestParam(defaultValue = "5", required = false) Integer pageSize) {

        return teacherServices.getAll(teacher, pageNumber, pageSize);
    }

}
