package com.management.cn.ying.service;

import com.github.pagehelper.PageInfo;
import com.management.cn.entity.Evaluating;

public interface EvalutingService {
   PageInfo<Evaluating> queryAllTeacher(Evaluating evaluating, int pageNumber, int pageSize);
}