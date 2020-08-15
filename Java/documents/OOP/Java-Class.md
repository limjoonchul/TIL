# Java 2-1
## 클래스(Class)
### 클래스와 객체
 * 클래스 - 객체를 생성하기 위한 설계도(class)
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
 * 객체 - 클래스를 구체화하여 값으로 생성된 것(object, instance)
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
 

### 변수
 * 클래스에 사용되는 변수들
  
  ````markdown
 - 종류
  *  클래스 멤버 변수(static variable, class variable)
  *  인스턴스 멤버 변수(member variable, attribute...)
  *  로컬 변수(local variable)
  *  로컬 파라미터 변수 (local parameter variable)
  *                    (arguments)

  ````
 * 변수들 실제 예제
 ````java
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
### 클래스와 객체의 메모리 구조
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
# Java 2-2
### 메소드(Methods)
 * 객체가 하는 동작(행위)을 정의하는 작업을 수행하는 코드의 집합이자 나열
 * 코드의 중복을 방지, 유지보수성을 향상, 코드의 가독성을 개선하는 역할을 한다.
 
#### 메소드의 구현
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
     
#### 메소드 오버로딩
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
### this 키워드
  * 객체가 스스로를 가르키는 참조
  * 멤버 변수와 로컬 변수의 이름이 같을 때, 멤버 변수임을 명시
  * 생성자를 호출하는 데에도 사용할 수 있다.
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
### Getter와 Setter
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
### 초기화 블록(Initializer)
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
