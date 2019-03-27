package com.management.cn.chen.dao;

import com.management.cn.entity.Grade;

import java.util.List;

public interface LongIGradeDao {
    //查询学期  (S1..S2..Y2...)
    List<Grade> getGrade();
}
