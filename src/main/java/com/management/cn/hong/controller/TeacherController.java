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
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {
    @Resource
    private TeacherServices teacherServices;

    @RequestMapping("/teacherListController")
    public String index(Model model, Teacher teacher, @RequestParam(defaultValue = "1", required = false) Integer pageNumber, @RequestParam(defaultValue = "50", required = false) Integer pageSize) {
        PageInfo<Teacher> page = teacherServices.getAll(teacher, pageNumber, pageSize);
        List<Teacher> list = page.getList();
        model.addAttribute("teacherList", list);
        System.out.println(list);
        if (teacher != null) {
            model.addAttribute("name", teacher.getName());
            model.addAttribute("type", teacher.getType());
        } else {
            model.addAttribute("name", "");
            model.addAttribute("type", 0);
        }

        return "admin/hteacherlist";
    }

    @RequestMapping("/ajaxteacherListController")
    @ResponseBody
    public Object ajaxteacherListController(Teacher teacher, @RequestParam(defaultValue = "1", required = false) Integer pageNumber, @RequestParam(defaultValue = "50", required = false) Integer pageSize) {
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
        arr[1] = teacherServices.getAll(teacher, 1, 50);
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
        arr[1] = teacherServices.getAll(null, 1, 50);
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
        arr[1] = teacherServices.getAll(null, 1, 50);
        return arr;
    }

    @RequestMapping("ajaxDeleteTeachers")
    @ResponseBody
    public Object[] ajaxDeleteTeachers(String[] teacherids) {
        System.out.print(teacherids);
        Object[] arr = new Object[2];
        Integer result = teacherServices.deleteTeachers(null);
        if (result != 0) {
            arr[0] = true;
        } else {
            arr[0] = false;
        }
        arr[1] = teacherServices.getAll(null, 1, 50);
        return arr;
    }

    @RequestMapping("ajaxTeacherCanvas")
    @ResponseBody
    public List<Object> ajaxTeacherCanvas() {
        List<Object> list = new ArrayList<>();
        list.add(100);
        list.add(200);
        list.add(400);
        list.add(600);
        list.add(200);
        list.add(340);
        return list;
    }


}
