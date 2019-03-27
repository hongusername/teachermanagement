package com.management.cn.yang.services.impl;

import com.management.cn.entity.*;
import com.management.cn.yang.dao.StudentDao;
import com.management.cn.yang.services.StudentServices;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServicesImpl implements StudentServices {

    @Resource
    private StudentDao studentDao;

    @Override
    public Classes queryStudentLogin(Integer grade) {
        return studentDao.queryStudentLogin(grade);
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
