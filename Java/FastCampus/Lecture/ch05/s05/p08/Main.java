package com.company.s05.p08;

import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

/**
 * Operator 계열
 *  - 입력받은 타입과 동일한 타입의 출력을 하는 함수형 인터페이스
 *  - Funtion 계열과 달리 입출력 타입이 다를 수 없다.
 */
public class Main {
    public static void main(String[] args) {
        // 그냥 operator는 없다.
        //입력이 1개 인 것을 Unary를 붙여서 표현
        UnaryOperator<String> operator = s -> s+"."; //리턴타입을 따로 입력받지 않는다 입출력이 같으니깐
        System.out.println(operator.apply("왔다")); // apply() 사용.

        BinaryOperator<String> operator1 = (s1,s2) -> s1 + s2;//타입은 하나만 입력받게 되어있다. 출력은 동일한 타입이여야 하니깐?
        System.out.println(operator1.apply("나","왔다"));

        IntUnaryOperator op = value -> value*10; //타입을 받지 않는다 어차피 int입력 int출력이니
        System.out.println(op.applyAsInt(5));
        // LongUnaryOperator, DoubleUnaryOperator

        IntBinaryOperator ibo = (v1,v2) -> v1 * v2;
        System.out.println(ibo.applyAsInt(10,20));
        //LongBinaryOperator, DoubleBinaryOperator
    }
}
