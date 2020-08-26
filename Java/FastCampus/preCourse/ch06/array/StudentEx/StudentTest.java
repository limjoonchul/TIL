package com.company.ch06.array.StudentEx;

import com.company.ch06.array.StudentEx.Student;

public class StudentTest {
    public static void main(String[] args) {
        Student student = new Student(1,"kim");
        student.addSubject("영어",95);
        student.addSubject("국어",85);
        student.addSubject("수학",70);
        student.showStudentInfo();

        System.out.println("=======================");

        Student studentLee = new Student(1002,"lee");
        studentLee.addSubject("영어",100);
        studentLee.addSubject("국어",90);
        studentLee.addSubject("수학",80);
        studentLee.showStudentInfo();
    }
}
