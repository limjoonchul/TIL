package com.company.s14.p02;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        Thread p1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.print("~");
                Thread.yield(); // 다른 스레드로 양보하고 바로 실행 대기 sleep(0)과 유사
                // 러닝상태에서 빠져나오고 바로 대기상태로 들어가기때문에 바로 동작할 수도 있다.다른 스레드가 동작을 하지 않게되면
                // 자주 변하게 되면 오버헤드가 증가하는 것이다.

//                try {
//                    Thread.sleep(1); // 오버헤드가 엄청 큼! Running 상태에서 Timed_Waiting 상태로 이동 그 다음에 실행가능상태로 넘어감
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });


        Thread p2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.print("*");
                Thread.yield();

//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });

        // priority 기능이 있으나, 보장되지 않는다.
        // 이유는 starying(서빙 받지 못하는) 하는 Thread가 없게 하기 위해서 os가 조절하기 때문.
        // 우선순위가 높은 것에만 집중하면 우선순위가 높은거에만 동작만하고 다른건 동작못하게 할 수 있기 때문에 동작을 하지 못하는 스레드를 동작시키게 해주게 os에서 제한을 둔다.
        System.out.println(p1.getPriority()); // 우선순위 - 값이 높을 수록 우선순위가 높다. default가 5 MIN =1 MAX = 10
        p1.setPriority(Thread.MAX_PRIORITY);
        p2.setPriority(Thread.MIN_PRIORITY);
        p1.start();
        p2.start();
        // 한스레드가 일정시간을 점유하고 다른 스레드가 점유하는 식으로 진행됨 왔다갔다한다. 예측하기 어려움.
    }
}
