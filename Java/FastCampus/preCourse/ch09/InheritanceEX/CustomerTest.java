package com.company.ch09.InheritanceEX;

public class CustomerTest {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.buy();
        customer.sell();
        customer.order();
        customer.sayHello();

        System.out.println("=============");

        Buy buy = customer;
        buy.buy();
        buy.order();

        System.out.println("=============");
        Sell sell = customer;
        sell.sell();
        sell.order();

        //구현된 인스턴스의 것이 출력된다.


    }
}
