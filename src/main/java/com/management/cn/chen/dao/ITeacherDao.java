package com.management.cn.chen.dao;

import com.management.cn.entity.Teacher;

import java.util.List;

public interface ITeacherDao {

    Teacher getTeacher(String username);

    List<Teacher> getTea(int type);
}
