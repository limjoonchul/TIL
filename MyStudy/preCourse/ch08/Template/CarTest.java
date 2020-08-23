package com.company.ch08.Template;

public class CarTest {
    public static void main(String[] args) {
        Car aiCar = new AICar();
        aiCar.run();
        System.out.println("============");
        Car manualCarnuCar = new ManualCar();
        manualCarnuCar.run();

    }
}
