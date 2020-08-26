package com.company.ch05.StaticEx;

public class StudentIdTest {
    public static void main(String[] args) {
        Student studentLee = new Student("lee");
//        System.out.println(Student.serialNum);
        System.out.println(Student.getSerialNum());
        Student studentKim = new Student("Kim");

//        System.out.println(Student.serialNum);
//        System.out.println(Student.serialNum);

        System.out.println(studentLee.getStuID());
        System.out.println(studentKim.getStuID());



    }
}
