package com.management.cn.ying.dao;

import com.management.cn.entity.Evaluating;
import com.management.cn.entity.Teacher;

import java.util.List;

public interface EvaluatingDao {
    List<Evaluating> queryAllTeacher(Evaluating evaluating);
    Integer updatePassword(Teacher teacher);
}
