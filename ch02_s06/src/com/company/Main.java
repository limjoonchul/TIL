package com.company;

/**
 *  반복문 Loops
 *  for 문, while문
 *  초기화 - 반복문을 실행하기 위해서 증감할 변수를 초기화
 *  조건식 - 반복무을 실행할 조건 또는 종료할 조건을 정하는 것
 *  실행문 - 조건식이 참 또는 거짓일 경우 실행할 코드
 *  증감식 - 실행문이 실행된 후에 변수에 증감을 주는 것 중요*
 */
public class Main {

    public static void main(String[] args) {
        // for 문 초기화 조건식  실행문 증감식
              //초기화;   조건식;   증감식
        for (int i = 0; i < 5; i++){
            System.out.println(i);// 실행문 조건식 거짓일 때 까지
        }
        System.out.println("");

                        //대입연산자 가능
        for (int i=0; i<5; i+=2){
            System.out.println(i);
        }
        System.out.println("");

        for (int i=4; i>=0; i--){
            System.out.println(i);
        }
        System.out.println("");

        // nested for
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                System.out.printf("(%d, %d)\n",i, j);
            }
        } // 5*5=25

        for (int i =0; i<5; i++) //중괄호가 없어서 바로 밑에 줄만 실행됨.
            System.out.print(i);// iterated
        System.out.println(""); //not iterated

        //while 문
        // 직접 초기화와 증감식을 적어줘야 한다
        // 초기화
        //while(조건문){
        //  실행문
        //  증감식
        //}

       // int i =0; // i가 외부에서 사용됨 for문은 안에서 사용하고 사라짐 그것이 차이점이다.
//        while (i<5){
//            System.out.println(i);
//            i++;
//        }
//
//        i =0;
//        do{
//            System.out.println(i);
//            i++;
//        }while (i < 5); //조건문이 false 라고해도 단 한번은 무조건 실행됨.

        // 제어문 - break continue

        for (int i=0; i<10; i++){
            if(i == 3){
                continue; //continue를만나면 실행문을 실행하지않고 증감식이 되서 반복됨
            }
            System.out.println(i);
        }
        System.out.println("");

        for (int i=0; i<10; i++){
            if(i == 3){
                break; //반복문 바로 종료
            }
            System.out.println(i);
        }
        System.out.println("");

//        outer: //레이블 가장 가까운걸 건너 뛰는게 아니라 레이블명이 젹혀있는 반복문 쪽으로 감 실행(break도 가능)
        for (int i =0; i<5; i++){
            for (int j=0; j<5; j++){
                if(i == 3){
                    System.out.println("continue called");
                    continue;
                    //여기서 continue만 사용하면 j는 4번동안 반복됨 레이블을 사용하면 i쪽으로가서 제어함.
                }
                System.out.printf("%d * %d = %d\n", i, j, i*j);
            }
        }


//        여기서는 i==3 && j==2 조건식을 만나면 그다음 j반복문이 실행되지 않고 3단이 0,1만 실행됨
//        outer: //레이블은 반복문에서만 사용
//        for (int i =0; i<5; i++){
//            for (int j=0; j<5; j++){
//                if(i == 3 && j == 2){
//                    System.out.println("continue called");
//                    continue outer;
//
//                }
//                System.out.printf("%d * %d = %d\n", i, j, i*j);
//            }
//        }

    }
}
