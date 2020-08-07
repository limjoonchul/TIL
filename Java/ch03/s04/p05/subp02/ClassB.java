package com.company.s04.p05.subp02;
import com.company.s04.p05.subp01.ClassA;

class ClassAA extends ClassA{
    public void methodTest(){
        System.out.println(x);
        System.out.println(y); //protected 자식이면 다른 패키지여도 ok 실제로 많이 씀. 하나의 자식 외에는 못봄. 부모의 부모는 건들 수 없음.(자바특성)
        // protected는 private처럼 쓰지만, 상속한 경우 구현 시 접근이 필요 할 때 사용.
       // System.out.println(z);
       // System.out.println(w); private - 상속해도 못봄

        methodA();//public
        methodB();//protected
        //methodC();
        //methodD();
    }
}

// public default만 사용 가능, private은 쓸 수 없음 같은 클래스만 볼 수 있기 때문에 다른 클래스라는 개념이 없기 때문에. protected도 사용 불가.
public class ClassB {
    public static void main(String[] args) {
        ClassA obj = new ClassA();
        System.out.println(obj.x); //다됨 public
       // System.out.println(obj.y); //protected 다른 패키지인경우 자식만됨.
        //System.out.println(obj.z); //default 다른 패키지이면 안됨.
        //System.out.println(obj.w); 접근 불가

        obj.methodA();
        //obj.methodB();//protected 다른 패키지인경우 자식만됨.
        //obj.methodC();//default 다른 패키지이면 안됨.
        //obj.methodD(); private 검색도 안됨
    }

}
