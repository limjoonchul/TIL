# [문자열 내림차순으로 배치하기](https://programmers.co.kr/learn/courses/30/lessons/12917)

## 문제 설명
* 문자열 s에 나타나는 문자를 큰것부터 작은 순으로 정렬해 새로운 문자열을 리턴하는 함수, solution을 완성해주세요.
* s는 영문 대소문자로만 구성되어 있으며, 대문자는 소문자보다 작은 것으로 간주합니다.

### 제한 사항
* str은 길이 1 이상인 문자열입니다.

| 입출력 | 예 |
| ----- | --- |
| s | return |

## 구현 코드
```java
import java.util.ArrayList;

class SolutionSortStringDescending {
    public String solution(String s) {

        String answer = "";
        ArrayList<Character> list = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            list.add(s.charAt(i));
        }

        list.sort((o1, o2) -> o2-o1);

        for (int i = 0; i < s.length(); i++) {
           answer+= list.get(i);
        }

        return answer;
    }
}

public class SortStringDescending {
    public static void main(String[] args) {
        String s = "Zbcdefg";
        System.out.println(new SolutionSortStringDescending().solution(s));
    }
}
```