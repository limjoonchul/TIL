# 프로그래머스 - [문자열 내 마음대로 정렬하기](https://programmers.co.kr/learn/courses/30/lessons/12915)
## 문제 설명
* 문자열로 구성된 리스트 strings와, 정수 n이 주어졌을 때, 각 문자열의 인덱스 n번째 글자를 기준으로 오름차순 정렬하려 합니다. 
예를 들어 strings가 [sun, bed, car]이고 n이 1이면 각 단어의 인덱스 1의 문자 u, e, a로 strings를 정렬합니다.

### 제한 조건
* strings는 길이 1 이상, 50이하인 배열입니다.
* strings의 원소는 소문자 알파벳으로 이루어져 있습니다.
* strings의 원소는 길이 1 이상, 100이하인 문자열입니다.
* 모든 strings의 원소의 길이는 n보다 큽니다.
* 인덱스 1의 문자가 같은 문자열이 여럿 일 경우, 사전순으로 앞선 문자열이 앞쪽에 위치합니다.

### 입출력 예

| strings |	n |	return |
| -------- | --- | ----- |
| [sun, bed, car] | 1 |	[car, bed, sun] |
| [abce, abcd, cdx] |2 | [abcd, abce, cdx] |

### 입출력 예 설명
* 입출력 예 1
  * sun, bed, car의 1번째 인덱스 값은 각각 u, e, a 입니다. 이를 기준으로 strings를 정렬하면 [car, bed, sun] 입니다.

* 입출력 예 2
  * abce와 abcd, cdx의 2번째 인덱스 값은 c, c, x입니다. 따라서 정렬 후에는 cdx가 가장 뒤에 위치합니다. abce와 abcd는 사전순으로 정렬하면 abcd가 우선하므로, 답은 [abcd, abce, cdx] 입니다.

## 구현 코드
* 우선, 나 혼자서 완벽히 해결하지 못한 문제이다. 결과 값은 똑같이 나오지만 프로그래머스의 테스트를 실행했을 대
실패하는 테스트가 많이 나왔다. 처음에 내부 구현의 차이를 생각하지 못하고 `list.sort()` 와 다른 사람이 구현했던 방식인 `Arrays.sort()`의 차이가 있는건지
생각이 들었는데 결국에는 Comparator 인터페이스를 구현하는 것이여서 그 문제가 아니였다.. 좀 멍청한 생각이였던 것 같다.
그래서 확인해보니 제한조건에서 주어진 n번째 인덱스가 같은 경우에 전체를 비교해서 사전순으로 정렬하도록 구현을 했어야 했는데
나는 n+1번째 인덱스만 비교하도록 구현을 해놨었다. 이게 테스트 실패가 된 부분이였다.
제한 조건을 확실히 파악하는게 중요하다는 걸 다시 한번 느꼈다. 그래도 하나 배웠으니... 된 거겠지?
내가 처음 실패했던 구현 코드, 바꾼 부분, 다른 분의 코드를 나눠서 올려보겠다.
### 처음 실패했던 구현 코드
```java
package com.company.Programmers.PracticeProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class SolutionStringArrange {
    public String[] solution(String[] strings, int n) {
        String[] answer = {};
        List<String> list = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }

        list.sort((o1, o2) ->{
            if (o1.charAt(n) > o2.charAt(n)){
                return 1;
            }else if (o1.charAt(n) == o2.charAt(n)){
                return o1.charAt(n+1) > o2.charAt(n+1) ? 1 : -1;
            }else{
                return -1;
            }
        });

        answer = new String[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
public class StringArrange {
    public static void main(String[] args) {
        String[] strings = {"abce","abcd","cdx"};
        int n = 2;
        System.out.println(Arrays.toString(new SolutionStringArrange().solution(strings,n)));

    }
}
``` 
### 수정해서 성공한 구현 코드
```java
package com.company.Programmers.PracticeProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class SolutionStringArrange {
    public String[] solution(String[] strings, int n) {
        String[] answer = {};
        List<String> list = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i]);
        }

        list.sort((o1, o2) ->{
            if (o1.charAt(n) > o2.charAt(n)){
                return 1;
            }else if (o1.charAt(n) == o2.charAt(n)){
                return o1.compareTo(o2); // 이렇게 전체를 비교했어야 했다.
            }else{
                return -1;
            }
        });

        answer = new String[list.size()];

        for (int i = 0; i < answer.length; i++) {

            answer[i] = list.get(i);
        }
        return answer;
    }
}
public class StringArrange {
    public static void main(String[] args) {

        String[] strings = {"abce","abcd","cdx"};
        int n = 2;
        System.out.println(Arrays.toString(new SolutionStringArrange().solution(strings,n)));

    }
}
```
### 다른 사람의 구현 코드
* 이렇게 간단하게도 구현 가능한 쉬운 문제였는데.. 왜 나는 해결하지 못 했을까...
```java
package com.company.Programmers.PracticeProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class SolutionStringArrange {
    public String[] solution(String[] strings, int n) {

        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.charAt(n) > o2.charAt(n)){
                    return 1;
                }else if (o1.charAt(n) == o2.charAt(n)){
                    return o1.compareTo(o2);
                }else{
                    return -1;
                }
            }
        });
        return strings;
    }
}
public class StringArrange {
    public static void main(String[] args) {
        String[] strings = {"abce","abcd","cdx"};
        int n = 2;
        System.out.println(Arrays.toString(new SolutionStringArrange().solution(strings,n)));
    }
}

```