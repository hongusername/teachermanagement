package com.management.cn.ying.Controller;

import com.management.cn.entity.Evaluating;
import com.management.cn.ying.service.EvalutingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class EvalutingController {
    @Resource
    private EvalutingService evalutingService;

    @RequestMapping("queryAllEvaluatingController")
    public String queryAllScore(Evaluating evaluating, @RequestParam(defaultValue="1",required=false)int pageNumber, @RequestParam(defaultValue="5",required=false)int pageSize, Model model){
        model.addAttribute("TeacherScore",this.evalutingService.queryAllTeacher(evaluating,pageNumber,pageSize));
        return "index";
    }



}
