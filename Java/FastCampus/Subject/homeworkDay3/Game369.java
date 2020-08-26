package com.company;

import java.util.ArrayList;

public class Game369 {
    public static void main(String[] args) {
        int gameLength = 1000;
        int numPeople = 12;
        int myTurn = 7;


        for (int i=1; i<=gameLength; i++){
            int num1 = i/100;
            int temp = i % 100;
            int num2 = temp/10;
            int num3 = temp % 10;
            int numClap = 0;
            int numClap2 =0;

            if((i-myTurn) % numPeople !=0){
                continue;
            }
            if(num1 == 3 | num1 == 6 | num1 == 9){

                numClap++;
            }
            if(num2 == 3 | num2 == 6 | num2 == 9){

                numClap++;
            }
            if(num3 == 3 | num3 == 6 | num3 == 9){

                numClap++;
            }
            if(num1 == 5 | num2 == 5 | num3 == 5 ){

                numClap++;
            }
        if(num1 == 5){

            numClap2++;
        }
        if(num2 == 5){

            numClap2++;
        }
        if(num3 == 5){

            numClap2++;
        }
            if (numClap == 0){
                System.out.println(i);
            }else{
                for (int j=0; j<numClap; j++){
                    System.out.print("짝!");
                }
                for (int j=0; j<numClap2; j++){
                    System.out.print("쿵!");
                }
                System.out.println("");
            }


        }




    }
}
