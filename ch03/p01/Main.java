package com.company;

import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * 클래스 - 객체를 생성하기 위한 설계도(class)
 * 객체 - 클래스를 구체화하여 값으로 생성된 것(object, instance)
 * 클래스를 객체로 만드는 과정 - > instanciation (인스턴스화)
 */

class Car{ // 클래스 이름은 보통 PascalCase로 적는다. CarShop
    int speed = 0; // 속성, 멤버 변수라고도 한다.
    // 용어 중요**
    // 속성: atrribute, field
    // 멤버 변수 : member variable

    void move(){ // **메소드 (method)**, (가끔 멤버 함수), (가끔 함수)
        speed = 10; //행위를 구현, 속성을 변경
    }
}

public class Main {

    public static void main(String[] args) {
        Car carOne = new Car(); // new 키워드로 클래스에서 객체 생성
        System.out.println(carOne.speed); // .으로 속성 접근 가능
        carOne.move(); // 메소드 호출(method call) 이라고 부름.
        System.out.println(carOne.speed);

        Car carTwo = new Car();
        System.out.println(carTwo.speed); // speed=0 독립적인 객체를 의미.

        //STRING은 이뮤터블이기때문에 값이 변하지 않지만, 클래스는 대부분 뮤터뷸이기때문에 객체 참조에 대한 문제가 발생한다. 중요**
        Car carThree = carOne; // 참조형 객체이기 때문에 new일때만 새로운 객체를 생성하고 ,이렇게하면 같은 객체를 가르킨다.
        System.out.println(carThree.speed);
        carThree.speed = 5;
        System.out.println(carThree.speed);// 5
        System.out.println(carOne.speed); // 5



    }
}
