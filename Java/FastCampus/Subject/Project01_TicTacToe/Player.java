package com.company.Tictacto;



public abstract class Player {
    int totalWin = 0;
    String name = "default name";
    TicTacToe ticTacToe; //여기서 왜 TicTacToe 객체를 선언하는지 모르겠음

    //getter/setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 이긴 객체가 호출 했을 때 승리 수 증가
    public void win(){
        totalWin++;
    }

    // win()메소드를 호출해가면서 멤버변수에 totalWin이 쌓이니깐 최종결과에 이겼던 횟수 출력할 때 사용.
    public int getTotalWin(){
        return totalWin;
    }

    //Player에서 getTicTacToe를 하면 객체를 반환한다 아직까진 정확한 사용 의미를 잘 모르겠음.
    // getter/setter
    public TicTacToe getTicTacToe(){
        return ticTacToe;
    }


    public void setTicTacToe(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
    }

    //추상메소드 play
    abstract void play();


}
