#Java 1-3(2020-07-30)
####ch02_s03
## 자료의 입출력(Inputs and Outputs)
### 출력 메소드
* 포맷 문자열을 이용한 문자열/기본형 출력
* 객체를 직접 출력
#### printStream의 메소드
 * print(): 
    * public void print(Object obj)
    * obj를 스트림으로 출력한다.
 * println():
    * public void println(Object x)
    * x를 스트림으로 출력하고 줄바꿈한다.
 * printf() :
    * public PrintStream printf(String format, Object ... args)
    * Format 맞추어 args를 출력한다.
    * 정수 포맷팅
        ````
        - System.out.printf("%5d.\n", 10); //5d 5자리 확보 ...10
        - System.out.printf("%3d\n",104294); //자릿수가 더크면 그대로 출력이된다. 3칸은 최소한 확보하는 것
        ````
    * 실수 포맷팅
        ````
       - System.out.printf("%5d.\n", 10); //5d 5자리 확보 ...10
       - System.out.printf("%3d\n",104294); //자릿수가 더크면 그대로 출력이된다. 3칸은 최소한 확보하는 것
        ````
### 입력 메소드
 * Scanner 클래스를 이용하여 입력받을 수 있다.
   ````
   import java.util.Scanner;
   
   ...
   
   Scanner scanner = new Scanner(System.in);
   ````
 * Scanner 클래스의 주요 메소드
   * next(): 공백을 기준으로 한 단어씩 입력받는다.
   * nextLine(): 한 줄 전체를 입력받는다. (enter로 구별)
   * nextInt(): (공백을 기준으로) int 값을 입력 받는다.
   * nextDouble : double 값을 입력 받는다.
   * close() : 입력 스트림을 종료한다.
    
