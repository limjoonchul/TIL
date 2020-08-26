package com.company.ch10.CodingEX;



public class MydateTest {
    public static void main(String[] args) {
        Mydate mydate1 = new Mydate(2020,7,20);
        Mydate mydate2 = new Mydate(2020,7,20);

        System.out.println(mydate1.equals(mydate2));
        System.out.println(mydate1.hashCode());
        System.out.println(mydate2.hashCode());


    }
}
