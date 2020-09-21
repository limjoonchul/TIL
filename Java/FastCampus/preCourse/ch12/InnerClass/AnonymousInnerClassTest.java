package com.company.ch12;
class OuterClass{
    int outNum = 100;
    static int sNum = 200;

    Runnable getRunnable(int i){
        int num = 100;
        return new Runnable() {

            @Override
            public void run() {
                //num += 10; 안돼
                //i = 200; 안돼               // 로컬 내부클래스에서 메소드의 로컬 변수들을 가져올 때 로컬 변수들은 final처럼 된다.
                // 그래서 값을 변경할 수 없고 읽기만 된다.
                // 로컬 변수의 유효성이 더 짧기 때문에

                System.out.println(outNum);
                System.out.println(sNum);
            }
        }; //  이름이 없는 클래스
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("test");
        }
    };
    // 이렇게도 생성이 가능하다. 익명이너클래스는 추상클래스나 인터페이스에 대한 생성을 바로 할 수 있다.
    //원래는 추상클래스나 인터페이스를 생성할 때 상속하는 클래스를 만들고 그 클래스를 생성해서 사용했다.
    // 단 하나의 추상클래스나 인터페이스는 클래스 이름 없이 바로 new 키워드로 생성 가능.
    // 안드로이드의 위젯 핸들러들을 이런식으로 사용해서 만들고 있다 보니깐 알겠지??

}
public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        outer.runnable.run();
        Runnable runnable = outer.getRunnable(50);
        runnable.run();
    }
}
