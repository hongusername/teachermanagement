package com.management.cn.chen.dao;

import com.management.cn.entity.Classes;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IClassesDao {

    List<Classes>  getClasses();

    int addClass(Classes classes);

    Classes getClassByClassId(Integer classId);
}
