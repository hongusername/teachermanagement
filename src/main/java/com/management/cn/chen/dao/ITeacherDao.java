package com.management.cn.chen.dao;

import com.management.cn.entity.Role;
import com.management.cn.entity.Teacher;

import java.util.List;

public interface ITeacherDao {
    //权限登录
    Role getRole( String name);
    //查询教师
    List<Teacher> getTea(int type);

}
