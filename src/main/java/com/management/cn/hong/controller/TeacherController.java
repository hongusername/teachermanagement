package com.management.cn.hong.controller;

import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.github.pagehelper.PageInfo;
import com.management.cn.entity.Teacher;
import com.management.cn.hong.services.TeacherServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.List;

@Controller
public class TeacherController {
    @Resource
    private TeacherServices teacherServices;

    @RequestMapping("/teacherListController")
    public String index(Model model, Teacher teacher, @RequestParam(defaultValue = "1", required = false) Integer pageNumber, @RequestParam(defaultValue = "5", required = false) Integer pageSize) {
        PageInfo<Teacher> page = teacherServices.getAll(teacher, pageNumber, pageSize);
        List<Teacher> list = page.getList();
        model.addAttribute("teacherList", list);
        if (teacher != null) {
            model.addAttribute("name", teacher.getName());
            model.addAttribute("type", teacher.getType());
        } else {
            model.addAttribute("name", "");
            model.addAttribute("type", 0);
        }

        return "hteacherlist";
    }

    @RequestMapping("/ajaxteacherListController")
    @ResponseBody
    public Object ajaxteacherListController(Teacher teacher, @RequestParam(defaultValue = "1", required = false) Integer pageNumber, @RequestParam(defaultValue = "5", required = false) Integer pageSize) {
        System.out.println(teacher);
        PageInfo<Teacher> page = teacherServices.getAll(teacher, pageNumber, pageSize);
        return page;
    }

    @RequestMapping("ajaxDeleteTeacher")
    @ResponseBody
    public Object[] ajaxDeleteTeacher(Teacher teacher) {
        System.out.println(teacher);
        Object[] arr = new Object[2];
        Integer result = teacherServices.deleteTeacher(teacher.getTeacherid());
        if (result != 0) {
            arr[0] = true;
        } else {
            arr[1] = false;
        }
        arr[1] = teacherServices.getAll(teacher, 1, 5);
        return arr;
    }


    @RequestMapping("ajaxAddTeacher")
    @ResponseBody
    public Object[] ajaxAddTeacher(Teacher teacher) {
        Object[] arr = new Object[2];
        Integer result = teacherServices.insertTeacher(teacher);
        if (result != 0) {
            arr[0] = true;
        } else {
            arr[0] = false;
        }
        arr[1] = teacherServices.getAll(null, 1, 5);
        return arr;
    }

    @RequestMapping("ajaxUpdateTeacher")
    @ResponseBody
    public Object[] ajaxUpdateTeacher(Teacher teacher) {
        Object[] arr = new Object[2];
        Integer result = teacherServices.updateTeacher(teacher);
        if (result != 0) {
            arr[0] = true;
        } else {
            arr[0] = false;
        }
        arr[1] = teacherServices.getAll(null, 1, 5);
        return arr;
    }


}
