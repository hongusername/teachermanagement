package com.management.cn.yang.services;

import com.management.cn.entity.Classes;
import com.management.cn.entity.Student;
import com.management.cn.entity.Teacher;
import org.apache.ibatis.annotations.Param;

public interface StudentServices {
    Student queryStudentLogin(String grade, String name);
    Teacher queryTeacherById(@Param("key") String key, @Param("grade") String grade);
    Classes queryClassesById(@Param("id")Integer id);
}
