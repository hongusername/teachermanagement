package com.management.cn.hao.controller;

import com.management.cn.entity.Grade;
import com.management.cn.hao.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-22 14:23
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @RequestMapping("/getGradeList")
    public List<Grade> getGradeList(){
        return gradeService.getGradeList();
    }
}
