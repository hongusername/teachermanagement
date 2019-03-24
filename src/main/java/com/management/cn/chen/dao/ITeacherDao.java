package com.management.cn.chen.dao;

import com.management.cn.entity.Teacher;

import java.util.List;

public interface ITeacherDao {
    //教务登录
    Teacher getTeacher(String username);
    //查询教师
    List<Teacher> getTea(int type);
}
