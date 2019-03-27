package com.management.cn.chen.service;


import com.management.cn.entity.Teacher;

import java.util.List;

public interface ITeacherService {

    Teacher getTeacher(String name);


    List<Teacher> getTea(int type);


    Integer updatePassword(Teacher teacher);

}
