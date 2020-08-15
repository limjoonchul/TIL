# Java 1-2(2020-07-27)
#### ch02_s02
## 자료형(Data Type)

### 자료형이란
 * 변수의 종류, 타입
 * 기본형(Primitiv Type): 정수형, 실수형, 문자형, 논리형
 * 참조형(Reference Type) : 값을 직접 저장하지 않고, 값이 저장된 주소를 저장
### 여러가지 자료형
#### 정수형
 * 정수 리터럴
   * 정수 리터럴은 기본적으로 int 자료형
   * int 범위를 넘어서는 리터럴의 경우 L을 붙여서 표기 ex) long largeValue = 100000000000L;
   * 가독성 향상을 위해 세자리 수 마다 _를 이용해 천단위 표기 ex) long largeValue = 100_000_000_000L;
#### 실수형
 * 실수 리터럴
   * 실수 리터럴은 기본적으로 double 자료형
   * f를 붙여 float 타입을 표현할 수 있음 ex) float value = 14.42f;
   * e를 이용해 지수 표현을 할 수 있음 ex) double value = 1.42e3; 1.42 * 10^3 = 1420
#### 문자형
 * ASCII 코드
   * 작은 따옴표로 표현하며, 문자는 기본적으로 ASCII 코드로 저장됨 ex) char asciiCharacter = 'A';
   * ASCII 코드의 특수 문자 입력
 * 유니코드 테이블
   * 유니코드는 \u + 16진수로 표현 ex) char unicodeCharacter = '\u0041';
#### 논리형
 * true, false 두 가지 값만이 허용된다.
  ````groovy
  boolean isTrue = true;
  boolean isFalse = false;
  ````
#### 문자열
 * 문자열은 참조형 자료형이며, 큰 따옴표로 표현한다.
 ````groovy
  String s = "문자열은 큰따옴표로 묶는다.";
 ````
 * 문자열은 덧셈으로 이어 붙일 수 있다. 문자열로 변환이 가능한 자료형은 문자열로 변환되어 결합한다.
 ````groovy
  String s1 = "a" + "b" + "c";
  String s2 = 1 + "1" + 3;
 ````
### 형변환(Type Casting)
#### 형변환 연산자
 * 형변환 연산자는 ()로 변수 앞에 입력한다. ex) int value = (int)10.8; -> value = 10
#### 업캐스팅(Upcasting)
 * 값의 범위가 더 큰 자료형으로 변환하는 경우
   * <u>묵시적/명시적</u> 방법 모두 사용 가능
  ````groovy
   byte x = 10;
   int y = (int)x;
   int z = x;
   ````
#### 다운캐스팅(Downcating)
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