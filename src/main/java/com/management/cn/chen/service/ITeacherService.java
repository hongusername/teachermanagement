package com.management.cn.chen.service;


import com.management.cn.entity.Role;
import com.management.cn.entity.Teacher;

import java.util.List;

public interface ITeacherService {

    Role getRole( String name);


    List<Teacher> getTea(int type);


}
