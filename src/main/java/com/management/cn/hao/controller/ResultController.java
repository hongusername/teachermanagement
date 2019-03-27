package com.management.cn.hao.controller;

import com.alibaba.fastjson.JSON;
import com.management.cn.dto.ResultDTO;
import com.management.cn.dto.SurveyContentDTO;
import com.management.cn.dto.TeacherDTO;
import com.management.cn.entity.ResponseResult;
import com.management.cn.entity.Result;
import com.management.cn.hao.service.ResultService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/addResult")
    @ResponseBody
    public Map<String, Object> addResult(@RequestBody ResultDTO resultDTO) {
        Map<String, Object> map = new HashMap<>();
        resultService.addResult(resultDTO);
        map.put("success", true);
        map.put("message", "提交成功！");
        return map;
    }

    @RequestMapping("/detailResult")
    public String evaluatingDetail(Integer surveyTypeId, Integer classId, Integer teacherTypeId, String date, Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Result result = new Result();
        result.setSurveyType(surveyTypeId);
        result.setClassid(classId);
        result.setTeacherType(teacherTypeId);
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
        Map<String, Object> map = new HashMap<>();
        //定义日期实例

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        for (int i = 1; i <= 3; i++) {
            String date = sdf.format(calendar.getTime());
            ResponseResult<List<TeacherDTO>> responseResult = resultService.queryResultByClassIdAndDate(classId, date);
            map.put(date, responseResult);
            calendar.add(Calendar.MONTH, -i);
        }
        return map;
    }


}
