package com.company.s04.p04;


/**
 * super 키워드
 * this 가 자신의 객체를 참조하듯,
 * super 는 부모 객체를 참조한다.
 *
 * 다만 super.super 라는 식으로 부모의 부모는 참조할 수 없다. 바로 윗 부모만 가능.
 */

class Foo{
    String x = "Foo";

    public Foo(String x) {
        this.x = x;
    }
}
class Bar extends  Foo{
    String x = "Bar"; //멤버 변수명이 부모와 겹치면 재정의 하지만 부모가 사라지는 것은 아님.

    //부모클래스에 기본 생성자를 사용하는 경우에는 호출 안해주어도 된다.
    //부모클래스에 파라미터 생성자가 있으면 호출해 줘야 한다. super 사용.
    public Bar(String x, String x1) { //부모클래스 생성자와 다르게 파라미터 변수명을 다르게 해줘야 한다. x,x1
        super(x); //this와 마찬가지로 첫줄에 써야한다. 부모클래스 생성자 호출.
        //부모 객체를 먼저 생성하고, 그 다음에 자식 객체를 생성.
        this.x = x1; //자식클래스 생성자를 만들때 부모클래스 생성자도 같이 호출해줘야한다.
    }

    public void method(){
        //로컬변수명과 멤버변수명은 같을 수 있다.
//        String x = "method"; 로컬변수가 없어지면 멤버변수를 참조, 멤버변수도 없어지면 super클래스의 변수사용.
        System.out.println(x);//로컬변수 -> 멤버변수 -> 부모의 멤버변수 순으로 접근
        System.out.println(this.x); // 자기 자신의 객체에 접근가능 멤버변수 - > 부모의 멤버변수
        System.out.println(super.x); //부모 객체에 접근 가능    부모의 멤버변수

    }
}
// 아무것도 상속하지 않는 경우, object 클래스를 상속하는 것과 같다.
class  Jaemi extends  Object{
    public  void method(){
        //모든 클래스는 오브젝트 클래스를 자동으로 상속하게 되어있음 아무것도 안써져있음 사실 extends object와 같은 거임
    }
}
public class Main {
    public static void main(String[] args) {
        Bar bar = new Bar("","");
        bar.method();
        // 자식 객체를 생성을 하면,
        // 부모 객체를 먼저 생성하고, 그 다음에 자식 객체를 생성하게 된다.
        // 자식 객체를 생성할대마다 부모 객체를 따로따로 만들어서 가지고 있음.
    }
}
