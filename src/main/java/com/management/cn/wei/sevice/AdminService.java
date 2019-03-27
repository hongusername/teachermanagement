package com.management.cn.wei.sevice;

import com.management.cn.entity.Teacher;

import java.util.List;

public interface AdminService {

    Teacher login(String name,String pwd);

    //查询teacher type
    List<Teacher> queryType();

}
