package com.company.ch03.Operator;

public class OperatorEx4 {
    public static void main(String[] args) {

        int num1 = 0b00001010; //10
        int num2 = 0b00000101; //5

        System.out.println(num1  & num2); //0
        System.out.println(num1  | num2); //15
        System.out.println(num1  ^ num2); //15

        System.out.println( num2 << 1); //값은 변하지 않음
        //대입연산자를 해야 값이 변함.
        System.out.println(num2 >> 2);

        
    }
}
