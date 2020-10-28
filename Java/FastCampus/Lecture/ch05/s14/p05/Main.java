package com.company.s14.p05;


/**
 * Intrinsic lock (고유 락)과 synchronized 키워드
 * - 자바의 모든 객체(Object)는 고유 락을 가지고 있다.
 *    - 고유 락은 객체의 소유권을 한정하는 내부적인 구현 -> 소유권은 독점적이다. 한번에 하나밖에 가질 수 없다.
 * - synchronized를 이용하면 객체의 고유 락의 소유권을 가져올 수 있다.
 *    - 소유권이 이미 점유된 경우에는 blocking으로 동작 소유권을 가진애가 반환하면, 대기하던 스레드가 받아서 동작한다.
 */

// 1. 멀티스레드 동작에 취약한 구현
//class Counter{
//    private int count = 0;
//    public int increaseCount(){
//        return ++count; // 읽고, 수정하고, 쓰는 작업
//        // 동작들이 중복이 될 수 있다, 도중에 다른 스레드가 작업을 하게되면
//        // 경쟁적으로 동작하다보면, 읽고 수정하고 쓰기 전에 다른 쓰레드가 읽는 경우가 발생
//    }
//
//    public int getCount() {
//        return count;
//    }
//}


 //2. Object 객체의 Intrinsic Lock을 이용한 구현 - 굳이 이렇게 할 필요 없음.
class Counter{
    private Object lock = new Object();
    private int count = 0;
    public int increaseCount(){
        synchronized (lock){ // lock이라는 객체를 소유해야 내부 블록을 동작시킬 수 있다. lock은 한번에 한 스레드만 소유할 수 있다.
            return ++count; // 읽고, 수정하고, 쓰는 작업
        }
    }

    public int getCount() {
        return count;
    }
}

// 3.  this 객체의 Intrinsic Lock을 이용한 구현(모든 객체는 고유 락을 가지고 있기 때문에) 가장 좋은 구현 방법.
    // Intrinsic Lock의 범위가 넓어질 수록 성능이 점점 떨어진다.
//class Counter{
//    private int count = 0;
//    public int increaseCount(){
//        synchronized (this){
//            return ++count; // 읽고, 수정하고, 쓰는 작업
//        }
//    }
//
//    public int getCount() {
//        return count;
//    }
//}
// 4. 메소드에 synchronized 키워드를 사용
// synchronized 키워드가 사용된 메소드를 호출하기 위해서는
// 해당 객체를 소유해야만 호출이 가능, 소유하지 못하면 Blocking 동작을 하고 있으면, nonBlocking 멈춰 있으면 Blocking
//class Counter{
//    private int count = 0;
//    public synchronized int increaseCount(){
//        return ++count; // 읽고, 수정하고, 쓰는 작업
//    }
//
//    public int getCount() {
//        return count;
//    }
//}
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();

        // 고유락을 사용하는 것은   한번에 하나만 동작하게 하기위해서 제한을 해두는 것이다
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
//                synchronized(c){ // 이렇게 싱크를 하면, 병렬 동작이 전혀 이루어지지 않는다. 하나의 스레드가 100번을 수행하고 다음 스레드가 100번 수행하는 형태로 동작한다.
                    // 가장 안전하지만 가장 효율이 떨어지는 코드가 된다.
                    for (int j = 0; j < 100; j++) {
                        // c라는 shared object 공유객체 가 있을 때
                        // 멀티스레드로부터 안전한 영역을 생성하는 방법이다.
                   // synchronized (c) { // 5. c의 고유 락을 획득해야만 동작. {}영역안에서는 다른 스레드가 접근하지 못함
                        c.increaseCount();
//                    }
                    //}
                }

            }).start();
        }

        Thread.sleep(1000);
        System.out.println(c.getCount());
    }
}
