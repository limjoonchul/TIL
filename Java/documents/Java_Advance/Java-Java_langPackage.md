# java.lang 패키지
## java.lang
* Java에서 가장 기본적이며 자주 사용되는 클래스를 모은 패키지
* 별도로 import하지 않아도 사용이 가능한, Java의 기본 중의 기본

## ObjectClass
* 모든 클래스의 최상위 클래스
* java.lang.Object 클래스
* 모든 클래스는 Object 클래스에서 상속 받음
* 모든 클래스는 Object 클래스의 메소드를 사용할 수 있음
* 모든 클래스는 Object 클래스의 일부 메소드를 재정의하여 사용할 수 있음
  * final로 재정의된 메소드들은 하위클래스에서 재정의 할 수 없기 때문에 일부 메소드만 재정의할 수 있다 라고 하는 것

### ObjectClass의 메소드

| 메소드 | 설명 |
|------|------|
| public final native Class<?> getClass() |	현재 객체의 클래스를 반환한다.|
| public native int hashCode() | 현재 객체의 해시코드 값을 반환한다.|
| public boolean equals() |	현재 객체와 대상이 같은 객체를 참조하는지 여부를 반환한다.|
| public String toString() | 객체를 문자열로 변환하여 반환한다.|
| proteted native clone() | throws CloneNotSupportedException 객체를 복사하여 새로운 객체로 반환한다.|

#### toString() 메소드
* toString()메소드의 원형
```groovy
getClass().getName() + '@' + Integer.toHexString(hashCode())
```

* 객체의 정보를 String으로 바꾸어 사용할 때 유용함
* 자바 클래스중에는 이미 정의된 클래스가 많음
* 예 : String, Integer, Calender 등
* 많은 클래스에서 재정의하여 사용
```groovy
// extends Object 된 것과 같다 컴파일할 때 자동으로 생성이됨
class Book{
    String title;
    String author;

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return author+","+title;
    }
}
public class ToStringTest {
    public static void main(String[] args) {
        Book book =  new Book("토지","박경리");
        System.out.println(book.toString());
        // 메모리주소가 출력됨.

        String str = new String("토지");
        System.out.println(str);
        //String 안에 이미 toString()메소드가 정의되어 있어서
        // String클래스 안에 있는 Chararcter의 배열을 출력하도록 되어 있다.

    }
}
```
#### equals() 메소드
* 두 객체의 동일함을 논리적으로 재정의 할 수 있음
* 물리적 동일함 : 같은 주소를 가지는 객체
* 논리적 동일함 : 같은 학번의 학생, 같은 주문 번호의 주문
* 물리적으로 다른 메모리에 위치한 객체라도 논리적으로 동일함을 구현하기 위해 사용하는 메소드

```groovy
class Student{
    int studentNum;
    String studentName;

    public Student(int studentNum, String studentName){
        this.studentName = studentName;
        this.studentNum = studentNum;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student){
            Student std = (Student)obj;
            if(this.studentNum == std.studentNum){
                return true;
            }
            else return false;
        }
        return false;
    }
}
public class EqualsTest {
    public static void main(String[] args) {
        Student lee = new Student(100,"이상원");
        Student lee2 =lee;
        Student won = new Student(100,"이상원");

        System.out.println(lee == lee2); //true
        System.out.println(lee == won);//false
        System.out.println(lee.equals(won)); //false

        System.out.println(lee.hashCode());
        System.out.println(won.hashCode());
    }
}
```
#### hashCode() 메소드
* 자바에서 JVM이 있는데 인스턴스가 생성이 됬을 때 메모리 주소를 준다.
그 주소값을 `hashCode`라고 한다.
* hashCode() 메소드의 반환값: 인스턴스가 저장된 가상머신의 주소를 10진수로 반환
* 두 개의 서로 다른 메모리에 위치한 인스턴스가 동일하다는 것은?
  * 논리적으로 동일 : equals() 의 반환값이 true
  * 동일한 hashCode 값을 가짐 : hashCode()의 반환 값이 동일 
* `navtive` 메소드이므로 구현 내용을 볼 수 없다.
   * native : c 또는 c++등 외부 언어로 작성된 메소드 속도 최적화를 위해서 자바로 구현되어 있지 않다!
    jvm에 종속 되어 있어야 하고 그런 문제가 있어서
    
* 실제 메모리 값이 아닌 hashCode값이 동일해야 객체들이 동일하다고 말할 수 있다.
* 실제 메모리값은 그대로 있다. 단지 overriding만 하는 것이다.
* 일반적으로 equals를 오버라이딩하면 hasCode도 오버라이딩 한다.

```groovy
        Integer i1 = 100;
        Integer i2 = 100;
        System.out.println(i1.equals(i2));
        System.out.println(i1.hashCode());
        System.out.println(i2.hashCode());

        System.out.println(System.identityHashCode(i1));
        System.out.println(System.identityHashCode(i2)); //진짜 메모리주소 확인하는 메소드
```

##### hashCode()의 제한 사항
* 한 객체의 hashCode()를 여러 번 호출할 경우, equals()에 사용하는 값이 변하지 않으면 
동일한 값을 반환해야 한다.
* equals() 메소드가 같다고 판단한 두 객체의 hashCode() 반환값은 같아야 한다.
* equals() 메소드가 다르다고 판단한 두 객체의 hashCode()가 반드시 다를 필요는 없으나,
다른 값이 나오면 HashTable 성능이 향상된다.

#### clone() 메소드
* 객체의 복사본을 만듦
* 기본 틀(prototype)으로 부터 같은 속성 값을 가진 객체의 복사본을 생성할 수 있음
* 객체지향 프로그래밍의 정보은닉에 위배되는 가능성이 있으므로 복제할 객체는 cloneable 인터페이스를
명시해야 함.
* 같은 객체를 동일하게 인스턴스 값을 동일하게 해서 만드는 것

##### 주의
생성자와는 다름 생성자는 기본 초기화를하면서 만드는데 이건 인스턴스의 상태를 그대로 복제하는 것 
private필드까지 다 복제해올 수 있어서 정보은닉에 위배될 수 있어서 객체복제를 할 때
cloneable인터페이스가 명시되어 있어야 한다.
* clone() 메소드를 사용할 때 object 형으로 반환되므로 다운캐스팅을 해줘야한다
* 구현 클래스에 `Cloneable` 인터페이스를 implements해야 한다.
```groovy
class Book implements Cloneable{
    String title;
    String author;

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return author+","+title;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
public class ToStringTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Book book =  new Book("토지","박경리");


        Book book2 =(Book)book.clone();
        //book.clone()이렇게 했을 때 object 자료형으로 반환이되서
        //명시적으로 다운캐스팅을 해줘야하고 예외처리를 해줘야 한다.

        System.out.println(book2);
        //그냥하면 예외가 발생함
        // 이유 위에 cloneable 인터페이스를 implements 하지 않아서
        // 이런 인터페이스를 마크인터페이스라고 한다.
    }
}
```
#### getClass() 메소드
* Class 에 대한 정보를 담고 있는 Class 객체를 반환
* 객체의 getClass() 메소드 또는 해당 클래스의 정적 변수 class를 이용 가능
```groovy
class Foo {
    public void methodA() {
        System.out.println("method A called.");
    }
}

class FooTest {
    public static void main(String[] args) throws Exception {
        Foo foo = new Foo();
        Class fooClass = foo.getClass();

        System.out.println(fooClass.getName());
        System.out.println(fooClass.getDeclaredMethods().length);
        System.out.println(Foo.class.getDeclaredMethod("methodA").invoke(foo));
    }
}
```
#### finalize() 메소드
* 직접 불러서 사용하는 메소드는 아니다.
* 이메소드는 이 객체가 heap메모리에서 해체될 때
* gc에서 호출되는 메소드 이게 정의가 되어있으면 gc가 이 메소드 부분을 수행한다.
* 주로 리소스해제, 안닫혔을 소켓을 닫는다.

```groovy
@Override
protected void finalize() throws Throwable {
      super.finalize();
}
```
#### System
* 실행 중인 OS와 interact하기 위한 클래스
* System 클래스의 주요 정적 변수

| 속성 | 설명 |
| --- | ---- |
| public static final PrintStream err | 오류를 출력하기 위한 표준 출력 스트림 |
| public static final InputStream in |	표준 입력을 처리하기 위한 입력 스트림 |
| public static final PrintStream out |	표준 출력을 처리하기 위한 출력 스트림 |

* System.in / System.out
```groovy
System.out.println(System.out);// PrintStream 이라는 객체   의 클래스
System.out.println(System.err);// PrintStream 이라는 객체   의 클래스
System.out.println(System.in);// inputStream 이라는 객체 의 클래스 스트림 api랑은 다른 것이다!!!
// Stream API와는 다른,  I/O Stream이다 전혀 연관이 없다!!!!!

System.out.println("f"); // 표준 출력을 처리하기 위한 객체
System.err.println("w"); // 요류를(표준 출력장치에) 출력하기 위한 객체 같은 객체여서 메소드가 같은게 있다.
Scanner sc = new Scanner(System.in); // 표준 입력장치를 사용하기 위한 객체, 스캐너가 인풋스트림을 입력받게 되어있다.
```

* System 클래스의 주요 정적 메소드

  | 메소드 | 설명 |
  | ----- | ---- |
  | arraycopy() |	src 배열의 내용을 dst 배열로 복사한다. |
  | currentTimeMillis() |	현재 시스템 시간을 ms로 반환한다. |
  | exit() |	프로그램을 종료한다. |
  | gc() | GC를 요청한다. |
  | getenv() | 환경 변수의 값을 반환한다. |
  | getProperties() |	시스템 속성을 Property로 반환한다. |
  | getProperty() | 시스템 속성 값을 문자열로 반환한다. 없을 경우 null 또는 def를 반환 |
  | identityHashCode() |	객체의 해시코드 값을 반환한다. |
  | lineSeparator() |	시스템의 줄넘김 문자열을 반환한다. UNIX: \n, WINDOWS: \r\n |
  | nanoTime() |	시스템 시간을 ns로 반환한다. |
  | setProperties() |	시스템 속성을 한번에 설정한다. |
  | setProperty() |	시스템 속성을 하나씩 설정한다. |

   * 주요 메소드
   
     | 속성 | 설명 |
     | --- | --- |
     | user.country | OS 로케일 정보 |
     | java.io.tmpdir |	임시 경로 |
     | line.separator |	줄넘김 문자열 |
     | user.home |	유저 홈 경로 |
     | file.separator |	파일 경로 구분 |
     
* arrayCopy() - 이것도 native로 구현되어 있다. 시스템에 구현되어있는게 native여서 이다. 
OS적으로 최적화가 들어가 있다 그래서 for문 쓰는 것보다 효율적이다.
```groovy
int[] ints = {1,2,3,4};
int[] ints1 = new int[10];
System.arraycopy(ints,0,ints1,0,ints.length);
```

* currentTimeMills(), nanoTime() 시간에 관련된 것들
```groovy
System.out.println(System.currentTimeMillis()); //어떤 기준으로 밀리초마다 카운터하는게 있다
System.out.println(System.currentTimeMillis()); // 두번연속 출력해도 차이가없다
System.out.println(System.nanoTime()); // 어떤 기준으로 나노초마다 카운터하는게 있다. 0으로끝나있어서 1나노까지 정밀하게 구현되어있지 않다. 리얼타임오에스(RTOS)가 아니기대문에 정확하지 않을 수 있다.
System.out.println(System.nanoTime());// 두번연속하면 차이가 생긴다.
```

* exit() / gc()
```groovy
System.exit(0);// 프로그램 강제 종료
// status = 0 : 정상 종료를 의미 status!=0 : 비정상 종료(1을 많이씀)
// Process finished with exit code 0 런타임했을 때 맨마지막에 나왔었던 것이다.

System.gc(); // Garbage Collection  우리가 직접 실행해줄 필요는 없다. 다만 실행해주면 좀 더 원하는 타이밍에 빨리 동작하게 할 수 있다.

System.out.println(System.getenv("JAVA_HOME")); // 내 피시에 있는 환경 변수들을 가져온다. 원하는 환경변수들을 가져올 수도 있다.
// System이 os와 통신을 하는 것이기 때문에 가능하다.
System.out.println(System.getProperties()); // 프로퍼티를 가져옴
System.out.println(System.getProperty("user.country")); // 위치정보가 나온다 한국사람이다 영문인지 한국어인지 결정할 수 있다
System.out.println(System.getProperty("java.io.tmpdir"));// 디렉토리를 나오게함
System.out.println(System.getProperty("line.separator")); // window \r\n, unix \n
System.out.println(System.getProperty("user.home"));
System.out.println(System.getProperty("file.separator"));// window \ unix /
```

#### Math

* 수학 계산에 필요한 메소드를 가진 final 클래스
   * 상속을 할 수 없음(상속해서 구현해 봐야 기존 구현보다 나을리 없다. 뻘짓하지마라라는 의미이다~)
* 모든 메소드가 static 메소드로 구현되어 있어서 그대로 사용하게 되어 있다

  | 메소드 |	설명 |
  | ----- | --- |
  | abs() |	절대값을 반환한다. |
  | ceil() | 올림 값을 double로 반환한다. |
  | floor() | 내림 값을 double로 반환한다. |
  | max() |	두 값 중 더 큰 값을 반환한다. |
  | min() |	두 값 중 더 작은 값을 반환한다. |
  | random() |	0 이상 1.0 미만의 임의의 값을 반환한다. |
  | round() | 소수점 첫째자리에서 반올림한 정수 값을 반환한다. |
  | addExact() | 덧셈 연산으로, Overflow 발생 시 ArithmaticException 발생. |
  | subtractExact() | 뺄셈 연산으로, Overflow 발생 시 ArithmaticException 발생. |
  | multiplyExact() | 곱셈 연산으로, Overflow 발생 시 ArithmaticException 발생. |

```groovy
System.out.println(Math.abs(-4)); // int long float double 오버라이딩
System.out.println(Math.ceil(1.2)); //올림 연산 double 입력을 받고, 출력도 double -> 값의 범위때문에 오버플로우가 발생할 수 있다.

System.out.println(Math.floor(1524.4));//내림 연산 double 입력, 출력도 double -> 값의 범위때문에 오버플로우가 발생할 수 있다.
System.out.println(Math.max(4,2)); // 2개의 값만 비교하게 되어있다. -> java에서는 2개씩만 비교
System.out.println(Math.min(4,2));
System.out.println(Math.random()); // 0.0 이상, 1.0 미만의 값을 임의로 출력
System.out.println(Math.round(1.4f)); // float -> int, double -> long
```
```groovy
// 확률 표현
int count=0;
for (int i = 0; i < 1000; i++) {
    if(Math.random() < 0.3) { //몬테카를로기법 확률상으로 30프로가 된다
        count += 1;
    }
}
System.out.println(count);

// 랜덤한 정수
int minVal = 2;
int maxVal = 10;
int randInt = (int)(Math.random() * (maxVal - minVal + 1) + minVal);
System.out.println(randInt);


System.out.println(Integer.MAX_VALUE+10); // 오버플로우가 발생하는것은 에러가아니고 특징이다.
try{
   System.out.println(Math.addExact(Integer.MAX_VALUE, 10));
   System.out.println(Math.subtractExact(Integer.MAX_VALUE, -10));
   System.out.println(Math.multiplyExact(Integer.MAX_VALUE, 4));
}catch (ArithmeticException e){
     e.printStackTrace();
}
System.out.println("end"); // 이게 먼저나오는게 ide의 버그이다.
```
 
## Class 클래스
* 자바의 모든 클래스와 인터페이스는 컴파일 후 class파일로 생성됨
* class 파일에는 객체의 정보(멤버 변수, 메소드, 생성자 등) 가 포함되어 있음
* Class 클래스는 컴파일된 class파일에서 객체의 정보를 가져올 수 있음
* 동적로딩할 때 주로 사용하고 Class클래스를 이용해서 코딩할 일이 많지는 않다.
* 이미 내 로컬PC에는 내가 사용하고자하는 라이브러리들이 있고 라이브러리들이 자료형을 다
알고 있는 상태이기 때문에

### Class 클래스 가져오기
1. String s = new String();
   Class c = s.getClass(); //object의 메소드 Class 클래스를 가져옴
2. Class c = String.Class; //  컴파일된 상태로 있는 경우 
3. Class c = Class.forName("java.lang.String"); //동적로딩
   * 대부분 컴파일 할 때 시스템에서 어떤 애들을 가져다 쓸것인지 컴파일타임에 거의다 바인딩이된다(정적로딩)
   스태틱으로 다 바인딩이 된다 그런데 클래스.forName은 런타임때 바인딩이 됨(동적로딩)
   클래스.forName("이름") 이름에 해당하는 클래스가 로컬에 있으면 불러다가 사용할 수 있다.
   
```markdown
자바에서 jdbc를 사용할 때 관련 db라이브러리(orcale, mysql, mssql) 같은 것들이 있는데
그런 라이브러리들을 모두 스태틱하게 링크해서 컴파일 할 수 없음 
너무 많은 라이브러리들을 링크해서 사용해야 하는건데 그럴 필요없이 라이브러리들을 install 한 
상태에서 필요할 때 부를수 있게 끔 oracle과 연결이될거다 orcacle 이름이 들어가면 된다.
```
* 장점 : 런타임 때 그때그때상황에맞게 라이브러리를 매칭시킬 수 있다는 것
* 단점 : 오타가나면 로딩되다가 클래스를 찾을 수없은 예외가 나타나서 런타임때 오류가날수 있다.

### reflection 프로그래밍
* Class 클래스로부터 객체의 정보를 가져와 프로그래밍 하는 방식
* 로컬에 객체가 없고 자료형을 알 수 없는 경우 유용한 프로그래밍
* java.lang.reflect 패키지에 있는 클래스 활용

### newInstance()메소드
* Class 클래스 메소드
* new 키워들 ㄹ사용하지 않고 인스턴스를 생성

### forName() 메소드와 동적 로딩
* Class 클래스 static 메소드
* 동적로딩이란? 
  * 컴파일 시에 데이터 타입이 모두 binding 되어 자료형이 로딩되는 것(static loding)이 아니라 실행 중에 데이터 타입을 알고 binding 되는 방식
* 실행 시에 로딩되므로 경우에 따라 다른 클래스가 사용될 수 있어 유용하다.
* 컴파일 타임에 체크할 수 없으므로 해당 문자열에 대한 클래스가 없는 경우 예외(ClassNotFoundException)이 발생 할 수 있다.
### 주의 사항
````markdown
Class 클래스에서 class.forName을 주의 깊게 봐야 한다.

다른 언어들도 동적로딩하는 방법이 있다.

스태틱으로 컴파일타임에 다 어떤걸쓰는지 바인딩되는 그런 방법도 있고,
런타임 때 내가 쓸걸 로딩해서 쓰는 방법도 있고 언어들마다 제공되고 있다.

자바에서 동적바인딩은 class.forName으로 제공되고있다.
````
