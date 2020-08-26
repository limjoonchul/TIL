package com.company.ch05.StaticEx;

public class Student {
    private static int serialNum = 1000; //클래스 멤버 변수 인스턴스들이 공통으로 사용할 수 있음
    // 외부에서 바꿀 수 없게 private으로 설정하는게 좋음.
    private int stuID;
    String stuName;
    String address;

    public static int getSerialNum() {
        return serialNum;
    }

    public static void setSerialNum(int serialNum) {
        Student.serialNum = serialNum;
    }

    public int getStuID() {
        return stuID;
    }

    public void setStuID(int stuID) {
        this.stuID = stuID;
    }

    public Student(String name){
        stuName = name;
        serialNum++;
        stuID = serialNum;
    }

    public Student(int id, String name){
        stuID = id;
        stuName = name;
        address="주소 없음";
        serialNum++;
        stuID = serialNum;
    }


    public void showInfo(){
        System.out.println(stuName + "," + address);
    }
    public String getStudentName(){
        return stuName;
    }
}
