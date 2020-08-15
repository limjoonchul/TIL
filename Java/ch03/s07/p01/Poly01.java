package com.company.s07.p01;


/**
 * 부모 클래스 타입으로 자식 클래스 타입의 객체를 참조하는 특징
 */

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
