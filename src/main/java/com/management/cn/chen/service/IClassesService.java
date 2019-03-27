package com.management.cn.chen.service;

import com.management.cn.entity.Classes;

import java.util.List;

public interface IClassesService {

    List<Classes> getClasses();

    int addClass(Classes classes);

    int updClass(Classes classes);

    int delClass(int id);

    int updStatus(int status);

     List<Classes> selClassByTypeId(Integer typeId);

    //模糊查询
    List<Classes> selClassName(String key);
    List<Classes> selClassType(String key);
    List<Classes> selClassJy(String key);
    List<Classes> selClassBzr(String key);

}
