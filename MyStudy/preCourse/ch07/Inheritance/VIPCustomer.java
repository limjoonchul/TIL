package com.company.ch07.Inheritance;

public class VIPCustomer extends Customer {

    double salesRatio;
    private int agentID;

    public VIPCustomer(){

        customerGrade="VIP";
        bonusRatio = 0.05;
        salesRatio = 0.1;
        System.out.println("vipcustomer 생성자 호출");
    }



    public VIPCustomer(int customerID, String customerName){
        super(customerID,customerName);
        customerGrade="VIP";
        bonusRatio = 0.05;
        salesRatio = 0.1;

        System.out.println("vipcustomer 파라미터생성자 호출");
    }

    @Override  //에노테이션 꼭 써야하는건 아니다. 역할은 컴파일 오류를 막아주고, 컴파일러에게 정보를 전달해줌.
    public int calcPrice(int price) {
        bonusPoint += price * bonusRatio;

        return price -(int)(price * salesRatio); //salesRatio가 double형이기 때문에 다운 캐스팅을 해줌.
    }

}
