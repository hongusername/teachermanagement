package com.management.cn.ying.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.management.cn.entity.Evaluating;
import com.management.cn.entity.Teacher;
import com.management.cn.ying.dao.EvaluatingDao;
import com.management.cn.ying.service.EvalutingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EvalutingServiceImpl implements EvalutingService {
    @Resource
    private EvaluatingDao evaluatingDao;

    @Override
    public PageInfo<Evaluating> queryAllTeacher(Evaluating evaluating, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<Evaluating> list = this.evaluatingDao.queryAllTeacher(evaluating);
        PageInfo<Evaluating> pi = new PageInfo<>(list);
        return pi;

    }

    @Override
    public Integer updatePassword(Teacher teacher) {
        return evaluatingDao.updatePassword(teacher);
    }
}
