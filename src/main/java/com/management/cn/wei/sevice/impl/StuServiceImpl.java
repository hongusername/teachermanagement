package com.management.cn.wei.sevice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.management.cn.entity.Classes;
import com.management.cn.entity.Student;
import com.management.cn.wei.dao.StuDao;
import com.management.cn.wei.sevice.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Resource
    private StuDao stuDao;


    @Override
    public PageInfo<Student> queryStuAll( Integer pageNum, Integer pageSize ) {
        PageHelper.startPage(pageNum,pageSize);
        List<Student> list=stuDao.queryStuAll();
        PageInfo<Student> page=new PageInfo<>(list);
        return page;
    }

    public Integer updateStu( Student student ) {
        return stuDao.updateStu(student);
    }

    @Override
    public Integer addStu( Student student ) {
        return stuDao.addStu(student);
    }

    @Override
    public Integer delStu( Integer stu_id ) {
        return stuDao.delStu(stu_id);
    }

    @Override
    public List<Student> detailStu( Integer stu_id ) {
        return stuDao.detailStu(stu_id);
    }

    @Override
    public List<Classes> queryAllClasses() {
        return stuDao.queryAllClasses();
    }
}
