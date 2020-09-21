package com.company.ch12.Lambda;

interface PrintString{
    void showString(String str);
}

public class TestLambda {


    public static void main(String[] args) {
        PrintString printString = s -> System.out.println(s); //이런 메소드를 담은 객체를 넘긴다고 생각하면 될까?

        printString.showString("showstring");

        showMyString(printString);

        // 함수의 구현부가 마치 변수처럼 반환이 되서 변수값에 대입이 되고,
        // 그 메소드가 호출이 될 수 있다.
        PrintString test = returnString(); // s-> System.out.println(s+"!!!");
        test.showString("Test");


    }

    public static void showMyString(PrintString p){
        p.showString("showString22Test");
    }

    public static PrintString returnString(){
        return s-> System.out.println(s+"!!!");
    }

}
