package com.company.ch12;
class Outer{
    int outNum = 100;
    static int sNum = 200;

    Runnable getRunnable(int i){
        int num = 100;
        class MyRunnable implements Runnable{

            @Override
            public void run() {
                //num += 10; 안돼
                //i = 200; 안돼               // 로컬 내부클래스에서 메소드의 로컬 변수들을 가져올 때 로컬 변수들은 final처럼 된다.
                // 그래서 값을 변경할 수 없고 읽기만 된다.
                // 로컬 변수의 유효성이 더 짧기 때문에

                System.out.println(outNum);
                System.out.println(sNum);
            }
        }
        return new MyRunnable();
    }
}
public class LocalInnerClassTest {

    public static void main(String[] args) {
        Outer outer = new Outer();
        Runnable runnable = outer.getRunnable(50);
        runnable.run();
    }
}
