package com.management.cn.hao.controller;

import com.alibaba.fastjson.JSON;
import com.management.cn.dto.ResultDTO;
import com.management.cn.dto.SurveyTypeDTO;
import com.management.cn.entity.OptionScore;
import com.management.cn.entity.Result;
import com.management.cn.hao.service.ResultService;
import com.management.cn.hao.service.SurveyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
}
