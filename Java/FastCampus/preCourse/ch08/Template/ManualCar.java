package com.company.ch08.Template;

public class ManualCar extends Car {
    @Override
    public void drive() {
        System.out.println("운전자가 주행합니다..");
        System.out.println("운전자가 방향을 바꿉니다.");
    }

    @Override
    public void stop() {
        System.out.println("운전자가 멈춥니다.");
    }
}
