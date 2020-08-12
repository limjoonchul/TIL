package com.company.Gomoku;

import com.company.Gomoku.Inputtable;

import java.util.Scanner;

public class Player implements Inputtable {
    Scanner sc = new Scanner(System.in);
    int flag = 1;
//    Gomoku g = new Gomoku();

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumWin() {
        return numWin;
    }

    public void setNumWin(int numWin) {
        this.numWin = numWin;
    }

    private int numWin;

    @Override
    public void getKeyboardInput() {
        int x = sc.nextInt();
        int y = sc.nextInt();


        if(flag == 1){
            x = sc.nextInt();
            y = sc.nextInt();
            flag = -1;


        }else if(flag == -1){
            x = sc.nextInt();
            y = sc.nextInt();
            flag = 1;
        }

    }
}
