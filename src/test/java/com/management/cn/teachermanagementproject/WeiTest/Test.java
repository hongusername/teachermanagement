package com.management.cn.teachermanagementproject.WeiTest;

import com.management.cn.entity.Student;
import com.management.cn.entity.Teacher;
import com.management.cn.wei.dao.AdminDao;
import com.management.cn.wei.dao.StuDao;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {


    @Resource
    private StuDao stuDao;
    @Resource
    private AdminDao adminDao;

    @org.junit.Test
    public void testsadll() {
        for (Student student : stuDao.queryStuAll()) {
            System.out.println(student.getClasses().getClass_name());
        }

    }

    @org.junit.Test
    public void deta() {
//        for (Student student:stuDao.detailStu(2))
//        System.out.println(student.getClasses().getClass_name());
    }

    @org.junit.Test
    public void update() {
        System.out.println(stuDao.updateStu(new Student(13, "asd", "1232132", 1, null)));
    }

    @org.junit.Test
    public void query() {
        List<Teacher> teacher1=adminDao.queryType();
        for (Teacher teacher:teacher1) {
            System.out.println(teacher);
        }
    }
}
