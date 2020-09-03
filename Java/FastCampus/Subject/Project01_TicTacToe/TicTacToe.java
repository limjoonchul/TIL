package com.company.Tictacto;

/**
 *
 */
public class TicTacToe implements  Initailizable,Observerable<Board>,Playable<Point> {
    static final int BOARD_SIZE = 3;
    protected final Board board = new Board(BOARD_SIZE);
    protected Player playOne,playTwo;

    public TicTacToe(Player playOne, Player playTwo){
        setPlayOne(playOne);
        setPlayTwo(playTwo);
    }
    @Override
    public void initialize() {
        board.initialize();
    }


    public void setPlayOne(Player player) {
        playOne = player;
        playOne.setTicTacToe(this);
    }

    public void setPlayTwo(Player playTwo) {
        this.playTwo = playTwo;
        playTwo.setTicTacToe(this);
    }

    @Override
    public Board observe() {
        return board;
    }

    //여기서 플레이어가 플레이어1이면 'o' 아니면 'x'를 넣어 줌.
    @Override
    public boolean play(Point move, Player player) {
        // 1.2중 3중 보안 값이 들어오는 것에 대해서 정확한지 체크해주는것 자주 사용할 수록 좋음 (무분별한 사용은 당연히 안되겠지만) 그정도로 생각하면됨
        // 2.그래서 if문을 통과하면 사용가능한 좌표이니깐 파라미터로 받음 player가 playone 이면 MARKER_ONE이 'O' 아니면 MAKER_TWO는 'x'
        // 3.그 다음 board에 좌표에 플레이어에 따라 'o','x' 를 넣어줌 (설정해준다)
        if(!board.isPossible(move)){ // -> 1
            return false;
        }else{
            char mark = player == playOne ? Board.MARKER_ONE : Board.MARKER_TWO;//->2
            board.setMark(move,mark);//->3
            return true;
        }
    }
}
