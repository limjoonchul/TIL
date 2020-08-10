package com.company.s07.p02;

/**
 * 멤버 변수의 재정의는 선언된 객체의 타입을 따른다. (문법적으로 본다) 클래수 변수도 마찬가지 변수는 별도의 메모리에 저장됨.
 * 메소드 오버라이딩은 메모리상의 객체 타입을 따른다. (실제 객체로 본다)
 * (가상 메소드 호출, virtual method call)
 */

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
