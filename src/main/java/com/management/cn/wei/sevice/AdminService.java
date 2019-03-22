package com.management.cn.wei.sevice;

import com.management.cn.entity.Teacher;

public interface AdminService {

    Teacher login(String name,String pwd);
}
