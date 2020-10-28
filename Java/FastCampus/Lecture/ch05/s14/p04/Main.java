package com.company.s14.p04;

/**
 * 데몬 스레드(daemon thread)
 *  - 다른 스레드가 모두 종료될 경우, 스스로 종료되는 스레드 <- 정의
 *  - 무한 루프로 대기하면서 동작하는 구현이 많음 <- 활용
 *     - 일정 시간마다 동작, interrupt등에 의해서 동작, 외부의 요청에 의해서 동작하는 것
 */
class AutoSaver extends Thread{
    public AutoSaver() {
        this.setDaemon(true); // true로하면 데몬스레드가 된다.
        // 메인 스레드에서 종료되면 스스로 종료되는 데몬 설정
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.out.println("interrupt!!");
            }
            // save something...
            System.out.println("Auto save done!");
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        new AutoSaver().start();
        Thread p1 = new AutoSaver();
        p1.start();
        for (int i = 0; i < 15; i++) {
            Thread.sleep(1000);
            System.out.println("working");
            p1.interrupt(); // 이렇게 해주면 AutoServer객체를 바로5초동안 기다리지 않고 바로 출력할 수 있다.

        }
        // 메인에서 1초간격으로 동작을 해서 15번이 실행이되면 autosaver는 같이 종료됨
    }
}
