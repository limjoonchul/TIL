package com.company.ch04.IfEx2;

import java.util.Scanner;

public class IfEx2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
//        //System.in - 입력용 스트림
//        int age = sc.nextInt();
//
//        int charge = 0;
//
//        if(age <8){
//            charge = 1000;
//        }else if(age<14){
//            charge = 1500;
//        }else if(age <20){
//            charge= 2000;
//        }else {
//            charge= 3000;
//        }
//
//        System.out.println("나이 :"+age+"세의 가격은 :"+charge+"입니다.");
//        //System.out - 출력용 스트림

        int score = sc.nextInt();

        if(score >=90 && score<=100){
            System.out.println("A");
        }else if(score >=80 && score<=89){
            System.out.println("B");
        }else if(score >=70 && score<=79){
            System.out.println("C");
        }else if(score>=60 && score<=69){
            System.out.println("D");
        }else if(score <=59){
            System.out.println("F");
        }else{
            System.out.println("잘못된 숫자 입니다.");
        }
        //101 을 입력했을 때 첫번째 조건문에 해당하지 않으니 두번째 조건문이 출력됨

        int a = 10;
        int b = 20;

        int max =0;
        max = a >b ? a: b;
        System.out.println(max);


    }
}
