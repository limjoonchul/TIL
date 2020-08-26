package com.company;

/**
 * N-D Array 연습
 */
public class Main {

    public static void main(String[] args) {
	// write your code here
        int [][] matA = {{1,2,3},{3,4,5}};
        int [][] matB = {{3,4,5},{1,4,2}};



        // 1. matA + matB 를 구하고 출력
        // 2. matA를 Transpose하고 출력 i번째 j번째 뒤집기
        //Transpose
        //123
        //456

        //1 4
        //2 5
        //3 6

        // 1번
        int [][] result = new int[matA.length][matB[0].length];
        for (int i=0; i<matA.length; i++){
            for (int j=0; j<matA[0].length;j++){
                result[i][j] += matA[i][j] + matB[i][j];

            }
        }
//        for (int i=0; i<result.length; i++){
//            for (int j=0; j<result[0].length; j++){
//                System.out.print(result[i][j]);
//            }
//            System.out.println("");
//        }
//        for (int []row: result){
//            for (int rowVal : row){
//                System.out.printf("%d",rowVal);
//            }
//            System.out.println("");
//        }

        // 2번
        int [][] trans = new int[matA[0].length][matA.length];
        for (int i=0; i<matA[0].length;i++){
            for (int j=0; j<matA.length; j++){
               trans[i][j] = matA[j][i];
            }
        }

//        for (int i=0; i<matA.length;i++){
//            for (int j=0; j<matA[0].length; j++){
//                trans[i][j] = matA[j][i];
//            }
//        }

        for (int i=0; i<trans.length; i++){
            for (int j=0; j<trans[0].length; j++){
                System.out.print(trans[i][j]);
            }
            System.out.println("");
        }
    }
}
