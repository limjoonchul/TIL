# java-TIL
JAVA 오늘 배운것(TIL)

# 자바 1주- 1번째(2020-07-28)
- java_first
  - Hello World 출력
- ch02_s01
  - 변수선언 방법, code convention
- ch02_s02
  - 자료형, 자료형의 종류(기본형(Primitive Type), 참조형(Reference Type))
  - 기본형 자료형 : 정수형, 실수형, 문자형, 논리형</br>
        - 정수형 : Byte(1Byte), Short(2Byte), Int(4Byte), Long(8Byte)
# 자바 1주- 2번째(2020-07-29)
* ch02_s03
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
* ch02_s04
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
* ch02_s05
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
* ch02_s06
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
  
    * 중괄호가 없어서 바로 밑에 줄만 실행됨.
    for (int i =0; i<5; i++) 
        System.out.print(i);// iterated
        System.out.println(""); //not iterated
  
    while 문 - 직접 초기화와 증감식을 적어줘야 한다

     int i =0; // i가 외부에서 사용됨 for문은 안에서 사용하고 사라짐 그것이 차이점이다.
     while (i<5){
        System.out.println(i);
        i++;
     }
    do while문 - 조건문이 false 라고해도 단 한번은 무조건 실행됨.
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
* ch02_s07
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
 * ch02_s09
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
