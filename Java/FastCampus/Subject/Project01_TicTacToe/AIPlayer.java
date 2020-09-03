package com.company.Tictacto;





public class AIPlayer extends Player{
    @Override
    void play() {
        int x,y;


        while(true){
            /**
             * Math.random 의 범위는 0 <= 실수 범위(0.12..., 0.245..) < 1 의 범위를 갖습니다.
             * 즉 0을 포함하면서 1을 포함하지 않는 0.9999999 사이의 실수값을 무작위로 생성하는 메서드입니다.
             * 0~2까지 랜덤으로 x,y에 값이 들어감.
             */
            x = (int)(Math.random() * TicTacToe.BOARD_SIZE); //이런 표현법은 아직 내가 생각해서 사용하기엔 부족하니깐 이런 방법이 있다고 생각하고 넘어가자
            y = (int)(Math.random() * TicTacToe.BOARD_SIZE);

            Point move = new Point(x,y);
            if(ticTacToe.observe().isPossible(move)){
                ticTacToe.play(move,this);
                break;
            }
        }
        System.out.printf("AI Player %s's input is :(%d %d)\n",name, x+1, y+1);
    }
}
