package com.company.Gomoku;

public class Gomoku implements Simulatable, Winnable, Playable, Printable {
    Player JeongHoon;
    Player JoonChul;
    int [][] array;
//    int flag = 1;
    @Override
    public void printStatus() {

    }

    @Override
    public void initialize() { //초기설정

    }

    @Override
    public void isFinished() {

    }

    @Override
    public void reset() {

    }

    @Override
    public Player getWinner() {
        return null;
    }

    @Override
    public void play(Player player, Position pos) {
        array = new int[15][15];
        player.setName("정훈");
        player.getKeyboardInput();


    }
}
