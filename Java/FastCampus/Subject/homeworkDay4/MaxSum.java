package com.company;

public class MaxSum {
    public static void main(String[] args) {
        int [] integers = {-4, 7, 14, 9, -5, 4, 16, -22, 31};
        int max = 0;

        for (int i=0; i<integers.length; i++){
            int sum = 0;
            for (int j=i; j<integers.length; j++){
                sum += integers[j];
            }
            if(max < sum){
                max = sum;
            }
        }
        System.out.println(max);
    }
}
