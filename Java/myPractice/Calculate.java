package com.company;

public class Calculate {
    int totalScore;

    public String calculateScore(int score,int levelCompleted,int bonus){
        totalScore = score + (levelCompleted * bonus);
        if(totalScore <0){
            return "-1";
        }else{
            return String.format("your final score was %d",totalScore);
        }
    }

    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        System.out.println(calculate.calculateScore(-2,2,3));
    }


}
