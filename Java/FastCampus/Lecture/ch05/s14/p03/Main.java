package com.company.s14.p03;

public class Main {
    public static void main(String[] args) {
        // p1은 1초를 쉬고 피원을 외침
        // try/catch를 쓰는 이유는 만약 10초동안 쉬고 있는데 다른 동작이 끝나고 쉬고있는 동작을 interrupte로 깨워줄 수 있다.
        Thread p1 = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            System.out.println("p1!!");
        });

        // p
        Thread p2 = new Thread(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("p2!!");
            p1.interrupt(); // 방해를 의미하긴 하는데 컴퓨터 사이언스적인 용어로 기존 동작을 방해하고 반응을 강제하는 메소드.
                            // 주로 임베디드에서 많이 사용. 별로 쓸일은 없다. 스레드 동작을 이해하는데 필요하다 잘 못 sleep된 동작을 깨워준다.
                            // 의도적으로 쓸일은 많지 않다.
        });

        p1.start();
        p2.start();


    }
}
