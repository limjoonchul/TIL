package com.company.s04.p02;

/**
 * 인스턴스 내부 클래스
 */
class Outer{
    class InstanceInner{ //클래스자체가 외부 클래스의 객체에 속해있기 때문에 이ㅣㄴ스턴스 메소드가 자연스러움
        int x = 1;
        void innerMethod(){
            int x = 0;
            System.out.println(x);//0
            System.out.println(this.x);//1
            System.out.println(Outer.this.x);//2
            System.out.println(Outer.y);//3
            // 객체가없으니 Outer.this로 사용해야한다? Outer.x는 안됨
            // Outer클래스의 인스턴스 멤버변수를 출력 하는 의미
            // this를 객체로 사용된다는 걸로 이해
            //4:16
        }
    }

    private int x = 2; // 이건 인스턴스멤버변수
    private static  int y = 3; // Outer.y 일 때 출력됨

    public static void main(String[] args) {
        Outer outer = new Outer();
        InstanceInner inner = outer.new InstanceInner();
        inner.innerMethod();
    }

}
public class Main {
}
