package com.wzt.demo.example.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.wzt.demo.bean.BaseEntity;

/**
 * @author wangzitao
 * @create 2018-05-17 10:03
 **/
@TableName("t_student")
public class Student extends BaseEntity<Student> {

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("school")
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

    @Override
    public String toString() {
        return "Student{" + ", name=" + name + ", age=" + age + ", school=" + school + "}";
    }
}
