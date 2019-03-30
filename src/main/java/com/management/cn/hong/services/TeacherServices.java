package com.management.cn.hong.services;

import com.github.pagehelper.PageInfo;
import com.management.cn.entity.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherServices {

    //得到所有的老师
    PageInfo<Teacher> getAll(Teacher teacher,Integer pageNumber,Integer pageSize);
    //得到某个老师的信息
    Teacher queryTeacherById(Integer id);
    //修改老师的信息
    Integer updateTeacher(Teacher teacher);
    //删除老师的信息
    Integer deleteTeacher(Integer id);

    Integer insertTeacher(Teacher teacher);

    //批量删除
    Integer deleteTeachers(Integer[] teacherids);

}
