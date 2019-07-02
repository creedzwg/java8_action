package com.keking.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MethodReference {


    public static void main(String[] args) {

        List<Apple> apples = Arrays.asList(new Apple("red", 15), new Apple("green", 18), new Apple("red", 45));

      //  apples.sort((a1,a2)->a1.getColor().compareTo(a2.getColor()));
//
       apples.sort(Comparator.comparing(Apple::getColor));

        Apple apple = new Apple("red", 15);
//        biconsumer<apple, string> applestringbiconsumer = (apple a, string s) -> a.setcolor(s);



    }


}
