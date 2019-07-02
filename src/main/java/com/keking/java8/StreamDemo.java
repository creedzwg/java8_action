package com.keking.java8;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    List<Dish> menu = new ArrayList<>();

    @Before
    public void initMenu() {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("blackfish", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 300, Dish.Type.FISH));
    }

    //创建stream
    @Test
    public void test1() {
        //1. 可以通过集合顶级集合Collection的默认stream()或者parallelStream方法
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过Arrays中的静态方法stream() 获取数组流
        Dish[] dishs = new Dish[10];
        Stream<Dish> stream2 = Arrays.stream(dishs);
        //3. 通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");
        //创建无限流
        Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
       // iterate.forEach(System.out::println);
        iterate.limit(10).forEach(System.out::println);

        //通过Stream的generate方式,也是无限流

//        Stream<Double> generate = Stream.generate(Math::random);
//        generate.limit(10).forEach(System.out::println);

    }

    /**
     * 筛选与切片
     * filter  - 过滤,接受一个predicate,返回符合谓词的流
     * distinct- 去重 ,返回一个元素各异的流()
     * skip(n) -跳过元素,返回一个扔掉前n个元素的流
     * linit(n) -截断流,使其元素不会超过给定的数量
     */
    @Test
    public void test2() {
        //过滤不是蔬菜的
        menu.stream().filter(Dish::isVegetarian);

        /**
         * menu.stream().filter(dish->{
         *             System.out.println(11);
         *             return dish.isVegetarian();
         *         });
         */
        //去重,根据hashcode和equals方法进行去重
        menu.stream().distinct();

        menu.stream().limit(6);


        menu.stream().skip(6);
    }

    /**
     * 映射
     * <p>
     * map -它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素
     *
     * flatMap -他会接受一个参数作为函数,将流中每个值都换成另一个流,最后再将所有流拼成另外一个流
     */
    @Test
    public void test3() {
        List<String> list = Arrays.asList("aa", "nb", "cc", "dd");


        menu.stream().map(Dish::getName).forEach(System.out::println);
        System.out.println("-----------------------------");

       Stream<Stream<Character> > Character= list.stream().map(StreamDemo::Character);
        Character.forEach(st ->{
            st.forEach(System.out::println);
        });
        Stream<Character> characterStream = list.stream().flatMap(StreamDemo::Character);
        characterStream.forEach(System.out::println);


    }


    /**
     *
     *  排序
     *
     *  sorted() -自然排序
     *  sorted(Comparator comparator) --定制排序
     *
     *

     */
    @Test
      public void  test4(){

          List<String> list = Arrays.asList("aa", "nb", "cc", "dd");

          list.stream().sorted().forEach(System.out::println);
           System.out.println("----------------");
          menu.stream().sorted((a1,a2)->-Integer.compare(a1.getCalories(),a2.getCalories())).forEach(System.out::println);
      }


    /** 规约
     * T reduce(T identity, BinaryOperator<T> accumulator); 可以将流中元素集合起来,得到一个值
     *
     */
    @Test
    public void test5(){

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
//        int  num=0;
//        for (Integer a:list
//             ) {
//            num+=a;
//        }
        Integer reduce = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println(reduce);
        Optional<Integer> reduce1 = menu.stream().map(Dish::getCalories).reduce(Integer::sum);
    }

    /**
     * collect 搜集器,将流中的元素进行汇总
     *
     * Collector 接口,java已经通过Collectors工具类提供了许多静态方法,很方便的创建Collector示例
     */
    @Test
    public void  test6(){
        //转成集合
        menu.stream().map(Dish::getCalories).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("-------------------------------");
        menu.stream().map(Dish::getCalories).collect(Collectors.toSet()).forEach(System.out::println);
        System.out.println("-------------------------------");
        menu.stream().map(Dish::getCalories).collect(Collectors.toCollection(ArrayList::new)).forEach(System.out::println);
        //所有的卡路里个数
        System.out.println("-------------------------------");
        Long collect = menu.stream().map(Dish::getCalories).collect(Collectors.counting());
        System.out.println(collect);

        //所有的卡路里平均值c
        System.out.println("-------------------------------");
        double avg = menu.stream().map(Dish::getCalories).collect(Collectors.averagingInt(c->c));
        System.out.println(avg);

        //所有的卡路里总和
        System.out.println("-------------------------------");
        int sum = menu.stream().map(Dish::getCalories).collect(Collectors.summingInt(c->c));
        System.out.println(sum);

        //卡路里最大值
        System.out.println("-------------------------------");
        Optional<Integer> collect1 = menu.stream().map(Dish::getCalories).collect(Collectors.maxBy(Integer::compare));
        System.out.println(collect1.get());

        System.out.println("-------------------------------");
        //多个字符串进行join
        List<String> list = Arrays.asList("aa", "nb", "cc", "dd");
        String join = String.join(",", list);
       //获取所有的菜名
        String collect3 = menu.stream().map(Dish::getName).collect(Collectors.joining("#", "--", ";;"));
        System.out.println(collect3);


        //根据菜的卡路里值进行分组
        Map<String,List<Dish>> stringListMap=menu.stream().collect(Collectors.groupingBy((Dish dish)->{
             if(dish.getCalories()>500){
                 return "high";
             }
             else{
                 return "low";
                    }
                }));
        System.out.println("-------------------------------");
        System.out.println(stringListMap);

        // 根据菜是否是蔬菜进行partitioningBy 分区
        Map<Boolean, List<Dish>> collect2 = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println("-------------------------------");
        System.out.println(collect2);



        //collectingAndThen
        Integer integer = menu.stream().map(Dish::getCalories).collect(Collectors.collectingAndThen(Collectors.toList(), List::size));



    }






    public static  Stream<Character> Character(String s){
        List<Character > Character=new ArrayList<>();

        for (Character c:s.toCharArray()
             ) {
            Character.add(c);
        }
        return Character.stream();
    }

    //传统方式
    @Test
    public void getDishNamesByCollections() {

        List<Dish> lowCalories = new ArrayList<>();


        //filter 找出卡路里小于400的菜
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCalories.add(dish);
            }
        }

        //排序 ,根据卡路里升序排序
        Collections.sort(lowCalories, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));

        //取每个菜的名字
        List<String> lists = new ArrayList<>();
        for (Dish dish : lowCalories) {

            lists.add(dish.getName());
        }
        System.out.println(lists);


    }


    @Test
    public void getDishNamesByStream() {

        List<String> collect = menu.stream().filter(dish -> dish.getCalories() < 400)

                .sorted((d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()))
                //.sorted(Comparator.comparing(Dish::getCalories))

                .map(Dish::getName).collect(Collectors.toList());
        System.out.println(collect);


    }

    @Test
    public void getParallelDishNamesByStream() {

        List<String> collect = menu.parallelStream().filter(dish -> {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return dish.getCalories() < 400;
        })

                .sorted((d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()))
                //.sorted(Comparator.comparing(Dish::getCalories))

                .map(Dish::getName).collect(Collectors.toList());

        System.out.println(collect);

    }

    @Test
    public void flatMap() {
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        int[] numbers = {4, 5, 3, 9};
        // int[] numbers ={3, 71, 66, 22, 35, 43, 35, 26, 22, 101 };

    }


}
