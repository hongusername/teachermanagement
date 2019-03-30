package com.management.cn.chen.service.impl;

import com.management.cn.chen.dao.ITeacherDao;
import com.management.cn.chen.service.ITeacherService;
import com.management.cn.entity.Role;
import com.management.cn.entity.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService{

    @Resource
    private ITeacherDao iTeacherDao;


    @Override
    public Role getRole( String name ) {
        return iTeacherDao.getRole(name);
    }

    @Override
    public List<Teacher> getTea(int type) {
        return iTeacherDao.getTea(type);
    }


}
