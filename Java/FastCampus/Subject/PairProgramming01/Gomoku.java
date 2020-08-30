package com.company.homeworkReview.PairProgramming01;

import java.util.Scanner;

public class Gomoku implements Simulatable, Winnable, Playable, Printable {

    Player player1;
    Player player2;

    Position position;

    String[][] board;
    
    
    public void startGame(){
        initialize();
        while(true){

        }
    }

    @Override
    public void initialize() {
        Scanner sc = new Scanner(System.in);
        player1.setName(sc.nextLine());
        player2.setName(sc.nextLine());

        board = new String[15][15];
        for (int i = 0; i<board[0].length; i++){
            for (int j=0; j<board[1].length; j++){
                board[i][j] = "ㅁ";
            }
        }
    }

    @Override
    public void isFinished() {

    }

    @Override
    public void reset() {
        board = new String[15][15];
        for (int i = 0; i<board[0].length; i++){
            for (int j=0; j<board[1].length; j++){
                this.board[i][j] = "ㅁ";
            }
        }
    }

    @Override
    public Player getWinner() {
        return null;
    }

    @Override
    public void printStatus() {
        for (int i = 0; i<board[0].length; i++){
            for (int j=0; j<board[1].length; j++){
                System.out.print(board[i][j]+' ');
            }
            System.out.println();
        }
    }
    @Override
    public void play(Player player, Position pos) {
        position = pos;

//        if(player.trun){
//            player1.getKeyboardInput();
//            board[pos.getX()][pos.getY()] = "X";
//            player.trun = false;
//        }else{
//            player2.getKeyboardInput();
//            board[pos.getX()][pos.getY()] = "O";
//            player.trun = true;
//        }

    }
}
