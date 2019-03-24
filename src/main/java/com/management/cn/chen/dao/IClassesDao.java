package com.management.cn.chen.dao;

import com.management.cn.entity.Classes;

import java.util.List;

public interface IClassesDao {

    List<Classes>  getClasses();

    int addClass(Classes classes);
}
