# Java 2-1
## 문자열(Strings)
### 문자열의 특징
 * 문자열은 내부적으로 '클래스'로 구성되어 있다.
 * 내부에는 문자의 배열로 된 데이터가 있다. char []
 * 한번 만든 문자열은 변하지 않습니다. (Immutable)
 * 문자열 편집은 String을 쓰지 않고 Builder나 Buffer 등을 사용한다.
 
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
 * 문자열안의 메소드
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
