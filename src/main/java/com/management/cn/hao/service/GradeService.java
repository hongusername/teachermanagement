package com.management.cn.hao.service;

import com.management.cn.entity.Grade;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-22 14:21
 */
public interface GradeService {
    List<Grade> getGradeList();
    Grade getGradeBId(Integer id);
}
