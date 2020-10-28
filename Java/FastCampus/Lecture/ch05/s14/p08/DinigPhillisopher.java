package com.company.s14.p08;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 식사하는 철학자들 문제!
 */

class Philosopher extends Thread{
    private final int id;
    private final Fork left;
    private final Fork right;

    public Philosopher(int id,Fork left, Fork right) {  // DI
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            try {
                left.acquire();
                System.out.println(id + ": left taken.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
//            right.acquire();
                // 1초동안기다리다가 오른쪽 포크를 못 집으면 왼쪽 포크를 내려놓는다.
                if (!right.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
                    left.release();
                    Thread.yield(); // 왼쪽 포크를 내려놓고 컨티뉴되서 다시 반복문이돌아서 다시 집어든다 좀더 양보를해서 다른 스레드가 획득할 기회를 더 준다.
                    continue;
                }
                System.out.println(id + ": right taken.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                System.out.println(id + " is eating.");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            left.release();
            right.release();
            Thread.yield(); // 먹은 다음에 양보함.
        }
    }
}
class Fork extends Semaphore{

    public Fork() {
        super(1);
    }
}
public class DinigPhillisopher {
    public static void main(String[] args) {
        Philosopher[] phils = new Philosopher[5];
        Fork[] forks = new Fork[5];

        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork();
        }

//        for (int i = 0; i < 5; i++) {
//            phils[i] = new Philosopher(i,forks[i],forks[(i+1) % 5]);
//        }

        for (int i = 0; i < 5-1; i++) {
            phils[i] = new Philosopher(i,forks[i],forks[(i+1) % 5]);
        }
        phils[4] = new Philosopher(4, forks[0],forks[4]); // 이건 오른쪽을 먼저 들기 때문에 다른사람들이 먹는 확률이 올라감 근데 먹는사람만 먹는 단점이 생김

        for (int i = 0; i < 5; i++) {
            phils[i].start();
        }
    }
}