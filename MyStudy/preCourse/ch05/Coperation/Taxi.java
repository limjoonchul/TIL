package com.company.ch05.Coperation;

public class Taxi {
    int taxiNumber;
    int money;
    int passengerNum;

    public Taxi(int taxiNumber){
        this.taxiNumber = taxiNumber;
    }

    public void takeTaxi(int money){
        this.money +=money;
        passengerNum++;
    }

    public void showTaxiInfo(){
        System.out.println("택시번호 " +taxiNumber+"의 승객은 "+passengerNum+"명 이고, 돈은"+money+"입니다.");
    }
}
