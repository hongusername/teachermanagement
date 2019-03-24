package com.management.cn.chen.service.impl;

import com.management.cn.chen.dao.IClassesDao;
import com.management.cn.chen.service.IClassesService;
import com.management.cn.entity.Classes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassesServiceImpl implements IClassesService {

    @Resource
    private IClassesDao iClassesDao;


    @Override
    public List<Classes> getClasses() {
        return iClassesDao.getClasses();
    }
}
