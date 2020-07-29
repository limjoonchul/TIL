package com.company;

public class RingCounter {
    public static void main(String[] args) {
        int numBits = 12;
        int numCount = 19;

        int j;
        int result;
        String a2="";
        for (int i=0; i<=numCount; i++){
            j = i;
            if(j >= numBits){
                j = j%numBits;

            }
            System.out.printf("0b%12s\n", Integer.toBinaryString(1 << j));
            a2 = Integer.toBinaryString(1 << j);
        }

        result = Integer.parseInt(a2,2);
        System.out.println(result);













    }
}
