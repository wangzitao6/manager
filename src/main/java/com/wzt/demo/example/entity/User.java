package com.wzt.demo.example.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wangzitao
 * @create 2018-05-16 17:08
 **/
@Getter
@Setter
@TableName("t_user")
public class User {

    private Integer id;

    private String userName;

    private Integer userAge;

    private String userAddress;

}