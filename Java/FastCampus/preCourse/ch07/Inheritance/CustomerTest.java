package com.company.ch07.Inheritance;

public class CustomerTest {
    public static void main(String[] args) {
//        Customer customerLee = new Customer();
//        customerLee.setCustomerName("이순신");
//        customerLee.setCustomerID(10010);
//        customerLee.bonusPoint=1000;
//
//        System.out.println(customerLee.showCustomerInfo());

        VIPCustomer customerKim = new VIPCustomer(10020,"김유신");
//        customerKim.setCustomerName("김유신");
//        customerKim.setCustomerID(10020);
        customerKim.bonusPoint = 10000;

        System.out.println(customerKim.showCustomerInfo());


        Customer vc = new VIPCustomer();
        // 여기서 vc가 가리키는 것은?
        /**
         * VIPCustomer() 생성자의 호출로 인스턴스는 모두 생성되었지만
         * 타입이 Customer 이므로 접근 할 수 있는 변수나 메소드는 Customer의 변수와 메서드이다.
         */
    }
}
