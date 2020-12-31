# SkillCheck Level1 [k번째 수](https://programmers.co.kr/learn/courses/30/lessons/42748)

## 풀이
```java
package com.company.Programmers.skillchecklevel1;

import java.util.ArrayList;
import java.util.Arrays;

class SolutionProblem2 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = {};

        ArrayList<Integer> tempList = new ArrayList<>();
        ArrayList<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < commands.length; i++) {
            tempList.clear();
            for (int j = (commands[i][0])-1; j < commands[i][1]; j++) {
                System.out.println(array[j]);

                tempList.add(array[j]);
            }

            tempList.sort(Integer::compareTo);

            answerList.add(tempList.get(commands[i][2]-1));
        }

        answer = new int[answerList.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}

public class Problem2 {
    public static void main(String[] args) {
        int[] array = {1,5,2,6,3,7,4};
        int[][] commands = {{2,5,3}, {4,4,1}, {1,7,3}};
        System.out.println(Arrays.toString(new SolutionProblem2().solution(array, commands)));
    }
}
```
