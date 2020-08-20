package com.company.ch07.Inheritance;

public class Customer {
    protected int customerID;
    protected String customerName;
    protected String customerGrade;
    int bonusPoint; // 보너스 포인트
    double bonusRatio; //보너스 포인트 적립 비율

    public Customer(){ //생성자를 기본 초기값으로 설정해주듯이 사용하면 될 것 같다.
        customerGrade = "Silver";
        bonusRatio = 0.01; //1%로 적립

        System.out.println("customer 생성자 호출");
    }

    public Customer(int customerID,String customerName){
        this.customerID = customerID;
        this.customerName = customerName;
        customerGrade = "Silver";
        bonusRatio = 0.01; //1%로 적립
        System.out.println("customer 파라미터 생성자 호출");

    }

    public String showCustomerInfo(){
        return customerName+"님의 등급은 "+customerGrade+"이며, 적립된 보너스 포인트는 "+bonusPoint+"점 입니다.";

    }

    public int calcPrice(int price){ //지불할 가격 반환.
        bonusPoint += price * bonusRatio;
        return price;
    }


    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerGrade() {
        return customerGrade;
    }

    public void setCustomerGrade(String customerGrade) {
        this.customerGrade = customerGrade;
    }


}
