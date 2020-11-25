package com.company.pholymorphism3;

public class GoogleTv implements Tv {
    public GoogleTv() {
        System.out.println("===> GoogleTv 생성");
    }

    @Override
    public void powerOn(){
        System.out.println("GoogleTv---전원 켠다.");
    }

    @Override
    public void powerOff(){
        System.out.println("GoogleTv---전원 끈다.");
    }

    @Override
    public void volumeUp(){
        System.out.println("GoogleTv---소리 올린다.");
    }

    @Override
    public void volumeDown(){
        System.out.println("GoogleTv---소리 내린다.");
    }
}
