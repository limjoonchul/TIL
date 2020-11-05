# Multi-Thread Programming
## Process and Thread
 * Thread : 최소의 프로세스 동작 단위  -> 하나의 프로세스는 여러개의 스레드를 가질 수 있다. 하나의 스레드가 하나의 프로세스에 속한다.
 * Process: OS로부터 메모리를 할당받아 동작하는 프로그램의 동작 단위, 프로그램이 실제로 메모리에 상주하면서 동작할 때 프로세스가 된다.
   프로그램이 클래스이고 프로세스가 객체로 생각할 수 있다.
   * 프로그램을 실행할 때, 멀티 프로세스로 동작하는 프로그램도 있다. 프로세스끼리 통신하면서 동작할 수도 있다. -> 리눅스에서 사용한다.
   * 프로세스끼리 메모리를 공유하는 영역은 없다. 스레드는 같은 프로세스내에있기 때문에 같은 메모리영역을 공유한다.
### 멀티태스킹(Multi tasking)
* 두 가지 이상의 작업을 동시에 처리하는 것.
* 멀티 프로세스 : 독립적으로 프로그램들을 실행하고 여러 가지 작업 처리
* 멀티 스레드 : 한 개의 프로그램을 실행하고 내부적으로 여러 가지 작업 처리
   * 채팅을 하는 어플리케이션 채팅을하는 작업과 파일을 전송하는 작업 등 여러 작업을 동시에 처리할 수 있다.
* 메인메소드를 실행해서 예제를 만들었는데 jvm이 하나의 프로세스를 만들어서 메인스레드가 메인메소를 실행하는 방식으로
싱글 스레드를 사용해 왔다.  

### 메인 스레드
* 모든 자바 프로그램은 메인 스레드가 main() 메소드를 실행하면서 시작 된다.
* main() 메소드의 첫 코드부터 아래로 순차적으로 실행한다.
* main() 메소드의 마지막 코드를 실행하거나, return 문을 만나면 실행이 종료된다.
* 스레드는 하나의 코드의 실행 흐름이다 라고 생각하면 된다.
* main 스레드는 작업 스레들을 만들어서 병렬로 코드를 실행할 수 있다.
   * 즉 멀티 스레드를 생성해서 멀티 태스킹을 수행한다.
* 프로세스의 종료
  * 싱글 스레드 : 메인 스레드가 종료하면 프로세스도 종료된다.
  * 멀티 스레드 : 실행 중인 스레드가 하나라도 있다면, 프로세스는 종료되지 않는다.
     * 메인 스레드가 작업 스레드보다 먼저 종료되더라도 작업 스레드가 계속 실행 중이라면
    프로세스는 종료되지 않는다.
    
## 멀티스레드 프로그래밍의 장단점
### 장점 
* 여러 동작을 병렬적으로 처리하여 CPU 사용률 향상 (CPU Utilization)
   * 인코딩, 렌더링, 배치작업(모아둔걸 한번에 처리하면 되는 것들 DB정리, 로그 처리) 등의 작업들 / 서비스가 커질 수록 최적화가 중요하다.
* 시간이 걸리는 동작을 분리하여 프로그램의 응답성 향상
   * GUI, 게임, 앱, 웹 API(다 프레임워크를 사용하기 때문에 내부적으로 관리해줌, 직접 사용할 일은 없음)
### 단점
* 디버깅이 어렵다! 쓰레드가 동시에 동작하기 때문에, 디버거로 확인하기 어려움.
   * 디버거를 쓰거나, 디거빙을 하기 위한 코드를 추가하면 동작이 변한다. (나노 세컨드로 작업을 하기때문에 동작의 순서가 바뀌면 크게 바뀔수 있다.)
* 구현이 어렵다 쓰레드간의 동기화를 하기 위한 구현이 어렵다. 쉽게 동기화하면 느려진다.
   * Context Switching 오버헤드가 있기 때문에 동기화를 잘 못하면 오히려 더 느려진다. (쓰레드가 동작할 수 있는 것이 정해져 있다.)
   * OS에서 하드웨어의 CPU에 동작이 제한이 걸려있어서 동작하는 CPU를 바꿔가면서 동작하도록 하기 때문에 Context Switching을 하면 오버헤드가 발생한다.
## 스레드 구현
* 스레드 생성
  ```groovy
   // 방법 1. 익명 내부 클래스를 이용한 생성
   Thread threadOne = new Thread(new Runnable() {
       public void run() {
           System.out.println("Hello Thread!");
       }
   });
   
   // 방법 2. 람다식을 이용한 생성
   Thread threadTwo = new Thread(() -> {
       System.out.println("Hello Again, Thread!");
   });
   
   // 방법 3. 스레드를 클래스로 만들어서 생성
   class MyThread extends Thread {
       @Override
       public void run() {
           System.out.println("Hello Again Again, Thread!");
       }
   }
   Thread threadThree = new MyThread();
   
   // 방법 4. 구현 후 즉시 실행
   new Thread(()->{
       System.out.println("IDEA");
   }).start(); // 객체에 대한 참조를 가지고 있지 않기 때문에 이렇게 하면 JOIN등 활용이 어려움. 씽크가 상관이 없다면 이렇게도 사용가능.
  ```
* 스레드 실행
  * 쓰레드 객체는 1회용이며, start()로 실행한다.
  * 신입 개발자들이 무한 루프에서 스레드를 스타트하는 실수를 많이 한다.
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
   
   threadOne.run(); // 이렇게도 동작 가능. 이건 멀티스레드랑 상관없이 클래스의 run()를 그냥 콜한 것이라 상관없음.
   threadOne.start(); // 가능.
   threadOne.run(); // 그냥 메소드콜이니깐 다시 동작해도 잘 된다.
   threadOne.start(); // 스레드 객체는 1회용이므로, start()가 재실행될 수 없다.
   // 신입개발자들이 무한루프에서 스레드를 스타트하는 실수를 많이 한다.
   
   threadTwo.start();
   System.out.println("Done!");
   ```

## 스레드의 상태 및 제어
* 스레드의 상태 
  * getState() 메소드로 스레드의 상태를 확인할 수 있다.

  | 상태 | 열거 상수 | 설명 |
  | --- | -------- | --- |
  | 객체 생성 | NEW | 스레드 객체가 생성, 아직 start() 메소드가 호출되지 않은 상태 |
  | 실행 대기 | RUNNABLE | JVM에 의해 실행 가능한 상태, 실행 상태로 언제든지 갈 수 있는 상태 |
  | 일시 정지 | BLOCKED | 사용하고자 하는 객체의 락이 풀릴 때까지 기다리는 상태 |
  |  | WAITING | sleep(), wait(), join() 등에 의해 대기중인 상태로, 다른 스레드가 통지할 때까지 기다리는 상태 |
  |  | TIMED_WAITING | sleep(), wait(), join() 등에 의해 정해진 시간 동안 대기 중인 상태  |
  | 종료 | TERMINATED | 실행을 마친 상태,run() 메소드가 종료된 상태  |

### 스레드 우선 순위 
* 동시성(Concurrency)
  * 멀티 작업을 위해 하나의 코어에서 멀티 스레드가 번갈아 가며 실행하는 성질
* 병렬성(Parallelism)
  * 멀티 작업을 위해 멀티 코어에서 개별 스레드를 동시에 실행하는 성질
  * 처리속도가 빠름.
* 우선 순위 효과
   * 싱글 코어 경우
      * 우선 순위가 높은 스레드가 실행 기회를 더 많이 가지기 때문에
      * 우선 순위가 낮은 스레드보다 계산 작업을 빨리 끝낸다.
   * 멀티 코어 경우
      * 쿼드 코어 경우에는 4개의 스레드가 병렬성으로 실행 될 수 있기 때문에
      * 4개 이하의 스레드를 실행할 경우에는 우선 순위 방식은 크게 영향을 미치지 못한다.
      * 최소 5개 이상 되어야 영향을 받는다.
      
* 예제 해보니깐 별다른 영향이 없는것 같아... 우선순위를 높게 줘도 늦게 끝나는데?  
  
* 스레드의 우선순위 제어
   * priority 기능이 있으나, 보장되지 않는다.
   * 이유는 starying(서빙 받지 못하는) 하는 Thread가 없게 하기 위해서 OS가 조절하기 때문.
   * 우선순위가 높은 것에만 집중하면 우선순위가 높은거에만 동작만하고 다른건 동작 못하게 할 수 있기 때문에 동작을 하지 못하는 스레드를 동작시키게 해주게 OS에서 제한을 둔다.
  ```groovy
  public class Main {
      public static void main(String[] args) {
          Thread p1 = new Thread(() -> {
              for (int i = 0; i < 100; i++) {
                  System.out.print("~");
              }
          });
  
          Thread p2 = new Thread(() -> {
              for (int i = 0; i < 100; i++) {
                  System.out.print("*");
              }
          });
  
          // 우선순위 - 값이 높을 수록 우선순위가 높다.   
          System.out.println(p1.getPriority()); // default 5
          p1.setPriority(Thread.MAX_PRIORITY); // MAX = 10
          p2.setPriority(Thread.MIN_PRIORITY); // MIN =1
          p1.start();
          p2.start();
          // 한스레드가 일정시간을 점유하고 다른 스레드가 점유하는 식으로 진행된다, 왔다 갔다한다. 예측하기 어려움.
      }
  }

  ```
  
  | 메소드 | 설명 |
  |------|------|
  | void setPriority(int newPriority) | 새로운 우선순위로 설정한다. |
  | int getPriority() | 우선순위를 반환한다. |
  

* `sleep()`을 이용한 제어
   * timedWating이 되서 주어진 시간동안 일시정지가 된다.
  ```java
  public class Main {
      public static void main(String[] args) throws InterruptedException {
          Thread p1 = new Thread(new Runnable() {
              @Override
              public void run() {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 일반스레드에서 sleep을 사용할 때는 try/catch를 해줘야한다. 
                    // throws를 해줄 수 있지만 나중에 Interrupt 때문에 try/catch를 해주는게 좋다.
                  System.out.println("Hello Thread");
              }
          }); // run이라는 단일 메소드를 동작시키는 객체
  
  
          Thread p2 = new Thread(()->{
              System.out.println("Thread by lambda");
          });
  
          p2.start();
  
          Thread.sleep(100); // 100미리 세컨드 동안 쉬고 동작을 한다. sleep을 이용해서 시간차를 줄 수있는 방법!
          // 사용률을 감소시킴, 그래서 느려진다. 메인에서 하면 메인 쓰레드를 sleep시킨다.
  
          System.out.println("Main thread ended");
          
      }
  }
  ``` 

* `join()`을 이용한 스레드 조인
   * 스레드 동작을 동기화하기 위해서 사용한다.
   * 한 스레드의 동작이 끝난 다음에 다른 스레드를 동작시키는 메소드이다.
   * 일시정지가 되는데 join()을 호출한 스레드가 종료가 되면 다시 실행대기상태로와서 실행할 수 있게 된다.
   * 계산 작업을 하는 스레드가 모든 계산 작업을 마쳤을 때, 계산 결과값을 받아 이용하는 경우에 주로 사용.
   ```java
    public class Main {
        public static void main(String[] args) throws InterruptedException {
            // 방법 1. 익명 내부 클래스
            Thread p1 = new Thread(new Runnable() {
                @Override
                public void run() {
                   try {
                        Thread.sleep(50);
                   } catch (InterruptedException e) {
                        e.printStackTrace();
                   } // 일반스레드에 쓸때는 try/catch를 해줘야한다 throws를 해줄 수 있지만 트라이캐치를해줘야한다.
                    System.out.println("Hello Thread");
                }
            }); // run이라는 단일 메소드를 동작시키는 객체
            
            Thread p2 = new Thread(()->{
                System.out.println("Thread by lambda");
                while (true){
    
                }
            });
            p2.start();
            p2.join(); // 한스레드가 동작을 끝내면 p2동작이 끝나면 그때 조인을해서 p1이 실행이 된다. sleep 없이 join으로 맞출 수 있다. 무언갈 동작하다 막혀있는게 blocking 동작
            p2.join(100);  // p2가 무한루프가 돌고 있을 대 안에 100을주면 100밀리세컨트 뒤에 다음 스레드가 동작될 수 있게 한다. 하지만 p2는 계속 돌고있음.
            // 100ms 기다렸다가 동작을 할수 있게 설정한다.
    
            // p2끝날 때까지 기다렸다가 시작이 된다. 그전까지 대기상태로 들어가 있지 않은 상태다.
            p1.start(); // start를 해줘야 동작한다. 실제로 OS에 스레드 동작을 요청한다. main이 아닌 새로운 스레드를 동작한다.
    
            p1.join();
            Thread.sleep(100); // 100미리 세컨드동안 쉬고 동작을 한다. sleep을 이용해서 시간차를 줄 수있는 방법!
            // 사용률을 감소시킴, 그래서 느려진다. 메인에서 하면 메인쓰레드를 sleep시킨다.
    
            System.out.println("Main thread ended");
        }
    }
   ```
* `interrupt()` 을 이용항 대기 중지
    * 방해를 의미하긴 하는데 컴퓨터 사이언스적인 용어로 기존 동작을 방해하고 반응을 강제하는 메소드.
    * 주로 임베디드에서 많이 사용. 별로 쓸일은 없다. 스레드 동작을 이해하는데 필요하다 잘 못 스립된 동작을 깨워준다.
    * 의도적으로 쓸 일은 많지 않다.
    
    ```java
    public class Main {
        public static void main(String[] args) {           
            // try/catch를 쓰는 이유는 만약 10초동안 쉬고 있는데 다른 동작이 끝나고 쉬고있는 동작을 interrupte로 깨워줄 수 있다.
            Thread p1 = new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
                System.out.println("p1!!");
            });
    
            Thread p2 = new Thread(()->{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("p2!!");
                p1.interrupt(); 
            });
    
            p1.start();
            p2.start();
              
        }
    }
    ```
* `yield()`를 이용한 상태 제어
   * 다른 스레드로 양보하고 바로 실행 대기 sleep(0)과 유사
   * 러닝상태에서 빠져나오고 바로 대기 상태로 들어가기 때문에 다른 스레드가 동작을 하지 않게 되면 바로 동작할 수도 있다.
   * 자주 변하게 되면 오버헤드가 증가하는 것이다.
   * 실행중일 때 yield()메소드를 호출하면 즉시 실행 대기 상태가 되고 동일하거나 높은 우선순위를 갖는 다른 스레드가 실행할 수 있게 만들어준다.
   
* `sleep()`을 이용하면 오버헤드가 엄청 크다. Running 상태에서 Timed_Waiting 상태로 이동 그 다음에 실행 가능 상태로 넘어간다.
   ```java
   
   public class Main {
       public static void main(String[] args) {
           Thread p1 = new Thread(() -> {
               for (int i = 0; i < 100; i++) {
                   System.out.print("~");
                   Thread.yield(); 
   
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
           
           System.out.println(p1.getPriority()); 
           p1.setPriority(Thread.MAX_PRIORITY);
           p2.setPriority(Thread.MIN_PRIORITY);
           p1.start();
           p2.start();
       }
   }
   ```
### 스레드의 안전한 종료 - stop 플래그, interrupt()
* 겨웅에 따라서는 실행중인 스레드를 즉시 종료할 필요가 있다.
* stop()메소드
   * 즉시 종료시키지만 갑자기 종료하게 되면 사용중이던 자원들이 불안전하게 상태가 남겨진다 (deprecated)

#### 안전한 종료방법
* 방법 1. stop 플래그를 이용하는 방법
   * stop플래그로 run()메소드의 정상 종료를 유도한다.
* 방법 2. Interrupt() 메소드를 이용하는 방법
   * 일시 정지 상태일 경우 InterruptedException을 발생시킨다.
   * 실행대기 또는 실행상태에서는 InterruptedException이 발생하지 않는다.
   * 일시 정지 상태로 만들지 않고 while문을 빠져나오는 방법
boolean status = Thread.interrupted(); 사용됬다면 true, 아니면 false
boolean status - objThread.isInterrupted();  

## 데몬 스레드(daemon thread)
* 다른 스레드가 모두 종료될 경우, 스스로 종료되는 스레드 <- 정의
* 무한 루프로 대기하면서 동작하는 구현이 많음 <- 활용
  * 일정 시간마다 동작, interrupt등에 의해서 동작, 외부의 요청에 의해서 동작하는 것
* setDaemon(true)로하면 데몬스레드가 된다.
* 메인 스레드에서 종료되면 스스로 종료되는 데몬 설정
   ```java
   class AutoSaver extends Thread{
       public AutoSaver() {
           this.setDaemon(true); 
       }
   
       @Override
       public void run() {
           while (true){
               try {
                   Thread.sleep(5000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               // save something...
               System.out.println("Auto save done!");
           }
       }
   }
   
   public class Main {
       public static void main(String[] args) throws InterruptedException {
           new AutoSaver().start();
           for (int i = 0; i < 15; i++) {
               Thread.sleep(1000);
               System.out.println("working");
           }
           // 메인에서 1초간격으로 동작을 해서 15번이 실행이되면 autosaver는 같이 종료됨
       }
   }
   ```
## 데이터 공유와 동기화
* 스레드간 데이터 공유 시 신뢰성에 문제가 발생할 수 있다.
### Intrinsic lock (고유 락)과 synchronized 키워드
* 자바의 모든 객체(Object)는 고유 락을 가지고 있다.
   * 고유 락은 객체의 소유권을 한정하는 내부적인 구현 -> 소유권은 독점적이다. 한번에 하나밖에 가질 수 없다.
* synchronized를 이용하면 객체의 고유 락의 소유권을 가져올 수 있다.
   * 소유권이 이미 점유된 경우에는 Blocking으로 동작 소유권을 가진 객체가 반환하면, 대기하던 스레드가 받아서 동작한다.
 
   ```java
   // 1. 멀티스레드 동작에 취약한 구현
   class Counter{
       private int count = 0;
       public int increaseCount(){
           return ++count; // 읽고, 수정하고, 쓰는 작업
           // 동작들이 중복이 될 수 있다, 도중에 다른 스레드가 작업을 하게되면
           // 경쟁적으로 동작하다보면, 읽고 수정하고 쓰기 전에 다른 쓰레드가 읽는 경우가 발생
       }
   
       public int getCount() {
           return count;
       }
   }
   
   // 2. Object 객체의 Intrinsic Lock을 이용한 구현 - 굳이 이렇게 할 필요 없음.
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
   
   // 3. this 객체의 Intrinsic Lock을 이용한 구현(모든 객체는 고유 락을 가지고 있기 때문에) 가장 좋은 구현 방법.
   // Intrinsic Lock의 범위가 넓어질 수록 성능이 점점 떨어진다.
   class Counter{
       private int count = 0;
       public int increaseCount(){
           synchronized (this){
               return ++count; // 읽고, 수정하고, 쓰는 작업
           }
       }
   
       public int getCount() {
           return count;
       }
   }
   
   // 4. 메소드에 synchronized 키워드를 사용
   // synchronized 키워드가 사용된 메소드를 호출하기 위해서는
   // 해당 객체를 소유해야만 호출이 가능, 소유하지 못하면 Blocking 동작을 하고 있으면, nonBlocking 멈춰 있으면 Blocking
   class Counter{
       private int count = 0;
       public synchronized int increaseCount(){
           return ++count; // 읽고, 수정하고, 쓰는 작업
       }
   
       public int getCount() {
           return count;
       }
   }
   public class Main {
       public static void main(String[] args) throws InterruptedException {
           Counter c = new Counter();
   
           // 고유락을 사용하는 것은 한번에 하나만 동작하게 하기위해서 제한을 해두는 것이다
           for (int i = 0; i < 100; i++) {
               new Thread(()->{
   //                synchronized(c){ // 이렇게 싱크를 하면, 병렬 동작이 전혀 이루어지지 않는다. 하나의 스레드가 100번을 수행하고 다음 스레드가 100번 수행하는 형태로 동작한다.
                       // 가장 안전하지만 가장 효율이 떨어지는 코드가 된다.
                       for (int j = 0; j < 100; j++) {
                           // c라는 shared object 공유객체 가 있을 때
                           // 멀티스레드로부터 안전한 영역을 생성하는 방법이다.
                       synchronized (c) { // 5. c의 고유 락을 획득해야만 동작. {}영역안에서는 다른 스레드가 접근하지 못함
                           c.increaseCount();
   //                    }
                       }
                   }
   
               }).start();
           }
   
           Thread.sleep(1000);
           System.out.println(c.getCount());
       }
   }
   ```
### 스레드 간의 협업
* `wait()`,`notify()`, `notifyAll()` - 스레드에 있는 메소드가 아닌
`동기화 메소드 또는 블록에서만` 호출 가능한  Object의 메소드들이다.
다른스레드가 notify(), notifyAll()을 실행해줘야 일시정지에서 풀려서 실행대기로 간다.
* `wait(long timeout), wait(long timeout, int nanos)` - timeout이 있는건 시간이 지나면 자동으로 실행대기상태로 갈 수 있다.
이시간이 다되기전에 notify를 호출하게 되면 역시 실행대기 상태로 간다.
* sleep은 시간이 다되어야 실행대기 상태로 가는데 이건 notify가 호출되면 바로 실행대기상태로 간다.

* 두개의 스레드가 교대로 번걸아 가며 공유객체를 실행해야 할 경우에 주로 사용.

* 완벽히 번갈아 가면서 동작하게 하는 건 아니지만 최대한 유사하게 동작하게 하는방법이다.
* `notify()` - 대기중인 다른 스레드를 하나 동작상태로 만든다. wait()중인 다른스레드가 들어와도 된다. 하나한테만 알려준다.
* `notifyAll()` - 나머지 전체한테 알려준다.
* `wait()` - 호출한 스레드가 일시 정지가 된다. waiting pool에 스레드가 관리가 된다. 
   스스로는 실행대기상태로 못간다. Lcok을 반환하고, 대기상태로 들어간다.
   * 다른스레드가 notify(), notifyAll()을 실행해줘야 일시정지에서 풀려서 실행대기로 간다. 
* `notify()` 와 `wait()`의 순서가 중요하다 순서를 바꾸게 되면 한 번 실행하고 대기 상태로 들어가기 때문에 `notify()`를 기다리는 데드락 상태가 된다.
   ```java
   class WorkObject{
       // A가 먼저 실행된다고 할 때 wait()이되면 lock을 반환하고  B가 실행되고 notify를 날리고 동작한다음에 wait()이되고 lock을 반환하고 A가 실행되는 왔다갔다 이렇게 동작함!
       public synchronized void methodA(){
           System.out.println("methodA() called");
           // 처음 스레드가 실행되는건 notify가 효과가 없다.
           notify(); 
   
           try {
               wait(); 
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
   
   //    위에 것과 같음
   //    public  void methodASAME(){
   //        synchronized (this){{
   //            System.out.println("methodA() called");
   //            notify(); 
   //            try {
   //                wait(); 
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
   ```

### 공유 객체를 사용할 때의 주의할 점
* 멀티 스레드가 하나의 객체를 공유하므로해서 생기는 오류
calc객체의 memory 필드가 있다. user1 스레드와 user2 스레드가 calc이라는 객체를 공유할 때 
user1는 meomory= 100을 실행하고 2초간 일시정지하고 출력하고 
user2는 meomory= 50을 실행하고 2초간 일시정지하고 출력한다.
했을 때 user1은 100을 출력하길 바라지만 일시정지해있는동안 user2의 50값이 들어가서
50이 출력하는 등의 오류가 생긴다.
이런 문제를 해결하기 위한 방법으로
### 동기화 메소드 및 동기화 블록 - synchronized
* 단 하나의 스레드만 실행할 수 있는 메소드 또는 블록을 말함.
* 다른 스레드는 메소드나 블록이 실행이 끝날 때까지 대기해야 한다.
임계영역 - 단하나의 스레드만 실행가능.
calc객체의 동기화 메소드가 5개가 있다고 가정했을 때, user1 스레드가 동기화 메소드를 호출해서
실행중이라고하면 객체가 잠금이 되서 user2스레드는 나머지 4개의 동기화 메소드도 실행이 불가능하다.
일반 메소드만 실행가능하고 대기상태로 있음.

## 세마포(Semaphore)
* 사전적 의미 횟대(깃발)
* n개의 깃발을 놓고, 여러 스레드가 경쟁하도록 하는 sync 기법
* n = 1 이면, BinarySemphore라고 하며, Lock(락은 하나만 존재하니깐)과 유사하게 동작
* 자원관리를 할 수 있다. 충분히 무언가 쌓였을 때 동작할 수 있게 구현할 수 있다.
* `acquire()` - 세마포의 허용권을 가져오는 메소드
* `release()` - 세마포의 허용권을 증가시키는 메소드, ()안에 숫자를 써주면 그 숫자만큼 증가한다.
* `availablePermits()` - 허용권이 몇개 있는지 체크할 때 사용하는 메소드
* `tryAcquire()` - 시도를 해서 성공하면 true, 실패하면 false를 반환하고 Blocking하지 않고 바로 다음코드를 진행한다.
   * 일정시간 동안 Blocking을 할 수 있게 ()안에 타임 아웃을 지정할 수 있다. (ex-`sem.tryAcquire(2000, TimeUnit.MILLISECONDS)`)
  ```java
  public class Main {
      public static void main(String[] args) {
          Semaphore sem = new Semaphore(1); // 세마포 개수를 설정한다.
  
          sem.release(); 
          
          System.out.println(sem.availablePermits());
  //        sem.release(11);
  //        try {
  ////            sem.acquire(12); release가 11개인데 12개를 가져오려고 하면 blocking이 걸림
  //            sem.acquire(); // 세마포를 획득하는 과정
  //        } catch (InterruptedException e) {
  //            e.printStackTrace();
  //        }
  
  //        sem.acquireUninterruptibly(); // acquire와 비슷한데  인터럽트에 반응하지 않음
  
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
  ```

### 세마포를 이용한 식사하는 철학자들 문제
  ```java
  package com.company.s14.p08;
  
  import java.util.concurrent.Semaphore;
  import java.util.concurrent.TimeUnit;
  
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
  ```

### JCF와 멀티스레드
  ```java
  package com.company.s14.p09;
  
  import java.util.ArrayList;
  import java.util.Collections;
  import java.util.List;
  import java.util.Vector;
  import java.util.stream.Collectors;
  
  public class Main {
      public static void main(String[] args) throws InterruptedException {
          List<Integer> list1 = new Vector<>();
          List<Integer> list2 = new ArrayList<>();
          List<Integer> list3 = Collections.synchronizedList(list2);
          // 어레이리스트인데 싱크된다. 이게 훨씬 빠르다.
          // list3 : list2의 싱크된 버전
  
          for (int i = 0; i < 100; i++) {
              new Thread(() -> {
                  for (int j = 0; j < 100; j++) {
                      list1.add(1);
                  }
              }).start();
          }
  
          for (int i = 0; i < 100; i++) {
              new Thread(() -> {
                  for (int j = 0; j < 100; j++) {
  //                    synchronized (list2) { // 이렇게 해줬을 때 위에 보다 느림??
                          list3.add(1);
  //                    }
                  }
              }).start();
          }
  
  
          Thread.sleep(1000); 
          // 멀티스레드여서 이렇게 기다려야 더 정확해진다? 뭐지 생각해봐
          // 3개의 스레드가 동작한다 메인, 스레드 1, 스레드2 메인스레드에서 동작을 하면서 스레드1,2가 동작을 하는데 이 스레드들이 동작이 다끝나지 않은 상태에서
          // 메인스레드가 종료될 수 있으니깐 메인스레드에 sleep을 줘서 시간차를 준다음에 사이즈를 출력하게 하기 위해서이다.
  
          System.out.println(list1.size());
          System.out.println(list2.size());
  
      }
  }
  ```
## 스레드 풀(Thread pool)
* 일반 스레드를 직접 만들어 사용할 경우, 작업을 마친 스레드는 종료된다.(1회용 이니깐)
   * 멀티 스레들 작업을 계속 할 경우, 스레드를 생성 / 삭제하는 오버헤드가 있다.(속도가 떨어지고, 관리가 어렵다.)
* 스레드 풀
   * 미리 생성해 둔 스레드의 집합을 스레드 풀 이라고 한다.( 뭔가를 사용하기 위해 모아둔 것을 풀이라고 함)
   * 미리 스레드를 생성해 두고, 작업만 스레드를 할당하여 동작한다.
   * 배치 작업(모아두고 한번에 처리하는 작업)에 많이 사용.
* 핵심! 미리 만들어 놓고 상황에 맞춰 동작하게 한다.

### 스레드 풀 생성 방법
* Executors 
   * 실행하는 애들을 만들어 놓는 클래스
   * ExecutorService 객체를 생성하며, 다음 메소드를 제공하여 쓰레드 풀을 개수 및 종류를 정할 수 있습니다.
#### 1. newCachedThreadPool()
* 필요할 때, 필요한 만큼 스레드 풀을 생성한다.
* 초기 스레드가 0개 하나도 안돌아가고 있어서 오버헤드도 발생하지 않고 어떻게 동작할지 모르는 상태에서 무난하게 사용 가능
* 코어 스레드가 0개 - 일하지 않아도 살려두는 스레드
* 요청 작업보다 스레드가 부족하면 새 스레드를 생성한다.(작업에 맞춰 스레드를 생성)
* 60초 동안  일하지 않은 스레드를 제거한다.
* 안정적으로 사용할 때 사용!
```groovy
ExecutorService pool = Executors.newCachedThreadPool();
```

#### 2. newFixedThreadPool();
* 인자 개수만큼의 고정된 스레드풀을 생성한다.
* 최대 스레드 nThreads개
* 코어 스레드 nThreads개 입력  값 개수
* 요청 작업보다 스레드가 부족하면 새 스레드를 생성
* 작업하지 않는 스레드도 제거하지 않고 동작한다.
* 한번 증가하면 죽이지 않는다. 풀파워로 쓸 때 사용
```groovy
ExecutorService pool = Executors.newFixedThreadPool(10); // 입력받음
```

#### 3. new ThreadPoolExecutor()
* corePoolsize, maximumPoolsize, keepAliveTime, TimeUnit, BlockingQueue를 인자로 받을 수 있다.
   * corePoolsize : 코어 스레드의 개수
   * maximumPoolsize : 최대 스레드 개수
   * keepAliveTime : 스레드가 일하지 않을 때 제거하기 위한 대기 시간
   * TimeUnit : 시간 단위를 지정
   * BlockingQueue : 작업 요청이 오면 쌓아뒀다가 스레드 풀에 하나씩 할당해서 동작한다.
      * 이게 없으면 작업 요청하면 실제 스레드로 들어가서 동작하기 전까지 메인 스레드가 멈춰 있어야 한다.
       그래서 큐에 던져놓고 메인스레드는 동작하게 하는 것이다.(존재한다.)
       
```groovy
ExecutorService es = new ThreadPoolExecutor(
      10, // 코어 스레드 개수
      100, // 최대 스레드 개수
      120, // 스레드가 이 시간 동안 일하지 않으면 제거 (대기 시간)
      TimeUnit.SECONDS, // 시간 단위를 지정
      new SynchronousQueue<Runnable>() // 작업을 요청하면 -> 작업을 쌓아둘 큐 -> 스레드 풀로 할당해서 넘어가서 동작한다.
      // 이게 없으면 작업 요청하면 실제 스레드로 들어가서 동작하기 전까지 메인 스레드가 멈춰있어야 한다. 그래서 큐에 던져 놓고
      // 메인스레드는 동작하게 하는 것이다.(존재한다.)
);
```

### 스레드 풀 동작 순서
#### 1. 스레드 풀 생성
```groovy
ExecutorService pool1 = Executors.newCachedThreadPool();
```

#### 2. 스레드에 할당할 작업 생성
````groovy
class Work implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }
}

class CallableWork implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "작업 종료";
    }
}
````
#### 3. 스레드에 작업 요청
* sumbit() - 스레드를 실행는 메소드
```groovy
Future<String> future = null; 
future = pool1.submit(new CallableWork());

for (int i = 0; i < 100; i++) {
    pool1.submit(new Work()); // 리턴이 있음
    
}
```
#### 4. 스레드 종료(동기화)
* shutdown() - Thread.join()과 비슷하게 작업이 끝나기를 기다려서 종료
   * 데몬 스레드가 아닌 이상 (일반 스레드는 )스레드 풀은 자동 종료가 안되기 때문에,직접 스레드 풀을 종료해 주어야 한다.
* shutdownNow()는 바로 종료시키는 것
* cancle() -  스레드를 종료시킬 때 사용하는 또 다른 메소드로 여기서는 실행중인 Callable 객체를 강제 종료할 수 있다. (mayInterruptIfRunning)안에 true, false 매개값을 줄 수 있다.
   * true - 해당 스레드에 인터럽트를 보낸다, true를 주면 추가 기능이 생긴다.(false의 기능에서 더 추가된다.) 작업하는 스레드에 인터럽트를 걸어준다.
   * false - false를 주고 cancle()을 호출하면 스레드는 아무 동작이 일어나지 않고.(종료하지 않음), get을 할 수 없게 된다.(CanclelationException 발생)
* get() - Blocking Method로 CallableWork()의 객체의 작업이 다 끝나고 future로 값이 들어올 때까지 기다리다가 값이 들어오면 작업을 수행한다.
   * 기다리기는 상황이 있기 때문에 Interrupt가 들어올 수 있다. 들어오면 InterruptException을 수행한다.
   * Blocking Method이니깐 기다리는 상황일 때 계속 기다리기만 하는 상황이 생길 수 있어서 매개값으로 timeout을 설정할 수 있다.
      * Blocking - 다른 작업이 기다릴 때가지 기다리는 것.
* isCancelled() - cancle() 됬는지 확인할 때 사용하는 메소드
* isDonle() - 작업이 잘 끝났는지 확인하는 메소드이다.
* Callable가 어떻게 작업하는지 모르기때문에 외부에서 컨트롤할 수 있게 하기위해 위의 메소드들을 제공한다.
```groovy
pool1.shutdown(); // Thread.join()과 비슷하게 작업이 끝나기를 기다려서 종료

Thread.sleep(1000);
// 다른 스레드와 동기화를 맞추기 위해(여기서는 pool1이 작업을 진행중) 잠시 기다렸다가 밑에 코드가 진행되니깐 작업완료가 마지막에 출력됨!

future.cancel(true);

try {
    System.out.println(future.get());
    
    future.isCancelled(); //캔슬 됬는지 확인
    future.isDone(); // 작업이 잘 끝났는지 확인
    
} catch (InterruptedException e) {
    e.printStackTrace();
} catch (ExecutionException e) {
    e.printStackTrace();
}
```