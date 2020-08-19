package com.company.ch06.array;

public class ArrayCopy {
    public static void main(String[] args) {
        int[] arr1 = {10,20,30,40,50};
        int[] arr2 = {1,2,3,4,5,};
        System.arraycopy(arr1,0,arr2,1,3);
        //arraycopy() - System의 static method
        //한 배열에 있던 값들을 다른 배열의 값에 넣을 때 사용하는 메소드.
        for (int i=0; i<arr2.length; i++){
            System.out.println(arr2[i]);
        }
    }
}
