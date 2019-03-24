package com.management.cn.yang.services;

import com.management.cn.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentServices {
    Student queryStudentLogin(String grade, String name);
    Teacher queryTeacherById(@Param("key") String key, @Param("grade") String grade);
    Classes queryClassesById(@Param("id")Integer id);
    SurveyType querySurveyTypeById(@Param("teacherid") Integer teacherid, @Param("classesid") Integer classesid);

}
