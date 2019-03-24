package com.management.cn.wei.sevice;

import com.github.pagehelper.PageInfo;
import com.management.cn.entity.Classes;
import com.management.cn.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface StuService {

    PageInfo<Student> queryStuAll(Integer pageNum,Integer pageSize);

    Integer updateStu(Student student);

    Integer addStu(Student student);

    Integer delStu(Integer stu_id);

    Student detailStu(Integer stu_id);

    List<Classes> queryAllClasses();


    void batchDeletes(List delList);


}
