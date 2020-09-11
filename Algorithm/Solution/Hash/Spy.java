package com.company.Programmers.Hash;

import java.util.Hashtable;
import java.util.Map;

/**
 * 위장 Num42578
 * 하루한개의 의상을입는건 중요한 단서이다.
 * 조합으로 계산? 조합은 (파이)  C 7C4
 * 조합을 다 계산하고 아무것도 착용하지 않은 걸 빼준다 3 * 2 * 2 * 2 -1
 * 의상의 종류이니깐(만 확인하면됨) String (headegear)가 나올때마다 integer로 값 증가시킨다.
 * 각 의상의 종류에서 입지 않은 경우의 수가 있을 수 있으니 값을 먼저증가시켜서 map에 넣고
 * 각 의상의 종류에서 입지 않은 경우의 수끼리 만났을때 완전히 옷을 입지 않은 경우가 되는데,
 * 제한사항에서 스파이는 하루 최소 한개의 의상은 입어야 한다고 나와있기 때문에 총 구한 수에서 이 한경우를 빼줌(-1)
 *
 * 의상의 이름을 기준으로 하는게 아니라 의상의 종류를 기준으로 경우의 수를 구해야 하는 문제이다.
 * 의상의 종류중에는 하나밖에 착용을 하지 못하기 때문에 예를 들어 모자 종류 중에 하나, 야구모자만 착용할 수 있고,
 * 야구모자와 상의 중에 파란색 티셔츠 등 과 같이 하나의 조합씩 계산을 해야 한다.
 * 학생 때 배웠던 조합을 사용하면 된다. (7C4)
 * 그런데 여기서 중요한 점은 의상은 종류에 상관없이 최소 한 개의 의상만 입으면 된다고 제한사항에 나와있다.
 * 그러니 종류가 모자, 상의, 하의, 신발 이 있을 때 모자만 착용할 수있고 상의 하의 신발은 착용하지 않을 수 있는 것이다.
 * 그래서 계산을 할 때 각 의상종류의 경우의 수에 +1을 해줘서 계산을 하면 되는데,
 * 이렇게 할때 마지막에는 모든 의상의 종류가 다 착용하지 않는 경우의 수가 전체에서 한 번 나오기 때문에 이건 전체에 -1을 해주면 된다.
 *
 *
 */
public class Spy {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new Hashtable<>();

        for (String [] cloth : clothes){ //clothes 배열의 크기만큼 반복하면서 각 요소씩 뽑는다.
            String key = cloth[1];
            // 의상의 종류를 key값으로 해주기 위해서  cloth[1]을 해준다.
            //{"yellow_hat","headgear"}일때 인덱스가 yellow_hat은 0, headegear는 1이니깐~

            int value = map.getOrDefault(key,1);
            //getOrDefault()는 처음봤지만, map에 해당 key가 저장이 되어 있는지 확인해서 없으면 defaultValue의 값을 대입한다.
            // defaultValue를 1로해준 이유는 뽑혔을 때 의상의 종류가 착용하지 않는 경우도 있어서 1로 default값을 준다.
            // 값이 있으면 그 key값의 value값을 대입한다.

            map.put(key,++value);
            // 옷을 안입는 경우의수를 기본 1로 설정해줘서 값이 나왔을 때 거기다 증감연산을 먼저해서 값을 넣는다.
            // 옷의 종류중에 똑같은 키값이 나오면 그값을 증가해서 값을 넣는다.
        }
        int answer = 1;
        for (int value: map.values()){ //map에 저장된 value값들을 반복하면서 하나씩 뽑늗다.
            // values()메소드는 처음 사용하는 것인데, map value값들이 저장되어있다고 생각하면 될 것같다.
            // value()는 컬렉션을 반환하는데 향상된 포문을 이용하면 iterator을 이용해서 포문을 돌아준다.
            // Map이여서 순서는 없지만 하나씩 접근을 해서 반환을 해준다.
            answer += value; // 뽑은 값을 answer에 더한다.
        }
        answer--;//위에서 말한 모든 종류의 옷을 안입는 경우의 수가 1번 생기니 전체에서 -1을 빼준다.

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
