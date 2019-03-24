package com.management.cn.chen.dao;

import com.management.cn.entity.Classes;

import java.util.List;

public interface IClassesDao {

    //查班级
    List<Classes>  getClasses();
    //添加班级
    int addClass(Classes classes);
    //修改班级
    int updClass(Classes classes);
    //删除班级
    int delClass(int id);
}
