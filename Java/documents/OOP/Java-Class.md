# Java 2-1
# 클래스(Class)

## 클래스 - 객체를 생성하기 위한 설계도(class)
 * 문자열(String) - 한번 만든 문자열은 변하지 않는다. (Immutable) 그래서 새로운 문자열을 만들어서 값을 넣어 주는 것과 같다.
 * 클래스는 문자열과 달리 mutable 변할 수 있다.
 * 참조형 객체이기 때문에new일때만 새로운 객체를 생성하고,이렇게하면 같은 객체를 가르킨다.
 * 이처럼 carThree 에 carOne을 넣었을 때 Three에서 속성 값을 변경시켰을 때 one의 속성값도 변경됨.
 ```groovy
 Car carThree = carOne;
 
 System.out.println(carThree.speed); //carThree.speed = 5;
 System.out.println(carThree.speed);// 5
 System.out.println(carOne.speed);// 5
 ```
 
 ````groovy
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
## 객체 - 클래스를 구체화하여 값으로 생성된 것(object, instance)
 * 객체는 클래스를 구체화하여 값을 생성하는 것을 객체라 한다.
 * 하나의 클래스로 여러개의 객체를 만들 경우, 같은 타입의 `독립적인` 객체가 됨
  ````groovy
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

## 변수
1. 클래스 멤버 변수(static variable, class variable)
2. 인스턴스 멤버 변수(member variable, attribute...)
3. 로컬 변수(local variable)
4. 로컬 파라미터 변수 (local parameter variable)(arguments)

1. 클래스 멤버 변수(스태틱, 정적 변수)
      * static을 이용하여 정의하는 변수를 말하며, 클래스에서 바로 접근하는 변수를 의미 한다.  
      * 객체명으로도 접근이 가능하지만 권장하지 않는다.
      * new 될때마다 새롭게 잡히는 멤버변수와 달리 한번만 메모리가 잡히고 모든 인스턴스가 공유한다. 
      * 처음 프로그래밍에 로드될 때 클래스영역에 생긴다.
      * 인스턴스들이 공동으로 사용하는 변수, 외부에서 접근하지 못하게 private으로 하는게 좋음.
      * 초기화를 안해줬을 경우, 자동으로 자료형의 기본값으로 초기화 됨.(자동으로 초기화가 안이루어지는 경우도 있음.)
 
  ````groovy
   static int classVar; 
   // 클래스 멤버 변수, 스태틱 변수(정적 변수) 바로 메모리에 올라감.
   
   System.out.println("클래스 변수");
   System.out.println(Variable.classVar); 
   //0으로 초기화됨(외워야 됨 어떤변수들은 초기화가 안이뤄지는 경우도 있음)
   ````
2. 인스터스 멤버 변수(필드, 속성) 
   * 객체를 생성해서 사용하는 변수로 클래스명으로 접근이 불가능하다.
   * 초기화를 안해줬을 경우, 자료형의 기본값으로 자동 초기화 됨.
````groovy
int instanceVar; // 인스턴스 멤버 변수, 필드, 속성
System.out.println("인스턴스 멤버 변수");
Variable var = new Variable();
// 인스턴스를 만들어야 실체가 있기 때문에
System.out.println(var.instanceVar);  
//0으로 초기화가 됨.외워야됨.
````

3. 로컬 변수 
   * 메소드 또는 중괄호 블록 내부에서 생성되는 변수
   * 스택 영역에 생성되며, 초기화가 이루어지지 않음
   * 생명 주기(Life cycle)은 생성된 중괄호 블록이 종료될 때 까지
   
4. 파라미터 변수 
   * 메소드에서 값을 호출할 때 넣어 주는 변수. 메소드내부에서만 사용 가능.
````groovy
public void method(int paramVar){ //로컬 파라미터 변수
  System.out.println(paramVar);
  int localVar; //로컬 변수
  System.out.println(localVar); //error 로컬변수는 초기화가 자동으로 안됨. 
  localVar = 10;
  System.out.println(localVar); //이렇게 값을 대입해서 사용해야 한다.
  {
    localVar = 30
  };
  int localVar2 = 20;
}
System.out.println(localVar); //블록 내에서 수정한 것도 반영됨.     
localVar2 = 40; // 접근 불가. 생명주기가 끝났다. Life-Cycle이 끝났다.
````

* 테스트
````java
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

## 메모리 할당 시점
````markdown
변수가 메모리에 생성될 때는 초기화가 이루어지는 시점인데, 초기화가 되는 시점은
클래스나 메소드에서 초기화 값을 준다고 해도 메모리에 생성이 되는게 아니다.
변수를 선언하는 것은 메모리를 자료형의 크기만큼 쓰겠다라고 하는 것이다(메모리에 공간 확보)
변수 초기화는 메모리에 실제 값을 할당 하는 것이다.
메인 메소드안에 있는 변수들은 실행될 때 메모리에 생성됨.
정적 변수 - 클래스가 생성이 될 때 메모리에 할당이 된다.
````

## 클래스와 객체의 메모리 구조
 * 클래스와 객체의 메모리 구조 특징
 ````markdown
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
  ````java
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

## String 타입의 메모리 구조
 * class, method 같은 것들은 클래스 영역에 만들어지고, 
 * method 안에 있는 값들은 스택영역에 만들어졌다가 method의 생명주기가 끝나면 사라짐.
 * class 안에 변수들은 힙영역에 생성이 되는데, 정수, 실수형 변수는 constant pool 안에 정의 되어있는 값들을 가져와서 사용하는 것이고, 
   String은 정의할 때 힙 영역에 값이 생성됨.
 * 처음에 young메모리(nursery)에 생성되었다가 오랫동안 사용하면 올드메모리로 옮겨짐.
 * new로 생성했을 때 기본적으로 young 메모리에 들어간다.
 
 ````groovy
 @org.junit.Test
 public void testStringEquality (){
     final String literal = "Hello";
     final String object = new String("Hello");
 
     Assert.assertTrue(literal.equals(object));
     Assert.assertFalse(literal == object);
 }
 ````
 * 위에서 String의 equals은 true, ==는 false가 나오는 경우를 볼 수 있는데,
 * equals는 `문자열`을 비교하고, ==는 객체의 `주소값`을 비교하기 때문이다.
 
 ````groovy
 @org.junit.Test
 public void testStringIntern(){
     final String literal = "Hello";
     final String object = new String("Hello");
     final String intern = literal.intern();
 
     Assert.assertTrue(literal.equals(object));
     Assert.assertFalse(literal == object);
     Assert.assertTrue(literal.equals(intern));
     Assert.assertTrue(literal == intern);
 }
 ````
`````markdown
 위의 코드를 보면 intern()메서드를 호출한 결과값을 intern 변수에 할당되는 것을 볼 수 있다. 
 그 후 literal과 object의 동일성(==)과 동등성(equals)를 비교해보면 둘다 true값이 나와 테스트에 성공하게 된다.
 리터럴로 "Hello"라는 문자열이 string constant pool에 저장되었고, 
 inter() 메서드를 호출하면서 string constant pool에서 Hello라는 문자열을 검색하고 
 이미 존재하기 때문에 "Hello" 의 동일한 주소값을 반환하게 되어 true가 나오게 된다.
`````

 * String을 리터럴로 선언할 경우 내부적으로 String의 intern()메서드가 호출되게 된다. 
 * intern() 메서드는 주어진 문자열이 string constant pool에 존재하는지 검색하고 있다면 그 주소값을 반환 하고,
   없다면 string constant pool에 넣고 새로운 주소값을 반환한다.
 
# Java 2-2
## 메소드(Methods)
 * 객체가 하는 동작(행위)을 정의하는 작업을 수행하는 코드의 집합이자 나열
 * 코드의 중복을 방지, 유지보수성을 향상, 코드의 가독성을 개선하는 역할을 한다.
 * 함수를 사용하는 것을 호출한다라는 것이고 함수는 반환값이 있어서 결과를 나를 호출해준 애한테 돌려준다 반환값을 return해준다.
 * 함수 호출이 끝나면 자동으로 메모리가 반환된다. 메인만 남게된다.
 * 함수의 호출이 될 때 생성되서 호출이 끝나면 사라지는 변수들이다. (지역변수)
 * 메소드는 클래스 영역에 생성됨!!**
 ````groovy
 System.out.println(student);
 ````
 * com.company.ch05.classPart.Student@1ddc4ec2의 주소값은 실제 물리적인 주소값은 아니고
 가상 jvm이 준 해시코드 값이다. student가 가리키는 메모리의 위치가 32비트를 나타내는 메모리를 가리킨다
### 메소드의 구현
   * 메소드는 함수의 형태로 구성된다.
     * 파라미터(Parameters,입력)
     * 실행문(Executional Statements)
     * 리턴 값(Return Value, 출력)
   * 함수의 작성
   ````groovy
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
   ````groovy
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
     * 변수의 자료형은 기본 자료형, 참조 자료형으로 나눠진다.
        * 변수에 대한 생성은 생성자에서 주로하는데 사용하기 전에 만 하면 된다.
        사용하기 전 상태에서 값을 넣으면 NullPointException이 발생할 수 밖에 없다.
        메모리가 없는데 값을 넣으면 에러가 날 수 밖에 없다.   
       
   
   ````groovy
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
   ````groovy
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
     
### 메소드 오버로딩
   * 동일 기능의 함수를 추가로 구현하는 방법
   * 입력 파라미터를 다르게 해서 동일한 함수명으로 구현한다.
   ````groovy
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
### 애노테이션(Annotation)
* 재정의된 메소드라는 의미로 선언부가 기존의 메소드와 다른 경우 에러가 발생한다.(같게 해줘야 함)
* 애노테이션은 컴파일러에게 특정한 정보를 제공해주는 역할을 한다
  * (컴파일 오류를 막아주고, 컴파일러에게 정보를 전달해줌.)
* 주로 사용되는 자바에서 제공되는 애노테이션

| 애노테이션 | 설명 |
| ---------| -----|
| @Override | 재정의된 메소드라는 정보 제공|
| @FuctionalInterface | 함수형 인터페이스라는 정보 제공|
| @Deprecated | 이후 버전에서 사용되지 않을 수 있는 변수, 메서드에 사용됨|
| @SupperessWarnings | 특정 경고가 나타나지 않도록 함 ex) @SupperessWarnings("deprecations")는 @Depreacted가 나타나지 않도록 함.|

## 생성자(Constructor)
 * 클래스에서 인스턴스를 생성할 때 사용되는 메소드
   * new 키워들 이용해서 호출
 * 함수의 기능이필요할 때 호출해서 사용하지만 생성자는 객체를 생성할 때만 호출 가능
 * 인스턴스를 초기화 하는 코드가 구현 됨. 주로 멤버 변수 초기화
 * 상속되지 않고, 반환 값이 없다.

### 기본 생성자 (Default Constructor)
 * 구현하지 않아도 자동으로 생성되는 생성자.
 * 아무런 동작도하지 않고, 객체만을 생성
 * 매개변수가 없고, 구현부가 없는 것
 
#### 기본 생성자 컴파일에서 과정
 * 자바 컴파일러가 코드가 컴파일하기전에 프리컴파일이라는 단계가 있는데 우리가 쓴 코드가 바로 기계어로 번역이되는게 아니라 
 코드들이 과감이 되서 필요한 부분이 추가가 되고 없는 부분이 변형이이루어되고 이런식으로 코드가 바뀌는데 생성자가 없을 때,
 프리컴파일 과정에서 디폴트 생성자가 추가가됨 생성자가 하난도 없을 때 추가가 되는 것
   
  
### 파라미터 생성자 (Parameter Constructors)
 * 입력 파라미터를 받는 생성자
 * 여러개의 파라미터 생성자를 오버로딩할 수 있음
 * 보통 멤버 변수를 초기화하는 동작 수행
### 생성자 오버로딩
* 생성자를 두 개 이상 구현하는 경우
* 사용하는 코드에서 여러 생성자 중 선택하여 사용할 수 있음
* private 변수도 생성자를 이용하여 초기화를 할 수 있음
 ````java
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


## this 키워드
  * 객체가 스스로를 가르키는 참조, 자신의 메모리를 가리킨다.
  * 멤버 변수와 로컬 변수의 이름이 같을 때, 멤버 변수임을 명시
  * 생성자를 호출하는 데에도 사용할 수 있다.
    * 생성자 오버로딩에서 한 클래스 안에 생성자가 여러개 있을 수 있는데
      한 생성자에서 다른 생성자를 호출하는 경우가 종종있다.
  * 반드시 생성자의 첫 줄에서만 사용해야 한다.
  ````java
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
* 인스턴스 자신의 주소를 반환할 일이 있으면 this를 반환 하면 된다.
* day로부터 setYear라는 메소드를 호출하게 되면 setYear는 BirthDay에 대한 메소드이니깐
* setYear의 세그멘테이션이 stack영역에 만들어지고 만약 setYear 안에서 this를 썼다하면 
힙영역의 BirthDay에 대한 메모리를 가리킴 
* 인스턴스 내부에서 객체 내부에서 자기 자신이 생성된 메모리주소를 가리킴
* 인스턴스가 여러개 생성 됬다 하면, 각각의 this는 다 다름 코드상에서 this하나만 쓰지만 각각은 다 다름

* 여기서 stack 영역에 this가 들어가게되는데 왜 this가 들어가는지 이해가 안갔는데
메인에서 this가 date.setYear() 이런식으로 호출이 되어있다면 this는 date변수 대신이라고 생각해도 될까?
* 로컬변수에 this는 참조변수로 생성된다. 그런 의미로 이해하면 될 것 같다
 ```groovy
public class MyDate {
    private int day;
    private int month;
    private int year;

    private boolean isVaild = true;

//    public MyDate(int year, int month, int day) {
//        this.year = year;
//        this.month = month;
//        this.day = day;
//    }

    public  void setDay(int day){
        this.day = day;
    }
    public int getDay(){
        return day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if(month <1 || month > 12){
            isVaild = false;
        }
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void showDate(){
        if (isVaild){
            System.out.println(this.year + "년" + this.month + "월" +this.day+"일");
        }else{
            System.out.println("유효하지 않은 범위 입니다.");
        }
    }
}

    public class MyDateTest {
        public static void main(String[] args) {
            MyDate date = new MyDate();
  
            date.setYear(2019);
            date.setMonth(13);
            date.setDay(10);
    
            date.showDate();
    
    //        MyDate date2 = new MyDate();
    //        date2.setYear(2020);
    //        date2.setMonth(2);
    //        date2.setDay(11);
    //
    //        date2.showDate();
        }
    }
  ```

## Getter와 Setter
  * 클래스의 멤버 변수를 간접적으로 다룰 수 있게 하는 메소드
  * 멤버 변수의 캡슐화를 구현하기 위해 사용. -> 정보은닉 / 보호
  * 멤버 변수의 값을 제한해야 할 때 유용.
  ````java
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
## 초기화 블록(Initializer)
 * 클래스 또는 인스턴스를 생성할 때 단 한번 실행되는 코드 블록
 ````java
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
