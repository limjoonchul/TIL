package com.company;

import java.util.Scanner;

/**
 * 사분면 고르기
 */
public class QuadrantN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num1 = sc.nextInt();
        int num2 = sc.nextInt();

        if(num1 > 0 & num2 > 0){
            System.out.println("quadrant 1");
        }else  if(num1 < 0 & num2 > 0){
            System.out.println("quadrant 2 ");
        }else  if(num1 < 0 & num2 < 0){
            System.out.println("quadrant 3 ");
        }else{
            System.out.println("quadrant 4 ");
        }
    }
}
