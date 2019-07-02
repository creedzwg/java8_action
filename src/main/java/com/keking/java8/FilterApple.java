package com.keking.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FilterApple {


    public static void main(String[] args) throws InterruptedException {

        List<Apple> apples = Arrays.asList(new Apple("red", 15), new Apple("green", 18), new Apple("red", 45));


        List<Apple> apples1 = findGreenApple(apples);











        List<Apple>  apples2 = findGreenAndWeightApple(apples);


        filterApple(apples,new GreenAppleFilter());

        List<Apple> greenApple = filterApple(apples, a ->"green".equals(a.getColor()));
        //greenApple.sort((a1,a2)->a1.getColor().compareTo(a2.getColor()));

        //greenApple.sort(Comparator.comparing(Apple::getColor));

//        new  Thread(()->{
//
//           System.out.println(Thread.currentThread().getName());
//       }).start();
//
//        System.out.println("aha");

    }




     static  interface  AppleFilter{
        Boolean   filter(Apple a);
     }


     static  class  GreenAppleFilter implements AppleFilter{

         @Override
         public Boolean filter(Apple a) {
             return "green".equals(a.getColor());
         }
     }
       static   class  GreenWeightFilter implements AppleFilter{

         @Override
         public Boolean filter(Apple a) {
             return "green".equals(a.getColor()) && a.getWeight()>15;
         }
     }

    private static List<Apple> findGreenAndWeightApple(List<Apple> apples) {
        List<Apple> list= new ArrayList<>();
        for (Apple apple:apples){
            if("green".equals(apple.getColor()) && apple.getWeight()>15){
                list.add(apple);
            }
        }
        return list;

    }
    public static  List<Apple> filterApple (List<Apple> apples,AppleFilter AppleFilter){
        List<Apple> list= new ArrayList<>();
        for (Apple apple:apples){
            if(AppleFilter.filter(apple)){
                list.add(apple);
            }

        }
        return list;
    }




    public static  List<Apple> findGreenApple (List<Apple> apples){
       List<Apple> list= new ArrayList<>();
        for (Apple apple:apples){
            if("green".equals(apple.getColor())){
                list.add(apple);
            }

        }
        return list;
    }
}
