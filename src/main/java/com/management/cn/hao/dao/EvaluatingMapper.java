package com.management.cn.hao.dao;

import com.management.cn.entity.Evaluating;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-27 15:57
 */
@Component
public interface EvaluatingMapper {

    List<Evaluating> selectEvaluatingList();

    Evaluating selectEvaluatingById(@Param("id") Integer id);

    Evaluating selectEvaluatingByTeacherTypeAndGradeId(@Param("teacherType") Integer teacherType,@Param("gradeId") Integer gradeId);

    boolean insertEvaluating(Evaluating evaluating);

    int deleteEvaluating(Integer id);

    int updateEvaluating(Evaluating evaluating);

    boolean insertEvaluatingClassName(@Param("className") String className, @Param("evaluatingId") Integer evaluatingId);

    int deleteEvaluatingClassName(@Param("className") String className, @Param("evaluatingId") Integer evaluatingId);

    int deleteAllEvaluatingClassName(@Param("evaluatingId") Integer evaluatingId);

    List<String> selectEvaluatingClassName(@Param("evaluatingId") Integer evaluatingId);
}
