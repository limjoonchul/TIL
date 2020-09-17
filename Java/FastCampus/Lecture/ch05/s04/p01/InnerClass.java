package com.company.s04.p01;

/**
 * 특정클래스내부에서 컴포지션 어그리게이션  같은 경우에 중첩클래스를 사용함
 * has-a관계에 있는 클래스가 해동 클래스에서만 주로 사용될 경우
 *
 * 내부클래스의 종류
 * 크래스 영역에 선언
 * 인스턴스내부클래스 - 외부 클래스의 멤버변수처럼 사용 가능
 *
 *
 * 클래스내부클래스 -static이 붙은 정적인 내부클래스
 */

class Outer{
    class InstanceInner{// outer클래스의 객체가 있어야 innerclas의 정의가 생긴다.
        int innerMemberVar = 1;
        // static int staticvar = 20; 스태틱변수가 있을 수 없다
        // 있으면 어색하기 때문에? 사용하지 않는다.
        static final int CONSTANT_VAR= 1000; //반드시 FINALL 만가능
        // 상수는 하나만들어놓고 같이쓰면됨

        void innerMethod(){
            System.out.println(innerMemberVar);
            System.out.println(outerMemberVar); // outer의 private 멤버변수에도 접근 가능
        }
    }

    private int outerMemberVar = 2;

    void outerMethod(){
        // 이건 아우터크래스이 객체가 있어서 호출이되니 이너클래스를 생성할 수 있다.
        InstanceInner obj = new InstanceInner();
        obj.innerMethod(); //이너메소드를 콜할 수 있다.
    }

    public static void main(String[] args) {
        //innerclass는 인스턴스이다 그래서 객체를 생성해ㅑㅇ 효력이 발생한다.
        // 그런데 static 메소드에서는 객체생성이 안된다
        // 그래서 아우터 메소드를 만들어준다음에 이너클래스를 만들어야 한다.
        Outer outer = new Outer();

        InstanceInner inner = outer.new InstanceInner(); // 받아들어야함 암기해야한다

        Outer outer2 = new Outer();
//        Outer.InstanceInner.staticVar;
//        Outer2.InstanceInner.staticVar; 다른객체로만들어지는데 두개를 어떻게 써야하는지 애매해짐



        inner.innerMethod();

    }
}

public class InnerClass {
    public static void main(String[] args) {

    }
}
