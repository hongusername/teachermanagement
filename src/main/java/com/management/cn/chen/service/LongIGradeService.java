package com.management.cn.chen.service;

import com.management.cn.entity.Grade;

import java.util.List;

public interface LongIGradeService {
    List<Grade> getGrade();
    //增加
    Integer addGrade(Grade grade);
    //删除
    Integer deleteGrade(int id);
    //修改
    Integer updateGrade(Grade grade);
    //根据id查询
    Grade queryGradeById(int id);
}
