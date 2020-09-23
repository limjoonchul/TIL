package com.company.s05.p09;

import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * Predicate 계열
 * - 논리 판단을 해 주는 함수형 인터페이스
 * - 입력을 받아서 boolean 타입 출력을 반환
 */
public class Main {
    public static void main(String[] args) {
        Predicate<String> predicate = (s) -> s.length() == 4; // 조건식이 들어가야 한다.
        System.out.println(predicate.test("Four")); // test()를 사용한다 true or false 값 출력
        System.out.println(predicate.test("six"));

        BiPredicate<String, Integer> pred2 = (s,v) -> s.length() ==v;
        System.out.println(pred2.test("abcd",23));
        System.out.println(pred2.test("abc",3));

        IntPredicate pred3 = x -> x > 0;
        //LongPredicate, DoublePredicate asP출력은 존재하지 않는다.

    }
}
