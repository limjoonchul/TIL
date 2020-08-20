package com.company.ch07.Inheritance;

public class OverridingTest {
    public static void main(String[] args) {
        Customer customerLee = new Customer(10010,"이순신");
        customerLee.bonusPoint=1000;


        VIPCustomer customerKim = new VIPCustomer(10020,"김유신");
        customerKim.bonusPoint = 10000;

        int priceLee = customerLee.calcPrice(10000);
        int priceKim = customerKim.calcPrice(10000);
        System.out.println(customerLee.showCustomerInfo() +"지불금액은 "+priceLee);

        System.out.println(customerKim.showCustomerInfo()+"지불금액은 "+priceKim);

        Customer customerNo = new VIPCustomer(10030,"나몰라");
        customerNo.bonusPoint=10000;
        System.out.println(customerNo.showCustomerInfo()+"지불금액은 "+customerNo.calcPrice(10000));
        // 지불금액이 예상하기로 10000이 나와야하지만 결과는 9000이나옴 이유는 vipcustomer의 calcPrice()메소드가 호출되었기 때문에 이것을 가상메소드호출이라 한다.
    }
}
