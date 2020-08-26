package com.company;

/**
 * 자료형 (data type)
 * 자료형 - 기본형(primitive type), 참조형(Reference type)
 * 기본형 자료형 -  정수형, 실수형, 문자형, 논리형
 * 참조형 - 문자열(String)
 */
public class Main {

    public static void main(String[] args) {
        // 정수형 : byte, short, int, long
        System.out.println("Byte");
        System.out.println(Byte.BYTES); //바이트의 자료형은 1byte짜리 자료형이다.
        byte byteValue = 42;
        System.out.println(byteValue);
        System.out.println(Byte.MAX_VALUE); //2^7 -1 까지 표현
        System.out.println(Byte.MIN_VALUE); // -2^7
//        byte byteValue1 = 128; error
        System.out.println("");

        System.out.println("short"); //2Byte
        System.out.println(Short.BYTES);
        System.out.println(Short.MAX_VALUE); //2^15 -1
        System.out.println(Short.MIN_VALUE); // -2^15
        System.out.println("");

        System.out.println("int");
        System.out.println(Integer.BYTES); // 4Byte
        System.out.println(Integer.MAX_VALUE); // 2^31 -1
        System.out.println(Integer.MIN_VALUE); // - 2^31
        System.out.println("");

        System.out.println("long");
        System.out.println(Long.BYTES); // 8Byte
        System.out.println(Long.MAX_VALUE); // 2^63 -1
        System.out.println(Long.MIN_VALUE); // - 2^63
        System.out.println("");

        //Overflow
        // 최대가 32767인데 = 2^15 -1 => 0111111111111111
        // 64000 =>           1011111111111100111110111(대충) short로 바꾸면 그 자릿수만큼 잘라버림
        short shortValue = (short)64000; //캐스캐이딩 억지로 바꿈
        System.out.println(shortValue);

        // 정수형은 기본적으로 int
        byte byteValue3 = 14;  //4바이트로 표현됨 값이넘어가면 에러
        short shortA = 10;
        short shortB = 20;
//        short shortC = shortA + shortB; 더했을 때 리터널이아님  short끼리더하면 범위를 넘어갈 수 있어 오류 기본적으로 인트형으로됨
//        short이나 바이트는 서로 더하면 int로 바뀜
        short shortC = (short)(shortA + shortB); //임의로 바꿔줌

        int bigInt = Integer.MAX_VALUE;
        int biggerInt = bigInt + 1; //overflow 주의
        System.out.println(biggerInt); //주의 해야함

        long veryBigInt = 100000000000L;
        System.out.println(veryBigInt);

        // 진수법 - Binary(2), Octal(8), Deciaml(10), Hexadecimal(16)
        System.out.println(0b1101); //2진수
        System.out.println(071); //8진수
        System.out.println(1424);
        System.out.println(0x10); // 0~9 10~15: a,b,c,d,e,f 까지
        System.out.println(0xff);


    }
}
