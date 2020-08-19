package com.company.ch05.BuyCoffee;

public class CoffeBuyTest {
    public static void main(String[] args) {
        Person p = new Person("김피곤",10000);
        Person p1 = new Person("이 피곤",10000);

        StarCoffee sc = new StarCoffee();
        BeanCoffee bc = new BeanCoffee();

        p.buyStarCoffee(sc,Menu.STAR_AMERICANO);
        p1.buyBeanCoffee(bc,Menu.BEAN_CAFELATTE);
    }
}
