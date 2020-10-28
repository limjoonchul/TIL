package com.company.s14.p01;

/**
 * Multi -Thread programming
 * 여러개의 스레드를 사용하는 프로그래밍 기법
 *
 * Thread : 최소의 프로세스 동작 단위  -> 하나의 프로세스는 여러개의 스레드를 가질 수 있다. 하나의 스레드가 하나의 프로세스에 속한다.
 * Process: OS로부터 메모리를 할당받아 동작하는 프로그램의 동작 단위, 프로그램이 실제로 메모리에 상주하면서 동작할 때 프로세스가 된다.
 *          프로그램이 클래스이고 프로세스가 객체로 생각할 수 있다.
 *          - 프로그램을 실행할 때, 멀티 프로세스로 동작하는 프로그램도 있다. 프로세스끼리 통신하면서 동작할 수도 있다. -> 리눅스에서 사용한다.
 *          프로세스끼리 메모리를 공유하는 영역은 없다. 스레드는 같은 프로세스내에 있기 때문에 같은 메모리영역을 공유한다.
 *
 * 장점
 * - 여러 동작을 병렬적으로 처리하여 CPU 사용률 향상 (CPU Utilization)
 *    - 인코딩, 렌더링, 배치작업(모아둔걸 한번에 처리하면 되는 것들 DB정리, 로그 처리, ),...등의 작업들 / 서비스가 커질 수록 최적화가 중요하다.
 * - 시간이 걸리는 동작을 분리하여 프로그램의 응답성 향상
 *    - GUI, 게임, 앱, 웹 API(다 프레임워크를 사용하기 때문에 내부적으로 관리해줌, 직접 사용할 일은 없음)
 * 단점
 * - 디버깅이 어렵다! 쓰레드가 동시에 동작하기 때문에, 디버거로 확인하기 어려움.
 *   디버거를 쓰거나, 디거빙을 하기 위한 코드를 추가하면 동작이 변한다. (나노세컨드로 작업을 하기때문에 동작의 순서가 바뀌면 크게 바뀔수 있다.)
 * - 구현이 어렵다 쓰레드간의 동기화를 하기 위한 구현이 어렵다. 쉽게 동기화하면 느려진다.
 *    - Context Switching 오버헤드가 있기 때문에 동기화를 잘 못하면 오히려 더 느려진다. (쓰레드가 동작할 수 있는것이 정해져있다.)
 *       - os에서 하드웨어의 cpu에 동작이 제한이 걸려있어서 동작하는 cpu를 바꿔가면서 동작하도록 하기 때문에 contextswtiching을 하면 오버헤드가 발생함.
 *
 */

// 방법 3. 쓰레드를 클래스로 만들어서 사용할 수 있다.
class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
        }
        System.out.println();
    }
}


public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 방법 1. 익명 내부 클래스
        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } // 일반스레드에 쓸때는 try/catch를 해줘야한다 throws를 해줄 수 있지만 트라이캐치를해줘야한다.
                System.out.println("Hello Thread");
            }
        }); // run이라는 단일 메소드를 동작시키는 객체

        // 쓰레드 객체는 1회용이며, start()로 실행한다.
//        p.run(); // 이렇게도 동작 가능. 이건 멀티스레드랑 상관없이 클래스의 run()를 그냥 콜한 것이라 상관없음.

//        p.run(); // 그냥 메소드콜이니깐 다시 동작해도 잘 됨
        // p.start(); // 스레드 객체는 1회용이므로, start()가 재실행될 수 없다.
        // 신입개발자들이 무한루프에서 스레드를 스타트하는 실수를 많이 한다.

        // 방법 2.람다식 구현
        Thread p2 = new Thread(()->{
            System.out.println("Thread by lambda");
//            while (true){
//
//            }
        });
        p2.start();
//        p2.join(); // 한스레드가 동작을 끝내면 p2동작이 끝나면 그때 조인을해서 p1이 실행이 된다. sleep 없이 join으로 맞출 수 있다. 무언갈 동작하다 막혀있는게 blocking 동작
//        p2.join(100);  // p2가 무한루프가 돌고 있을 대 안에 100을주면 100밀리세컨트 뒤에 다음 스레드가 동작될 수 있게 한다. 하지만 p2는 계속 돌고있음.
        // 100ms 기다렸다가 동작을 할수 있게 설정한다.

        // p2끝날 때까지 기다렸다가 시작이된다. 그전까지 대기상태로 들어가 있지 않은 상태다.
        p1.start(); // start를 해줘야 동작한다. 실제로 OS에 스레드 동작을 요청한다. main이 아닌 새로운 스레드를 동작한다.

//        p1.join();
        Thread.sleep(2000); // 100미리 세컨드동안 쉬고 동작을 한다. sleep을 이용해서 시간차를 줄 수있는 방법!
        // 사용률을 감소시킴, 그래서 느려진다. 메인에서 하면 메인쓰레드를 sleep시킨다.

        MyThread myThread = new MyThread();
        myThread.start();
//
//        // 4. 구현 후 즉시 실행
//        new Thread(()->{
//            System.out.println("IDEA");
//        }).start(); // 객체에 대한 참조를 가지고 있지 않기 때문에 이렇게 하면 JOIN등 활용이 어려움. 씽크가 상관이 없다면 이렇게도 사용가능.

        System.out.println("Main thread ended");
        // 멀티스레드를 동작시키면 자바는 일관성이있다 jvm에서 동작시킬때 어느정도 최적화되는게 일관성있게 되는게 있다라고 강사님의 생각!


    }
}
