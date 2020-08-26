package com.company;

public class Main {
    /**
     * ************
     * ************
     * ************
     * ************
     */

    /**
     * *
     * **
     * ***
     * ****
     * *****
     */
    public static void main(String[] args) {
	// write your code here
//        for (int i=0; i<4; i++){
//            for (int j=0; j<10; j++){
//                System.out.print("*");
//            }
//            System.out.println("");
//        }
//        //1번
//        for (int i=1; i<=5; i++){
//            for (int j=1; j<=i; j++){
//                System.out.print('*');
//            }
//            System.out.println("");
//        }
//
//        for (int i=0; i<5; i++){
//            for (int j=0; j<i+1; j++){
//                System.out.print('*');
//            }
//            System.out.println("");
//        }
//
//       // 2번
//        for (int i=1; i<=5; i++){
//            for (int j=5-i; j>0; j--){
//                System.out.print(' ');
//            }
//            for (int j=1; j<=i; j++){
//                System.out.print('*');
//            }
//            System.out.println("");
//        }
//
//        for (int i=0; i<5; i++){
//            for (int j=0; j<4-i; j++){
//                System.out.print(' ');
//            }
//            for (int k=0; k<i+1; k++){
//                System.out.print('*');
//            }
//            System.out.println("");
//        }

        //3번
//        for (int i=0; i<5; i++){
////            for (int j=4-i; j>0; j--){ //역순으로 하는것
////                System.out.print(' ');
////            }
//            for (int j=0; j<4-i; j++){
//               System.out.print(' ');
//            }
//            for (int k=0; k<i*2+1; k++){
//                System.out.print('*');
//            }
//            System.out.println("");
//        }
        //4번
//        for (int i=0; i<5; i++){
//            for (int j=0; j<4-i; j++){
//                System.out.print(' ');
//            }
//            for (int k=0; k<i*2+1; k++){
//                System.out.print(i+1);
//            }
//            System.out.println("");
//        }
        //5번
//        for (int i=0; i<5; i++){
//            for (int j=0; j<4-i; j++){
//                System.out.print(' ');
//            }
//            for (int l=0; l<i; l++){
//                System.out.print(i+1);
//            }
//            System.out.print(1);
//            for (int k=0; k<i; k++){
//                System.out.print(i+1);
//            }
//            System.out.println("");
//        }
//        for (int i=0; i<5; i++){
//            for (int j=0; j<4-i; j++){
//                System.out.print(' ');
//            }
//            for (int k=0; k<i*2+1; k++){
//                System.out.print(k-i>0? k-i+1 : i-k+1);
//            }
//            System.out.println("");
//        }
//        for (int i=0; i<5; i++){
//            for (int j=5; j>1; j--){
//                if(j <= i + 1){
//                    System.out.print(j);
//                }else{
//                    System.out.print(' ');
//                }
//            }
//            for (int k=1; k<=5; k++){
//                if(k <= i + 1){
//                    System.out.print(k);
//                }else{
//                    System.out.print(' ');
//                }
//            }
//            System.out.println("");
//        }

        /**
         * 369게임 기본형
         */
//        for (int i=1; i<10; i++){
//            if(i == 3 | i==6 | i==9){ // i%3==0
//                System.out.println("짝!");
//            }else{
//                System.out.println(i);
//            }
//        }
        /**
         * 순서대로 다 출력, 1~30
         */
//        for (int i=1; i<=30; i++){
//            int num1 = i/10;
//            int num2 = i%10;
//
//            if(num1 == 3 | num1 ==6 | num1 ==9 | num2 ==3 | num2 ==6| num2==9){
//                System.out.println("짝");
//            }
//           else{
//                System.out.println(i);
//            }
//        }

        /**
         * 369
         * 인원 5 명 순서 1번
         * 3,6,9가 여러번 있으면 해당 횟수만큼 짝!
         */

//        for (int i=1; i<=60; i+=5){
//            int num1 = i/10;
//            int num2 = i%10;
//            boolean isCheck = false;
//            if(num1 == 3 | num1 ==6 | num1 ==9){
//                System.out.print("짝");
//                isCheck = true;
//            }
//
//            if(num2 ==3 | num2 ==6| num2==9){
//                System.out.print("짝!");
//                isCheck = true;
//            }
//           if(!isCheck){
//                System.out.println(i);
//            }
//        }

//        for (int i=1; i<=60; i+=5){
//            int num1 = i/10;
//            int num2 = i%10;
//            int numClap = 0;

//            if(num1 == 3 | num1 ==6 | num1 ==9){
//                System.out.print("짝");
//                numClap++;
//            }
//
//            if(num2 ==3 | num2 ==6| num2==9){
//                System.out.print("짝!");
//                numClap++;
//            }
//            if(numClap == 0){
//                System.out.println(i);
//            }else{
//                for (int j=0; j<numClap; j++){
//                    System.out.print("짝");
//                }
//                System.out.println("");
//            }
//        }

        /**
         * 369게임
         * 내가 1번, 8명같이 합니다.
         * 짝을 여러번 하고,
         * 10의 배수에서는 다같이 만세를 외칩니다.
         */
        for (int i=1; i<=99; i++){
            int num1 = i/10;
            int num2 = i%10;
            int numClap = 0;

            if(i % 10 == 0){
                System.out.print("만세!");
                continue;
            }
            if((i-1) % 8 !=0){
                continue;
            }
            if(num1 == 3 | num1 ==6 | num1 ==9){
                System.out.print("짝");
                numClap++;
            }
            if(num2 ==3 | num2 ==6| num2==9){
                System.out.print("짝!");
                numClap++;
            }
            if(numClap == 0){
                    System.out.println(i);
            }else{
                for (int j=0; j<numClap; j++){
                        System.out.print("짝");
                }
                System.out.println("");
            }
        }
    }
}
