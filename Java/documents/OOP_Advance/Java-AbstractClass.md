#추상 클래스(Abstract Class)
2020-08-10 3-1차
##추상 클래스란
* 일부 메소드가 구현되지 않고, 선언만 되어있는 클래스
   * 자식 클래스에서 이것을 반드시 구현하게끔 강제하는 것
   * 필요한 모든 클래스가 구현될 수 있도록 하여 안정성을 높이는 효과를 가져옴.
````
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