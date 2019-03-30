package com.management.cn.hong.services;

import com.github.pagehelper.PageInfo;
import com.management.cn.entity.Classes;

public interface HClassesServices {

    PageInfo<Classes> getAllClasses(Integer PageNum,Integer PageSize);
}
