package com.management.cn.ying.service;

import com.github.pagehelper.PageInfo;
import com.management.cn.entity.Evaluating;
import com.management.cn.entity.Teacher;

public interface EvalutingService {
   PageInfo<Evaluating> queryAllTeacher(Evaluating evaluating, int pageNumber, int pageSize);

}
