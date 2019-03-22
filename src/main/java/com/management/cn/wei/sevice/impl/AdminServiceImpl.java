package com.management.cn.wei.sevice.impl;


import com.management.cn.entity.Teacher;
import com.management.cn.wei.dao.AdminDao;
import com.management.cn.wei.sevice.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;


    @Override
    public Teacher login(String name,String pwd) {
        return adminDao.login(name,pwd);
    }
}
