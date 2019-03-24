package com.management.cn.hao.service.impl;

import com.management.cn.entity.Grade;
import com.management.cn.hao.dao.GradeMapper;
import com.management.cn.hao.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-22 14:21
 */
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;
    @Override
    public List<Grade> getGradeList() {
        return gradeMapper.selectGradeList();
    }

    @Override
    public Grade getGradeBId(Integer id) {
        return gradeMapper.selectGradeBId(id);
    }
}
