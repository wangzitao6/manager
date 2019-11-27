package com.wzt.demo.example.dao;
import java.util.List;

import com.wzt.demo.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangzitao
 * @create 2018-05-16 17:09
 **/


@Mapper
public interface UserMapper {

    int deleteById(Integer userId);

    int insert(User user);

    User selectById(Integer userId);

    int updateById(User user);

    List<User> selectAllUser();

}
