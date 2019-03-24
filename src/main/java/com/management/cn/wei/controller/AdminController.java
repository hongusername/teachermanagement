package com.management.cn.wei.controller;


import com.management.cn.entity.Teacher;
import com.management.cn.wei.sevice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /*
     * 去登录
     * */
    @RequestMapping("/login")
    public String tologin() {
        return "admin/adminLogin";
    }

    /*
     * 登录
     * */
    @RequestMapping("/dologin")
    public String dologin( HttpSession session, String name, String pwd, Model model) {
        Teacher login = adminService.login(name, pwd);
        if(login==null){
            model.addAttribute("msg","用户名或者密码错误");
            return "admin/adminLogin";
        }else{
            session.setAttribute("admin", adminService.login(name, pwd));
            return "redirect:admin/index";
        }

    }

    /*
     * 注销
     * */
    @RequestMapping(value = "/logout")
    public String logout( HttpServletRequest request ) {
        HttpSession session = request.getSession();//获取当前session
        System.out.println(session);
        if (session != null) {
            Teacher user = (Teacher) session.getAttribute("admin");//从当前session中获取用户信息
            System.out.println(user);
            session.invalidate();//关闭session
        }
        return "redirect:/login";
    }
}
