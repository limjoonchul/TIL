package com.company.s14.p10;

import java.util.concurrent.*;
// 작업을 병렬적으로 처리하고자할 때는 스레드 풀이 적합하고, gui같은건 일반스레드를이용함?
// 옵션이 많은데 경험을 통해서 쌓아야한다.
/**
 * 스레드 풀 (Thread pool)
 * - 스레드를 직접 만들어 사용할 경우, 작업을 마친 스레드는 종료된다 (1회용)
 *    -> 멀티스레드 작업을 계속 할 경우, 스레드를 생성/삭제하는 오버헤드가 있다.(속도가 떨어지고 관리가 어렵다)
 * - 스레드 풀
 *    - 미리 스레드를 생성해 두고, 작업만 스레드를 할당하여 동작
 *    - 미리 생성해 둔 스레드의 집합을 스레드 풀이라고 한다.(뭔가를 사용하기 위해 모아둔 것을 풀이라고 한다.)
 *    - 배치 작업(모아두고 한번에 처리하는 작업)에 많이 사용.
 *    핵심! - 미리 만들어 놓고 상황에 동작하게 한다
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 거의 정형화 되어있다.
        ExecutorService pool1 = Executors.newCachedThreadPool();
        /**
         * newCachedThreadPool()
         * - 초기 스레드가 0개 하나도 안돌아가고 있어서 오버헤드도 발생하지 않고 어덯게 동작할지 모르는상태에서 무난하게 사용 가능
         * - 코어 스레드가 0개 - 일하지 않아도 살려두는 스레드
         * - 요청 작업보다 스레드가 부족하면 새 스레드를 생성한다.(작업에 맞춰 스레드를 생성)
         * - 60초 동안  일하지 않은 스레드를 제거한다.
         * - 안정적으로 사용할 때
         */
        ExecutorService pool2 = Executors.newFixedThreadPool(10); // 입력받음
        /**
         * newFixedThreadPool(10);
         * - 최대 스레드 nThreads개
         * - 코어 스레드 nThreads개 입력값 개수
         * - 요청 작업보다 스레드가 부족하면 새 스레드를 생성
         * - 작업하지 않는 스레드도 제거하지 않고 동작한다.
         * - 한번 증가하면 죽이지 않는다. 풀파워로 쓸 때
         */
        // Executors - 실행하는 애들을 만들어 놓는 클래스

        ExecutorService es = new ThreadPoolExecutor(
                10, // 코어 스레드 개수
                100, // 최대 스레드 개수
                120, // 스레드가 이 시간 동안 일하지 않으면 제거 (대기 시간)
                TimeUnit.SECONDS, // 시간 단위를 지정
                new SynchronousQueue<Runnable>() // 작업을 요청하면 -> 작업을 쌓아둘 큐 -> 스레드 풀로 할당해서 넘어가서 동작한다.
                // 이게 없으면 작업요청하면실제 스레드로 들어가서 동작하기전가지 메인스레드가 멈처워있어야한다. 그래서 큐에 던져놓고
                // 메인스레드는 동작하게 하는 것이다.(존재한다.)
        );

        // 2. 스레드에 할당할 작업 생성
        class Work implements Runnable{

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
        // 3. 스레드에 작업 요청
        Future<String> future = null; // 값이 나중에 받아 올수있을것이다라고 알 수 있다 get은 값이나오면 작업을하고 아니면 대기하고있다.
        future = pool1.submit(new CallableWork());
        // 퓨쳐로 값이 들어오기 전까지 대기하고 있다.
        // 서
        for (int i = 0; i < 100; i++) {
            pool1.submit(new Work()); // 리턴이 있음
            // 데몬 스레드가 아닌 이상 (일반 스레드는 )스레드풀은 자동 종료가 안되기 때문에,직접 스레드풀을 종료해 주어야 한다.


            // pool1.execute(); // 리턴이 없음


        }
        // 4 . 스레드 종료(동기화)
        pool1.shutdown(); // Thread.join()과 비슷하게 작업이 끝나기를 기다려서 종료
        // shutdownNow()는 바로 종료시키는 것
        Thread.sleep(1000);
        // 다른 스레드와 동기화를 맞추기 위해(여기서는 pool1이 작업을 진행중) 잠시 기다렸다가 밑에 코드가 진행되니깐 작업완료가 마지막에 출력됨!


        future.cancel(true); // 캔슬해서 종료시킬 수도 있다. 실행중인 Callable 객체를 강제 종료할 수 있다.
        // true는 해당 스레드에 인터럽트를 보낸다
        // 캔슬은 false를 주면 캔슬을 호출하면 스레드는 아무 동작이 일어나지 않음, 종료하지 않음 캔슬을 하고나면 get을 할 수 없게 된다.(CanclelationException 발생)
        //  true를 주면 추가 기능이 생김(false의 기능에서더 추가된다.) 작업하는 스레드에 인터럽트를 걸어줌
        try {
            System.out.println(future.get()); // get()은 Blocking method
            // future.get 쓰레드가 future의 작업이 끝날때까지 기다렸다가 퓨쳐로 값이들어오게되서 사용이가능하면 get해서 사용이 가능하다
            // 그래서 그전에 인터럽트가 들어올 수 있는 것이다.
            // get()이 영원히 기다릴 수 있는 문제가 발생할 수 있어서 timeout 설정 가능하다/
            // blocking - 다른 작업이 기다릴 때가지 기다리는 것.

             future.isCancelled(); //캔슬 됫는지 확인
            future.isDone(); // 작업이 잘 끝났는지 확인
            // Callable가 어떻게 작업하는지 모르기때문에 외부에서 컨트롤할 수 있게 하기위해 위의 메소드들을 제공한다.
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
