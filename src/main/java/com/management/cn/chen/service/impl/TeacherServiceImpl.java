package com.management.cn.chen.service.impl;

import com.management.cn.chen.dao.ITeacherDao;
import com.management.cn.chen.service.ITeacherService;
import com.management.cn.entity.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Resource
    private ITeacherDao iTeacherDao;

    @Override
    public Teacher getTeacher(String username) {
            return iTeacherDao.getTeacher(username);
        }

    @Override
    public List<Teacher> getTea(int type) {
        return iTeacherDao.getTea(type);
    }

    @Override
    public Integer updatePassword(Teacher teacher) {
        return iTeacherDao.updatePassword(teacher);
    }
}
