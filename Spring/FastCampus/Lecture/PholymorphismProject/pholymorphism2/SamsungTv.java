package com.company.pholymorphism2;

public class SamsungTv implements Tv {
    public SamsungTv() {
        System.out.println("===> Samsung TV 생성");
    }

    @Override
    public void powerOn(){
        System.out.println("SamsungTv---전원 켠다.");
    }

    @Override
    public void powerOff(){
        System.out.println("SamsungTv---전원 끈다.");
    }

    @Override
    public void volumeUp(){
        System.out.println("SamsungTv---소리 올린다.");
    }

    @Override
    public void volumeDown(){
        System.out.println("SamsungTv---소리 내린다.");
    }
}
