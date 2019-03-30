package com.management.cn.chen.service;


import com.management.cn.entity.Teacher;

import java.util.List;

public interface ITeacherService {

    Teacher getTeacher(String username);


    List<Teacher> getTea(int type);


}
