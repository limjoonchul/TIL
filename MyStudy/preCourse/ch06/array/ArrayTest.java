package com.company.ch06.array;

public class ArrayTest {
    public static void main(String[] args) {


        //선언과 동시에 값을 넣어주는 것은 경우에만 new int[]가 생략될 수 있고
        // 선언하고 저렇게 초기화하는 것은 안됨.
        //int[] arr;
       // arr = {1,2,3};

//        int[] arr = new int[10];
//        for(int i : arr){
//            System.out.println(i);
//        }
//
//        double[] dArr = new double[5];
//        dArr[0] = 1.1;
//        dArr[1] = 2.1;
//        dArr[2] = 3.1;
//
//        double total = 1;
//        for (int i=0; i<dArr.length; i++){
//            total *= dArr[i];
//        }
//        System.out.println(total); // 0이나온다
        //이유 인덱스 0,1,2까지만 값을 넣어주고 나머지 3,4는 넣어주지 않았을 때
        //자동으로 빈값은 0.0으로 초기화 됨. 그래서 곱해도 0이 나옴.

        char []alphabets = new char[26];
        char ch = 'A';

        for (int i=0; i<alphabets.length; i++){
            alphabets[i] = ch++;
            //ch++; 이렇게 다음줄에 ch++ 안해도 되고 바로 윗줄에 연산자를 이용해서 ++해주면 됨
            // 배운거지만 응용력이 부족함 생각하면서 코드를 작성하는 습관을 들이자.
            //System.out.println(alphabets[i]);
        }

        for (int i=0; i<alphabets.length; i++){
            System.out.println(alphabets[i]+","+(int)alphabets[i]);
        }

    }
}
