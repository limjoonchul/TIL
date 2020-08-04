# java-TIL
JAVA 오늘 배운것(TIL)

# 자바 1주- 1번째(2020-07-28)
## java_first
  - Hello World 출력
## ch02_s01
  - 변수선언 방법, code convention
## ch02_s02
  - 자료형, 자료형의 종류(기본형(Primitive Type), 참조형(Reference Type))
  - 기본형 자료형 : 정수형, 실수형, 문자형, 논리형</br>
        - 정수형 : Byte(1Byte), Short(2Byte), Int(4Byte), Long(8Byte)
# 자바 1주- 2번째(2020-07-29)
## ch02_s03
  * 입출력 메소드
  ````
  - 입력 : Scanner를 사용해서 키보드로 값 입력시킬 수 있다.
    - .next() : 입력받을 내용이 있을 때까지 기다린다. Blocking 메소드라고도 함.
                    공백으로 문자열 구분
    - .nextLine() : 한줄 전체를 받음, \n(enter)로 구분됨.
    - .nextInt() : 정수형을 입력받음, 공백으로 구분되는 정수.
  - 출력 : 
    - print() :   System.out.print("123121\r\n"); r = 캐리지 리턴  첫 글자 자리로 보냄, n = newline을 의미
  
    - println() : System.out.println(1523.22); ln = new line은 줄 바꿈을 의미한다.
  
    - printf() : System.out.printf("%s is for string\n","string"); new line이 자동으로 안붙여짐
                 System.out.printf("%f\n",14.23f); //꼭 float은 아니다 f가 flaot이 아니라
                 floating number float과 double 둘다 나타내야기 때문에
    - 정수 포맷팅
           - System.out.printf("%5d.\n", 10); //5d 5자리 확보 ...10
           - System.out.printf("%3d\n",104294); //자릿수가 더크면 그대로 출력이된다. 3칸은 최소한 확보하는 것
    - 실수 포맷팅
           - System.out.printf("%5.4f\n", 152523.456228); //소수점 아래에 4자리까지만 출력 5는 최소한 5칸 확보 소숫점도 한칸 차지한다.
           - System.out.printf("%5.2f.\n",1.425); //소수점 포함 자릿수를 만든다.
  ````
## ch02_s04
  * 연산자(operator), 피연산자(operand) -> 연산식(Expression : 현업에서 많이 쓰이는 용어)

  1.사칙 연산자
  ````
 z = x + y 일 때, 1.+ : 연산자,  x y: 피연산자, x+y : 연산식 / 2. = : 연산자, z: 피연산자, x+y: 피연산자
 1.1 정수형 사칙 연산
     150 / 8 - 정수 나누기 -> 몫
     150 % 8 - modulus연산,나머지 값이 나옴
 1.2 실수형 사칙 연산
     10.5f + 12.3 - float, double 같이 연산되면 double로 변환 후 연산
     10.4-50 - 실수형, 정수형을 같이 연산하면 실수형으로 변환 후 연산
     5.2 / 1.2 - 몫이 아니고 실수 값으로 계산 됨
     5.2 % 1.2 - 실수 나눗셈도 modulus 연산으로 나머지 계산 가능 연산 이루어짐

 * 사칙연산의 주의사항
     -가장 작은 값이됨 가장 큰 값에서 overflow 발생 시 가장 작은 값이 됨
     Integer.MAX_VALUE + 1 - //-2147483648 오버풀로우 발생.
     Integer.MAX_VALUE - //2147483647
     Integer.MAX_VALUE / 2 * 3 - //-1073741827 오버플로우 발생.

     int maxVal  = 0b01111111111111111111111111111; //b다음에 0이면 양수 1이면 음수 32비트
     int maxVal1 = 0b10000000000000000000000000001; //2의 보수
     int minVal2 = 0b10000000000000000000000000000; //1의보수
  
 * 중요!
     (6-5.9) * 10 - // 0.9999999999999964
     Math.floor((6-5.9) * 10)) -  // 0.0
     - 내림연산했을때 우리는 안에 계산식이 1이나오길 (예상)기대하지만 0.9999가나와서 내림연산했을때 0.0이 나옴
     (정밀도 문제 6과 관련했을 때 많이 발생)
     (40 / 0.0) 정수형을 실수형으로 나눌때 :
     - infinity 수식은 문제 없지만 값에서 문제가 생길경우 발생 연산 도중에 한번 infinity가 발생하면 최종적으로 NaN이뜸
     (40 % 0.0) - //NaN = not a number
  ````
        
  2.대입 연산자
  ````
 z +=10, z-=10, 논리 연산자, 비트 연산자 등등 다 된다.
   ````
  3.비교 연산자 
  ````
  int x = 10,y = 10 일 때 
  System.out.println(x == y); 
  system.out.println(x != y);
  ````
       
  4.논리 연산자 : 입출력이 모두 boolean
  ````
  a AND b : a,b 모두 참일때만 참
  a OR b : a,b 둘중 하나만 참이어도 참
  a XOR b : a 또는 b 둘중 하나만 참이어야 참 //exclusive or, 배타적 OR
  NOT a : a가 참이면 거짓, 거짓이면 참 -> 단항연산자
  System.out.println(10 < 20 & 40 >= 30); // AND
  System.out.println(40 < 2 | 1 >0); // OR
  System.out.println(!true);
  System.out.println(!(10>20)); //NOT
  System.out.println(10 > 2 ^ 5 < 2); //XOR true
  ````    
  5.증감 연산자 = 단항 연산자
  ````
  int val = 0;
  System.out.println(val++); // val = 0으로 먼저 Expression 평가 후에 val += 1 적용
  // sout(val); // val +=1;
  System.out.println(++val); // val += 1 먼저 계산한 후에 Expreesion 평가
  // val +=1; //sout(val);
  ````
       
  6.삼항 연산자
  ````
  > boolean             값                 값
 (condition)? (true Expression):(false Expression)
  
  System.out.println(true?  1 : 0);
  System.out.println(false? 1 : 0);

  >x = 10; y = 13;
  System.out.println(x > y? x: y); // max function
  System.out.println(x < y? x: y); // min function
  ````
       
  7.비트 연산자
  ````                  
  System.out.printf("b%32s\n", Integer.toBinaryString(7)); b                             111</br>
  System.out.printf("b%32s\n", Integer.toBinaryString(7 >> 1)); b                              11</br>
      
  System.out.printf("b%32s\n", Integer.toBinaryString(-1));
  System.out.printf("b%32s\n", Integer.toBinaryString(-1 >> 1));
  System.out.printf("b%32s\n", Integer.toBinaryString(-1 >>> 1));
  
  //앞에 0으로 시작 sing비트와 무관하게 0으로 채운다.
  System.out.printf("b%32s\n", Integer.toBinaryString(1252 & 15234));
  System.out.printf("b%32s\n", Integer.toBinaryString(1252 | 15234));
  System.out.printf("b%32s\n", Integer.toBinaryString(1252 ^ 15234));
  ````
# 자바 1주- 3번째 (2020-07-30)
## ch02_s05
  * 조건문 (if문, switch~case문)
   ````
  * if문 : if문같은경우 조건식을 마음대로 쓸 수 있다
    int x = 7; // 6은 완전한 수이다
    if (x % 2 ==0){
      System.out.println("짝수");
    }else{
      System.out.println("홀수");
    }
  * Nested if문 nest(둥지) 안쪽으로 계속쌓여가는것
   if (score >=90 ){
      System.out.println("A");
   }else if(score >=80){
      System.out.println("B");
   }else if(score >=70){
      System.out.println("C");
   }else if(score >=60){
      System.out.println("D");
   }else{
      System.out.println("F");
   }

  * switch ~ case문
    -switch ~ case는 좀 다름 조건문은 '값'이 들어오게된다. boolean에 한정되 않습니다.
    -case범위가 될 수 없고, case도 값이어야 합니다.
    -break가없으면 다른 케이스로 계속 넘어감, fall-through가 발생함.
    -fall-through를 의도하지 않았다면 주석을 달아줘야한다.
    grade = 'F';
    switch (grade){
        case 'A':
            System.out.println("훌륭");
            break;

        case 'B':
             System.out.println("멋집니다");
             break;
        case 'C':
             System.out.println("노력하세요");
             break;
    //  case 'C':
    //  case 'D':
    //       System.out.println("많이 노력하세요");
    //       break; //이렇게도 사용가능.
        case 'D':
             System.out.println("많이 노력하세요");
             break;
        default: //***무조건 마지막에 실행된다.***
             System.out.println("다음엔 잘하세요");
    }
  ````
##ch02_s06
   * 반복문 (for문, while문, do while문)
   ````
   *  for 문, while문
   *  초기화 - 반복문을 실행하기 위해서 증감할 변수를 초기화
   *  조건식 - 반복무을 실행할 조건 또는 종료할 조건을 정하는 것
   *  실행문 - 조건식이 참 또는 거짓일 경우 실행할 코드
   *  증감식 - 실행문이 실행된 후에 변수에 증감을 주는 것 중요*
   * label : 레이블 가장 가까운 반복문을 건너 뛰는게 아니라 레이블이 적혀 있는 반복문을 제어
    
   ex)
   * for 문
    for (int i = 0; i < 5; i++){
        System.out.println(i);// 실행문 조건식 거짓일 때 까지
    }
    System.out.println("");
  
   * nested for문
   for (int i=0; i<5; i++){
      for (int j=0; j<5; j++){
          System.out.printf("(%d, %d)\n",i, j);
      }
   } // 5*5=25
   
   
   for (int i =0; i<5; i++) 
       System.out.print(i);// iterated
       System.out.println(""); //not iterated
   - 중괄호가 없어서 바로 밑에 줄만 실행됨.
  
 * while 문 - 직접 초기화와 증감식을 적어줘야 한다

    int i =0; // i가 외부에서 사용됨 for문은 안에서 사용하고 사라짐 그것이 차이점이다.
    while (i<5){
       System.out.println(i);
       i++;
    }
 * do while문 - 조건문이 false 라고해도 단 한번은 무조건 실행됨.
     i =0;
     do{
        System.out.println(i);
        i++;
     }while (i < 5); 
  ````   

  * break, continue 
  ````
 for (int i=0; i<10; i++){
    if(i == 3){
       continue; //continue를만나면 실행문을 실행하지않고 증감식이 되서 반복됨
       break; //반복문 바로 종료
    } 
      System.out.println(i);
 }
   System.out.println("");
  
  ````
  * label
  ````
   outer: //레이블 가장 가까운걸 건너 뛰는게 아니라 레이블명이 젹혀있는 반복문 쪽으로 감 실행(break도 가능)
   for (int i =0; i<5; i++){
      for (int j=0; j<5; j++){
         if(i == 3){
            System.out.println("continue called");
            continue;
            //여기서 continue만 사용하면 j는 4번동안 반복됨 레이블을 사용하면 i쪽으로가서 제어함.
         }
         System.out.printf("%d * %d = %d\n", i, j, i*j);
      }
   }
  ````
 * Practice
   * 반복문과 조건문을 이용하여 369게임, 별찍기 연습
   
# 자바 1주- 4번째 (2020-07-31)
## ch02_s07
  * 배열
   ````
  * 특성
   - 하나의 변수로 여러 개의 값을 다룰 수 있다.
   - 동일 자료 형만 다룰 수 있다,
   - 한번 생성한 배열의 크기는 변하지 않는다.
   - 배열에 속한 값은 메모리에 연속으로 위치한다.**
  
  ````
  * 선언 및 생성과 초기화
  ````
  배열의 선언: int [] integers;(메모리를 잡고 있지 않은 상태)
  
  배열의 생성: int [] integers2 = new int[10];
  
  초기화 : 
  integers2[0] = 5; (값을 넣어주지 않으면 0으로 자동으로 초기화 된다.)
  int [] integers3 = new int[]{5,2,6,3,12,4}; // 길이를 입력하지 않아도 된다.
  int [] integers4 = {1,4,5,23,0}; //new int를 빼도 초기화 가능.
 
  for문을 이용한 초기화:
  float [] floats = new float[5];
  for (int i =0; i<floats.length; i++){
       floats[i] = (float)(i * 0.25);
  }
  
  Enhancded for, for each문(향상된 포문):
  
  for(float floatVal: floats){ // 배열의 길이만큼 알아서 포문을 돌면서 하나씩 값을 받아옴. 0~ 길이까지
           System.out.println(floatVal);
  }
  ````
## ch02_s09
   * N-D Array
   ````
    * 배열이 배열을 담고 있으면 다차원 배열이라 하는데 이거싱 N-DArray이다.
    수학에서 말하는 점 -> 선 -> 면 -> 공간 -> 4차원 -> 5차원
    
   ````
   * 다차원 배열의 선언
  
   ````
   int [][] ints;
   int [] halfCStyle[]; //hybrid 안좋음
   int oldCStlye[][]; // lod... 안좋음
    
   int [][] ints1 = new int[10][10]; // 앞에 있는 숫자가 더 큰 차원의 수 길이가 5인 인테저어레이를 담고 있는 10인 인테저어레이
    
   int [][] ints2 = new int[10][];
   for (int i =0; i< ints2.length; i++){
        ints2[i] = new int[5];
   }
    
   int [][] ints4 = {{1,2,3},{4,5,6}}; // [2][3]
   int [][] ints5 = {{1,3,2},{1,2},{4,5,3}}; //[3][] 두개짜리는 실제 길이가 더 짧은 것 비어있는게 아니다.
   ````
   * for 문을 이용한 출력
   ````
   for (int i=0; i<ints5.length; i++){ //과제에 이용하면 역순 가능.
       System.out.printf("[%d]", ints5[i].length);
       for (int j=0; j<ints5[i].length; j++){
           System.out.printf("%d ",ints5[i][j]);
       }
       System.out.println("");
   }
    
   for (int [] array : ints5){ //for each 문 사용
        for (int val: array){
             System.out.printf("%d ", val);
        }
        System.out.println("");
   }
   ````
 # 자바 2주 - 1일(2020-08-03)
 ## ch02_s09
   * 문자열(String)
   ````
   - 특성
    * 문자열은 내부적으로 '클래스'로 구성되어 있다.
    * 내부에는 문자의 배열로 된 데이터가 있다. char []
    * 한번 만든 문자열은 변하지 않습니다. (Immutable)
    * 문자열 편집은 String을 쓰지 않고 Builder나 Buffer 등을 사용한다.
   ````
   * 문자열의 생성
   ````
   String s1 = "문자열 생성 방법";
   String s2 = new String("문자열 생성 방법2"); //클래스 생성자 권장하지 않음.
   
   String s3 = "abcde";
   String s4 = "abcde";
   String s5 = new String("abcde");
   
   s3 == s4 : //true 메모리 참조값에 대한 것,문자열을 곧바로 생성할 경우 상수 풀에서 찾아 사용(상수 풀이라는 공간에 저장되어있음)
   s3 == s5 : //false 문자열을 클래스로 생성할 경우 새로운 값을 생성하므로 false 권장x.
   
   s3.equals(s4) // true
   s3.equals(s5) // true
   ````
   * 문자열 안의 메소들
   ````
   String s = "This is a string" 일 때,
   - s.length() : 문자열의 길이를 나타냄
   - s.charAt(2) : 특정 문자의 인덱스 출력
   - s.indexOf('a') : 특정 문자의 인덱스 출력
   - s.equalsIgnoreCase("this is a string"))
     : 대,소문자와 상관없이 값이 같은지 확인.
   - s.replace('i','t') : 문자를 대체(변환)해주는 메소드.(기존의 string을 바꾸는 것이 아님)
   - s.substring(3,9) : 3~8번 인덱스까지 자르기는 메소드, 새로운 string 생성 후 출력
   - .trim() : 양 옆의 공백을 제거해주는 메소드.
   - .repeat() : 문자열을 반복해주는 함수.
   - s.toCharArray() :char array로 바꿔즈는 메소드.
  ````
## ch03
### 클래스,객체
 * 클래스 - 객체를 생성하기 위한 설계도(class)
    ````
    class Car{ // 클래스 이름은 보통 PascalCase로 적는다. ex)CarShop
        int speed = 0; // 속성, 멤버 변수라고도 한다.
        // 용어 중요**
        // 속성: atrribute, field
        // 멤버 변수 : member variable
        void move(){ // **메소드 (method)**, (가끔 멤버 함수), (가끔 함수)
            speed = 10; //행위를 구현, 속성을 변경
        }
    }
    
    ````
 * 객체 - 클래스를 구체화하여 값으로 생성된 것(object, instance)
   ````
   Car carOne = new Car(); // new 키워드로 클래스에서 객체 생성
   System.out.println(carOne.speed); // .으로 속성 접근 가능
   carOne.move(); // 메소드 호출(method call) 이라고 부름.
   System.out.println(carOne.speed);
     
   Car carTwo = new Car();
   System.out.println(carTwo.speed); // speed=0 독립적인 객체를 의미.
     
   //STRING은 Immutable이기때문에 값이 변하지 않지만, 클래스는 대부분 muttable이기때문에 객체 참조에 대한 문제가 발생한다. 중요**
   Car carThree = carOne; // 참조형 객체이기 때문에 new일때만 새로운 객체를 생성하고 ,이렇게하면 같은 객체를 가르킨다.
   System.out.println(carThree.speed);
   carThree.speed = 5;
   System.out.println(carThree.speed);// 5
   System.out.println(carOne.speed); // 5
   ````
  
   
 * 클래스를 객체로 만드는 과정 - > instanciation (인스턴스화)
 

### 변수
 * 클래스에 사용되는 변수들
  
  ````
 - 종류
  *  클래스 멤버 변수(static variable, class variable)
  *  인스턴스 멤버 변수(member variable, attribute...)
  *  로컬 변수(local variable)
  *  로컬 파라미터 변수 (local parameter variable)
  *                    (arguments)

  ````
 * 변수들 실제 예제
 ````
 public class Variable {
     static int classVar; // 클래스 멤버 변수, 스태틱 변수(정적 변수) 바로 메모리에 올라감.
     int instanceVar; // 인스턴스 멤버 변수, 필드, 속성
 
     public void method(int paramVar){ //로컬 파라미터 변수
         System.out.println(paramVar);
         int localVar; //로컬 변수
 //        System.out.println(localVar); //error 로컬변수는 초기화가 자동으로 안됨. **시험문제**
         localVar = 10;
         System.out.println(localVar); //이렇게 값을 대입해서 사용해야 한다.
 
         {
             localVar = 30;
             int localVar2 = 20;
         }
         System.out.println(localVar); //블록 내에서 수정한 것도 반영됨.
 //        localVar2 = 40; // 접근 불가. 생명주기가 끝났다. Life-Cycle이 끝났다.
     }
 }
 ````

* 테스트
````
class VariableTest{
    public static void main(String[] args) {
        System.out.println("클래스 변수");
        System.out.println(Variable.classVar); //0으로 초기화됨 (외워야 됨 어떤변수들은 초기화가 안이뤄지는 경우도 있음 **시험문제가능성**)
        // 클래스 변수는 클래스 이름으로 바로 접근 가능.
        Variable.classVar = 10; //클래스이름.변수명으로 접근 가능
        System.out.println(Variable.classVar);
        System.out.println("");

        System.out.println("인스턴스 멤버 변수");
        Variable var = new Variable(); // 인스턴스를 만들어야 실체가 있기 때문에
        System.out.println(var.instanceVar);  //0으로 초기화가 됨.외워야됨.
        var.instanceVar = 20;
        System.out.println(var.instanceVar);

        Variable var2 = new Variable();
        System.out.println(var2.instanceVar); //초기화 안됨 0으로 나옴.

//        System.out.println(var2.classVar); //클래스 변수에도 접근 가능 하지만 권장 x 언어에따라 아예 안되는 경우도 있음.
        // 클래스 변수는 클래스에 속하기 때문에 특정 객체에 속해서 출력하는 것은 권장하지 않음.
//        Variable.instanceVar 접근 불가
        System.out.println("로컬 변수");
        var.method(9);
    }
}
````
### 클래스와 객체의 메모리 구조
 * 클래스와 객체의 메모리 구조 특징
 ````
 *  - 클래스 영역(class area, method area, code area, static area) 클래스의 공통된 부분이 들어감 어떤 필드가 있는지 어떤 타입을 갖는다던지
 *  어떤메소드가 있고 어떤동작을 하는 코드라던지. 프로그램이 돌아가면 계속 존재함.
 *   -> field 정보, method 정보, type 정보, constant pool
 *  - 스택 영역 메소드콜이 이뤄질때 메소드에 의해 이뤄지는 임시적인 영역
 *   -> method 호출 시 선언된 로컬 변수(호출시 임시로 있는 공간 (사라짐))
 *  - 힙 영역 new키워드로 발생함.
 *   -> new 키워드로 생성된 객체
 *   -> garbage collection이 동작하는 영역 : 더 이상 사용하지 않는 메모리를 알아서 반환하는  jvm의 기능을 의미한다.(GC)
 *
 ````
 * 클래스와 객체의 메모리 구조 예제
  ````
  public class MemoryStructure { // 클래스 영역
      int x, y; // 힙 영역 객체에 속하기 때문에 객체를 콜할 때는 힙 영역에 들어감. 객체에 속하는 속성들이므로.
      // 클래스가 아니기 때문에 값 자체가 담긴다.(32비트 값을 잡아서 쓰여져있음)
  
      String string = "String!!"; // 힙영역, 상수풀에도 생성 애는 상수풀에 생성됨. 클래스를 만드는 클래스는 힙영역에있고 내용은 상수풀에 들어감.
  
      public void method(int value){ // 클래스 영역에 만들어 짐 (메소드의 정의)
          // int value = > 스택 영역
          char c = 'w'; // 스택 영역
      }
      //대부분의 주소값은 64비트 운영체제가 대부분 64비트이기때문에 거기에 맞춰지는 것.
  
  }
  ````
### 메소드(Methods)
 * 객체가 하는 동작(행위)을 정의하는 작업을 수행하는 코드의 집합이자 나열
 * 코드의 중복을 방지, 유지보수성을 향상, 코드의 가독성을 개선하는 역할을 한다.
 
  #### 메소드의 구현
   * 메소드는 함수의 형태로 구성된다.
     * 파라미터(Parameters,입력)
     * 실행문(Executional Statements)
     * 리턴 값(Return Value, 출력)
     
   * 함수의 작성
   ````
   // 인스턴스 메소드, 메소드 라고도 부름
       //return type(출력의 자료형)
       public  int add(int x, int y)// 여기까지는 선언 // 입력 파라미터.
       {
           return  x + y; // 반환값 (Return value)
       }
       // 선언(Declaration) - ~한 것이 있다. 실제 구현은 x
       // 정의(Definition) - 선언 + 구현(초기화)
   ````
   * 가변 인자(Variable Arguments)
   ````
   public static int sumAll(int...params){ // 여러개의 int를 입력 받는다 라는 의미.
        // 입력받은 결과는 배열로 주어진다. 배열로 넘어옴.(가변 인자 variable arguments)
        int sum = 0;
        for (int value : params){
            sum += value;
        }
        return sum;
   }
   ````
   * 기본형 과 참조형 변수
     * 기본형: 메소드 인자로 값이 전달됨(Call by Value)
     * 참조형: 메소드 인자로 참조가 전달됨(Call by Reference)
   
   ````
   public static void swapPrimitive(int x, int y){
        //int x, int y의 사본이 넘어온다. 외부와 전혀 상관없는 값이 된다. 밖에 있는 값에 영향을 주지 못한다.
        // 기본형 타입인 경우에 해당.
        // call by value : 메소드 호출을 할 때, 값을 복사해서 넘긴다.
        int temp = x;
        x = y;
        y = temp;
   }
   public static void swapReference(Foo x, Foo y){
        // Foo - > class, class는 참조형 변수
        // 참조형 변수이기 때문에 x,y에 참조가 넘어오게 된다.
        int temp = x.value;
        x.value = y.value;
        y.value = temp;
   }

   int x = 10;
   int y = 20;
   Bar.swapPrimitive(x,y); 
   // 메소드를 사용해서 하면 10,20이라는 값이 전달됨 변수가 전달되는게 아니라 사본이 넘어가는 것이다.
   // int temp = x; // 이렇게 하면 잘 뒤집어짐
   // x = y;
   // y = temp;
   System.out.println(x+ ","+y);
   
   Foo f1 = new Foo();
   Foo f2 = new Foo();
   
   f1.value = 10;
   f2.value = 20;
   Bar.swapReference(f1,f2);
   System.out.println(f1.value +","+f2.value);
   ````
   * 클래스 메소드
     * static 키워드를 이용하여 선언된 메소드
     * 인스턴스가 아닌 클래스에 속하는 메소드
     * 대표적으로 main 메소드가 클래스 메소드이다.
   ````
   ex 1)
   public  static  void classMethod(){
        System.out.println("클래스 메소드 호출");
   }
   classMethod(); // 같은 클래스의 클래스메소드를 바로 호출 가능. 동일 클래스에 속한 클래스 메소드 호출.
   Methods.classMethod(); // 이렇게도 호출 가능.
   Methods m = new Methods();
   m.instanceMethod(); //인스턴스 메소드 호출. //퀴즈에 나올 문제. 중요***

   class Person{
       static String korWord = "사람"; //바로 할당됨
       boolean isHungry = true; // 인스턴스 멤버 변수 객체를 만들 때 생성
   
       // 클래스 메소드  메소드라는 것은 변수와 다루는 것이 다름.
       public static  void describe(){ //클래스로 만들기 때문에 각각의 객체와 상관없음. 공통?
           System.out.println(korWord+"입니다."); //스태틱 변수는 사용할 수 있음 접근, 수정 가능.
           // 클래스 메소드는 클래스 변수를 사용할 수 있다.
           // 단, 객체에 속하는 속성은 사용할 수 없다.
       }
       //인스턴스 메소드
       public void eat(){
           isHungry = false;
       }
   }
   ex2)
   public class Foo{
     static public void main(String args[]){
           //class method
     }
   }

   ````
   * 메소드 호출 스택 (Method Call Stack)
     * 메소드가 호출될 때 마다 메소드 동작과 로컬 변수가 쌓이는 메소드 영역
     * 메소드가 종료될 때 메모리가 함께 반환됨.
     
#### 메소드 오버로딩
   * 동일 기능의 함수를 추가로 구현하는 방법
   * 입력 파라미터를 다르게 해서 동일한 함수명으로 구현한다.
   ````
   public static void swapReference(Foo x, Foo y){
        // Foo - > class, class는 참조형 변수
        // 참조형 변수이기 때문에 x,y에 참조가 넘어오게 된다.
        int temp = x.value;
        x.value = y.value;
        y.value = temp;
   }
   
   
   public static int sumAll(int...params){ // 여러개의 int를 입력 받는다 라는 의미.
        // 입력받은 결과는 배열로 주어진다. 배열로 넘어옴.(가변 인자 variable arguments)
        int sum = 0;
        for (int value : params){
           sum += value;
        }
        return sum;
   }
   System.out.println(Bar.sumAll(2,5,2,10,6,-2));
   System.out.println(Bar.sumAll(2.2f,0.2f,0.2f));
   ````
### 생성자(Constructor)
 * 클래스에서 인스턴스를 생성할 때 사용되는 메소드
   * new 키워들 이용해서 호출
   
 * 기본 생성자 (Default Constructor)
   * 구현하지 않아도 자동으로 생성되는 생성자.
   * 아무런 동작도하지 않고, 객체만을 생성
   
 * 파라미터 생성자 (Parameter Constructors)
   * 입력 파라미터를 받는 생성자
   * 여러개의 파라미터 생성자를 오버로딩할 수 있음
   * 보통 멤버 변수를 초기화하는 동작 수행
 ````
 public class Constructor {
     int x;
     int y;
     String z;
 
     public Constructor(){ // 기본 생성자, 구현하지 않아도 알아서 생긴다.
         this(0,0);
         //x = 1;
         //y = 2;
          //z = "초기화";
     }

 //    private Constructor(){} //외부에서 사용할 수 없음 호출이 불가능한 생성자.
 

       // 파라미터 생성자
     public Constructor(int a, int b, String c){ //오버로딩
         x = a;    
         y = b;
         z = c;
     }

 }
 
 class ConstructorTest{
     public static void main(String[] args) {
         Constructor c = new Constructor(); // 기본 생성자 호출
         System.out.println(c.x + "," + c.y + ","+ c.z);
         // z의 경우, string은 클래스이기 때문에 null로 초기화가 된다.
         // null -> 아무것도 참조하고 있지 않다.
 
         Constructor c1 = new Constructor(10,20,"파라미터생성자");
         System.out.println(c1.x+ "," + c1.y + ","+ c1.z);

     }
 }
 ````
### this 키워드
  * 객체가 스스로를 가르키는 참조
  * 멤버 변수와 로컬 변수의 이름이 같을 때, 멤버 변수임을 명시
  * 생성자를 호출하는 데에도 사용할 수 있다.
  * 반드시 생성자의 첫 줄에서만 사용해야 한다.
  ````
   public class Constructor {
       int x;
       int y;
       String z;
   
       public Constructor(){ // 기본 생성자, 구현하지 않아도 알아서 생긴다.
           this(0,0);
   //        x = 1;
   //        y = 2;
   //        z = "초기화";
       }
   //    private Constructor(){} //외부에서 사용할 수 없음 호출이 불가능한 생성자.
   
   
         // 파라미터 생성자
       public Constructor(int x, int y, String z){ //오버로딩
           this.x = x; // this는 멤버 변수를 표기하기 위해 사용될 수 있다.
           this.y = y;
           this.z = z;
       }
   
       public Constructor(int a, int b){
           this(a,b,""); // 자기 자신을 가르키는 애 위에 함수가 호출됨. this는 무조건 첫줄에만 쓰일 수 있다.(단한번) 규칙입니다.
   //        x = a;
   //        y = b;
   //        z = "";
       }
   }
  ````
### Getter와 Setter
  * 클래스의 멤버 변수를 간접적으로 다룰 수 있게 하는 메소드
  * 멤버 변수의 캡슐화를 구현하기 위해 사용. -> 정보은닉 / 보호
  * 멤버 변수의 값을 제한해야 할 때 유용.
  ````
   public class Main {
       int x,y;

       //멤버변수를 만들어야 생성가능.
       public int getX() { //경우에 따라 구현되지 않을 수 있다. 출력하지 않으면 사용 안하는 것.
           return x;
       }
   
       public void setX(int x) {
           if(x > 0 && x <= 1000){
               this.x = x;
           }else{
               System.out.println("x should be 1 < x <= 1000!");
               System.out.println("however, you put in x = "+x);
           }
           this.x = x;
       }
   
       public int getY() {
           return y;
       }
   
       public void setY(int y) {
           this.y = y;
       }
   
   }
   
   class MainTest{
       public static void main(String[] args) {
           Main m = new Main();
           m.x=10; //x에 private를 넣으면 직접 값을 변경할 수 없음.
           System.out.println(m.x);
   
           m.setX(20);
           System.out.println(m.getX()); //권장된 처리 방식.
   
           m.x = 20; // 권장하지 않는 멤버 변수 처리 방식
   
           m.setX(11111);
           System.out.println(m.getX());
   
       }
   }
  ````
### 초기화 블록(Initializer)
 * 클래스 또는 인스턴스를 생성할 때 단 한번 실행되는 코드 블록
 ````
 public class Main {
     static int classVar;
     static  int instanceCount;
     int instanceVar;
 
     // static initializer
     static { //객체가 생성되지 않은 상태 객체에서 접근 불가
         System.out.println("static block1");
         classVar = 20;
     }
     //object initialzer 객체를 만들어서 접근.
     {
         System.out.println("block1");
         instanceVar = 30;
         classVar = 50; // 추천되지 않는다. 객체를 생성하는데 클래스에 대해 변하는것은 권장하지 않음.
         instanceCount++; // 이러한 패턴은 사용된다.
     }
     //여러개 사용 가능. 스태틱은 스태틱끼리 출력되고, 값은 나중에 선언된 값이 삽입됨.
     static {
         System.out.println("static block2");
         classVar = 5;
     }
     {
         System.out.println("block2");
         instanceVar = 5;
     }
 }
 
 class MainTest{
     public static void main(String[] args) {
         System.out.println(Main.classVar);
         Main m = new Main();
         System.out.println(Main.instanceCount);
 
 
         System.out.println(m.instanceVar);
         System.out.println(Main.classVar);
 
         Main m2 = new Main();
         System.out.println(Main.instanceCount);
         Main m3 = new Main();
         System.out.println(Main.instanceCount);
 
     }
 }
 ````

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
  
   
