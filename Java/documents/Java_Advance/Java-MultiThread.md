# Multi-Thread Programming
## Process and Thread
* Process: OS로부터 메모리를 할당받아 실행중인 프로그램
* Thread: 프로세스 동작의 최소 단위로, 하나의 프로세스는 여러 스레드로 이루어질 수 있다.
## 멀티스레드 프로그래밍의 장단점
* 장점 
  * 여러 동작을 병렬적으로 처리하여 CPU의 사용률 향상
  * 시간이 걸리는 동작을 분리하여 프로그램의 응답성 향상
* 단점
  * Context Switching 오버헤드 발생
  * 스레드 제어의 어려움
  
## 스레드 구현
* 스레드 생성
```groovy
Thread threadOne = new Thread(new Runnable() {
    public void run() {
        System.out.println("Hello Thread!");
    }
});

Thread threadTwo = new Thread(() -> {
    System.out.println("Hello Again, Thread!");
});

class MyThread extends Thread {
    public void run() {
        System.out.println("Hello Again Again, Thread!");
    }
}
Thread threadThree = new MyThread();
```
* 스레드 실행
```groovy
Thread threadOne = new Thread(() -> {
    for (int i = 0; i < 10; i++) {
        System.out.print("1");
    }
});

Thread threadTwo = new Thread(() -> {
    for (int i = 0; i < 10; i++) {
        System.out.print("2");
    }
});

threadOne.start();
threadTwo.start();
System.out.println("Done!");
```

## 스레드의 상태 및 제어
* 스레드의 상태 
  * getState() 메소드로 스레드의 상태를 확인할 수 있다.

| 열거형 상수 | 설명 |
| --------- | --- |
| NEW |	start() 메소드가 아직 호출되지 않음 |
| RUNNABLE | JVM에 의해 실행 가능한 상태 |
| BLOCKED |	객체가 블락된 상태 |
| WAITING |	sleep(), wait(), join() 등에 의해 무한히 대기 중인 상태 |
| TIMED_WAITING | sleep(), wait(), join() 등에 의해 정해진 시간 동안 대기 중인 상태 |
| TERMINATE | run() 메소드가 종료된 상태 |

* 스레드의 우선순위 제어

```groovy
public final static int MIN_PRIORITY = 1;
public final static int NORM_PRIORITY = 5;
public final static int MAX_PRIORITY = 10;
```

| 메소드 | 설명 |
| void setPriority(int newPriority) | 새로운 우선순위로 설정한다. |
| int getPriority() | 우선순위를 반환한다. | 

