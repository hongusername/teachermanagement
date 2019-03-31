package com.management.cn.yang.controller;

import com.management.cn.chen.service.IClassesService;
import com.management.cn.entity.*;
import com.management.cn.hao.controller.BaseController;
import com.management.cn.hao.dao.EvaluatingMapper;
import com.management.cn.hao.service.EvaluatingService;
import com.management.cn.hao.service.GradeService;
import com.management.cn.yang.services.StudentServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController extends BaseController {

    private static final String CLASS_JY = "class_jy";
    private static final String CLASS_BZR = "class_bzr";

    @Resource
    private StudentServices studentServices;

    @Resource
    private IClassesService classesService;

    @Resource
    private EvaluatingService evaluatingService;

    @Resource
    private GradeService gradeService;

    @RequestMapping("/user/login")
    public String login(Model model) {
        model.addAttribute("classes", classesService.getClasses());
        return "yang_login";
    }

    @RequestMapping("/toEvaluation")
    public String toEvaluation(Model model, Integer grade) {
        Classes student = studentServices.queryStudentLogin(grade);
        if (student != null) {
            model.addAttribute("grade", student);
            return "yang_toEvaluation";
        } else {
            model.addAttribute("classes", classesService.getClasses());
            return "yang_login";
        }
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @RequestMapping("/isEvaluation")
    @ResponseBody
    public ResponseResult isEvaluation(String key, String classId) {
        ResponseResult responseResult = new ResponseResult();
        Teacher teacher = studentServices.queryTeacherById(key, classId);
        Classes classes = studentServices.queryClassesById(Integer.parseInt(classId));

        List<Evaluating> evaluatingList = evaluatingService.getEvaluatingByTeacherTypeAndGradeId(teacher.getType(), classes.getClass_type());
        Evaluating evaluating = getOneEvaluating(evaluatingList, classes.getClass_name());
        if (evaluating == null) {
            responseResult.setStatus(500);
            responseResult.setMessage("无需评测！");
            return responseResult;
        }
        try {
            Long startTime = sdf.parse(evaluating.getStartTime()).getTime();
            Long endTime = sdf.parse(evaluating.getEndTime()).getTime();
            //开始时间大于当前时间
            if (startTime > System.currentTimeMillis()) {
                responseResult.setStatus(500);
                responseResult.setMessage("评测还未开始！");
                return responseResult;
            }
            //结束时间小于当前时间
            if (endTime < System.currentTimeMillis()) {
                responseResult.setStatus(500);
                responseResult.setMessage("评测已结束！");
                return responseResult;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        responseResult.setStatus(200);
        return responseResult;
    }

    @RequestMapping("/evaluation")
    public String Evaluation(Model model, String key, String grade) {
        if ("".equals(key) || key == null || "".equals(grade) || grade == null) {
            return "redirect:/toEvaluation";
        } else if (!CLASS_JY.equals(key) && !CLASS_BZR.equals(key)) {
            return "redirect:/toEvaluation";
        } else {
            Teacher teacher = studentServices.queryTeacherById(key, grade);
            if (teacher == null) {
                return "redirect:/toEvaluation";
            }
            Classes classes = studentServices.queryClassesById(Integer.parseInt(grade));
            if (classes == null) {
                return "redirect:/toEvaluation";
            }
            Integer surveyTypeId = 0;

            SurveyType surveyType = studentServices.querySurveyTypeById(teacher.getType(), classes.getClass_type());
            if (surveyType == null) {
                return "redirect:/toEvaluation";
            }
            surveyTypeId = surveyType.getId();

            List<Evaluating> evaluatingList = evaluatingService.getEvaluatingByTeacherTypeAndGradeId(teacher.getType(), classes.getClass_type());

            Evaluating evaluating = getOneEvaluating(evaluatingList, classes.getClass_name());
            if (evaluating == null) {
                return "redirect:/toEvaluation";
            }

            try {
                if (sdf.parse(evaluating.getEndTime()).getTime() < System.currentTimeMillis()) {
                    return "redirect:/toEvaluation";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String remainingTime = remainingTime(evaluating.getEndTime());
            model.addAttribute("teacher", teacher);
            model.addAttribute("grade", classes);
            model.addAttribute("surveyTypeId", surveyTypeId);
            model.addAttribute("date", new Date());
            model.addAttribute("remainingTime", remainingTime);
        }
        return "evaluating";
    }


    public String remainingTime(String end) {
        Long currentTime = System.currentTimeMillis();
        Long endTime = null;
        try {
            endTime = sdf.parse(sdf.format(sdf.parse(end))).getTime();
            currentTime = sdf.parse(sdf.format(currentTime)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endTime - currentTime;
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return hour + ":" + min + ":" + sec;
    }
}
