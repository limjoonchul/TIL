package com.company.ch05.classPart;

public class Student {

    int stuID;
    String stuName;
    String address;

    public Student(int id, String name){
        stuID = id;
        stuName = name;
    }


    public void showInfo(){
        System.out.println(stuName + "," + address);
    }
    public String getStudentName(){
        return stuName;
    }
}
