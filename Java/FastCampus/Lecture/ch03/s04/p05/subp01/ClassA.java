package com.company.s04.p05.subp01;




// public 클래스를 꼭 가지고 있어야 한다.
public class ClassA {
    public  int x;
    protected  int y;
    int z; // default (=package)
    private int w;

    public void methodA(){} //
    protected void methodB(){} //
    void methodC(){}//default (=package)
    private void methodD(){} //private - 내부 구현을 위해서만 사용.

    public void methodTest(){ //같은 클래스
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        System.out.println(w);

        methodA();
        methodB();
        methodC();
        methodD();
    }
}

class ClassATest{ // 같은 패키지인 경우.
    public static void main(String[] args) {
        ClassA obj = new ClassA();
        System.out.println(obj.x);
        System.out.println(obj.y);
        System.out.println(obj.z);
        //System.out.println(obj.w); 접근 불가

        obj.methodA();
        obj.methodB();
        obj.methodC();
        //obj.methodD(); private 검색도 안됨
    }
}
