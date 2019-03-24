package com.management.cn.yang.dao;

import com.management.cn.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    Student queryStudentLogin(@Param("grade") String grade,@Param("name") String name);

    Teacher queryTeacherById(@Param("key") String key, @Param("grade") String grade);
    Classes queryClassesById(@Param("id")Integer id);

    SurveyType querySurveyTypeById(@Param("teacherid") Integer teacherid,@Param("classesid") Integer classesid);




}
