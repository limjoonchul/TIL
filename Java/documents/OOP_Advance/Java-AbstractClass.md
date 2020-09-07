# 추상 클래스(Abstract Class)
2020-08-10 3-1차

## 추상 클래스란
* 일부 메소드가 구현되지 않고, 선언만 되어있는 클래스
   * 자식 클래스에서 이것을 반드시 구현하게끔 강제하는 것
   * 필요한 모든 클래스가 구현될 수 있도록 하여 안정성을 높이는 효과를 가져옴.
## 추상클래스 특징
* abstract 예약어 사용
* 추상클래스는 new(인스턴스화) 할 수 없다.
* 추상클래스는 단독으로 사용되는 것이아니라 상위클래스로서 사용된다
* 공통적으로 사용할 수 있는 메소드들은 사용할 수 있다.
* 구현해야하는 메소드들은 상위클래스에서 선언을 해놓고,구현의 책임을 하위클래스에 위임한다.
* 하나의 추상메소드라도 포함을 하면 추상클래스가 되야 한다.
* 추상메소드가 없어도 abstract 키워드를 사용하면 추상클래스가 된다
* 생성자를 가질 수 있고, 일반 메소드도 가질 수 있다. 
* 생성자를 갖지만, 객체 생성이 불가능하다. 그런데 구현된 자식클래스의 객체는 받을 수 있다.
* 추상클래스의 변수를 초기화 할 수 있다.(자동 초기화도 된다.)
* 자기 자신의 객체를 생성하지 못하는 거 외에는 일반클래스와 비슷하다.
* 인터페이스와의 차이점은 멤버 변수를 갖고 있느냐 없느냐의 차이.

````java
abstract class AbstractFoo{
    int x, y;

    public void method(){
        System.out.println("method");
    }
                                       //세미콜론을 잊지 말아야 합니다. 시험*
    public abstract void abstractMethod(); // 선언만 하고 구현하지 않음. Virtual method call이여서 부모메소드는 비어있음.
}

class Foo extends AbstractFoo{

    @Override
    public void abstractMethod() {
        System.out.println("implemented abstractMethod");
    }
}

public class AbstractClass {
    public static void main(String[] args) {
       // AbstractFoo afoo = new AbstractFoo() //추상 클래스는 객체 생성이 불가합니다. 구현이안되있으니 인스턴스화가 안된다.
        Foo foo = new Foo();
        foo.abstractMethod();

        AbstractFoo afoo = (AbstractFoo)foo;
        afoo.abstractMethod(); // virtual method call
        //추상클래스는 객체 생성은 불가하지만, 구현된 자식클래스의 객체는 받을 수 있다.
    }
}
````

# 템플릿 메소드
* 템플릿 - 틀이나 견본을 의미한다
* 템플릿메소드 - 추상메소드나 구현된 메소드를 활용하여 전체의 흐름(시나리오)를 정의해 놓은 메소드
final로 선언하여 재정의 할 수 없게 한다.

## 템플릿 메소드 패턴
* 디자인 패턴의 일종으로 프레임워크에서 많이 사용되는 설계패턴이다
* 추상클래스로 선언된 상위클래스에서 추상메소드를 이용하여 전체 구현의 흐름을 정의하고
구체적인 각 메소드 구현은 하위클래스에 위임함 
* 하위클래스가 다른 구현을 했 다고 해서 템플릿 메소드에 정의된 시나리오대로 수행됨.