package com.company.s04.p03;

/**
 * 클래스 내부 클래스
 * static 키워드와 함께 선언되 내부 클래스
 * 외부클래스 없어도 생성가능
 * static 멤버 변수를 가질 수 잇다.
 */

class Outer{
    static class ClassInner{
        int innerVal = 1;
        static int staticInnerVar = 100;

        void innerMethod(){
            Outer outer  = new Outer();
            System.out.println(outer.outerVar);
            System.out.println(innerVal);

            /**
             * 내부 클래스 메소드에서 외부클래스 객체를 생성해야만
             * 외부클래스의 인스턴스 멤버변수에 접근가능
             * 내부에서 외부를 접근하는건 드물다다
             * 외부클래스의 private를 접근하는게 포인트?
             * 여기서 클래스 ,메소드가 정의되는 시점에 ㅇ특정 인스턴스에 메어있는게 아닌데
             * outer 인스턴스가 연관이 되어있지 않은데 객체를 생성해서 선언해줘야 한다
             * 그럼에도 private 변수에 접근ㄴ이 가능한게 이상하지만 가능하다
             * 이게 핵심
             *
             * 외부클래스가 싱글톤이면 자바에서 문법적으로 제공하는 것은 아니다?
             * 다만 로직컬하게 하나만 사용하게 끔 막아놓은것 디자인패턴이다
             * 문법적으로 이것에 영향을 주지 않음
             * 직접접근 불가하고 Outer. 이렇게 접근 가능
            */
        }
    }

    private int outerVar = 2;

}
public class Main {
//    4:26
   public static void main(String[] args) {

  }
}
