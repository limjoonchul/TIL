package com.company.p07;

/**
 * 초기화 블록(initializer)
 */
public class Main {
    static int classVar;
    static  int instanceCount;
    int instanceVar;

    // static initializer
    static { //객체가 생성되지 않은 상태 객체에서 접근 불가
        System.out.println("static block1");
        classVar = 20;
    }
    //object initialzer 객체를 만들어서 접근.
    {
        System.out.println("block1");
        instanceVar = 30;
        classVar = 50; // 추천되지 않는다. 객체를 생성하는데 클래스에 대해 변하는것은 권장하지 않음.
        instanceCount++; // 이러한 패턴은 사용된다.
    }
    //여러개 사용 가능. 스태틱은 스태틱끼리 출력되고, 값은 나중에 선언된 값이 삽입됨.
    static {
        System.out.println("static block2");
        classVar = 5;
    }
    {
        System.out.println("block2");
        instanceVar = 5;
    }
}

class MainTest{
    public static void main(String[] args) {
        System.out.println(Main.classVar);
        Main m = new Main();
        System.out.println(Main.instanceCount);


        System.out.println(m.instanceVar);
        System.out.println(Main.classVar);

        Main m2 = new Main();
        System.out.println(Main.instanceCount);
        Main m3 = new Main();
        System.out.println(Main.instanceCount);

    }
}
