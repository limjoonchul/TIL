# Java 1-2(2020-07-27)
#### ch02_s02
# 자료형(Data Type)

## 자료형이란
 * 변수의 종류, 타입
 * 기본형(Primitiv Type):  자바 언어에서 기본적으로  제공해 주는 자료형,메모리의 크기가 정해져 있음 EX)정수형, 문자형, 실수형, 논리형
 * 참조형(Reference Type) : 클래스 자료형, JDK에서 제공되는 클래스와 프로그래머가 정의하는 클래스, 클래스에 따라 사용하는 크기가 다르다. EX) String, Student
 
 |     | 1byte | 2byte | 4byte | 8byte |
 |-----|-------| -----|----|------|
 | 정수형 |  byte| short| int | long |
 | 문자형 |      | char |     |      |
 | 실수형 |      |      | float | double |
 | 논리형 | boolean|    |       |        |
 
## 여러가지 자료형
### 정수형
 * 정수 리터럴
   * 정수 리터럴은 기본적으로 int 자료형
   * int 범위를 넘어서는 리터럴의 경우 L을 붙여서 표기 ex) long largeValue = 100000000000L;
   * 가독성 향상을 위해 세자리 수 마다 _를 이용해 천단위 표기 ex) long largeValue = 100_000_000_000L;
### 실수형
 * 실수 리터럴
   * 실수 리터럴은 기본적으로 double 자료형
   * f를 붙여 float 타입을 표현할 수 있음 ex) float value = 14.42f;
   * e를 이용해 지수 표현을 할 수 있음 ex) double value = 1.42e3; 1.42 * 10^3 = 1420
   * 실수는 부동 소수점 방식으로 표현
   * 실수를 지수부와 가수부로 표현함
   * 0.1을 표현하는 방식 1.0(가수) * 10(밑수)^-1(지수)
   * float = 부호비트 + 지수부(8비트) + 가수부(23비트) 총32비트
   * double = 부호비트 + 지수부(11비트) + 가수부(52비트)  총65비트
   
 * 부동소수점방식
   * 장점 아주 많은 범위를 실수를 표현할 수 있다.
   * 단점은 약간의 오차가 발생할 수 있음
  
 * 부동 소수점 방식의 오류
   * 지수와 가수로 표현되는 부동 소수 점은 0을 표현 할 수 없음
   * 따라서 부동 소수점 방식에서는 약간의 오차가 발생할 수 있음
   
### 문자형
 * ASCII 코드
   * 작은 따옴표로 표현하며, 문자는 기본적으로 ASCII 코드로 저장됨 ex) char asciiCharacter = 'A';
   * ASCII 코드의 특수 문자 입력
 * 유니코드 테이블
   * 유니코드는 \u + 16진수로 표현 ex) char unicodeCharacter = '\u0041';
   * 한글과 같은 복잡한 언어를 표현 하기 위한 표준 인코딩 ex) UTF-8, UTF-16
 * 내부적으로는 비트의 조합으로 표현 
 * 인코딩 - 각 문자에 따른 특정한 숫자 값( 코드값)을 부여
 * 디코딩 - 숫자 값을 원래의 문자로 변환
 * 문자를 변수에 저장하면? 문자에 해당하는 코드 값이 저장 된다
 * int val = 'A' 라 했을 때 65가 저장된다.
  ```groovy
   char ch = 'A';
   System.out.println(ch);
   System.out.println((int)ch);
  
   int iCh = 66;
   System.out.println(iCh);
   System.out.println((char)iCh);
  
   char hangul = '\uAC00';
   System.out.println(hangul);
  ```
### 논리형
 * true, false 두 가지 값만이 허용된다.
  ````groovy
  boolean isTrue = true;
  boolean isFalse = false;
  ````
### 문자열
 * 문자열은 참조형 자료형이며, 큰 따옴표로 표현한다.
 ````groovy
  String s = "문자열은 큰따옴표로 묶는다.";
 ````
 * 문자열은 덧셈으로 이어 붙일 수 있다. 문자열로 변환이 가능한 자료형은 문자열로 변환되어 결합한다.
 ````groovy
  String s1 = "a" + "b" + "c";
  String s2 = 1 + "1" + 3;
 ````

### 상수와 리터럴
* 상수(constant) : 변하지 않는 수
* 리터럴(literal) : 프로그램에서 사용하는 모든 숫자, 값, 논리 값 ex)10,3.14,'A',true
* 모든 리터럴은 상수 풀(constant pool)에 저장되어 있음
* 상수 풀에 저장될 때 정수는 int, 실수는 double로 저장됨

## 형변환(Type Casting)
* 서로 다른 자료형의 값이 대입되는 경우 형 변환이 일어남
* 묵시적 형 변환(implicit type conversion) : 작은 수에서 큰 수로 더 정밀한 수에서 더 정밀한 수로 대입되는 경우
```groovy
 int i = 1000;
 byte bNum = (byte)i;
 //데이터의 유실이 발생할 수 있다.
 System.out.println(bNum);

 double dNum1 = 1.2;
 float fNum = 0.9f;

 int iNum1 = (int)(dNum1 + fNum);
 int iNum2 = (int)dNum1 + (int)fNum;
 // 두개의 결과 값이 다름
```
* 명시적 형 변환(explict type conversion): 변환되는 자료 형을 몃이 자료의 손실이 발생할 수 있음
```groovy
 byte bNum = 10;
 int iNum = bNum;

 System.out.println(bNum);
 System.out.println(iNum);

 int iNum2 = 20;
 float fNum = iNum2;

 System.out.println(fNum);

 double dNum;
 dNum = fNum + iNum;
 System.out.println(dNum);
```
### 형변환 연산자
 * 형변환 연산자는 ()로 변수 앞에 입력한다. ex) int value = (int)10.8; -> value = 10
### 업캐스팅(Upcasting)
 * 값의 범위가 더 큰 자료형으로 변환하는 경우
   * <u>묵시적/명시적</u> 방법 모두 사용 가능
  ````groovy
   byte x = 10;
   int y = (int)x;
   int z = x;
   ````

### 다운캐스팅(Downcating)
 * 값의 범위가 더 큰 자료형으로 변환하는 경우
   * <u>명시적인</u> 방법으로만 캐스팅 가능
  ````groovy
   int x = 1024;
   // byte y = x;
   byte z = (byte)x;
   
   long x = 100;
   float y = x;
   // long z = y;
   long z = (long)y;
   ````
