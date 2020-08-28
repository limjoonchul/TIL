package com.company.ch03.Operator;

public class OperatorEx1 {
    public static void main(String[] args) {
        int num1 = -10;
        int num2 = 20;

        System.out.println(+num1);
        System.out.println(+num2);

        System.out.println(-num1);
        System.out.println(-num2);

        // -연산은 부호가 바뀐다.

        System.out.println(num1);
        System.out.println(num2);
        // 부호연산자는 그값을가져와서 참조해서 결과값만 가지고 쓰는거지
        //값자체의 메모리에가서 그값을 바꾸진 않는다.

        num1 = -num1;
        // 이렇게 해야 값이 바뀜.
    }
}
