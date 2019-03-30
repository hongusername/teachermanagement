package com.management.cn.chen.service.impl;

import com.management.cn.chen.dao.LongIGradeDao;
import com.management.cn.chen.service.LongIGradeService;
import com.management.cn.entity.Grade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class LongGradeServiceImpl implements LongIGradeService {

    @Resource
    private LongIGradeDao iGradeDao;


    @Override
    public List<Grade> getGrade() {
        return iGradeDao.getGrade();
    }

    @Override
    public Integer addGrade(Grade grade) {
        return iGradeDao.addGrade(grade);
    }

    @Override
    public Integer deleteGrade(int id) {
        return iGradeDao.deleteGrade(id);
    }

    @Override
    public Integer updateGrade(Grade grade) {
        return iGradeDao.updateGrade(grade);
    }

    @Override
    public Grade queryGradeById(int id) {
        return iGradeDao.queryGradeById(id);
    }
}
