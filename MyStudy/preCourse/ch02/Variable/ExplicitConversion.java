package com.company.ch02.Variable;

public class ExplicitConversion {
    public static void main(String[] args) {
        int i = 1000;
        byte bNum = (byte)i;
        //데이터의 유실이 발생할 수 있다.
        System.out.println(bNum);

        double dNum1 = 1.2;
        float fNum = 0.9f;

        int iNum1 = (int)(dNum1 + fNum);
        int iNum2 = (int)dNum1 + (int)fNum;
        // 두개의 결과 값이 다름




    }
}
