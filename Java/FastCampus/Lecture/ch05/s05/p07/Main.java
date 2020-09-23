package com.company.s05.p07;

import java.util.function.*;

/**
 * Function 계열
 * - Mapping : 입력 -> 출력의 Mapping을 하는 함수형 인터페이스
 * - 입력 타입과 출력 타입은 다를 수 있다. 같을 수도 있다는 말도 된다.
 * 진짜로 함수이다.
 */
public class Main {
    public static void main(String[] args) {
        Function<String,Integer> func = (s) -> s.length();
        // s 는 String타입, s.length() 는 Integer
        System.out.println(func.apply("Strings")); //이것은 apply로 출력한다

        // Bi가 붙으면 '입력'을 2개 받을 수 있다는 의미이다.
        BiFunction<String,String,Integer> biFunction = (s,u) -> s.length() + u.length();
        System.out.println(biFunction.apply("one","two")); //6

        // IntFunction<R>은 리턴 자료형
        // P type Funtion은 입력을 P타입으로 받는다.
        IntFunction<String> intFunction = (value) -> String.valueOf(value);// "" + value도 가능.
        System.out.println(intFunction.apply(512));

        //ToP Type Function은 출력을 P타입으로 한다.
        ToIntFunction<String> funcFour = (s) -> s.length(); // 4:21
        System.out.println(funcFour.applyAsInt("abcde"));
        // 출력이 P타입인 경우에는 AsP가 들어간다.!!!
        //ToIntBiFunction<String,String>// int 출력을 하는 Bi 함수
        // P: Int, Long, Double

        // int 에서 double로 바꾸는 함수 PTOQFunction : P -> Q로 매핑하는 함수
        IntToDoubleFunction funcfive;
        // IntToIntFunction은 없다. 동일한 것에 대해서는 다른게 있다 4:23



    }
}
