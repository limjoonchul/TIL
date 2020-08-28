package com.company.ch03.Operator;

public class OperatorEx3 {
    public static void main(String[] args) {
        int score = 100;

        System.out.println(++score);//많이 사용
        score = score + 1;
        score +=1; //많이 사용

        int num1 = 10;
        int i = 2;

        boolean value1 = ((num1 = num1 +10)<10) && ((i = i + 2)<10);
        System.out.println(num1 + ","+i); //20,2
        boolean value2 = ((num1 = num1 +10)<10) || ((i = i + 2)<10);
        System.out.println(num1 + ","+i); //30,4

        
    }
}
