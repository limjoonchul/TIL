package com.company.s05.p11;

import java.util.function.DoublePredicate;
import java.util.function.Predicate;

/**
 * Predicate 계열 기본 메소드/ 클래스 메소드
 * - and(), or(), negate() : 기본 메소드
 * - isEual() : 클래스 메소드
 */
public class Main {
    public static void main(String[] args) {
        // 객체에서 메소드로 접근한다.
        DoublePredicate p0 = x -> x > 0.5;
        DoublePredicate p1 = x -> x < 0.7;
        DoublePredicate p2 = p0.and(p1); //and연산자
        DoublePredicate p3 = p0.or(p1); //or연산자
        DoublePredicate p4 = p0.negate();//not
        System.out.println(p0.test(0.9));
        System.out.println(p1.test(0.9));
        System.out.println(p2.test(0.9));
        System.out.println(p3.test(0.9));
        System.out.println(p4.test(0.9)); // not p0


        // 클래스 메소드라는 것이 중요!
        Predicate<String> eq = Predicate.isEqual("String");
        // 함수형 인터페이스를 사용할 수 있다. 람다식 사용 x
        // 들어오는 String이랑 eq랑 같은지 테스트해주는 함수형 인터페이스를 리턴해줌
        System.out.println(eq.test("String"));
        System.out.println(eq.test("String!"));
    }
}
