package com.company.ch09.Scheduler;

import java.io.IOException;

public class SchedulerTest {
    public static void main(String[] args) throws IOException {
        System.out.println("전화 상담원 할당 방식을 선택하세요");
        System.out.println("R : 한명씩 차례대로");
        System.out.println("L : 대기가 적은 상담원 우선");
        System.out.println("P : 우선순위가 높은 고객우선 숙련도 높은 상담원");


        int ch = System.in.read(); //아스키코드 값이 입력된다.
        Scheduler scheduler = null;

        if(ch == 'R' || ch == 'r'){
            scheduler = new RoundRobin();
        }else if(ch == 'L' || ch=='l'){
            scheduler = new LeastJob();
        }else if(ch == 'P' || ch == 'p'){
            scheduler = new PriorityAllocation();
        }else{
            System.out.println("지원되지 않는 기능입니다.");
            return; // 왜 리턴을 넣지?
            /**
             * return을 사용하는 이유
             * return은 값을 봔한하거나 메소드를 호출한 곳으로 되돌아갈 때 사용하는 예약어이다
             * 만약 메소드 내부에서 return값이 void일때 return을 하면 여기서 메소드를 끝내겠다는의미
             * return을 호출하면 나머지 로직을 실행하지 않고 함수를 종료하는 의미이다.
             */
        }

        scheduler.getNextCall();
        scheduler.sendCallToAgent();
    }

}
