package com.management.cn.hong.dao;

import com.management.cn.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao {
    //得到所有的老师
    List<Teacher> getAll(Teacher teacher);
    //得到某个老师的信息
    Teacher queryTeacherById(Integer id);
    //修改老师的信息
    Integer updateTeacher(Teacher teacher);
    //删除老师的信息
    Integer deleteTeacher(Integer id);

    Integer insertTeacher(Teacher teacher);


}
