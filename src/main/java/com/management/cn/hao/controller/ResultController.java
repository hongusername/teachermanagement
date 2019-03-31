package com.management.cn.hao.controller;

import com.alibaba.fastjson.JSON;
import com.management.cn.chen.service.IClassesService;
import com.management.cn.dto.ResultDTO;
import com.management.cn.dto.TeacherDTO;
import com.management.cn.entity.*;
import com.management.cn.hao.service.EvaluatingService;
import com.management.cn.hao.service.GradeService;
import com.management.cn.hao.service.ResultService;
import com.management.cn.hong.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: GengHao
 * @date: 2019-03-22 22:11
 */
@Controller
@RequestMapping("/result")
public class ResultController {


    @Autowired
    private ResultService resultService;
    @Autowired
    private EvaluatingService evaluatingService;
    @Autowired
    private IClassesService classesService;
    @Autowired
    private TeacherDao teacherDao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @RequestMapping("/addResult")
    @ResponseBody
    public ResponseResult addResult(@RequestBody ResultDTO resultDTO, HttpServletRequest request) {
        ResponseResult responseResult = new ResponseResult();
        Classes classes = classesService.getClassByClassId(resultDTO.getClassId());
        Teacher teacher = teacherDao.queryTeacherById(resultDTO.getTeacherId());
        Evaluating evaluating = evaluatingService.getEvaluatingByTeacherTypeAndGradeId(teacher.getType(), classes.getClass_type());
        try {
            Long endTime = sdf.parse(evaluating.getEndTime()).getTime();
            if (endTime < System.currentTimeMillis()) {
                responseResult.setStatus(500);
                responseResult.setMessage("测评已停止");
                return responseResult;
            }
            responseResult = resultService.addResult(resultDTO);
            HttpSession session = request.getSession();
            //总分
            session.setAttribute("totalScore", responseResult.getData());
            //老师
            session.setAttribute("teacherName", teacherDao.queryTeacherById(resultDTO.getTeacherId()).getName());
            //班级
            session.setAttribute("classId", resultDTO.getClassId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseResult;
    }

    @RequestMapping("/detailResult")
    public String evaluatingDetail(Integer surveyTypeId, Integer classId, Integer teacherId, String date, Model model) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Result result = new Result();
        result.setSurveyType(surveyTypeId);
        result.setClassid(classId);
        result.setTeacherId(teacherId);
        try {
            result.setDate(sdf.format(sdf.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("data", resultService.queryIndividualOptionAVG(result));
        return "/admin/evaluating_detail_result";
    }

    @RequestMapping("/getResult")
    @ResponseBody
    public ResponseResult<List<TeacherDTO>> getResult(Integer classId, @DateTimeFormat(pattern = "yyyy-MM") Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        ResponseResult<List<TeacherDTO>> responseResult = resultService.queryResultByClassIdAndDate(classId, simpleDateFormat.format(date));
        return responseResult;
    }


    @RequestMapping("/getResult2")
    @ResponseBody
    public Map<String, Object> getResult2(Integer classId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Map<String, Object> map = new LinkedHashMap<>();
        //定义日期实例
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        for (int i = 1; i <= 5; i++) {
            String date = sdf.format(calendar.getTime());
            ResponseResult<List<TeacherDTO>> responseResult = resultService.queryResultByClassIdAndDate(classId, date);
            map.put(date, responseResult);
            calendar.add(Calendar.MONTH, -i);
        }
        return map;
    }


}
