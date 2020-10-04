package com.company.ch12.Stream;

import java.util.Arrays;

public class IntArrayTest {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        // 배열인 경우 Arrays.stream해서 배열을 넣을 수 있다.
        int sum = Arrays.stream(arr).sum();
        int count = (int)Arrays.stream(arr).count();
        System.out.println(sum);
        System.out.println(count);

        //직접 연산을 정할 수 있다.
        System.out.println(Arrays.stream(arr).reduce(0,(o1,o2)->o1+o2));
    }
}
