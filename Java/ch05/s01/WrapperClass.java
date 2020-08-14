package com.company.p01;

import java.nio.file.Watchable;

/**
 * Wrapper 클래스
 * - 기본형 타입을 객체로 쓰기 위해 있은 클래스
 * - 기본형 타입이 허용되지 않는 문법에 기본형 타입을 쓰기 위해서 제공 하는 클래스
 *
 * byte -> Byte
 * char -> Character
 * short -> Short
 * int -> Integer
 * flaot -> Float
 * double -> Double
 * boolean -> Boolean
 */
public class WrapperClass {
    public static Integer add(Integer x, Integer y){
        return x + y; //unboxing 원래는 덧셈이 안되는데 이 경우에 언박싱이 이뤄져서 자동으로 프리미티브 타입으로 바꿔주는 것이다.
        //자동으로 기본자료형으로 변형되어서 계산
        // 반환 시에는 다시 Autoboxing이 이루어짐.
    }

    //이걸사용하면 제네릭에도 사용할 수 있음.
    public static <T> T bypass (T x){
        return x;
    }

//    public static <T> T add (T x,T y){
//        return x+y; 타입 t에선 사칙연산 불가능.
//    } 불가능~!!!!!!!!!!!!!!
    public static void main(String[] args) {
        Integer integer = new Integer(10);
        Integer integer1 = Integer.valueOf(10);//정적메소드를 사용하여 쓸 수도 있음 valueof라는 스태틱메소드 위에랑 똑같음.

        Character character = new Character('v');
        Character character1 = Character.valueOf('v');

        // Autoboxing 자동으로 웨퍼클래스를쓰워준다는 의미.
        Integer integer2 = 4;
        System.out.println(add(4,2));
        bypass(5); // 원래는 프리미티브 타입을t에 넣을 수 없는데 autoboxing이 이뤄져서 가능.
        // T: wrapper class인 integer로 결정됨
        // 5 -> new Integer(5) autoboxing 1.5버전 이후로 가능해짐.
        //객체로 사용될때 오토박싱 다시 프리미티브타입이되는건 언박싱.

        //unboxing
        int m = new Integer(10); // 기본 자료형이 필요한 자리에 wrapperclass 객체가 있을 경우 자동 unboxing이 이루어짐.

        // 문자열 <-> 기본자료형
        int x = Integer.parseInt("100"); //parse+자료형 정적메소드를 이용하면 문자열에서 프리미티브타입으로 변환가능. 가장 권장하는 방법
        int y = new Integer("100"); // 이렇게도 가능 integer같은 경우에 생성자의 입력을 받을때 스트링을 받아서 파싱이 가능. 스트링을 원래 받음
        //deprecated 언젠가 안쓰일 것이여서 권장하지 않음
        int z = Integer.valueOf("100");  // valueof도 스트링을 받게 되있어서 가능.




    }
}

