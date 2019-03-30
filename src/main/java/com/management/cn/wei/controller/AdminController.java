package com.management.cn.wei.controller;


import com.management.cn.chen.service.ITeacherService;
import com.management.cn.entity.Role;
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

 /*   *//*
     * 登录
     * *//*
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

    }*/

    /*
     * 修改密码
     * */

    @RequestMapping("toupdatePwd")
    public String toupdate() {
        return "admin/updateTeacherPwd";
    }

    @RequestMapping("/doupdatePwd")
    public String doupda( Role role, Model model ) {
        Integer t = adminService.updatePwd(role);
        System.out.println(t);
        if(t!=0){
            model.addAttribute("msg","修改成功，请重新登录！");
        }
        return "admin/adminLogin";
    }






    /*
     权限登陆
     */
    @RequestMapping("/checkLogin")
    public String checkLogin(HttpSession session,String name, String pwd, Model model)  {
        Role t = iTeacherService.getRole(name);

        session.setAttribute("username", name);


        if (t == null) {
            model.addAttribute("msg", "账号或密码不正确!");
            return "adminLogin";
        } else  {
            if (t.getPwd().equals(pwd)||t.getPwd()==pwd) {
                session.setAttribute("role",t);
                System.out.println(t);
                return "redirect:admin/index";
            }else{
                model.addAttribute("msg", "账号或密码不正确!");
                return "adminLogin";
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
