package com.company.ch05.hiding;

public class MyDate {
    private int day;
    private int month;
    private int year;

    private boolean isVaild = true;

//    public MyDate(int year, int month, int day) {
//        this.year = year;
//        this.month = month;
//        this.day = day;
//    }

    public  void setDay(int day){
        this.day = day;
    }
    public int getDay(){
        return day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if(month <1 || month > 12){
            isVaild = false;
        }
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void showDate(){
        if (isVaild){
            System.out.println(this.year + "년" + this.month + "월" +this.day+"일");
        }else{
            System.out.println("유효하지 않은 범위 입니다.");
        }

    }


}
