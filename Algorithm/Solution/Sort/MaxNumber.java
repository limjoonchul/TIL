package com.company.Programmers;

import java.util.PriorityQueue;

/***
 * 95 59 중에 더 큰 수로 우선순위를 결정함
 * 303 330 도 마찬가지로
 * 작은 값이 가장 먼저 나오게 함 minHeap을 이용한 것
 * 이런게 우선순위 큐의 방식이다. 낮은값일수록 우선순위가 높으니깐
 *
 */
public class MaxNumber {
    public String solution(int[] numbers) {
        PriorityQueue<String> pq = new PriorityQueue<>((o1,o2)->{
           if(o1.equals(o2)){
               return 0;
           }else{
               if (Integer.parseInt(o1+o2) < Integer.parseInt(o2+o1)){
                   return 1; //01이 더 작은거면 뒤로가야하니깐
               }else{
                   return -1;
               }

           }
        });//순서대로 뽑을 수 있다. 오버라이딩한경우만 넣음 함수형프로그래밍
        for (int number : numbers){
            pq.offer(String.valueOf(number)); //offer로 넣을 수 있다. 빼면 포??
        }

        assert pq.peek() != null;
        if (pq.peek().equals("0")){
            return "0";
        }

        StringBuilder builder = new StringBuilder();
        while (!pq.isEmpty()){
            builder.append(pq.poll()); //뽑아내는거 가장작은걸 제거하면서 뽑아냄
        }


        return builder.toString();
    }

    public static void main(String[] args) {
        MaxNumber maxNumber = new MaxNumber();
        int[] numOne = {6, 10, 2};
        int[] numTwo = {3,30,34,5,9};
        System.out.println(maxNumber.solution(numOne));
        System.out.println(maxNumber.solution(numTwo));
    }
}
