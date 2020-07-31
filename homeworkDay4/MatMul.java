package com.company;

import java.util.ArrayList;

public class MatMul {

    public static void main(String[] args) {
        int [][] matA = {{1, 2, 3} ,{4, 5, 2}};
        int [][] matB = {{5, 2}, {6, 2}, {1, 0}};
        int [][] answer = new int[matA.length][matB[0].length];



        for (int i=0; i<answer.length; i++){
            for(int j=0; j<answer[0].length; j++){
                for (int k=0; k<matA[0].length; k++){
                    answer[i][j] +=matA[i][k] * matB[k][j];
                }
                System.out.print(answer[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
