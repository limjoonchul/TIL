package com.company.s14.p06;

class WorkObject{
    // A가 먼저 실행된다고 할 때 wait()이되면 lock을 반환하고  B가 실행되고 notify를 날리고 동작한다음에 wait()이되고 lock을 반환하고 A가 실행되는 왔다갔다 이렇게 동작함!
    public synchronized void methodA(){
        System.out.println("methodA() called");
        // 처음 스레드가 실행되는건 notify가 효과가 없다.
        notify(); // 대기중인 다른 스레드를 하나 동작상태로 만든다. wait()중인 다른스레드가 들어와도 된다 하나한테만 알려줌.
//        notifyAll(); // 나머지 전체한테 알려줌
        // notify랑 wati의 순서가 중요하다.
        // 순서를 바꾸면 서로 한번실행하고 대기상태로 들어가서 notify를 기다리는 데드락 상태가 된다.
        try {
            wait(); // synchronized안에서만 호출이 가능하다. Lcok을 반환하고, 대기상태로 들어감
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    위에 꺼랑 같음
//    public  void methodASAME(){
//        synchronized (this){{
//            System.out.println("methodA() called");
//            notify(); // 대기중인 다른 스레드를 하나 동작상태로 만든다. 다른스레드가 들어와도 된다 하나한테만 알려줌.
//            notifyAll(); // 나머지 전체한테 알려줌
//            // notify랑 wati의 순서가 중요하다.
//            // 순서를 바꾸면 서로 한번실행하고 대기상태로 들어가서 notify를 기다리는 데드락 상태가 된다.
              // 데드락은 두 개의 스레드가 서로 대기상태에 일 때를 의미한다.
//            try {
//                wait(); // synchronized안에서만 호출이 가능하다. 대기상태로 들어감
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }}
//    }

    public synchronized void methodB(){
        System.out.println("methodB() called");
        notify();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread extends Thread{
    private final WorkObject workObject;
    private boolean isA;

    public MyThread(WorkObject workObject, boolean isA){ // Dependency Injection
        this.workObject = workObject;
        this.isA = isA;
    }

    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            if (isA){
                workObject.methodA();
            } else{
                workObject.methodB();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        WorkObject sharedObj = new WorkObject();
        Thread p1 = new MyThread(sharedObj, true);
        Thread p2 = new MyThread(sharedObj, false);

        p1.start();
        p2.start();
    }
}
