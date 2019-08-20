package com.keking.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: zhangwengang
 * @Date: 2019/8/20
 * @Description:
 */
public class TestItreator {

    public static void main(String[] args) {


        List<Integer> list= Arrays.asList(1,3,4,5,67,4);

        System.out.println("---------------------");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }


        System.out.println("------------------");

        for (Integer i:list){
            System.out.println(i);
        }

        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

    }
}
