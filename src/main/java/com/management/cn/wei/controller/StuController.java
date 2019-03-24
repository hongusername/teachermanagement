package com.management.cn.wei.controller;

import com.management.cn.entity.Classes;
import com.management.cn.entity.Student;
import com.management.cn.wei.sevice.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StuController {

    @Autowired
    private StuService stuService;

    @RequestMapping("queryStuAll")
    public String query( Model model , @RequestParam(defaultValue = "1",required = false) Integer pageNum, @RequestParam(defaultValue = "5",required = false)Integer pageSize) {
        model.addAttribute("list", stuService.queryStuAll(pageNum,pageSize));
        model.addAttribute("listClass", stuService.queryAllClasses());
        return "listStu";
    }


    @RequestMapping("delStu")
    @ResponseBody
    public boolean delStu( Model model, Integer stu_id ) {
        Integer iii = stuService.delStu(stu_id);
        if (iii > 0) {
            return true;
        }
        return false;
    }

    @RequestMapping("toupdateStu")
    @ResponseBody
    public Student toupdateStu(Integer stu_id) {
        return stuService.detailStu(stu_id);
    }
    @RequestMapping("toupdateStulist")
    @ResponseBody
    public List<Classes> toupdateStul() {
        return stuService.queryAllClasses();
    }
    @RequestMapping("updateStu")
    public String updateStu( Model model, Student student ) {
        model.addAttribute("list", stuService.updateStu(student));
        return "redirect:/queryStuAll";
    }



    @RequestMapping("addStu")
    public String addStu( Model model, Student student ) {
        model.addAttribute("list", stuService.addStu(student));

        return "redirect:/queryStuAll";
    }

    @RequestMapping("doFalseDelete")
    @ResponseBody
    public Map<String, Object> doFalseDelete( String checkedId) {
        Map<String, Object> map = new HashMap<String, Object>();
        stuService.falseDelete(checkedId);
        map.put("message", "删除成功");
        return map;

    }
  /*  @RequestMapping(value = "/queryAllStudent")
    public void query( HttpServletResponse resp ) {
        try {
            *//*list集合中存放的是好多student对象*//*
            List<Student> students = stuService.queryStuAll();
            *//*将list集合装换成json对象*//*
            JSONArray data = JSONArray.fromObject(students);
            //接下来发送数据
            *//*设置编码，防止出现乱码问题*//*
            resp.setCharacterEncoding("utf-8");
            *//*得到输出流*//*
            PrintWriter respWritter = resp.getWriter();
            *//*将JSON格式的对象toString()后发送*//*
            respWritter.append(data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

}
