package com.company;

import java.util.Scanner;

/**
 * 알람 시계
 */
public class Alarm {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int H  = sc.nextInt();
//        int M =  sc.nextInt();
//
//        if(M < 45){
//            M = (60-(45-M));
//            H--;
//            if(H < 0){
//                H = 23;
//            }
//            System.out.printf("%d : %d",H,M);
//        }

        int num1 = 123456780;
        int num2 = 123456780;

        float num3 = num2;
        num2 = (int) num3;

        int result = num1 - num2;
        System.out.println(result);

    }
}
