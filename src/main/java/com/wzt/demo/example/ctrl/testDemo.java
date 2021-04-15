package com.wzt.demo.example.ctrl;

import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @Author wangzt29
 * @Date 2021/4/15 11:06
 * @Version 1.0.0
 */
public class testDemo {
    public static void main(String[] args) {
        String aa = "0x222f0xaa0x";
        Boolean re = aa.startsWith("0x");
        if (!re) {
            System.out.println(Boolean.FALSE+ ":"+ aa.toUpperCase());
        }

       String[] aaray = aa.split("0x",2);
        for (int i = 0; i <aaray.length ; i++) {
            System.out.println("split_ "+ i +" : "+ aaray[i]);
        }
        aaray[0] = "0x";
//        aaray[1] =  aaray[1].toUpperCase();
        StringBuilder stringBuilder = new StringBuilder("0x");
        stringBuilder.append(aaray[1].toUpperCase());

//        String ss = java.util.Arrays.stream(aaray).collect(Collectors.joining(""));
        System.out.println(stringBuilder.toString());

    }

}
