package com.keking.java8;

import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaExpression {


    public static void main(String[] args) {

        Function<Apple, String> appleStringFunction = (Apple a) -> a.getColor();

        Consumer<Apple> appleConsumer = (Apple a) -> {
            System.out.println(a);
        };


    }
}
