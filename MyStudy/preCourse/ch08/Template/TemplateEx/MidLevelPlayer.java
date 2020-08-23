package com.company.ch08.Template.TemplateEx;

public class MidLevelPlayer extends Player{
    @Override
    public void run() {
        System.out.println("빠르게 달립니다");
    }

    @Override
    public void jump() {
        System.out.println("높이 jump합니다.");
    }


    @Override
    public void turn() {
        System.out.println("turn 할 줄 모르지롱.");
    }
    @Override
    public void levelMessage() {
        System.out.println("*********중급자 레벨입니다************");
    }
}
