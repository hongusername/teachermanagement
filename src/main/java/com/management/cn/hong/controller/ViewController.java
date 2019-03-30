package com.management.cn.hong.controller;

import com.github.pagehelper.PageInfo;
import com.management.cn.chen.service.IClassesService;
import com.management.cn.entity.Classes;
import com.management.cn.hong.services.HClassesServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ViewController {
    @Resource
    private HClassesServices hClassesServices;

    /*  @RequestMapping("/toHomeView")
      public String toHomeView(Model model){
          model.addAttribute("classList",iClassesService.getClasses());
          return  "admin/home";
      }*/
    @RequestMapping("ajaxGetClasses")
    @ResponseBody
    public PageInfo<Classes> ajaxGetClasses(@RequestParam(required = false, defaultValue = "1") Integer pageNum, @RequestParam(required = false, defaultValue = "4") Integer pageSize) {
        PageInfo<Classes> page = hClassesServices.getAllClasses(pageNum, pageSize);
        return page;
    }

}
