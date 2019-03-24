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

    @RequestMapping("checkLogin")
    public String checkLogin(String username, String password, Model model) throws IOException {
        Teacher t = iTeacherService.getTeacher(username);
        model.addAttribute("username", username);
        if (t == null) {
            model.addAttribute("LoginInfo", "账号或密码不正确!");
            return "Long_index";
        } else  {
            if (t.getPwd().equals(password)||t.getPwd()==password) {
                return "redirect:getClasses";
            } else {
                model.addAttribute("LoginInfo", "账号或密码不正确!");
                return "Long_index";
            }
        }
    }
}
