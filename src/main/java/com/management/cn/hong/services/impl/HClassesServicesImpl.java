package com.management.cn.hong.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.management.cn.chen.dao.IClassesDao;
import com.management.cn.entity.Classes;
import com.management.cn.hong.services.HClassesServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HClassesServicesImpl implements HClassesServices {
    @Resource
    private IClassesDao iClassesDao;

    @Override
    public PageInfo<Classes> getAllClasses(Integer PageNum, Integer PageSize) {
        PageHelper.startPage(PageNum,PageSize);
        List<Classes> list = iClassesDao.getClasses();
        PageInfo<Classes> page=  new PageInfo<>(list);
        return page;
    }
}
