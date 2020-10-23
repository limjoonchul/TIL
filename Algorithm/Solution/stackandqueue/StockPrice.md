# Algorithm - [주식가격](https://programmers.co.kr/learn/courses/30/lessons/42584)

## 문제 설명
* 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.
   
### 제한사항
* prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
* prices의 길이는 2 이상 100,000 이하입니다.

### 입출력 예
| prices | return |
| ------ | ------ |
| [1, 2, 3, 2, 3] |	[4, 3, 1, 1, 0] |

### 입출력 예 설명
* 1초 시점의 ₩1은 끝까지 가격이 떨어지지 않았습니다.
* 2초 시점의 ₩2은 끝까지 가격이 떨어지지 않았습니다.
* 3초 시점의 ₩3은 1초뒤에 가격이 떨어집니다. 따라서 1초간 가격이 떨어지지 않은 것으로 봅니다.
* 4초 시점의 ₩2은 1초간 가격이 떨어지지 않았습니다.
* 5초 시점의 ₩3은 0초간 가격이 떨어지지 않았습니다.

## 구현 코드
### 내가 구현한 코드
* 처음에 내가 구현했더 코드이다. 너무나 쉽게 구현했다고 생각이 들어서 다른 테스트 케이스를 넣어서 기대값들을 예상해서 출력을 해보면
기대했던 값들과 똑같이 나온다. 그런데 프로그래머스 채점을 돌리게 되면 맞는게 하나도 없다.. 완전히 잘 못 구현한 것 같다 
```java
package com.company.Programmers.stackandqueue;

class SolutionStockPrice {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length-1; i++) {
            if (prices[i] < prices[i+1]){
                answer[i] = prices.length - (i+1);
            }else{
                answer[i] = 1;
            }
        }

        return answer;

    }
}
public class StockPrice {
    public static void main(String[] args) {
        int[] prices = {1,2,3,2,3};
        System.out.println(Arrays.toString(new SolutionStockPrice().solution(prices)));
    }
}
```

### 다른 사람의 풀이
* 다른 사람들이 풀었던걸 보고, 다시 안보고 구현해 봤다. 이렇게 하니 모든 테스트를 통과했다.
```java
package com.company.Programmers.stackandqueue;

class SolutionStockPrice {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        int count=0;

        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                if (prices[i] <= prices[j]){
                    count++;
                }else{
                    count++;
                    break;
                }
            }

            answer[i] = count;
            count=0;
        }

        return answer;

    }
}
```