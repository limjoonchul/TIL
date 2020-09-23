package com.company.s05.p05;

import java.util.function.*;

/**
 * 표준 함수형 인터페이스
 * java.util.function이 모듈에 들어가 있다.
 * 입력 몇개 출력이 정의되어 있다.4:02
 * Consumer 계열
 *  - 파라미터 입력을 받아서 그것을 소비하는 Funtional Interface이다.
 *     - 소비는 함수가 이용된다 라고 생각하면 된다. 왜 소비라고하녀 리턴이 되지않고 함수내에서 사용이되고
 *       없어진다 새로운 출력으로되는게 아니고 그래서 소비한다라고 의미를 부여한 것 뿐이다.
 *
 *  - accept 메소드를 사용하는데 리턴이 void인 특징이다.
 *  Consumper<T>
 * <T>는 메소드의 입력 파라미터의 자료형이다.
 * Consumer의 메소드는 accept이여서 람다식이 이 메소드를 구현하는 것이다.
 */
public class Main {
    public static void main(String[] args) {
        Consumer<String> consumer = (s)-> System.out.println(s);
        consumer.accept("A String.");

        BiConsumer<String, String> biConsumer = (t,u) -> System.out.println(t+","+u);
        biConsumer.accept("Hello","world");

        // 오토박싱/ 언방식 사용하면 비효율적이다.
        Consumer<Integer> integerConsumer = (x) -> System.out.println(x);
        integerConsumer.accept(10); // 값이 들어갈 땐 오토박싱 출력할 때 언박싱

        // 효율적으로 하기 위해서 IntConsumer 제네릭이 아니다 기본형 타입
        // 기본형 입력을 하려고 할 경우, PConsumber (p: primitive type)을 사용 가능.
        // 주의! 오버로딩이 아니고 별도의 인터페이스이다. 최적화를 위해서 불편하더라도 별도로 만들어 놓은 것이다.
        IntConsumer intConsumer = (x) -> System.out.println(x);
        intConsumer.accept(5); // 객체가 아니라 값을 입력을 받는 것이다. 기본자료형이니깐
        //LongConsumer, DoubleConsumer

        // t는 <>안에 값 x는 objIntconsumber의 int의 자료형이 들어간다.
        ObjIntConsumer<String> objIntConsumer = (t,x) -> System.out.println(t + ": "+ x);
        objIntConsumer.accept("x",1024);
        // ObjLongConsumer,ObjDoubleConsumer
        // 총 4가지 타입이 있다.
    }

}
