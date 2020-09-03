package com.company.Tictacto;

public class Board implements Initailizable {

    enum WinnerStatus{
        NOT_FINISHED,TIE,PLAYER_ONE,PLAYER_TWO;
    }

    static final  char BLANK ='-';
    static final  char MARKER_ONE ='o';
    static final  char MARKER_TWO ='x';

    final char [][] board; //blankfinal

    public Board(int size) {
        board=new char[size][size];
        initialize();
    }

    @Override
    public void initialize() {
        for (int i = 0; i<board.length; i++){
            for (int j=0; i<board[0].length;j++){
                board[i][j]= BLANK;
            }
        }
    }

    public boolean isPossible(Point move){
        if(move.getX() < 0 || move.getX() >=board.length){
            return false;
        }else if(move.getY() < 0 || move.getY() >= board.length){
            return false;
        }else{
            return board[move.getY()][move.getX()] == BLANK;
        }
    }

    // 파라미터로 좌표랑 mark('o','x')를 받고 또 if문에서 보안 값이 가능한 것인지 아닌지 체크를 해줌
    // 조건문을 통과하면 board배열에 해당 x,y좌표에 mark를 넣어줌 근데 왜 여기서 x,y를 바꿔서 넣어주는지 이해 안감.
    public boolean setMark(Point move, char mark){
        if(!isPossible(move)){
            return false;
        }else{
            board[move.getY()][move.getX()] = mark;
            return true;
        }
    }

    // board의 값들을 보여주는 메소드
    public void showBoard(){
        for (char[] chars : board){
            for (char mark : chars){
                System.out.printf("%s",mark);
            }
            System.out.println();
        }
    }

    //이겼는지 상태확인을 해주는 메소드
    public WinnerStatus getWinnerStatus(){
        if(isMarkWin(MARKER_ONE)){
            return WinnerStatus.PLAYER_ONE;
        }else if(isMarkWin(MARKER_TWO)){
            return WinnerStatus.PLAYER_TWO;
        }else if(isMarkFull()){
            return WinnerStatus.TIE;
        }else{
            return WinnerStatus.NOT_FINISHED;
        }
    }

    //
    public boolean isMarkWin(char mark){
        //가로 행들이 {o o o}, {x x x}이 되면 이기는 것이니깐
        // 위 처럼만들어질 때 sum에 계속 값이 쌓이니깐 sum=3이되었을 때 빙고가 되는 것이니깐 true를 반환
        for (char[] chars : board){
            int sum = 0;
            for (int j = 0; j<board[0].length; j++){
                sum += chars[j] == mark? 1 : 0;
            }
            if(sum == board.length){
                return true;
            }
        }
        for (int j =0; j<board[0].length; j++){
            int sum = 0;
            for (char[] chars : board){
                sum+= chars[j] == mark ? 1 : 0;
            }
            if (sum == board.length){
                return true;
            }
        }

        int sum1 = 0;
        int sum2 = 0;
        for (int i =0; i< board.length; i++){
            sum1 += board[i][i] == mark ? 1 : 0;
            sum2 += board[i][board.length - 1 + i] == mark ? 1 : 0;
            // i가 0 1 2로 증가할 때 2 1 0 으로 감소하게 하기 위함.
        }
        return sum1 == board.length || sum2 == board.length;
    }

    //board가 꽉 찼는지 확인해주는 메소드
    private boolean isMarkFull(){
        for (char[] chars : board){
            for (int j =0 ; j<board[0].length; j++){
                if(chars[j] == BLANK){
                    return false;
                }
            }
        }
        return true;
    }
}
