#다형성(Polymorphism)
2020-08-10 3-1차
##다형성의 다양한 특징
 * 오버라이딩에 의한 다형성
 * 오버라이딩에 의햔 다형성(상속에 의한 다형성)
 
 * 부모 클래스 타입으로 자식 클래스 객체를 참조하는 특징
 * 부모 클래스로 자식 클래스를 참조한 경우, 자식 클래스의 메소드는 사용할 수 없다.
 ````
class Foo{
    public void methodA(){
    }
}

class Bar extends Foo{
    public void methodB(){
    }
}
public class Poly01 {
    public static void main(String[] args) {
        Bar bar = new Bar(); //new가 있어야 새로운 객체를 만듬 자식 객체를 생성한 것이다.
        Foo foo = (Foo)bar; // 부모 클래스 타입으로 자식 객체를 받음.
        // 여전히 힙영역에는 자식객체가 있는 것이다.

        foo.methodA(); // Foo객체로받아서 문법적으로 a만 사용 가능, b는 불가능
        //foo.methodB();//이 경우 자식 클래스 메소드는 사용이 불가능 문법적으로 불가능함. 문법적으로 제한을 한 것이다.

        Foo foo1 = new Foo();
        //Bar bar1 = (Bar)foo1; // 자식 클래스 타입으로 부모 객체를 받음 이거 자체가 오류(문법 오류는 아님)
        // 문법 오류는 아니지만, 런타임 오류가 발생
        // 자식클래스 자료형으로 객체를 보려 하지만, 부모 객체 부분만 있기 때문에
        // 런타임에서 오류가 발생시킴.
        //bar1.methodA(); //문법오류x 성립하지 않음
       // bar1.methodB(); //문법오류x 성립하지 않음

        Bar bar1 = (Bar)foo; //원래자식객체를 자식객체로 캐스팅함 자식클래스 타입으로 자식 객체를 받음
        // 힙 영역에는 계속 자식 객체가 있었던 것. 부모객체 자료형으로 있는것을 자료객체로 캐스팅할 때
        // foo변수에 자식객체가 담겨져있는지 부모객체가 담겨져있는지 문법적으로 알수없음
        bar1.methodA();
        bar1.methodB();
    }
}
 ````
 * 자식클래스로 부모 클래스를 참조하려 하면 java.lan.ClassCastException 오류 발생
 ````
 public class Main {
     public static void main(String args[]) {
         Foo foo = new Foo();
         Bar bar;
 
         // bar = (Bar)foo; // error
         if (foo instanceof Bar) { // returns false
             bar = (Bar)foo;
         }
     }
 }
 ````
  * 멤버 변수의 재정의는 선언된 객체의 타입을 따른다. (문법적으로 본다) 클래수 변수도 마찬가지 변수는 별도의 메모리에 저장됨.
  * 메소드 오버라이딩은 메모리상의 객체 타입을 따른다. (실제 객체로 본다)
  * (가상 메소드 호출, virtual method call)
  ````
  class  Foo{
      static public String y = "super class";
      public String x = "Super";
      public  void methodA(){
          System.out.println("Super method"); //(가상 메소드 호출, virtual method call) 실제로 콜이 이루어지지 않지만 만들어줌 실제로 호출되지않ㅇ므
          // 없으면 안됨 문법을 맞춰주기 위한 것 뿐 구현된 것 자체는 중요하지 않음..
      }
  }
  
  class Bar extends Foo{
      static public String y = "sub class";
      public String x = "sub";
      @Override
      public void methodA(){
          System.out.println("sub method");
      }
  }

  public class Poly02 {
      public static void main(String[] args) {
          Bar bar = new Bar();
          Foo foo = (Foo)bar;
  
          System.out.println(bar.x); //sub
          bar.methodA(); //sub
  
          System.out.println(foo.x); //super
          foo.methodA(); //sub virtural method call
  
          System.out.println(Foo.y);
          System.out.println(Bar.y);
  
  //        System.out.println(foo.y); //super
  //        System.out.println(bar.y); //sub
      }
  }
  ````
 * 공변 변환 타입(Covariant return type)
 ````
 class Foo{
     public Foo getInstance(){
         return this;
     }
 }
 class Bar extends Foo{
     @Override
     public Bar getInstance(){ //오버라이딩이지만, 리턴 타입이 달라질 수 있다. 여기서는 예외적으로 반환값이 달라진다. 공변 반환 타입
         return this;
         //return (Foo)this; 이거와 같다.
     }
 }
 ````

 
