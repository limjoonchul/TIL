package com.company.ch05.Coperation;

public class Subway {
    int lineNumber;
    int passsengerCount;
    int money;

    public Subway(int lineNumber){
        this.lineNumber = lineNumber;
    }

    public void take(int money){
        this.money += money;
        passsengerCount++;
    }
    public  void showSubwayInfo(){
        System.out.println(lineNumber + "번 지하철의 승객은 "+passsengerCount+"이고, 수입은"+money+"입니다." );

    }
}
