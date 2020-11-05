# Programmers - [3진법 뒤집기](https://programmers.co.kr/learn/courses/30/lessons/68935)
## 문제 설명
* 자연수 n이 매개변수로 주어집니다. n을 3진법 상에서 앞뒤로 뒤집은 후, 
이를 다시 10진법으로 표현한 수를 return 하도록 solution 함수를 완성해주세요.

### 제한사항
* n은 1 이상 100,000,000 이하인 자연수입니다.

| 입출력 | 예 |
|------| ---- |
| n	| result 
| 45 |	7 |
| 125 |	229 |

### 입출력 예 설명
#### 입출력 예 #1

* 답을 도출하는 과정은 다음과 같습니다.

  | n (10진법) | n (3진법) | 앞뒤 반전(3진법) | 10진법으로 표현 |
  | :----------: | :---------: | :----------------: | :-------------: |
  | 45         | 1200      | 0021             |        7        |

* 따라서 7을 return 해야 합니다.

   		
#### 입출력 예 #2
* 답을 도출하는 과정은 다음과 같습니다.

  | n (10진법) | n (3진법) | 앞뒤 반전(3진법) | 10진법으로 표현 |
  | :----------: | :---------: | :----------------: | :---------------: |
  | 125        | 11122     | 22111            | 229             |

* 따라서 229를 return 해야 합니다.

### 구현 코드
```java
package com.company.Programmers;

import java.util.ArrayList;
import java.util.Collections;

class SolutionThreeBaseFlip {
    public int solution(int n) {
        int answer = 0;
        int tmp = 0;

        ArrayList<Integer> list = new ArrayList<>();

        while (n != 0) {

            tmp = n % 3;
            list.add(tmp);
            n /= 3;
        }

        Collections.reverse(list);

        for (int i = 0; i < list.size(); i++) {

            answer += (int) (list.get(i) * Math.pow(3,i));
        }

        return answer;
    }
}

public class ThreeBaseFlip {
    public static void main(String[] args) {
        int n = 45;
        System.out.println(new SolutionThreeBaseFlip().solution(n));
    }
}
```