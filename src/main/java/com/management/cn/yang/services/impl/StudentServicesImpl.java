package com.management.cn.yang.services.impl;

import com.management.cn.entity.Classes;
import com.management.cn.entity.Student;
import com.management.cn.entity.SurveyType;
import com.management.cn.entity.Teacher;
import com.management.cn.yang.dao.StudentDao;
import com.management.cn.yang.services.StudentServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServicesImpl implements StudentServices {

    @Resource
    private StudentDao studentDao;

    @Override
    public Student queryStudentLogin(String grade,String name) {
        return studentDao.queryStudentLogin(grade,name);
    }

    @Override
    public Teacher queryTeacherById(String key, String grade) {
        return studentDao.queryTeacherById(key,grade);
    }

    @Override
    public Classes queryClassesById(Integer id) {
        return studentDao.queryClassesById(id);
    }

    @Override
    public SurveyType querySurveyTypeById(Integer teacherid, Integer classesid) {
        return studentDao.querySurveyTypeById(teacherid,classesid);
    }
}
