# 동적 계획법과 분할 정복

- 두 개가 다른 기법인데 유사한 점이 많음

## 1. 정의

- 동적계획법(DP, Dynamic Programming)
    - 입력 크기가 작은 부분 문제들을 해결한 후, 해당 부분 문제의 답을 활용해서, 보다 큰 크기의 부분 문제를 해결하면서 최종적으로 전체 문제를 해결하는 알고리즘
    - 상향식 접근법으로, 가장 최하위 해답을 구한 후, 이를 저장하고, 해당 결과값을 이용해서 상위 문제를 풀어가는 방식이다.
        - 하위 문제를 저장하는 것은 예를 들어 중간 문제 2개가 존재한다고 할 때, 이 두 문제 모두 작은 문제 하나를 공통적으로 필요하다고 하면 중간 문제를 풀려고할 때 공통된 작은 문제가 각 각 계산되어지니깐 시간적으로 낭비가 된다 그래서 한번 문제를 풀면 저장해 놓는 것이다(메모리제이션 기법!)
    - Memorization 기법을 사용함
        - 프로그램 실행 시 이전에 계산한 값을 저장하여, 다시 계산하지 않도록 하여 전체 실행 속도를 빠르게 하는 기술
    - 문제를 잘게 쪼갤 때, 부분 문제는 중복되어, 재활용된다.
        - 예 : 피보나치 수열
- 분할 정복(Divide and Conquer)
    - 문제를 나눌 수 없을 때까지 나누어서 각각 풀면서 다시 합병하여 문제의 답을 얻는 알고리즘
    - 하향식 접근법으로, 상위의 해답을 구하기 위해, 아래로 내려가면서 하위의 해답을 구하는 방식
        - 일반적으로 재귀 함수로 구현
    - 문제를 잘개 쪼갤 때 부분 문제는 서로 중복되지 않는다.
        - 예 : 병합 정렬,  퀵 정렬 등

## 2 .공통점과 차이점

- 공통점
    - 문제를 잘개 쪼개서, 가장 작은 단위로 분할
- 차이점
    - 동적 계획법
        - 부분 문제는 중복되어, 상위 문제 해결 시 재활용 된다.
        - Memorization 기법 사용.
    - 분할 정복
        - 부분 문제는 서로 중복되지 않는다.
        - Memorization 기법 사용 안함.
   
## DP 예제
```java
package codinganddatastructure.dynamicprogramming;

import java.util.Arrays;

public class FibonacciTest {
    /**
     * 재귀함수를 이용해서 피보나치를 계산하면
     * 계속 내려가면서 하위의 값을 쌓고쌓고 해서 시간이 오래걸릴 수 있다.
     */
    public static int fibonacci(int num) {
        if (num < 2) {
            return num;
        }

        return fibonacci(num - 1) + fibonacci(num - 2);
    }

    // 재귀함수보다 속도가 빠르다 동일한 것에 대해서 다시 계산할 필요가 없고 저장해놓기 때문에
    public static int dp(int num) {
        // 입력받은 값까지 인덱스로 지정해놨기 때문에 길이는 입력받은 값 + 1 이다.
        int[] cache = new int[num + 1];
        Arrays.fill(cache, 0);
        cache[1] = 1;

        for (int i = 2; i < cache.length; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[num];
    }
    public static void main(String[] args) {
        System.out.println(fibonacci(5));
        System.out.println(dp(10));
    }
}

```