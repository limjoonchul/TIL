package com.company.ch08.Template.TemplateEx;

public abstract class Player {
    public abstract void run();
    public abstract void jump();
    public abstract void turn();
    public abstract void levelMessage();


    public void go(int num){
        levelMessage();
        run();
        for (int i=0; i<num; i++){
            jump();
        }
        turn();
    }
}
