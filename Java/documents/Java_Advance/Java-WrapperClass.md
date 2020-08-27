# Wrapper 클래스(Wrapper Class)
2020-08-13 3-3차

## Wrapper Class란
 * 기본형 타입을 객체로 쓰기 위해 있은 클래스
 * 기본형 타입이 허용되지 않는 문법에 기본형 타입을 쓰기 위해서 제공 하는 클래스
 
## Wrapper Class의 종류
| 기본형 | WrapperClass |
|:-----:|:-----------:|
|byte|Byte|
|char|Character|
|short|Short|
|int|Integer|
|flaot|Float|
|double|Double|
|boolean|Boolean|

## Wrapper 객체 생성
* 생성자를 이용한 객체 생성
````groovy
Integer integer = new Integer(10);
Character character = new Character('v');
````
* valueOf를 이용한 객체 생성
````groovy
Integer integer1 = Integer.valueOf(10);
Character character1 = Character.valueOf('v');
````
## Autoboxing & Unboxing
* 기본형을 객체로 사용할 수 있게 하는 것을 - > Autoboxing
* 객체를 기본형으로 바꾸는 것을 -> Unboxing

### Autoboxing
  * Java1.5부터 추가된 기능으로, 객체로 다루어야 할 때 자동으로 Wrapper 클래스로 변경하는 기능
 ````groovy
    public static <T> T bypass (T x){
      return x;
    }
  
    Integer integer2 = 4;
    System.out.println(add(4,2));
    bypass(5); // 원래는 프리미티브 타입을t에 넣을 수 없는데 autoboxing이 이뤄져서 가능.
   // T: wrapper class인 integer로 결정됨
   // 5 -> new Integer(5) autoboxing 1.5버전 이후로 가능해짐.
 ````
###  Unboxing
  * Wrapper 객체를 기본형으로 자동으로 변경하는 기능
 ````groovy
 int m = new Integer(10); 
   
 int i = 10;
 Integer wrapped = i;
 int b = 20 + wrapped;
 ````

## Wrapper 타입의 값 비교
* Wrapper 타입은 객체이므로, ==를 이용하여 값을 비교할 수 없다.
````groovy
Integer intOne = new Integer(100);
Integer intTwo = new Integer(100);

System.out.println(intOne == intTwo); // false
System.out.println(intOne.equals(intTwo)) // true
System.out.println(intOne == 100) // true (Unboxing)
````

## 문자열의 기본 자료형 반환
* Parsing 정적 메소드를 이용한 반환
````groovy
int x = Integer.parseInt("100");
long y = Long.parseLong("512345124");
````
* Wrapper 객체로의 반환
````groovy
Integer intObj = Integer.valueOf("1000");
Integer intObjTwo = new Integer("1234");
````