package com.company.ch05.reference;

public class StudentTest {
    public static void main(String[] args) {
        Student lee = new Student(100,"lee");
        System.out.println(lee.id);
        System.out.println(lee.name);
        lee.kor = new Subject("영어",75);
        System.out.println(lee.kor.subName);
        lee.setKorSub("국어",85);
    }
}
