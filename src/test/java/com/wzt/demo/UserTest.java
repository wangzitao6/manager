package com.wzt.demo;

import com.alibaba.fastjson.JSON;
import com.wzt.demo.example.entity.Student;
import com.wzt.demo.example.entity.User;
import com.wzt.demo.example.service.UserService;
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
public class UserTest {
    @Autowired
    UserService userService;

    @Test
    public void addUser() {
        User user=new User();
        String name ="姓名11";
        String school ="地址111";
        user.setUserAge(18);
        user.setUserName(name);
        user.setUserAddress(school);
        userService.addUser(user);
    }

    @Test
    public void selectUserById() {

        User user =  userService.selectUserById(1);
        System.out.println("User:"+ JSON.toJSONString(user));
    }

    @Test
    public void findAllUser() {
        List<User> users =  userService.findAllUser();
        System.out.println(users.size());
    }
}
