package com.company.ch13.Exception;

public class ArrayExceptionTest {
    public static void main(String[] args) {
        int [] arr = new int[5]; //new 로하면 0으로 자동 초기화.
        // ArrayIndexOutOfBoundsException

        try{
            for (int i=0; i<=5; i++){
                System.out.println(arr[i]);
            }

        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.toString());  //서버가 죽지 않고 예외만 남김
            // 런타임 익섹션
            System.out.println("예외처리");

        }
        System.out.println("프로그램 종료");

    }
}
