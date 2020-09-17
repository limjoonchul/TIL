package com.company.s04.p05;



/**
 * 익명의 내부 클래스
 * 로컬 내부클래스와 똑같다 똑같은데 이름이 없는 것이다.
 * 딱 한번만 쓸 수있다.
 * 추상클래스나 인터페이스의 구현에 많이 사용
 */
interface IFoo{
    void run();
    void walk();
}
//5:23
class Foo implements IFoo {
    @Override
    public void run() {
        System.out.println("Normally Run");
    }

    @Override
    public void walk() {
        System.out.println("Normally Walk");
    }
}


class AnonymousInnerClass{
    static void useIFoo(IFoo foo){
        foo.run();
        foo.walk();
    }

    public static void main(String[] args) {
        // 로컬내부클래스에서 외부클래스를 상속해서 많이 사용한다.
        //1.
        Foo foo = new Foo();
        useIFoo(foo); // 다형성

        //2.
        class InnerClass implements IFoo{
            @Override
            public void run() {
                System.out.println("Run, Foo Run!!");

            }

            @Override
            public void walk() {
                System.out.println("Walk, Foo Walk...");
            }
        }// 이걸 원래 밖에 있었는데 안에 적어줬다.
        InnerClass ic = new InnerClass();
        useIFoo(ic); // 다형성 + local inner Class

        // 익명 내부클래스 IFoo는 인터페이스여서 new가 올수 없는데
        // 익명의 내부 클래스를 만들 때는 이런 문법이 가능하다
        // IFoo를 상속하는 클래스를 구현할 때 이렇게 사용한다?
        // 구현이되자마자 객체가 한번되서 실행이 되고 끝남
        // 즉시 한번만 사용이 가능하다.

        //3.
        useIFoo(new IFoo() {
            @Override
            public void run() {
                System.out.println("Run!!");
            }

            @Override
            public void walk() {
                System.out.println("walk...");
            }
        }); // 다형성 + 익명내부클래스
    }
}





interface IBar{
    void run(int x, int y);
}

class Bar implements IBar{

    @Override
    public void run(int x, int y) {
        System.out.println("Bar");
    }
}


public class Main {

    static void runIBar(IBar bar){
        bar.run(10,100);
    }

    public static void main(String[] args) {
        runIBar(new Bar());

        class InnerBar implements IBar{

            @Override
            public void run(int x, int y) {
                System.out.println("InnerBar");
            }
        }
        runIBar(new InnerBar());

        runIBar(new IBar() {
            @Override
            public void run(int x, int y) {
                System.out.println("Anonymous");
            }
        });


        // this is lambda expression!!!!
        // 익명클래스를 한번 더 줄여쓴게 람다
        // 람다를 쓰기 위한 조건은 구현하고자하는
        // 인터페이스가 추상메소드가 단 하나만 존재해야한다!
        // 여기서는 run() 만존재한다.
        // 익명클래스는 두개를 줘서 나눠서 해서 람다식으로 대체 불가

        // 람다는 함수형 프로그래밍에서 사용하는 문법이여서
        // 기존 자바 객체지향프로그래밍과 패러다임이 달라서
        // 새롭게 보일 수 있다.
        runIBar((x,y)->{
            System.out.println(x);
            System.out.println(y);
            System.out.println("LAMBDA!!");
        });
    }
}
