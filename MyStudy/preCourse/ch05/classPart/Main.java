package com.company.ch05.classPart;
class Man{
    int age =40;
    String name = "James";
    boolean marry = true;
    int children = 3;

}
class Order{
    long orderNum;
    String orderId;
    String orderDate;
    String orderName;
    String prodNum;
    String address;
}
public class Main {

    public static void main(String[] args) {
        Man m = new Man();
        m.age = 45;
        System.out.printf("나이 : %d\n",m.age);
        System.out.printf("이름 : %s\n",m.name);
        System.out.printf("결혼 여부 : %b\n",m.marry);
        System.out.printf("자녀 수 : %d\n",m.children);

        Order or = new Order();
        or.orderNum = 201907210001L;
        or.orderId = "abc123";
        or.orderDate = "2019년07월21일";
        or.orderName = "홍길순";
        or.prodNum = "PD0345-12";
        or.address = "서울시 영등포구 여의도동 20번지";

        System.out.println("주문 번호 :"+or.orderNum);
        System.out.println("주문자 아이디 :"+or.orderId);
        System.out.println("주문 날짜 :"+or.orderDate);
        System.out.println("주문자 이름 :"+or.orderName);
        System.out.println("주문 상품 번호 :"+or.prodNum);
        System.out.println("배송 주소 :"+or.address);



    }
}
