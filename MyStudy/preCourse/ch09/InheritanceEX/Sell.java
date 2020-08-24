package com.company.ch09.InheritanceEX;

public interface Sell {
    void sell();
    default void order(){
        System.out.println("판매 주문");
    }
}
