package com.management.cn.wei.dao;

import com.management.cn.entity.Classes;
import com.management.cn.entity.Student;

import java.util.List;

public interface StuDao {

    List<Student> queryStuAll();

    Integer updateStu(Student student);

    Integer addStu(Student student);

    Integer delStu(Integer stu_id);

    Student detailStu(Integer stu_id);

    List<Classes> queryAllClasses();

    void batchDeletes(List delList);


}
