package com.management.cn.hao.controller;

import com.alibaba.fastjson.JSON;
import com.management.cn.dto.ResultDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: GengHao
 * @date: 2019-03-22 22:11
 */
@RestController
@RequestMapping("/result")
public class ResultController {

    @RequestMapping("/addResult")
    public Map<String, Object> addResult(@RequestBody ResultDTO resultDTO) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(JSON.toJSON(resultDTO));
        map.put("success", true);
        map.put("message", "提交成功！");
        return map;
    }
}
