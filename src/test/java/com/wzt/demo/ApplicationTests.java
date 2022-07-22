package com.wzt.demo;

import com.wzt.demo.example.entity.Student;
import com.wzt.demo.example.service.StudentService;
import com.wzt.demo.thirdpart.oss.OSSApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    StudentService studentService;

    @Autowired
    OSSApi ossApi;

    @Test
    public void contextLoads() {
    }

    @Test
    public void selectStudentById() {

    }

    @Test
    public void addUser() {
        Student student=new Student();
        String name ="姓名";
        String school ="学校";
        for (int i=0;i<5;i++){
            student.setAge(i);
            student.setName(name+i);
            student.setSchool(school+i);
            studentService.addUser(student);
        }

    }

}
