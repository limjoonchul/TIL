# [전화번호목록](https://programmers.co.kr/learn/courses/30/lessons/42577)

## 문제 설명
* 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.
  * 구조대 : 119
  * 박준영 : 97 674 223
  * 지영석 : 11 9552 4421
* 전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.

### 제한 사항
* phone_book의 길이는 1 이상 1,000,000 이하입니다.
* 각 전화번호의 길이는 1 이상 20 이하입니다.

### 입출력 예제
| phone_book | return |
| ---------- | ------ |
| [119, 97674223, 1195524421] | false |
| [123,456,789] | true |
| [12,123,1235,567,88] | false |

### 입출력 예 설명
#### 입출력 예 #1
* 앞에서 설명한 예와 같습니다.

#### 입출력 예 #2
* 한 번호가 다른 번호의 접두사인 경우가 없으므로, 답은 true입니다.

#### 입출력 예 #3
* 첫 번째 전화번호, “12”가 두 번째 전화번호 “123”의 접두사입니다. 따라서 답은 false입니다.
  
## 구현 코드
### 알고리즘 풀면서 배운 점

* 내가 구현한 코드는 이중 반복문을 하면서 같은 값이 나왔을 때는 `continue`하고, 접두어가 나오게 되면 false를 반환하도록 구현하는 풀이 과정은 맞았던 것 같다.
그런데 테스트 케이스는 통과했지만, 효율성 체크하는 부분에서 시관초과 실패를 했다... 다른 사람들의 풀이를 보면 정말 간단한 문제였다.
핵심 부분은 `return false`를 바로 하는 거였다고 생각한다. 이걸 바로 사용하면 더 이상 코드가 진행되지 않고 끝나게 되니깐.. 결정력이 부족했다

### 내가 구현한 코드
```java
package com.company.Programmers.Hash;

import java.util.HashMap;
import java.util.Map;

class SolutionPhoneNumberList {
    public boolean solution(String[] phone_book) {
        boolean answer = true;

        Map<String, Boolean> hash = new HashMap<>();
        for (int i = 0; i < phone_book.length; i++) {
            for (int j = 0; j < phone_book.length; j++) {
                if (phone_book[i].equals(phone_book[j])){
                    continue;
                }

                if (phone_book[j].startsWith(phone_book[i])){
                    hash.put(phone_book[i],false);
                    break;
                }else{
                    hash.put(phone_book[i],true);
                }
            }
        }

        if (hash.values().contains(false)){
            answer = false;
        }
       return answer;
    }
}

public class PhoneNumberList {
    public static void main(String[] args) {
        String[] phone_book = {"12","123","1235","567","88"};

        System.out.println(new SolutionPhoneNumberList().solution(phone_book));
    }
}
```

### 다른 사람들의 구현 코드
```java
package com.company.Programmers.Hash;

class SolutionPhoneNumberList {
    public boolean solution(String[] phone_book) {
        boolean answer = true;

        for (int i = 0; i < phone_book.length; i++) {
            for (int j = 0; j < phone_book.length; j++) {
                if (i == j){
                    continue;
                }

                if (phone_book[j].startsWith(phone_book[i])){
                    return false; // 여기서 return이 핵심인 것 같다.. retrun을 해주면 더 이상 진행이되지 않고 끝나니깐...
                    // 대박.. 이걸 생각 못함...
                }
            }
        }
        return answer;
    }
}

public class PhoneNumberList {
    public static void main(String[] args) {
        String[] phone_book = {"12","123","1235","567","88"};

        System.out.println(new SolutionPhoneNumberList().solution(phone_book));
    }
}
```