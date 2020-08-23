package com.company.ch08.Template.gameLevel;

public class BeginnerLevel extends PlayerLevel{

    @Override
    public void run() {
        System.out.println("천천히 달립니다.");
    }

    @Override
    public void jump() {
        System.out.println("jump를 못합니다.");
    }

    @Override
    public void turn() {
        System.out.println("turn할 줄 모릅니다.");
    }

    @Override
    public void showLevelMessage() {
        System.out.println("****초급자 레벨입니다******");
    }
}
