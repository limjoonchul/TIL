package com.company;

/**
 * 변수 (variable)
 *  변하는 수, 수학의 변수와 유사하다.
 *  변수의 타입, 변수명, 값 할당
 *  변수의 선언
 *  변수에 값 할당(리터럴)
 *  변수명 규칙
 */

public class Main {
    static int STATIC_VARIABLE = 10; //변하지 않는 수 대문자로사용하고 띄워쓰기는 _사용

    public static void main(String[] args) {
	// write your code here
        int x;
        x = 10; // 10 -> 리터럴 (순수한 값을 의미)
        System.out.println(x);

        int y, z, value;
        int valueOne = 10;
        int valueTwo = 20;
        int valueThree = 100, valueFour = 1000;
        int intOne; //명령어는 사용불가 넣어서 다른 단어를쓰는건 가능
//        int int;
//        int 4thworld;
        int val2ue1;
        int 한글도_됩니다; //잘 쓰지 않음
//       int fwei&efe; 특수문자 사용불가
        int $power; //사용가능하나 왠만하면 사용하지 마세요

        //code convention
        int valueOfComputer; // camelcase
        int lowerCamelCase;
        int UpperCamelCase; //pascalcase와 같은 것

        int ValueOfComputer; //pascalcase - > class 이름 등.. 변수에 사용x
        int _8thWord; //앞에 숫자를 사용할 때 _를 넣어서 사용

    }
}
