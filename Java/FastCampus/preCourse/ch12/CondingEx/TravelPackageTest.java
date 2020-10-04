package com.company.ch12.CondingEx;

import java.util.ArrayList;
import java.util.List;

//class ComparingAge implements {
//
//
//}
public class TravelPackageTest {
    public static void main(String[] args) {
//        List<Consumer> list = new ArrayList<>();
//        list.add(new Consumer("이순신",40));
//        list.add(new Consumer("김유신",20));
//        list.add(new Consumer("홍길동",13));
//
//
//        System.out.println("번호 이름  나이 비용");
//        int totalCost=0;
//        int num=1;
//        for(Consumer consumer : list){
//
//            System.out.println(num +"   "+consumer.getConsumerName()+" "+
//                    consumer.getConsumerAge()+"  "+consumer.getMoney());
//            num++;
//            totalCost += consumer.getMoney();
//        }
//        System.out.println();
//
//        System.out.println("총 여행비용: "+totalCost);
//
//        System.out.println();
//        list.sort((o1, o2) -> o1.getConsumerName().compareTo(o2.getConsumerName()));
//        num=1;
//        for(Consumer consumer : list){
//
//            System.out.println(num +"   "+consumer.getConsumerName()+" "+
//                    consumer.getConsumerAge()+"  "+consumer.getMoney());
//            num++;
//            totalCost += consumer.getMoney();
//        }

        Customer customerLee = new Customer("이순신",40,100);
        Customer customerKim = new Customer("김유신",20,100);
        Customer customerHong = new Customer("홍길동",13,50);

        List<Customer> customers = new ArrayList<>();
        customers.add(customerLee);
        customers.add(customerKim);
        customers.add(customerHong);

        System.out.println(customers);

        customers.stream().map(c->c.getCustomerName()).forEach(s->System.out.println(s));
        int total = customers.stream().mapToInt(c->c.getPrice()).sum();
        System.out.println(total);

        customers.stream().filter(c->c.getCustomerAge()>=20).map(c->c.getCustomerName()).sorted().forEach(s->System.out.println(s));
        // filter는 나이가 20이상인 사람들만 걸려주고, map을 통해서 customer객체 중에서 이름만 뽑아서 정렬한 다음 출력한다.

        

    }
}
