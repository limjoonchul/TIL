# [베스트앨범](https://programmers.co.kr/learn/courses/30/lessons/42579)
## 문제 설명 

* 스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
* 노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

### 제한사항
* genres[i]는 고유번호가 i인 노래의 장르입니다.
* plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
* genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
* 장르 종류는 100개 미만입니다.
* 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
* 모든 장르는 재생된 횟수가 다릅니다.
### 입출력 예
| genres | plays | return |
|---------|------|--------|
| [classic, pop, classic, classic, pop] | [500, 600, 150, 800, 2500] | [4, 1, 3, 0] |

```groovy
/**
 * 장르에 속한곡이 하나라면, 하나의 곡만 선택한다
 * 모든 장르는 재생된 횟수가 다르다ㅣ. 장르를 소팅했을대 문제가생기지 않는다?
 * 장르가 나올때마다 플레이횟수 누적
 */
// 장르랑 플레이횟수를 묶어주기 위해서 사용.
class Node implements Comparable<Node> {//정렬을 해줘야하는데 기준을 만들어주기 위해서 Comparable인터페이스를 이용한다.
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
    public int compareTo(Node o) {//Comparable 인터페이스를 구현 하면 compareTo 메소드를 구현해줄 수 있다
        return -Integer.compare(value, o.value);
        // -를 붙여준 이유는 기본적으로 오른차순으로 정렬이 이루어지는데
        // 이건 플레이수가 큰 것부터 정렬하기 위해서 -를 붙여서 내림차순으로 정렬
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
        Collections.sort(sumList);
        // value값을 기준으로 내림차순 정렬한다.
        // Node 클래스에서 compareTo로 비교를 해줬기 때문에 정렬이 가능하다.
        // Comparable 인터페이스에 comareTo()메소드가 정의되어 있는데
        // 메소드를 오버라이딩해주면 Collections.sort()의 매개변수로 List를 넘겨서 정렬할 수 있다.(매개변수를 List로 받게 되어있다.)
        // List들이 자료형타입을 Node로 해줬기 때문에 Node 클래스에서 compareTo() 메소드를 재정의 한것에 대해서
        // sort에 넘겨서 정렬할 수 있다. 정렬기준이 value인 것은 위에서 value값을 비교해줬기 때문이다.

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

```