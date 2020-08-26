package com.company.ch05.reference;

public class Student {
    int id;
    String name;

    Subject kor;
    Subject math;

    public Student(int id, String name){
        this.id = id;
        this.name = name;
//        kor = new Subject();
//        math = new Subject();
    }
//    public Student(String name,int id){
//        this.id = id;
//        this.name = name;
//        kor = new Subject();
//        math = new Subject();
//    }
    public void setKorSub(String name,int score){
        kor.subName = name;
        kor.subScore = score;

    }
    public void setMathSub(String name,int score){
        math.subName = name;
        math.subScore = score;

    }
}
