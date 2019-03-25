package com.management.cn.teachermanagementproject;

import com.management.cn.yang.services.StudentServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class zzyTest {

    @Resource
    private StudentServices studentServices;

    @Test
    public void test(){
//        System.out.println(studentServices.queryStudentLogin("TCMP068","赵子洋"));
    }
    @Test
    public void test2(){
        System.out.println(studentServices.queryTeacherById("class_jy","TCMP067"));
    }

    @Test
    public void test3() {
        System.out.println(studentServices.querySurveyTypeById(1,2));
    }
}
