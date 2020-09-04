package com.company;

import java.util.Scanner;

/**
 * 백준 1110
 */
public class LifeCycleAdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int cnt = 0;
//        int sum =0;
//        String str = Integer.toString(n);
//        int result = 0;
//        for (int i =0; i<str.length(); i++){
//            sum += str.charAt(i)-'0';
//        }
//        result = sum + (str.charAt(1) - '0');
//        System.out.println(result);


        int n = sc.nextInt();
        int cnt = 0;
        int sum =0;
        int rightNum = 0;
        String str = "";
        while (true){
        rightNum = n % 10;
            while (n != 0){
                sum += n % 10;
                n /= 10;
            }
            str = Integer.toString(rightNum)+Integer.toString(sum);
            n = Integer.parseInt(str);
            System.out.println(n);
            cnt++;
            if(n == 26){
                break;
            }
        }
        System.out.println(cnt);
//        rightNum = n % 10;
//        while (n != 0){
//            sum += n % 10;
//            n/=10;
//        }
//        System.out.println(rightNum);
//        System.out.println(sum);
//
//        System.out.println(str);
//        n = Integer.parseInt(str);
//        System.out.println(n);
    }
}
