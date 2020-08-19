package com.company.ch05.BuyCoffee;

public class BeanCoffee {
    int money;

    public String sells(int money){
        this.money += money;
        if(money == Menu.BEAN_AMERICANO){
            return "빈다방에서 아메리카노를 사먹다.";
        }else if(money == Menu.BEAN_CAFELATTE){
            return "빈다방 카페라떼";
        }else{
            return null;
        }
    }
}
