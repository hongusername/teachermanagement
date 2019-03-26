package com.management.cn.wei.dao;

import com.management.cn.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {

        Teacher login( @Param("name") String name,@Param("pwd") String pwd);

        //查询teacher type
        List<Teacher> queryType();

}
