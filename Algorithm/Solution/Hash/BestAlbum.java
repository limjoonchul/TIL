package com.company.Programmers.Hash;

import java.util.*;

/**
 * 장르에 속한곡이 하나라면, 하나의 곡만 선택한다
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
        return -Integer.compare(value, o.value);// 작은 값이 우선순위가 높으니깐? 모르겠다.
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
            String key = genres[i]; // 배열을 반복하면서 장르배열의 요소를 key에 대입해준다. key를 장르로 지정해준다.
            int value = plays[i]; // 같이 같은 위치에 있는 plays 요소의 값을 value에 대입해준다. value는 플레이 수.

            int currentValue = sumMap.getOrDefault(key,0); //sumMap에 해당하는 key값이 있는지 확인하고 없으면 0을 넣어준다. 있으면 value값을 넣어준다.

            sumMap.put(key,currentValue + value);
            //각 장르에 대한 플레이 수를 누적하는 것 조건에서 많이 플레이된 장르를 먼저 수록하게 되어있어서
            // 각 장르별로 누적된 플레이 수를 저장해서 나중에 플레이 수를 기준으로 비교하기 위해서

            list.add(new Node(key, value, i));
            // 장르 내에서많이 재생된 노래를 먼저 수록하게 되어있으므로,
            // 장르, 플레이수에 대한 각 요소들과 해당 인덱스(i는 각 요소들에 대한 고유번호이다.)를 list에 저장한다.
            // Node 객체를 만들어서 넣는 이유는 장르와 플레이 수의 배열에 대한 각 인덱스값이 같을 때
            // 해당 장르의 플레이 수이므로 하나로 묶어주기 위해 새로운 클래스를 만들어서 구현을 해준 것이다
        }

        List<Node> sumList = new LinkedList<>();

        for (Map.Entry<String,Integer> entry: sumMap.entrySet()){
            //entryset() 키와 밸류가 하나의 공간안에 묶여있는 set
            //키와 밸류가 한쌍의 집합으로 하나씩 반복문이 순회한다.

            sumList.add(new Node(entry.getKey(), entry.getValue()));
            // 새로운 리스트를 만들어서 sumMap에 있는 key와 valuef를 한 묶음으로 리스트에 넣어준다.
        }
        Collections.sort(sumList);// 더 이Collection.sort()를 알아봐야하지만, value값을 기준으로 내림차순 정렬한다.
        Collections.sort(list);// value값을 기준으로 내림차순 정렬한다.

        List<Integer> bests = new ArrayList<>(); //결과가 고유번호(인덱스)를 출력하는 것이므로 Integer로 자료형 타입을 정했다.
        while(!sumList.isEmpty()){ //sumList가 비어있지 않을 때까지 반복된다.
            String key = sumList.remove(0).key;
            // remove()는 해당 인덱스 값을 리스트에서 제거하고 값을 반환한다.
            // 반환된 값의 키를 key에 대입해준다.
            int num = 0;
            for (Node node: list){
                if(node.key.equals(key)){//list의 key값과 위의 String key의 값이 같은지 확인한다.
                    bests.add(node.index); // 같으면 새로운 bests라는 리스트에 list의 key의 같이 저장됬던 고유번호를 저장한다.
                    num++;
                }
                if(num == 2){ //num변수를 또 만든 이유는 장르별로 가장 많이 재생된 노래를 2개씩 저장하기 위해서이다.
                    //그래서 그것을 카운트할 변수를 만들고 2개가 되면 반복문을 빠져나가게 했다.
                    break;
                }
            }
        }

        int[] answer = new int[bests.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = bests.get(i);
        }
        // bests 리스트가 Integer타입이어서 타입을 맞추기위해 새로운 int 배열을 만들어서 대입해준다.

        return answer;
    }

    public static void main(String[] args) {
        BestAlbum bestAlbum = new BestAlbum();
        String[] genres = {"classic","pop","classic","classic","pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        System.out.println(bestAlbum.solution(genres,plays));
    }
}
