# [나누어 떨어지는 숫자 배열](https://programmers.co.kr/learn/courses/30/lessons/12910)
## 문제 설명
* array의 각 element 중 divisor로 나누어 떨어지는 값을 오름차순으로 정렬한 배열을 반환하는 함수, solution을 작성해주세요.
* divisor로 나누어 떨어지는 element가 하나도 없다면 배열에 -1을 담아 반환하세요.

### 제한사항
* arr은 자연수를 담은 배열입니다.
* 정수 i, j에 대해 i ≠ j 이면 arr[i] ≠ arr[j] 입니다.
* divisor는 자연수입니다.
* array는 길이 1 이상인 배열입니다.

### 입출력 예
| arr |	divisor | return |
| ---- | ------ | ------ |
| [5, 9, 7, 10] | 5 | [5, 10] |
| [2, 36, 1, 3] | 1 | [1, 2, 3, 36] |
| [3,2,6] | 10 | [-1] |

### 입출력 예 설명
#### 입출력 예#1
* arr의 원소 중 5로 나누어 떨어지는 원소는 5와 10입니다. 따라서 [5, 10]을 리턴합니다.

#### 입출력 예#2
* arr의 모든 원소는 1으로 나누어 떨어집니다. 원소를 오름차순으로 정렬해 [1, 2, 3, 36]을 리턴합니다.

#### 입출력 예#3
* 3, 2, 6은 10으로 나누어 떨어지지 않습니다. 나누어 떨어지는 원소가 없으므로 [-1]을 리턴합니다.

## 구현 코드
* 쉬운 문제이긴 했지만, 처음으로 한 번에 푼 문제이다! 손코딩으로 전체적인 코드를 구현 해보고 문법(isEmpty())이 생각 안 나다가 코드를 작성 하려고 하니깐
생각나서 넣어보니 한 번에 풀어졌다!
```java
package com.company.Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class SolutionDivideNumberArray {
    public int[] solution(int[] arr, int divisor) {

        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % divisor == 0){
                list.add(arr[i]);
            }
        }

        if (list.isEmpty()) {
            list.add(-1);
        }

        Collections.sort(list);

        answer = new int[list.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}

public class DivideNumberArray {
    public static void main(String[] args) {
        int [] arr = {2,36,1,3};
        int divisor = 1;
        System.out.println(Arrays.toString(new SolutionDivideNumberArray().solution(arr, divisor)));
    }
}
```