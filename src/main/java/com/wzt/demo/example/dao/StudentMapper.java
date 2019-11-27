package com.wzt.demo.example.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wzt.demo.example.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangzitao
 * @create 2018-05-17 10:19
 **/
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
