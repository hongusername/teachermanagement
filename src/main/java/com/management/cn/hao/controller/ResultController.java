package com.management.cn.hao.controller;

import com.management.cn.dto.ResultDTO;
import com.management.cn.dto.TeacherDTO;
import com.management.cn.hao.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: GengHao
 * @date: 2019-03-22 22:11
 */
@RestController
@RequestMapping("/result")
public class ResultController {


    @Autowired
    private ResultService resultService;

    @RequestMapping("/addResult")
    public Map<String, Object> addResult(@RequestBody ResultDTO resultDTO) {
        Map<String, Object> map = new HashMap<>();
        resultService.addResult(resultDTO);
        map.put("success", true);
        map.put("message", "提交成功！");
        return map;
    }

    @RequestMapping("/getResult")
    public List<TeacherDTO> getResult(Integer classId, @DateTimeFormat(pattern = "yyyy-MM") Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        List<TeacherDTO> teacherDTOList = resultService.queryResultByClassIdAndDate(classId, simpleDateFormat.format(date));


        return teacherDTOList;
    }
}
