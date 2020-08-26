package com.company.ch08.Template;

public abstract class Car {
    public abstract void drive();
    public abstract void stop();

    public void startCar(){
        System.out.println("시동을 켭니다.");
    }
    public void turnOff(){
        System.out.println("시동을 끕니다.");
    }
    public void washCar(){ } //아무기능이없어 여기서는 수행을 하지 않고 하위클래스에서 재정의해서 사용.
    // 모든 하위클래스에서 구현을해야하는 거라면 abstract를 사용하지만
    // 모든 하위클래에서 사용하지 않으면 이렇게 사용한다.
    //기능의 확장이 가능함
    final public void run(){ //재정의할 수 없게 final 키워드 사용.
        startCar();
        drive();
        stop();
        turnOff();
        washCar();
    }
}
