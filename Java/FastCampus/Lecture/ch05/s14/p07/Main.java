package com.company.s14.p07;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 세마포(Semaphore)
 * - 사전적 의미 횟대(깃발)
 * n개의 깃발을 놓고, 여러 스레드가 경쟁하도록 하는 sync 기법
 * n = 1 이면, BinarySemphore라고 하며, Lock(락은 하나만 존재하니깐)과 유사하게 동작
 *
 */

public class Main {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);

        sem.release(); // 2가된다. 스레드한테 권한을 준다는 개념보다는 세마포를 가지고 숫자 놀음을한다?
//        sem.release(11);
        // 자원관리를 할 수 있다 충분히 무언가 쌓였을 때 동작할 수 있게 구현할 수 있다.
        System.out.println(sem.availablePermits()); // 가능한 허용권을 체크할 수 있다


        try {
//            sem.acquire(12); release가 11개인데 12개를 가져오려고 하면 blocking이 걸림
            sem.acquire(); // 세마포를 획득하는 과정
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sem.acquireUninterruptibly(); // acquire와 비슷한데  인터럽트에 반응하지 않음
        // 세마포어를 얻을 때까지 계속 블록상태로 되어있다. 인터럽트에 반응하지 않음.
        // try/catch도 필요없다.
        System.out.println(sem.availablePermits()); // 가능한 허용권을 체크할 수 있다
        System.out.println(sem.tryAcquire()); // 시도를해서 성공하면 true를 반환함 blocking하지 않는다. 실패하면 false를 반환하고 넘어간다.
        // blocking하지 않는다는 건 기다리지 않고 다음걸 진행한다.

//        try {
//            System.out.println(sem.tryAcquire(2000, TimeUnit.MILLISECONDS));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // 일정 시간은 블락킹할 수 있다. 안에 시간을 넣을 수 있다.(타임아웃)

        System.out.println(sem.availablePermits()); // 가능한 허용권을 체크할 수 있다

        sem.release();
    }
}
