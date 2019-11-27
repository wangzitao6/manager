package com.wzt.demo.example.service;

import com.wzt.demo.bean.Page;
import com.wzt.demo.example.dto.StudentDTO;
import com.wzt.demo.example.entity.Student;
import com.wzt.demo.example.vo.StudentQueryPageVO;
import com.wzt.demo.example.vo.StudentQueryVO;

import java.util.List;

/**
 * @author wangzitao
 * @create 2018-05-17 10:22
 **/

public interface StudentService {

    void addUser(Student student);

    Student selectStudentById(StudentQueryVO vo);

    List<Student> findAllUser();

    Page<StudentDTO> pageUser(StudentQueryPageVO vo);

    String CallBack(String json);

    String payCallBack(String json);

}
