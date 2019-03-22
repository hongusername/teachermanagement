package com.management.cn.yang.dao;

import com.management.cn.entity.Classes;
import com.management.cn.entity.Student;
import com.management.cn.entity.SurveyType;
import com.management.cn.entity.Teacher;
import org.apache.ibatis.annotations.Param;

public interface StudentDao {
    Student queryStudentLogin(@Param("grade") String grade,@Param("name") String name);

    Teacher queryTeacherById(@Param("key") String key, @Param("grade") String grade);
    Classes queryClassesById(@Param("id")Integer id);

    SurveyType querySurveyTypeById(@Param("teacherid") Integer teacherid,@Param("classesid") Integer classesid);


}
