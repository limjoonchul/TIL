package com.company.ch05.BuyCoffee;

public class Person {
    String name;
    int money;

    public Person(String name, int money){
        this.name = name;
        this.money = money;
    }

    public void buyStarCoffee(StarCoffee sc, int money){
        String message = sc.sells(money);

        if(message != null){
            this.money -=money;
            System.out.println(name+","+money+","+message);
        }
    }

    public void buyBeanCoffee(BeanCoffee bc, int money){
        String message = bc.sells(money);

        if(message != null){
            this.money -=money;
            System.out.println(name+","+money+","+message);
        }
    }
}
