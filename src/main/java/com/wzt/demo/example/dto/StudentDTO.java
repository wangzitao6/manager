package com.wzt.demo.example.dto;


import java.io.Serializable;

/**
 * @author wangzitao
 * @create 2018-05-17 10:03
 **/
public class StudentDTO implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

    private String school;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Student{" + ", name=" + name + ", age=" + age + ", school=" + school + "}";
    }
}
