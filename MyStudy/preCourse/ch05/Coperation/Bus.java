package com.company.ch05.Coperation;

public class Bus {
    int busNumber;
    int passsengerCount;
    int money;

    public Bus(int busNumber){
        this.busNumber = busNumber;
    }

    public  void take(int money){ //승차
        this.money +=money;
        passsengerCount++;
    }

    public  void showBusInfo(){
        System.out.println(busNumber + "번 버스의 승객은 "+passsengerCount+"이고, 수입은"+money+"입니다." );

    }
}
