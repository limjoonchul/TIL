# [완주하지 못한 선수](https://programmers.co.kr/learn/courses/30/lessons/42576)
## 문제 설명
* 수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
* 마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.

### 제한사항
* 마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
* completion의 길이는 participant의 길이보다 1 작습니다.
* 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
* 참가자 중에는 동명이인이 있을 수 있습니다.

### 입출력 예
| participant |	completion | return |
| ----------- | ---------- | ------ |
| [leo, kiki, eden] | [eden, kiki] | leo |
| [marina, josipa, nikola, vinko, filipa] |	[josipa, filipa, marina, nikola] |	vinko |
| [mislav, stanko, mislav, ana] | [stanko, ana, mislav] | mislav |

### 입출력 예 설명
#### 예제 #1
* leo는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

#### 예제 #2
* vinko는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

#### 예제 #3
* mislav는 참여자 명단에는 두 명이 있지만, 완주자 명단에는 한 명밖에 없기 때문에 한명은 완주하지 못했습니다.

## 구현 코드
### 내가 구현한 코드
* 내가 구현한 코드인데 제한 사항중에서 참가자중에 동명이인이 있을 수 있는 3번째 케이스에 대한
구현을 어떻게 해결해야 하는지 생각해내지 못했다. 고민하다가 다른 분들의 코드를 참고하기로 했다.
```java
package com.company.Programmers.Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        int count=0;
        for (int i = 0; i < participant.length; i++) {
            for (int j = 0; j < completion.length; j++) {
                if (participant[i] != completion[j]){
                    count++;
                }
                if (count == completion.length){
                    answer = participant[i];
                }
            }
            count=0;
        }
        return answer;
    }
}
public class NotFinishedPlayer {
    public static void main(String[] args) {
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        System.out.println(new Solution().solution(participant,completion));
    }
}
```

### 해쉬를 이용한 방법
* 해쉬맵을 이용해서 구현한 방법이다. 해쉬에 입력할 때 `key: 참가자이름` 과 `value: 인원수`로 해서
그 이름에 몇명이 있는지 hash에 입력이 된다. 그러고 나서 completion 완주자를 반복하면서 인원수를 빼주면
인원수가 0이 남게 되면 그 사람은 완주한사람이 되는 것이다 그래서 0이 아닌 값은 완주하지 못한 사람이 되니깐
그 key 값을 반환해주면 된다.

```java
package com.company.Programmers.Hash;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {

        String answer = "";
        Map<String,Integer> hash = new HashMap<>();

        for (String name : participant){
            hash.put(name, hash.getOrDefault(name,0)+1);
        }

        for (String name : completion){
            hash.put(name, hash.get(name)-1);
        }

        for (String key : hash.keySet()){
            if (hash.get(key) != 0){
                answer = key;
            }
        }

        return answer;

    }
}
public class NotFinishedPlayer {
    public static void main(String[] args) {
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        System.out.println(new Solution().solution(participant,completion));
    }
}
```

### Arrays를 이용한 방법
* Arrays를 이용해서 participant와 completion을 정렬해준다.
* 그러면 둘 다 같은 순으로 정렬이 될테고, 순서상 같지 않은 참가자를 반환하거나
완주자는 참가자보다 길이가 1작으니깐 i를 반복문 전에 로컬 변수로 따로 선언해서 반복문이 돌아가고 순서상 다 같다면 조건문에 걸리지 않아서
return 되지 않으니깐 마지막 i++해서 완주자 목록에는 없는 참가자가 완주하지 못한 사람이 된다.
그래서 `return participant[i];` 을 해주게 되는 것이다.
 
```java
package com.company.Programmers.Hash;

import java.util.Arrays;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);

        int i;
        for (i = 0; i < completion.length; i++) {
            if (!participant[i].equals(completion[i])){
                return participant[i];
            }
        }
        return participant[i];
    }
}
public class NotFinishedPlayer {
    public static void main(String[] args) {
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        System.out.println(new Solution().solution(participant,completion));
    }
}
```
