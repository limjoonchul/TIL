package com.company.ch05.DateTest;

import java.util.Calendar;

public class MyDate {
    private int year;
    private int month;
    private int day;
    private boolean is_Valid = true;


    public MyDate(int year, int month, int day){
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(year > Calendar.getInstance().get(Calendar.YEAR)){
            is_Valid = false;
        }

        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if(month>12 || month <0){
            is_Valid = false;
        }else{
            this.month = month;
        }

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        switch (month){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                if(day <= 31){
                    this.day = day;
                }else{
                    is_Valid = false;
                }
                break;
            case 4: case 6: case 9: case 11:
                if(day <=30){
                    this.day = day;
                }else{
                    is_Valid = false;
                }
            case 2:
                if ((year % 4 ==0 && year % 100 != 0)||  year % 400 ==0){
                    if(day <= 29){
                        this.day = day;
                    }else{
                        is_Valid = false;
                    }
                }
        }

    }
    public String isValid(){
        if(is_Valid){
           return "유효한 값입니다.";
        }else{
            return "범위를 벗어났습니다.";
        }
    }

}
