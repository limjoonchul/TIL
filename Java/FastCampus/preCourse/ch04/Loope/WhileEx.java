package com.company.ch04.Loope;

import java.util.Scanner;

public class WhileEx {
    public static void main(String[] args) {
        /*
        int num = 1;
        int sum = 0;

        while(num<=10){
            sum+=num;
            num++;
        }
        System.out.println(sum);
        System.out.println(num);
        */

        Scanner sc = new Scanner(System.in);
        int input;
        int sum=0;
        while(true){
            input = sc.nextInt();
            if(input == 0){
                break;
            }
            sum += input;
        }
        System.out.println(sum);
        sc.close();
    }
}
