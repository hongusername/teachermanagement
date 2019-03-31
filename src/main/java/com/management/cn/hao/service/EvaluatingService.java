package com.management.cn.hao.service;

import com.management.cn.entity.Evaluating;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-27 16:12
 */
public interface EvaluatingService {
    List<Evaluating> getEvaluatingList();

    boolean addEvaluating(Evaluating evaluating);

    int deleteEvaluating(Integer id);

    int updateEvaluating(Evaluating evaluating);

    boolean addEvaluatingClassName(String className, Integer evaluatingId);

    int deleteEvaluatingClassName(String className, Integer evaluatingId);

    Evaluating getEvaluatingById(Integer id);

    List<Evaluating> getEvaluatingByTeacherTypeAndGradeId(Integer teacherType, Integer gradeId);

}
