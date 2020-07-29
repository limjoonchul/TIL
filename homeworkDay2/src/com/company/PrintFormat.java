package com.company;

import java.util.Scanner;

public class PrintFormat {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int id = sc.nextInt();
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        System.out.println("출력 포맷");
        System.out.println("*----------*--------*------------*");
        System.out.printf("| ID%06d |%-5s | %-10s |\n",id,s1,s2);
        System.out.println("*----------*--------*------------*");







    }
}
