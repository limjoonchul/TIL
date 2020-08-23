package com.company.ch08.Abstract;

public abstract class Computer {

    public abstract void display();
    public abstract void typing(); //하위클래스에서 구현됨.

    public void turnOn(){
        System.out.println("전원을 켭니다.");
    }
    public void turnOff(){
        System.out.println("전원을 끕니다.");
    }

}
