package com.company.s05.p13;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * 람다식에 기존 메소드/생성자 사용하는 예
 * - 람다식에 기존에 구현되어 있는 내용을 재사용하고자 할 때
 *   - 함수형 인터페이스의 재사용을 못하는 부분을 보완하기 위함.
 */

public class Main {
    public static void main(String[] args) {
        // 클래스::인스턴스_메소드 - 어떤 객체가 필요해서 첫번째가 객체로 땡겨진다 o.
        String[] strings = {"fast","campus","best","academy"};
        Arrays.sort(strings, String::compareTo); //기존 메소드를 참조한다
        // "o".comparaTo()
        // Comparator 인터페이스는 원래 2개의 입력을 받는다 o1,o2를 받음
        // compare(o1,o2) - > o1.comareTo(o2) 로 매핑된다.
        // o1.인스턴스_메소드(o2) 로 호출되는 메소드가 사용이 된것이다 여기서
        //5:56
        System.out.println(Arrays.toString(strings));
//        Function<String,Integer> func0 = String::compareTo;
        // 이렇게 안됨

        //클래스::클래스_메소드
        Function<String,Integer> func = Integer::parseInt;
        // 클래스메소드여서 바로 func에서 그대로 받아 줄 수 있다.

        // 인스턴스::인스턴스_메소드 - 인스턴스메소드이니깐 인스턴스의 호출이 되어 있는 상태니
        // 6 :00
        String s = "Stirng";
        Predicate<String> pred = s::equals;
        System.out.println(pred.test("String"));
        System.out.println(pred.test("String!"));

        // 클래스 :: new 새로운 스트링을 생성하게 된다
        UnaryOperator<String> fnc = String::new;
        // 스트링을 입력받아 새로운 스트링을 객체를 생성?해준다
        //integer로 안된다

        // 생성자를 antThen, compose 등과 함께 사용 가능하다.??
        //6:06

        // 클래스[]::new -> 배열 생성을 해준다.
        IntFunction<String[]> fnc2 = String[]::new;
        // 인트를 받아서 스트랭배열을 출력해주는 애
        // 스트링배열생성자를 그대로 활용한다?
        // new String[10] 처럼 그대로 사용한다.
        String [] strings1 = fnc2.apply(100);


    }
}
