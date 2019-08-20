package com.keking.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class MethodReference {


    public static void main(String[] args) {

        List<Apple> apples = Arrays.asList(new Apple("red", 15), new Apple("green", 18), new Apple("red", 45));

        //指向静态方法的方法引用(例如Integer.parseInt方法,可以写作Integer::parseInt)

        Function<String,Integer> stringIntegerFunction=(s)->Integer.parseInt(s);

        BinaryOperator<Double>   doubleBinaryOperator=(d1,d2)->Math.pow(d1, d2);



        //指向对象的实例方法的方法引用
         //假设你有一个局部变量a 用来存放 A类型的对象,它有个示例方法getValue,那么就可以写成a::getValue)
        BiConsumer<String,String> stringStringBiConsumer=(s1,s2)->s1.equalsIgnoreCase(s2);
        Apple red = new Apple("red", 15);
        Supplier<String> appleStringFunction=()->red.getColor();

         //构造器引用

        Supplier<Apple> appleSupplier=()->new Apple();


        //  apples.sort((a1,a2)->a1.getColor().compareTo(a2.getColor()));
//
       apples.sort(Comparator.comparing(Apple::getColor));

        Apple apple = new Apple("red", 15);
//        biconsumer<apple, string> applestringbiconsumer = (apple a, string s) -> a.setcolor(s);



    }


}
