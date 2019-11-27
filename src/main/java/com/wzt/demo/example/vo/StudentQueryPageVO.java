package com.wzt.demo.example.vo;

import com.wzt.demo.bean.PageQueryParam;

import javax.validation.constraints.NotNull;

/**
 * @author wangzitao
 * @create 2018-05-18 14:30
 **/

public class StudentQueryPageVO extends PageQueryParam {


    private String name;

    private Integer age;

    @NotNull(message = "学校不能为空")
    private String school;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
