# 틱택토하면서 알게된 점/ 궁금한점 정리
## 틱택토 구현하면서 배운 점
* 처음 플레이어의 순서를 정할 때 이렇게 코드를 구현할 수 있다.
```groovy
Player player = Math.random() > 0.5 ? playerOne : playerTwo;
            // 맨 처음 게임을 시작할 때 처음 시작하는 순서를 정하기 위한 코드
            //0.0~ 0.9 까지나오는데 0.5보다크면 ONE, 작으면 TWO
```

* 대각선을 구할 때 이런식의 코딩을 사용해서 할 수 있구나..
```groovy
// 대각선을 확인해줌 
       int sum1 = 0;
       int sum2 = 0;
       for (int i =0; i<board.length; i++){
           sum1 += board[i][i] == mark ? 1 : 0; // 왼쪽 위에서 오른쪽 아래로 대각선
           sum2 += board[i][board.length -1 -i] == mark ? 1 : 0;
           //왼쪽 아래에서  오른쪽 위로 대각선 0일때 2, 1일 때 1, 2 일때 0 이렇게 됨.
       }
```
 * tiactactoe를 선언해주는거에 대해서 이유를 생각하면서 구현하기
 * 이유 player에서도 board를 사용하여 값을 넣어주도록 해야하는데
 * board는 tictactoe안에서 생성이 되겠금 코드가 되어있어서
 * tictactoe.observe()를 활용하여 board에 접근하게 하기 위해서
 * plater에서 tictactoe를 선언해 둔 것이다. tictactoe와 player가 같은 board객체를 사용해야하니깐
 * 돌을 놓는 판이 같아야 하니깐!! 객체 지향 너무 어려워!!!
```groovy
public abstract class Player {
    String name = "default name";
    int totalWin = 0;
    TicTacToe ticTacToe;

    public void win(){
        totalWin++;
    }

    public int getTotalWin(){
        return totalWin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TicTacToe getTicTacToe() {
        return ticTacToe;
    }

    public void setTicTacToe(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
    }

    abstract void play();
}
```
* 여기서 getWinStatus를 호출하면, isMarkWin()메소드와 isMarkFull()메소드가 그안에서 호출되어 결과값을 반환하면 
getWinStatus가 조건을 확인해서 결과를 반환함.
```groovy

  switch (ticTacToe.observe().getWinnerStatus()){
     case PLAYER_ONE:
         playerOne.win();
         System.out.printf("%s has won!!",playerOne.name);
         break;
     case PLAYER_TWO:
         playerTwo.win();
         System.out.printf("%s has won!!",playerTwo.name);
         break;
     default:
         System.out.println("The game ended tie.");
         break;

  }
```
* Board 객체가 생성될 때 배열의 초기화도 같이 이루어지게 함.
```groovy

    public Board(int size){
        board = new char[size][size];
        initialize();
    }
```

## 틱택토 구현하면서 궁금한 점
* 열거형을 사용하는 이유는 뭐지 열거형이 어떤식으로 사용이되지?
* (이건 알게된점)마지막에 main메소드에서 o,x로 빙고가 완성되었을 때 o면 one이, x면 two가 승자라고 결과를 반환해줌
```groovy

    public WinnerStatus getWinnerStatus(){
        if(isMarkWin(MARKER_ONE)){
            return WinnerStatus.PLAYER_ONE;
        }else if(isMarkWin(MARKER_TWO)){
            return WinnerStatus.PLAYER_TWO;
        }else if(isMarkFull()){ //빙고가 만들어지지 않았지만, 돌을 판에 다뒀을 경우 비김.
            return WinnerStatus.TIE;
        }else{
            return WinnerStatus.NOT_FINISHED;
        }
    }
```
* board가 범위안에 들어가서 사용가능하다면, board의 요소들을 입력받은 mark(o or x)로 초기화 시킴
* 메소드들을 대부분 boolean 타입으로 해뒀는데, 이유가 뭐지? 메인메소드를 다 구현해보고 다시 확인**
```groovy

    public boolean setMark(Point move, char mark){
        if(!isPossible(move)){
            return false;
        }else{
            board[move.getY()][move.getX()] = mark;
            return true;
        }
    }
```

```groovy
while(true){
            x = (int)(Math.random() * TicTacToe.BOARD_SIZE);
            y = (int)(Math.random() * TicTacToe.BOARD_SIZE);

            Point move = new Point(x,y);
            // 이렇게 해주면 반복문을 돌릴 때 마다 객체가 생성되는거 아냐?
            // 어차피 move가 주소를 바꿔가며 받는거니깐 상관은 없을 것 같긴한데
            // 메모리가 낭비가 되는게 아닌가? 어치파 gc로 삭제하니깐 상관없는건가

            // player에서도 board를 이용해야하니깐 tictactoe.observe()가 board를 리턴하니깐
            // player클래스에서  tictactoe를 선언해준다
            if(ticTacToe.observe().isPossible(move)){
                ticTacToe.play(move,this);
                break;
            }

        }
```
```groovy
 protected final Board board = new Board(BOARD_SIZE);
    // 왜 상수로 지정하고 객체를 여기서 만들어서 사이즈를 넣어주지?
    protected Player playerOne, playerTwo;
    // 어디서 상속받길래 protected를 해줬지? 생각하면서 상속받는 부분 확인
```