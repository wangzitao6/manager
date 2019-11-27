package com.wzt.demo.example.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.wzt.demo.config.ShowClientServiceException;
import com.wzt.demo.example.entity.User;
import com.wzt.demo.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author wangzitao
 * @create 2018-05-16 17:11
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value={"/selectUserById"}, method=RequestMethod.GET)
    public User selectUserById(String id){
        if(StrUtil.isEmpty(id)) {
            throw new ShowClientServiceException("id不能为空");
        }
        User user = userService.selectUserById(Integer.parseInt(id));
        return user;
    }

    @RequestMapping(value={"/findAllUser"}, method=RequestMethod.GET)
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @RequestMapping(value={"/addUser"}, method=RequestMethod.POST)
    public void addUser(User user){
        userService.addUser(user);
    }


    @Cacheable(value = "String_demo",key = "#id")
    @RequestMapping(value={"/redis"}, method=RequestMethod.GET)
    public String String_demo(String id){
        System.out.println("如果第二次没有走到这里说明缓存被添加了");
        String str = "id_"+ id;

        return str;
    }

    @Cacheable(value = "list_demo",key = "#id")
    @RequestMapping(value={"/redis2"}, method=RequestMethod.GET)
    public List<String> list_demo(String id){
        System.out.println("如果第二次没有走到这里说明缓存被添加了");
        List<String> list = new ArrayList<>();
        list.add(id);
        list.add("123");
        list.add("123");
        return list;
    }

    @Cacheable(value = "map_demo",key = "#id")
    @RequestMapping(value={"/redis3"}, method=RequestMethod.GET)
    public  Map<String, String> map_demo(String id){
        System.out.println("如果第二次没有走到这里说明缓存被添加了");
        Map<String, String> map = new HashMap<>(); //导入字段映射关系
        map.put("用户姓名", "userName");
        map.put("手机号码", "phone");
        map.put("邮箱", "email");
        map.put("积分值", "amountValue");

        return map;
    }



}