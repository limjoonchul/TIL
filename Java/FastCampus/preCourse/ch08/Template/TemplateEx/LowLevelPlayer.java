package com.company.ch08.Template.TemplateEx;

public class LowLevelPlayer extends Player {
    @Override
    public void run() {
        System.out.println("천천히 달립니다.");
    }

    @Override
    public void jump() {
        System.out.println("jump할 줄 모르지롱.");
    }

    @Override
    public void turn() {
        System.out.println("turn 할 줄 모르지롱.");
    }

    @Override
    public void levelMessage() {
        System.out.println("*********초보자 레벨입니다************");
    }
}
