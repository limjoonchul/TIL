package com.company.ch06.array.StudentEx;


import java.util.ArrayList;

public class Student {
    public int studentID;
    public String name;
    ArrayList<Subject> list;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;

        list = new ArrayList<Subject>();
    }

    public void addSubject(String subjectName, int score) {
        Subject subject = new Subject(subjectName,score);
        list.add(subject);
    }
    public void showStudentInfo(){
        int toal = 0;
        for (int i=0; i<list.size(); i++){
            toal += list.get(i).getScore();
            System.out.println(name+"학생의"+list.get(i).getSubjectName()+"과목의 성적은"+list.get(i).getScore()+"입니다.");
        }
        System.out.println("학번이 "+studentID+"인 "+ name+"학생의 총점은 "+toal+"입니다.");

    }
}
