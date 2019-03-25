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
    Classes getClassByClassId(Integer classId);

    //模糊查询
    List<Classes> selClassName(String key);
    List<Classes> selClassType(String key);
    List<Classes> selClassJy(String key);
    List<Classes> selClassBzr(String key);


}
