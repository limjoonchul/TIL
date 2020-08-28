package com.company.ch04.SwitchCaseEx;

import java.util.Scanner;

public class SwtichCaseEx2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int month = sc.nextInt();
        int day = 0;
        switch (month){
            case 1 : case 3: case 5: case 7: case 8: case 10: case 12:
                day = 31;
                break;
            case 2:
                day = 28;
                break;
            case 4: case 6: case 9: case 11:
                day = 30;

        }
        System.out.println(month + "월은 "+day+"일 까지입니다.");

    }
}
