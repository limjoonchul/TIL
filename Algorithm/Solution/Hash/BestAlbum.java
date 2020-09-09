package com.company.Programmers.Hash;

import java.util.*;

/**
 * 장르에 속하 ㄴ곡이 하나라면, 하나의 곡만 선택한다
 * 모든 장르는 재생된 횟수가 다르다ㅣ. 장르를 소팅했을대 문제가생기지 않는다?
 * 장르가 나올때마다 플레이횟수 누적
 */

class Node implements Comparable<Node> {
    String key;
    int value;
    int index;

    public Node(String key, int value, int index) {
        this.key = key;
        this.value = value;
        this.index = index;
    }
    public Node(String key, int value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public int compareTo(Node o) {
        return -Integer.compare(value, o.value);// 작은 값이 우선순위가 높으니깐?
    }
    public String toString() {
        return "(" + key + ", " + value + ", " + index + ")";
    }
}

public class BestAlbum {
    public int[] solution(String[] genres, int[] plays) {

        Map<String,Integer> sumMap = new Hashtable<>();
        List<Node> list = new LinkedList<>();

        // for i 엔터를 클리하면 반복문이 만들어짐
        for (int i = 0; i <genres.length ; i++) {
            String key = genres[i];
            int value = plays[i];

            int currentValue = sumMap.getOrDefault(key,0);
            sumMap.put(key,currentValue + value);//각 음악장르에 대한 조회수를 누적하는 것??
            list.add(new Node(key, value, i));
        }
        List<Node> sumList = new LinkedList<>();
        //entryset키와 밸류가 하나의 공간안에 묶여있는 set
        for (Map.Entry<String,Integer> entry: sumMap.entrySet()){ //키와 밸류가 한쌍의 집합으로 하나씩 반복문이 순회한다.
            sumList.add(new Node(entry.getKey(), entry.getValue()));

        }
        Collections.sort(sumList);//모든 장르가 들어있음
        Collections.sort(list);

        List<Integer> bests = new ArrayList<>();
        while(!sumList.isEmpty()){
            String key = sumList.remove(0).key;
            int num = 0;
            for (Node node: list){
                if(node.key.equals(key)){
                    bests.add(node.index);
                    num++;
                }
                if(num == 2){
                    break;
                }
            }
        }

        int[] answer = new int[bests.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = bests.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        BestAlbum bestAlbum = new BestAlbum();
        String[] genres = {"classic","pop","classic","classic","pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        System.out.println(bestAlbum.solution(genres,plays));
    }
}
