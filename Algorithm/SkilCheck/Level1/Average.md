# SkillCheck Level1 [평균 구하기](https://programmers.co.kr/learn/courses/30/lessons/12944)

## 풀이
```java
package com.company.Programmers.skillchecklevel1;

class SolutionProblem1 {
    public double solution(int[] arr) {
        double answer = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        answer = (double)sum / arr.length;

        return answer;
    }
}

public class Problem1 {
    public static void main(String[] args) {
        int [] arr = {1,2,3,4};
        System.out.println(new SolutionProblem1().solution(arr));

    }
}
```