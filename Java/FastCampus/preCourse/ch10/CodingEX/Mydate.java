package com.company.ch10.CodingEX;

public class Mydate{
    int year;
    int month;
    int day;
    String str="";
    public Mydate(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;

    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Mydate){
            Mydate mydate = (Mydate)obj;
            if(this.year == mydate.year & this.month == mydate.month & this.day == mydate.day){
                return true;
            }
            else return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
//        str = Integer.toString(year);
//        str += Integer.toString(month);
//        str+=Integer.toString(day);
//
//
//        return Integer.parseInt(str);
        return day*11 +  month*101+ year * 1001;
    }

}
