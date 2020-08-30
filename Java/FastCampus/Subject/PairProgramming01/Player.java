package com.company.homeworkReview.PairProgramming01;

import java.util.Scanner;

public class Player implements Inputtable {
    private  String name;
    private int numWin;

    int trun = 1;

    Gomoku g;
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

    @Override
    public void getKeyboardInput() {
        Scanner sc = new Scanner(System.in);
        Position p;

        int x = sc.nextInt();
        int y = sc.nextInt();

        p = new Position(x,y);


        if (sc.nextLine() == "q"){
            System.out.println("게임을 종료합니다");
        }




//        while(true){
//            int x = sc.nextInt();
//            int y = sc.nextInt();
//            if(x<0 || x>14 || y<0 || y>14){
//                System.out.println("범위를 벗어났습니다. 다시 입력하세요");
//                continue;
//            }else if(!g.board.equals("ㅁ")){
//                System.out.println("이미 돌이 존재하는 자리입니다. 다시 입력하세요");
//                continue;
//            }else{
//                p = new Position(x,y);
//                break;
//
//            }
//        }


    }
}
