package com.company.s14.p09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * jcf 와 멀티스레드
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list1 = new Vector<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = Collections.synchronizedList(list2); // 어레이리스트인데 싱크된다. 이게 훨씬 빠르다.
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


        Thread.sleep(1000); // 멀티스레드여서 이렇게 기다려야 더 정확해진다? 뭐지 생각해봐
        // 3개의 스레드가 동작한다 메인, 스레드 1, 스레드2 메인스레드에서 동작을 하면서 스레드1,2가 동작을 하는데 이 스레드들이 동작이 다끝나지 않은 상태에서
        // 메인스레드가 종료될 수 있으니깐 메인스레드에 sleep을 줘서 시간차를 준다음에 사이즈를 출력하게 하기 위해서이다.

        System.out.println(list1.size());
        System.out.println(list2.size());

    }
}
