package com.management.cn.chen.dao;

import com.management.cn.entity.Grade;
import java.util.List;

public interface LongIGradeDao {
    //查询学期  (S1..S2..Y2...)
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
