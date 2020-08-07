package com.company;

/**
 * Array 연습
 */
public class Main {

    public static void main(String[] args) {
        int [] integers = {4,2,12,23,62,9,-2,-34,5};
//        int max = 0;
//        for (int i=0; i<integers.length; i++){
//            if(integers[i] > max){
//                max = integers[i];
//            }
//        }
//        System.out.println(max);
//
//        for (int val : integers){
//            max = max > val ? max : val;
//        }
//        System.out.println(max);

        //2. 두번째로 큰 값 찾기

//        int max2 = 0;
//        for (int i=0; i<integers.length; i++){
//            if(integers[i] > max){
//                max = integers[i];
//            }
//            for (int j=0; j<integers.length; j++){
//                if (integers[j] == max){
//                    integers[j] = -1;
//                }
//            }
//        }
//        for (int val : integers){
//            max2 = max2 > val? max2 : val;
//        }
//        System.out.println(max2);

        int [] maxVals = new int[2]; //가장큰거랑 두번째큰값을 기억하고 있으면 됨
        maxVals[0] = integers[0] > integers[1] ? integers[0] : integers[1];
        maxVals[1] = integers[0] < integers[1] ? integers[0] : integers[1];

        System.out.println(maxVals[0]);
        System.out.println(maxVals[1]);

        for (int val: integers){
            if(maxVals[0] < val){
                maxVals[1] = maxVals[0];
                maxVals[0] = val;
            }else  if(maxVals[1] < val){
                maxVals[1] = val;
            }
        }
        System.out.println(maxVals[1]);

    }
}
