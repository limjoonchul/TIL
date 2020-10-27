# Algorithm - [기능개발](https://programmers.co.kr/learn/courses/30/lessons/42586)
## 문제 설명
* 프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.
* 또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.
* 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.

### 제한 사항
* 작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
* 작업 진도는 100 미만의 자연수입니다.
* 작업 속도는 100 이하의 자연수입니다.
* 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.

### 입출력 예
| progresses |	speeds | return |
| ---------- | -------- | -------- |
| [93, 30, 55] | [1, 30, 5] | [2, 1] |
| [95, 90, 99, 99, 80, 99] | [1, 1, 1, 1, 1, 1] | [1, 3, 2] |

### 입출력 예 설명
* 입출력 예 #1
   * 첫 번째 기능은 93% 완료되어 있고 하루에 1%씩 작업이 가능하므로 7일간 작업 후 배포가 가능합니다.
   * 두 번째 기능은 30%가 완료되어 있고 하루에 30%씩 작업이 가능하므로 3일간 작업 후 배포가 가능합니다. 하지만 이전 첫 번째 기능이 아직 완성된 상태가 아니기 때문에 첫 번째 기능이 배포되는 7일째 배포됩니다.
   * 세 번째 기능은 55%가 완료되어 있고 하루에 5%씩 작업이 가능하므로 9일간 작업 후 배포가 가능합니다.
   * 따라서 7일째에 2개의 기능, 9일째에 1개의 기능이 배포됩니다.

* 입출력 예 #2
   * 모든 기능이 하루에 1%씩 작업이 가능하므로, 작업이 끝나기까지 남은 일수는 각각 5일, 10일, 1일, 1일, 20일, 1일입니다. 어떤 기능이 먼저 완성되었더라도 앞에 있는 모든 기능이 완성되지 않으면 배포가 불가능합니다.
   * 따라서 5일째에 1개의 기능, 10일째에 3개의 기능, 20일째에 2개의 기능이 배포됩니다.
   
## 구현 코드
* 혼자서 구현을 해보려고 했는데 결국 해결하지 못해서 다른 사람의 코드를 풀이해봤다.
* 먼저 앞의 기능이 완료가 되야 뒤에 기능들이 배포가 가능하므로, queue를 만든다.
* 그런다음, queue에  각 기능이 100%가 되는 기간을 구하는 식을 작성하여서 기간을 큐에 담아 준다.(이렇게 풀려했는데 이 식을 생각하지 못했다.)
* queue에 처음 요소를 firstFunc이라는 변수에 담아주고, 배포될 수 있는 기능들의 갯수를 세기 위한 count변수를 생성하고, 
이 count를 담기 위한 ArrayList를 만들어준다.
* queue가 비어있지 않을 때까지 반복문을 돌면서 다음 queue의 요소를 빼서 nextFunc이라는 변수에 담고, 만약 앞의 기능이 뒤의 기능보다 기간이 더 길면,
앞의 기능이 배포될 때 뒤의 기능이 같이 배포가 되니깐 count를 1증가시켜준다. 
* 그런데 앞의 기능이 뒤의 기능보다 크지 않으면 뒤의 기능은 아직 배포될 준비가 안되어 있는 상태이니깐 이전의 배포가 된 기능들의 갯수를 
result에 담고, count를 1로 다시 초기화 해준다. 뒤의 기능을 앞의 기능에 담아준다. 그래야 뒤의 기능이 처음이 되서 이 기능이 배포될 때 뒤의
기능들이 배포되는지 다시 카운트 할 수 있으니깐
* 이렇게 반복이 되다가 queue가 비어있으면 while문을 빠져나오고, 마지막 기능의 배포되는 count를 result에 넣어준다.
* 마지막으로 반복문을 돌면서 배포되는 기능들의 숫자들을 담아 놨던 result를 answer에 대입해서 반환한다.

```java
package com.company.Programmers.stackandqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


class SolutionFunctionDevelopment {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            queue.add((100-progresses[i])%speeds[i] == 0 ? (100-progresses[i])/speeds[i] : (100-progresses[i])/speeds[i]+1);
        } 

        int firstFunc = queue.poll();
        int count = 1;
        ArrayList<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()){
            int nextFunc = queue.poll();
            if (firstFunc >= nextFunc){
                count++;
            } else{
                result.add(count);
                count = 1;
                firstFunc = nextFunc;
            }
        }

        result.add(count);
        answer = new int[result.size()];

        for (int j = 0; j < answer.length; j++) {
            answer[j] = result.get(j);
        }

        return answer;
    }
}
public class FunctionDevelopment {
    public static void main(String[] args) {
        int[] progresses = {93,30,55};
        int[] speeds = {1,30,5};

        System.out.println(Arrays.toString(new SolutionFunctionDevelopment().solution(progresses,speeds)));;
    }
}
```