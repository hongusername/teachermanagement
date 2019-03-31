package com.management.cn.hao.service.impl;

import com.management.cn.entity.Evaluating;
import com.management.cn.hao.dao.EvaluatingMapper;
import com.management.cn.hao.service.EvaluatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-27 16:13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EvaluatingServiceImpl implements EvaluatingService {

    @Autowired
    private EvaluatingMapper evaluatingMapper;

    @Override
    public List<Evaluating> getEvaluatingList() {
        return evaluatingMapper.selectEvaluatingList();
    }

    @Override
    public boolean addEvaluating(Evaluating evaluating) {
        try {
            evaluatingMapper.insertEvaluating(evaluating);

            evaluating.getClassNameList().forEach(item ->
                    evaluatingMapper.insertEvaluatingClassName(item, evaluating.getId())
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public int deleteEvaluating(Integer id) {
        try {
            evaluatingMapper.deleteEvaluating(id);
            evaluatingMapper.deleteAllEvaluatingClassName(id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int updateEvaluating(Evaluating evaluating) {
        try {
            evaluatingMapper.updateEvaluating(evaluating);
            evaluatingMapper.deleteAllEvaluatingClassName(evaluating.getId());
            evaluating.getClassNameList().forEach(item ->
                    evaluatingMapper.insertEvaluatingClassName(item, evaluating.getId())
            );
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean addEvaluatingClassName(String className, Integer evaluatingId) {
        return evaluatingMapper.insertEvaluatingClassName(className, evaluatingId);
    }

    @Override
    public int deleteEvaluatingClassName(String className, Integer evaluatingId) {
        return evaluatingMapper.deleteEvaluatingClassName(className, evaluatingId);
    }

    @Override
    public Evaluating getEvaluatingById(Integer id) {
        return evaluatingMapper.selectEvaluatingById(id);
    }

    @Override
    public List<Evaluating> getEvaluatingByTeacherTypeAndGradeId(Integer teacherType, Integer gradeId) {
        return evaluatingMapper.selectEvaluatingByTeacherTypeAndGradeId(teacherType, gradeId);
    }
}
