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

    @Override
    public int addClass(Classes classes) {
        return iClassesDao.addClass(classes);
    }

    @Override
    public int updClass(Classes classes) {
        return iClassesDao.updClass(classes);
    }

    @Override
    public int delClass(int id) {
        return iClassesDao.delClass(id);
    }

    @Override
    public int updStatus(int status) {
        return iClassesDao.updStatus(status);
    }

    @Override
    public List<Classes> selClassByTypeId(Integer typeId) {
        return iClassesDao.selClassByTypeId(typeId);
    }

    @Override
    public List<Classes> selClassName(String key) {
        return iClassesDao.selClassName(key);
    }

    @Override
    public List<Classes> selClassType(String key) {
        return iClassesDao.selClassType(key);
    }

    @Override
    public List<Classes> selClassJy(String key) {
        return iClassesDao.selClassJy(key);
    }

    @Override
    public List<Classes> selClassBzr(String key) {
        return iClassesDao.selClassBzr(key);
    }
}
