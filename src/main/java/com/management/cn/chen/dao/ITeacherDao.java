package com.management.cn.chen.dao;

import com.management.cn.entity.Teacher;

import java.util.List;

public interface ITeacherDao {
    //教务登录
    Teacher getTeacher(String name);
    //查询教师
    List<Teacher> getTea(int type);
    //修改密码
    Integer updatePassword(Teacher teacher);
}
