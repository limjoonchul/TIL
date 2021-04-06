# [위장](https://programmers.co.kr/learn/courses/30/lessons/42578)
## 문제 설명 
* 스파이들은 매일 다른 옷을 조합하여 입어 자신을 위장합니다.

* 예를 들어 스파이가 가진 옷이 아래와 같고 오늘 스파이가 동그란 안경, 긴 코트, 파란색 티셔츠를 입었다면 다음날은 청바지를 추가로 입거나 동그란 안경 대신 검정 선글라스를 착용하거나 해야 합니다.

| 종류 |	이름 |
|----|------|
| 얼굴 |	동그란 안경, 검정 선글라스 |
| 상의 | 파란색 티셔츠 |
| 하의 | 청바지 |
| 겉옷 | 긴 코트 |

* 스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.

### 제한사항
* clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
* 스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
* 같은 이름을 가진 의상은 존재하지 않습니다.
* clothes의 모든 원소는 문자열로 이루어져 있습니다.
* 모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
* 스파이는 하루에 최소 한 개의 의상은 입습니다.

### 입출력 예
| clothes |	return |
|---------| ------- |
| [[yellow_hat, headgear], [blue_sunglasses, eyewear], [green_turban, headgear]] | 5 |
| [[crow_mask, face], [blue_sunglasses, face], [smoky_makeup, face]] | 3 |

### 문제 해설
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


```groovy

public class Disguise {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new Hashtable<>();

        for (String [] cloth : clothes){ //clothes 배열의 크기만큼 반복하면서 각 요소씩 뽑는다.
            String key = cloth[1];
            // 의상의 종류를 key값으로 해주기 위해서  cloth[1]을 해준다.
            //{"yellow_hat","headgear"}일때 인덱스가 yellow_hat은 0, headegear는 1이니깐~

            int value = map.getOrDefault(key,1);
            // map에 해당 key가 저장이 되어 있는지 확인해서 없으면 defaultValue의 값을 대입한다.
            // defaultValue를 1로해준이유는 뽑혀서 값을 대입할 때 사용되는 것과 같으니 1로 시작한다.
            // 값이 있으면 그 key값의 value값을 대입한다.

            map.put(key,++value);
            // 각 옷의 종류 중에서 안입는 경우의 수도 있기 때문에 먼저 증감 연산을 해서 값을 넣는다.
            // 옷을 안입는 경우의수를 기본 1로 설정해줘서 값이 나왔을 때 거기다 증감연산을 먼저해서 값을 넣는다.
        }
        int answer = 1;
        for (int value: map.values()){ //map에 저장된 value값들을 반복하면서 하나씩 뽑늗다.
            // values()메소드는 처음 사용하는 것인데, map value값들이 저장되어있다고 생각하면 될 것같다.
            //value()는 컬렉션을 반환하는데 향상된 포문을 이용하면 iterator을 이용해서 포문을 돌아준다.
            // Map이여서 순서는 없지만 하나씩 접근을 해서 반환을 해준다.
            answer *= value; // 뽑은 값을 answer에 더한다.
        }
        answer--;//위에서 말한 모든 종류의 옷을 안입는 경우의 수가 1번 생기니 전체에서 -1을 빼준다.

        return answer;
    }

    public static void main(String[] args) {
        // ctrl + 방향키 단어끝으로 이동
        Disguise disguise = new Disguise();
        String [][] p1 = new String[][]
                {{"yellow_hat","headgear"},
                 {"blue_sunglasses","eyewear"},
                 {"green_turban","headgear"}};
        String [][] p2 = new String[][]
                {{"crow_mask","face"},
                 {"blue_sunglasses","face"},
                 {"smoky_makeup","face"}};
        System.out.println(disguise.solution(p1));
        System.out.println(disguise.solution(p2));
    }
}
```
