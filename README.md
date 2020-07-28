# java-TIL
JAVA 오늘 배운것(TIL)

# 자바 첫날
- java_first
  - Hello World 출력
- ch02_s01
  - 변수선언 방법, code convention
- ch02_s02
  - 자료형, 자료형의 종류(기본형(Primitive Type), 참조형(Reference Type))
  - 기본형 자료형 : 정수형, 실수형, 문자형, 논리형</br>
        -정수형 : Byte(1Byte), Short(2Byte), Int(4Byte), Long(8Byte)
- ch02_s03
  - 입출력 메소드</br>
     -입력 : Scanner를 사용해서 키보드로 값 입력시킬 수 있다.</br>
        </t>-.next() : 입력받을 내용이 있을 때까지 기다린다. Blocking 메소드라고도 함.
                  공백으로 문자열 구분
        -.nextLine() : 한줄 전체를 받음, \n(enter)로 구분됨. 
        -.nextInt() : 정수형을 입력받음, 공백으로 구분되는 정수.
     -출력 : print(), println(), printf()
        -print() :   System.out.print("123121\r\n"); r = 캐리지 리턴  첫 글자 자리로 보냄, n = newline을 의미
        -println() : System.out.println(1523.22); ln = new line은 줄 바꿈을 의미한다.
        -printf() :  System.out.printf("%s is for string\n","string"); new line이 자동으로 안붙여짐
                 - System.out.printf("%f\n",14.23f); //꼭 float은 아니다 f가 flaot이 아니라
                    floating number float과 double 둘다 나타내야기 때문에
                 - 정수 포맷팅
                   -  System.out.printf("%5d.\n", 10); //5d 5자리 확보 ...10
                   - System.out.printf("%3d\n",104294); //자릿수가 더크면 그대로 출력이된다  3칸은 최소한 확보하는 것
                 - 실수 포맷팅
                   - System.out.printf("%5.4f\n", 152523.456228); //소수점 아래에 4자리까지만 출력 5는 최소한 5칸 확보 소숫점도 한칸 차지한다.
                   - System.out.printf("%5.2f.\n",1.425); //소수점 포함 자릿수를 만든다.
- ch02_s04
  - 연산자(operator), 피연산자(operand) -> 연산식(Expression : 현업에서 많이 쓰이는 용어)
    -사칙 연산자
       -z = x + y 일 때, 1.+ : 연산자,  x y: 피연산자, x+y : 연산식 / 2. = : 연산자, z: 피연산자, x+y: 피연산자
       -정수형 사칙 연산
        - System.out.println(150 / 8); // 정수 나누기 -> 몫
          System.out.println(150 % 8); //modulus연산  나머지 값이 나옴
       -실수형 사칙 연산
        - System.out.println(10.5f + 12.3); //float, double 같이 연산되면 double로 변환 후 연산
          System.out.println(10.4-50); // 실수형, 정수형을 같이 연산하면 실수형으로 변환 후 연산
          System.out.println(5.2 / 1.2);// 몫이 아니고 실수 값으로 계산 됨
          System.out.println(5.2 % 1.2); // 실수 나눗셈도 modulus 연산으로 나머지 계산 가능 연산 이루어짐
       -사칙연산의 주의사항
        - System.out.println(Integer.MAX_VALUE /2 * 3); // -1073741827 오버플로우
          System.out.println(Integer.MAX_VALUE); //2147483647
          System.out.println(Integer.MAX_VALUE + 1); //-2147483648 
          가장 작은 값이됨 가장 큰 값에서 overflow 발생 시 가장 작은 값이 됨
       -int maxVal = 0b01111111111111111111111111111; //b다음에 0이면 양수 1이면 음수 32비트
        int maxVal1 =0b10000000000000000000000000001; //2의 보수
        int minVal2 = 0b10000000000000000000000000000; //1의보수
       -중요!
        System.out.println((6-5.9) * 10); // 0.9999999999999964
        System.out.println(Math.floor((6-5.9) * 10)); // 0.0 
        내림연산했을때 우리는 안에 계산식이 1이나오길 (예상)기대하지만 0.9999가나와서 내림연산했을때 0.0이 나옴
        (정밀도 문제 6과 관련했을 때 많이 발생)
        System.out.println(40/0.0); 
        //infinity 수식은 문제 없지만 값에서 문제가 생길경우 발생 연산 도중에 한번 infinity가 발생하면 최종적으로 NaN이뜸
        System.out.println(40 % 0.0); //NaN = not a number
        
   - 대입 연산자
      -z +=10, z-=10, 논리 연산자, 비트 연산자 등등 다 된다.
      
   - 비교 연산자
      -System.out.println(10 > 20);
       System.out.println(10 < 20);
       int x = 10,y = 10;
       System.out.println(x == y);
       System.out.println(x != y);
       
   - 논리 연산자 : 입출력이 모두 boolean
     -a AND b : a,b 모두 참일때만 참
      a OR b : a,b 둘중 하나만 참이어도 참
      a XOR b : a 또는 b 둘중 하나만 참이어야 참 //exclusive or, 배타적 OR
      NOT a : a가 참이면 거짓, 거짓이면 참 -> 단항연산자
     -System.out.println(10 < 20 & 40 >= 30); // AND
      System.out.println(40 < 2 | 1 >0); // OR
      System.out.println(!true);
      System.out.println(!(10>20)); // NOT
      System.out.println(10 > 2 ^ 5 < 2); // XOR true
      
   - 증감 연산자 = 단항 연산자
     -int val = 0;
      System.out.println(val++); // val = 0으로 먼저 Expression 평가 후에 val += 1 적용
       // sout(val); // val +=1;
      System.out.println(++val); // val += 1 먼저 계산한 후에 Expreesion 평가
       // val +=1; //sout(val);
      System.out.println(val--);
       // sout(val); // val -=1;
      System.out.println(--val);
       // val -=1; // sout(val);
       
   - 삼항 연산자
     -(condition)? (true Expression):(false Expression)
       boolean             값                 값
       System.out.println(true? 1 :0);
       System.out.println(false? 1:0);

       x = 10; y = 13;
       System.out.println(x > y? x: y); // max function
       System.out.println(x < y? x: y); // min function
       
   - 비트 연산자
     -System.out.printf("b%32s\n", Integer.toBinaryString(8));       
      System.out.printf("b%32s\n", Integer.toBinaryString(8 >> 1)); //shift 연산자 b                             
      System.out.printf("b%32s\n", Integer.toBinaryString(7));      // b                             111
      System.out.printf("b%32s\n", Integer.toBinaryString(7 >> 1)); // b                              11
      
      System.out.printf("b%32s\n", Integer.toBinaryString(-1));
      System.out.printf("b%32s\n", Integer.toBinaryString(-1 >> 1));
      System.out.printf("b%32s\n", Integer.toBinaryString(-1 >>> 1));
      //앞에 0으로 시작 sing비트와 무관하게 0으로 채운다.
      System.out.printf("b%32s\n", Integer.toBinaryString(1252 & 15234));
      System.out.printf("b%32s\n", Integer.toBinaryString(1252 | 15234));
      System.out.printf("b%32s\n", Integer.toBinaryString(1252 ^ 15234));
             
