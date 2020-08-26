package com.company.ch05.Coperation;

public class TakeTransTest {
    public static void main(String[] args) {
        Student student = new Student("James",5000);
        Student student1 = new Student("Tomas",10000);
        Student student2 = new Student("Eric",10000);

        Bus bus100 = new Bus(100);
        Subway subwayGreen = new Subway(2);
        Subway subwayBlue = new Subway(4);
        Taxi taxi3699 = new Taxi(3699);

        student.takeBus(bus100);
        student1.takeSubway(subwayGreen);
        student2.takeTaxi(taxi3699);

        student.showInfo();
        student1.showInfo();
        student2.showInfo();

        bus100.showBusInfo();

        subwayGreen.showSubwayInfo();
        subwayBlue.showSubwayInfo();

        taxi3699.showTaxiInfo();
    }
}
