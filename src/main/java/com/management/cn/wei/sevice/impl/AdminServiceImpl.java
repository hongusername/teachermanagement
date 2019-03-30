package com.management.cn.wei.sevice.impl;


import com.management.cn.entity.Role;
import com.management.cn.entity.Teacher;
import com.management.cn.wei.dao.AdminDao;
import com.management.cn.wei.sevice.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;


    @Override
    public Teacher login(String name,String pwd) {
        return adminDao.login(name,pwd);
    }

    @Override
    public List<Teacher> queryType() {
        return adminDao.queryType();
    }

    @Override
    public Integer updatePwd( Role role) {
        return adminDao.updatePwd(role);
    }
}
