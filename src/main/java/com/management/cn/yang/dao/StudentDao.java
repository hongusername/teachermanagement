package com.management.cn.yang.dao;

import com.management.cn.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    Classes queryStudentLogin(@Param("grade") Integer grade);

    Teacher queryTeacherById(@Param("key") String key, @Param("grade") String grade);
    Classes queryClassesById(@Param("id")Integer id);

    SurveyType querySurveyTypeById(@Param("teacherid") Integer teacherid,@Param("classesid") Integer classesid);


    List<Result> queryResultAll(Result result);


}
