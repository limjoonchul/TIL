# Algorithm - 가장 먼 노드 (Num49189)
## 문제 설명
* n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다.
 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다. 
 가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.
* 노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때, 
1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.

## 제한사항
* 노드의 개수 n은 2 이상 20,000 이하입니다.
* 간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
* vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.
## 입출력 예
|n|	vertex	|return|
|---|-------|-----|
|6|	[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]| 3|
## 입출력 예 설명
* 예제의 그래프를 표현하면 아래 그림과 같고, 1번 노드에서 가장 멀리 떨어진 노드는 4,5,6번 노드입니다.

![가장먼노드](/Java/documents/images/가장먼노드.png) 

## 구현 코드

```groovy
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
class Node implements Comparable<Node> {
    int index;
    int dist;
    public Node(int index, int dist) {
        this.index = index;
        this.dist = dist;
    }
    @Override
    public int compareTo(Node o) {
        return Integer.compare(dist, o.dist);
        // 거리가 가까울 수록 우선순위를 갖게 함. 거리가 짧은게 우선순위를 갖게하기위해서
    }
}
class LongNode {
    public int solution(int n, int[][] edge) {
        int [] dists = new int[n + 1];
        // 거리를 구하기 위해서 배열 생성 노드의 수가 1부터 시작하기 때문에 편리하게 쓰기우해 n+1을 해준 것이다. 0은 무시한다.
        Arrays.fill(dists, Integer.MAX_VALUE);
        // 처음에 거리를 Integer의 가장 큰 값으로 초기화를 해준다.
        // 가장 짧은 거리를 찾을 때마다 노드간의 거리를 업데이트 한다.
        List<List<Integer>> adj_lists = new ArrayList<>();

        // 1,3 이라할 때 1번 노드와 3번 노드가 이어져 있다는 것이니 (1,3),(3,1)을 나눌 필요가 없다.
        // 그래서 e[0] 일 때와 e[1]일 때 둘 다 list에 값을 넣어준다.
        for (int i = 0; i < n + 1; i++) {
            List<Integer> adj_list = new ArrayList<>();
            for (int[] e: edge) {
                if (e[0] == i) {
                    adj_list.add(e[1]);
                } else if (e[1] == i) {
                    adj_list.add(e[0]);
                }
            }
            adj_lists.add(adj_list);
        }
        PriorityQueue<Node> heap = new PriorityQueue<>();
        //거리가 짧은애를 먼저 접근하게 되어 있다.
        // 그래야 가장 빠르게 계산을 할 수 있다.
        
        dists[1] = 0;
        heap.add(new Node(1, dists[1]));
        while (!heap.isEmpty()) { // 짧은 거리를 다 찾기 위한 반복문
            Node node = heap.remove();
            for (int indVisit: adj_lists.get(node.index)) {
                if (node.dist + 1 < dists[indVisit]) {
                    // node.dist + 1 은 현재 노드에서 다른 노드와의 연결은 무조건 1이 되니깐
                    // 예 노드1과 노드2사이의 거리는 0에 1 을 더해서 1이 된다.
                    // 각 노드에서 이어져있는 노드들과의 거리를 비교하는데 짧은 거리를 구하는 거니깐
                    // 위에 dists배열을 최대값으로 선언해줘서 그것보다 작은 값들이 대입되게 했다.
                    
                    dists[indVisit] = node.dist + 1;
                    heap.add(new Node(indVisit, dists[indVisit]));
                }
            }
        }

        // 가장 거리가 먼 것들의 개수를 세준다.
        int max = 0;
        int answer = 0;
        dists[0] = 0;
        for (int el: dists) {
            if (el == max) {
                // 가장 거리가 먼 것들이 몇개 인지 세기 위해서
                // dists 배열 요소(거리) 가 max와 같을 때 카운트를 증가시키고
                answer++;
            }
            if (el > max) {
                // 거리가 max보다 클 때 그것이 가장 먼 거리이니깐
                // max 값이 거리 값을 넣어준 다음에 다시 카운트를 1로 초기화시킨다.
                // (다시 가장 먼 거리의 값들이 설정이 됬으니 그 개수를 다시 세어줘야하니깐)
                
                max = el;
                answer = 1;
            }
        }
        return answer;
    }
}
class longNodeTest{

    public static void main(String[] args) {

        int [][]edge ={{3, 6}, {4, 3}, {3, 2}, {1, 3},
                {1, 2}, {2, 4}, {5, 2}};
        System.out.println(new LongNode().solution(6,edge));
    }

}
```

