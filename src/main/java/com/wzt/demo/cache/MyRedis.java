package com.wzt.demo.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * @author wangzitao
 * @create 2018-05-17 17:19
 **/

public class MyRedis {


    public static void main(String[] args) {
//        connection();
//        redisString();
        redisList();
//        redisSet();

    }

    public static void redisSet() {
        Jedis jedis = new Jedis("127.0.0.1");
        Set<String> list = jedis.keys("*");
        Iterator<String> ite = list.iterator();
        while (ite.hasNext()) {
            String key = ite.next();
            System.out.println("List of stored keys : " + key);
        }
    }

    public static void redisList() {
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");

        List<String> list = jedis.lrange("tutorial-list", 0, 5);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Stroed string in redis : " + list.get(i));
        }
    }

    public static void redisString() {
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.set("name", "wang");
        System.out.println("Stored string in redis : " + jedis.get("name"));
    }

    public static void connection() {
        Jedis jedis = new Jedis("localhost");
        System.out.println(jedis.ping());
        Transaction t = jedis.multi();
        t.exec();
    }
}