package com.management.cn.hong.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.management.cn.entity.Teacher;
import com.management.cn.hong.dao.TeacherDao;
import com.management.cn.hong.services.TeacherServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServicesImpl implements TeacherServices {
    @Resource
    private TeacherDao teacherDao;

    @Override
    public PageInfo<Teacher> getAll(Teacher teacher, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Teacher> list = teacherDao.getAll(teacher);
        PageInfo<Teacher> page = new PageInfo<Teacher>();
        return page;
    }

    @Override
    public Teacher queryTeacherById(Integer id) {
        return teacherDao.queryTeacherById(id);
    }

    @Override
    public Integer updateTeacher(Teacher teacher) {
        return teacherDao.updateTeacher(teacher);
    }

    @Override
    public Integer deleteTeacher(Integer id) {
        return teacherDao.deleteTeacher(id);
    }

    @Override
    public Integer insertTeacher(Teacher teacher) {
        return null;
    }
}
