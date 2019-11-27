package com.wzt.demo.example.ctrl;

import com.wzt.demo.bean.Page;
import com.wzt.demo.bean.ReqParamBody;
import com.wzt.demo.bean.WebResult;
import com.wzt.demo.example.dto.StudentDTO;
import com.wzt.demo.example.entity.Student;
import com.wzt.demo.example.service.StudentService;
import com.wzt.demo.example.vo.StudentQueryPageVO;
import com.wzt.demo.example.vo.StudentQueryVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wangzitao
 * @create 2018-05-17 10:25
 **/
@RestController
@RequestMapping("/student")
public class StudentController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    StudentService studentService;

    @ApiOperation(value="获取学生", notes="根据id获取学生")
    @PostMapping("/get")
    public WebResult selectStudentById(@Valid @RequestBody ReqParamBody<StudentQueryVO> paramBody) {
        Student student=studentService.selectStudentById(paramBody.getData());
        return WebResult.ok(student);
    }

    @ApiOperation(value="获取学生", notes="根据id获取学生")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public WebResult getStudentById(@Valid @RequestBody ReqParamBody<StudentQueryVO> paramBody) {
        Student student=studentService.selectStudentById(paramBody.getData());
        return WebResult.ok(student);
    }
    @PostMapping("/page")
    public WebResult findAllUser(@Valid @RequestBody ReqParamBody<StudentQueryPageVO> paramBody) {
        Page<StudentDTO> list= studentService.pageUser(paramBody.getData());
        return WebResult.ok(list);
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public WebResult addUser(Student student) {
        studentService.addUser(student);
        return WebResult.ok();
    }

    @RequestMapping(value = {"/callBack1"}, method = RequestMethod.POST)
    public void CallBack1(@Valid @RequestBody String data) {
        studentService.CallBack(data);
    }
    @RequestMapping(value = {"/callBack2"}, method = RequestMethod.POST)
    public WebResult CallBack2(@Valid @RequestBody String data) {
        studentService.CallBack(data);
        return WebResult.fail("500","jiu bu gao su ni");
    }
    @RequestMapping(value = {"/callBack3"}, method = RequestMethod.POST)
    public WebResult CallBack3(@Valid @RequestBody String data) {
        return WebResult.ok(studentService.CallBack(data));
    }


    @RequestMapping(value = {"/payCallBack1"}, method = RequestMethod.POST)
    public void payCallBack1(@Valid @RequestBody String data) {
        studentService.payCallBack(data);
    }
    @RequestMapping(value = {"/payCallBack2"}, method = RequestMethod.POST)
    public WebResult payCallBack2(@Valid @RequestBody String data) {
        studentService.payCallBack(data);
        return WebResult.fail("500","jiu bu gao su ni");
    }
    @RequestMapping(value = {"/payCallBack3"}, method = RequestMethod.POST)
    public WebResult payCallBack3(@Valid @RequestBody String data) {
        return WebResult.ok(studentService.payCallBack(data));
    }
}
