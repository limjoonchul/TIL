package com.company.Programmers.Hash;

import java.util.Hashtable;
import java.util.Map;

/**
 * 위장 Num42578
 * 하루한개의 의상을입는건 중요한 단서이다.
 * 조합으로 계산? 조합은 (파이)  C 7C4
 * 조합을다계산하고 아무것도 착용하지 않은 걸 빼준다
 * 3 * 2 * 2 * 2 -1
 * 의상의 종류이니깐(만 확인하면됨) String (headegear)가 나올때마다 integer로 값 증가시킨다.
 * 각 의상의 종류에서 입지 않은 경우의 수가 있을 수 있으니 값을 먼저증가시켜서 map에 넣고
 * 각 의상의 종류에서 입지 않은 경우의 수끼리 만났을때 완전히 옷을 입지 않은 경우가 되는데,
 * 제한사항에서 스파이는 하루 최소 한개의 의상은 입어야 한다고 나와있기 때문에 총 구한 수에서 이 한경우를 빼줌(-1)
 */
public class Spy {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new Hashtable<>();

        for (String [] cloth : clothes){
            String key = cloth[1];
            int value = map.getOrDefault(key,1);//맵에저장이되어있는지 판단 없으면 0
            map.put(key,++value);// 각옷의 종류중에서 안입는 경우의 수도 있기 때문에 먼저증감연산을해서 값을 넣는다.
        }
        int answer = 1;
        for (int value: map.values()){
            answer += value;
        }
        answer--;//

        return answer;
    }

    public static void main(String[] args) {
        // ctrl + 방향키 단어끝으로 이동
        Spy spy = new Spy();
        String [][] p1 = new String[][]
                {{"yellow_hat","headgear"},
                 {"blue_sunglasses","eyewear"},
                 {"green_turban","headgear"}};
        String [][] p2 = new String[][]
                {{"crow_mask","face"},
                 {"blue_sunglasses","face"},
                 {"smoky_makeup","face"}};
        System.out.println(spy.solution(p1));
        System.out.println(spy.solution(p2));
    }
}
