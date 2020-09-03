package com.company.Tictacto;


import java.util.Scanner;


//Player 클래스를 상속받음
public class HumanPlayer extends Player{
    @Override
    void play() {
        Scanner sc = new Scanner(System.in);


        while(true){
            System.out.printf("please input %s's move(x,y)\n",name);

            // 왜 x,y에 값을 받아서 -1을 해주는지 아직까지 모르겠음 더 봐야함.
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;

            // 좌표를 입력받아서 Point클래스에 값을 넣어준다.
            Point move = new Point(x,y);

            /**
             * ticTacToe.observe()가 board를 반환함 그래서 board.isPossible(move)와 같은 의미인데
             * isPossible은 board에 값이 들어갈 수 있는지 배열의 범위 안에 들어가는지 체크를 해준다.
             * 여기서 tictaetoe의 play는 값을 설정해주는 것
             */
            if(ticTacToe.observe().isPossible(move)){
                ticTacToe.play(move,this);
                break;
            }
        }
    }
}
