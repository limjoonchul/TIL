# [수 정렬하기 3](https://www.acmicpc.net/problem/10989)
 
| 시간 제한 | 메모리 제한 | 제출 |	정답 | 맞은 사람 | 정답 비율|
| -------- | ---------- | ----- | -----| ----| ----|
| 3 초 (하단 참고) |	8 MB (하단 참고) |101658 | 22826 |17152 | 22.953% |

## 문제
* N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.

## 입력
* 첫째 줄에 수의 개수 N(1 ≤ N ≤ 10,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 숫자가 주어진다. 이 수는 10,000보다 작거나 같은 자연수이다.

## 출력
* 첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.

### 예제 입력 1 
```text
10
5
2
3
1
4
2
3
5
1
7
```
### 예제 출력 1
```text
1
1
2
2
3
3
4
5
5
7
```

## Counting Sort
> 보통 퀵 정렬, 병합 정렬, 힙 정렬이 O(n log N) 으로 빠른 정렬에 속하지만
 카운터 정렬은 '범위 조건'이 있는 경우에 한해서 굉장히 빠른 알고리즘이다
 범위 조건이란 주어지는 수들의 크기가 특정 범위 안에서만 존재할 때를 말한다.
 O(n)의 시간 복잡도를 갖는다.
 데이터를 기준으로 갯수를 세는 알고리즘이다.
 데이터의 값가 1인거에 한해서 숫자를 세고 2인거에 한해서 숫자를 세는 알고리즘...
 지금까지의 모든 데이터들은 그 자체로 위치를 바꾸어가면서 정렬하는 알고리즘들이었지만
 카운팅 정렬은 데이터를 기준으로 갯수를 세주면 되서 위치를 바꿀 필요가 없다.
 또한 모든 데이터를 한번씩만 접근하면 되서 효율적인 알고리즘이다.
### 풀이 - 1

* 참고 - https://www.youtube.com/watch?v=n4kbFRn2z9M
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SortNumbers3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        int[] list = new int[n];


        int max = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            list[i] = num;

            if (max < num) {
                max = num;
            }
        }

        int[] count = new int[max];
        for (int i = 0; i < list.length; i++) {
            count[list[i]-1]++;
        }

        // 1부터 최대크기의 값까지 반복하면서 각 count 배열의 인덱스가 되서
        // count 배열의 인덱스의 데이터만큼 반복해서 값을 출력한다.
        for (int i = 0; i < count.length; i++) {
            if(count[i] != 0){
                for (int j = 0; j < count[i]; j++) {
                    sb.append(i+1).append('\n');
                }
            }

        }
        System.out.println(sb);
    }
}
```

### 풀이 - 2
* 참고 - https://itprogramming119.tistory.com/entry/07-%EA%B3%84%EC%88%98-%EC%A0%95%EB%A0%AC-Counting-Sort-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-1
```java
package baekJoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SortNumbers3 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] list = new int[n];
        int[] result = new int[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            list[i] = num;

            if (max < num) {
                max = num;
            }
        }

        int[] count = new int[max + 1];
        // 1.
        for (int i = 0; i < list.length; i++) {
            count[list[i]]++;
        }


        // 2.
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // 3.
        for (int i = list.length - 1; i >= 0; i--) {

            int value = list[i];
            count[value]--;
            result[count[value]] = value;
        }

        for (int i = 0; i < result.length; i++) {
            sb.append(result[i]).append('\n');
        }

        System.out.println(sb);
    }
}
```