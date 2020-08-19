package com.company.ch05.BuyCoffee;

public class StarCoffee {
    int money;


    public String sells(int money){
        this.money += money;

        if (money == Menu.STAR_AMERICANO){
            return "별다방 아메리카노를 사드셨습니다";
        }else if(money == Menu.STAR_CAFELATTE){
            return "별다방 카페라떼를 사드셨습니다.";

        }else{
            return null;
        }

    }
}
