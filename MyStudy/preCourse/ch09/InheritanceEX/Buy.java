package com.company.ch09.InheritanceEX;

public interface Buy {

    void buy();

    default void order(){
        System.out.println("구매 주문");
    }
}
