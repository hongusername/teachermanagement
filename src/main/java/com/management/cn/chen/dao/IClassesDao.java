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

    //修改考核状态(1:以考核,2:未考核)
    int updStatus(int status);

    //
    List<Classes> selClassByTypeId(Integer typeId);

    //模糊查询
    List<Classes> selClassName(String key);
    List<Classes> selClassType(String key);
    List<Classes> selClassJy(String key);
    List<Classes> selClassBzr(String key);


}
