package com.company.ch12.CondingEx;

public class Customer {
//    private int order;
//    private static int customer_id = 0;
    private String customerName;
    private int customerAge;
    private int price;
//
//    public Consumer(String customerName, int customerAge) {
////        consumer_id++; //그냥 이거로만 받아오게 되면 모든 객체들이 생성된 개수가 담아져온다
////        order = consumer_id; // 이렇게 해줘야 각 객체의 순서를 정해줄 수 있다.
//        this.customerName = customerName;
//        this.customerAge = customerAge;
//        price = customerAge >= 15 ? 100 : 50;
//
//    }
//
////    public int getOrder() {
////        return order;
////    }
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public int getCustomerAge() {
//        return customerAge;
//    }
//
//    public int getPrice() {
//        return price;
//    }


    public Customer(String customerName, int customerAge, int price){

        this.customerName = customerName;
        this.customerAge = customerAge;
        this.price = price;

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return
                "customerName='" + customerName + '\'' +
                ", customerAge=" + customerAge +
                ", price=" + price
                ;
    }
}
