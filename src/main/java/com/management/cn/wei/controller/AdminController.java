package com.management.cn.wei.controller;


import com.management.cn.chen.service.ITeacherService;
import com.management.cn.entity.Teacher;
import com.management.cn.wei.sevice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Resource
    private ITeacherService iTeacherService;


    /*
     * 去登录
     * */
    @RequestMapping("/login")
    public String tologin(Model model) {
        return "admin/adminLogin";
    }

    /*
     * 权限登陆
     * */
    @RequestMapping("/checkLogin")
    public String checkLogin(String username, String password, Model model) throws IOException {
        Teacher t = iTeacherService.getTeacher(username);
        model.addAttribute("username", username);
        if (t == null) {
            model.addAttribute("LoginInfo", "账号或密码不正确!");
            return "Long_index";
        } else  {
            if (t.getPwd().equals(password)||t.getPwd()==password&&t.getType()==3) {
                return "redirect:admin/管理员";
            }else if (t.getPwd().equals(password)||t.getPwd()==password&&t.getType()==4){
                return "redirect:admin/教务";
            }else {
                model.addAttribute("LoginInfo", "账号或密码不正确!");
                return "Long_index";
            }
        }
    }

    /*
     * 管理员登录
     * */
 /*   @RequestMapping("/admindologin")
    public String admindologin( HttpSession session, String name, String pwd, Model model, String username, String password ) {
        Teacher login = adminService.login(name, pwd);//管理员登录
        if (login == null) {
            model.addAttribute("msg", "用户名或者密码错误");
            return "admin/adminLogin";
        } else {
            session.setAttribute("admin", adminService.login(name, pwd));
            return "redirect:admin/管理员";
        }
    }*/



       /* for (Teacher teacher1 : teacher) {
            if (teacher1.getType() == 3) {
                if (login == null) {
                    model.addAttribute("msg", "用户名或者密码错误");
                    return "admin/adminLogin";
                } else {
                    session.setAttribute("admin", adminService.login(name, pwd));
                    return "redirect:admin/index";
                }
            } else if (teacher1.getType() == 4) {

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

        }*/

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


    //查询teacher type
    @RequestMapping("/queryTeacherType")
    public String query() {

        return "";

    }
}
