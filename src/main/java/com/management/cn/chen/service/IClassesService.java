package com.management.cn.chen.service;

import com.management.cn.entity.Classes;

import java.util.List;

public interface IClassesService {

    List<Classes> getClasses();

    int addClass(Classes classes);

    int updClass(Classes classes);

    int delClass(int id);

}
