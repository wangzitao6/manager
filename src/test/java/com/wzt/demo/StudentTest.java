package com.wzt.demo;

import com.alibaba.fastjson.JSON;
import com.wzt.demo.example.entity.Student;
import com.wzt.demo.example.service.StudentService;
import com.wzt.demo.example.service.impl.StudentServiceImpl;
import com.wzt.demo.example.vo.StudentQueryVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author wangzitao
 * @create 2018-05-17 20:32
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {
    @Autowired
    StudentService studentService;

    @Test
    public void addUser() {
        Student student=new Student();
        String name ="姓名11";
        String school ="学校1";
        student.setAge(18);
        student.setName(name);
        student.setSchool(school);
        studentService.addUser(student);
    }

    @Test
    public void selectStudentById() {
        StudentQueryVO vo=new StudentQueryVO();
        vo.setId(1);
        Student student =  studentService.selectStudentById(vo);
        System.out.println("Student:"+ JSON.toJSONString(student));
    }

    @Test
    public void findAllUser() {
        List<Student> students =  studentService.findAllUser();
        System.out.println(students.size());
    }
}
