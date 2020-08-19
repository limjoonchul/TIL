package com.company.ch05.thisEx;

public class PerseonTest {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.showInfo();

        Person p2 = new Person("lee",20);
        p2.showInfo();
        System.out.println(p2);

        Person p = p2.getSelf();
        System.out.println(p);
    }
}
