package com.company;

import java.math.BigInteger;

public class Fibonacci {

    public static void main(String[] args) {
//        https://woogyun.tistory.com/498



        int seqLength = 100;
//        for (int i =0; i<= seqLength; i++){
//            System.out.println(finbonacci(i));
//        }
        for (int i =0; i <= seqLength; i++){
            BigInteger big = new BigInteger(new Integer(i).toString());
            System.out.println(fibonacci(big));
        }
    }

//    private static int finbonacci(int i) {
//        if(i <= 2)
//            return i;
//        return finbonacci(i-1) + finbonacci(i-2);
//    }

    private static BigInteger fibonacci(BigInteger i) {
        if(i.compareTo(BigInteger.ONE.add(BigInteger.TWO)) == -1)
            return i;
        return fibonacci(i.subtract(BigInteger.ONE)).add(fibonacci(i.subtract(BigInteger.TWO)));
    }
}
