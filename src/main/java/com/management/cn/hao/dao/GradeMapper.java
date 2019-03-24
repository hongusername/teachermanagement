package com.management.cn.hao.dao;

import com.management.cn.entity.Grade;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-22 14:17
 */
@Component
public interface GradeMapper {

    List<Grade> selectGradeList();

    Grade selectGradeBId(Integer id);
}
