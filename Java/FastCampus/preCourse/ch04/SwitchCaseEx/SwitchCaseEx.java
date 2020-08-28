package com.company.ch04.SwitchCaseEx;

import java.util.Scanner;

public class SwitchCaseEx {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rank = sc.nextInt();
        String medalColor;
        // 초기값을 지정해주지 않고 default를 설정해주지 않았을 때
        //medalColor가 초기화가 되지 않았으므로 에러발생.
        switch (rank){
            case 1:
                medalColor = "GOLD";
                break; //중괄호를 빠져나가라라는 의미.
            case 2:
                medalColor = "SILVER";
                break;
            case 3:
                medalColor = "Bronze";
                break;
            default:
                medalColor = "NOMEDAL";

        }

        System.out.println(rank+"등은 "+medalColor+"입니다.");
    }
}
