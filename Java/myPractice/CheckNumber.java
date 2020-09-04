package com.company;

public class CheckNumber {
    public static String checkNumber(int num){
        if(num >0){
            return "positive";
        }else if(num <0){
            return "negative";
        }else{
            return "Zero";
        }
    }
    public static void main(String[] args) {
        System.out.println(checkNumber(0));
    }
}
