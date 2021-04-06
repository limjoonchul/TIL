# [N으로표현](https://programmers.co.kr/learn/courses/30/lessons/42895) 문제풀이

 * 어려운 문제임 DP 문제이다.
 * 수식을 만들어가면서 하면 힘들어 괄호를 이용해서 해야함, 경우의 수에 괄호가 포함이 되겠금
 * 우선, n이랑 number 가 같은 경우를 보면 5를가지고 5를 포현할 때 5가 하나 있으면됨?
 * 55는 두개의 5를 표현해서 붙이면 됨 10은 5+5로 표현 됨.
 * 55는 55빼고 5+5,5*5,5-5,5/5 4가 가능한데 여기서 5는 5를 한개만 사용하는걸로 볼 수 있음
 * (5*5)*5 는 5를 두개 사용한 것 * 5를 한개 사용한 것
 * 5*5를 25로 표현 해서 사용해도 되는 것이다
 * 5를 1개로 표현한거, set이 쭉있고 5를 두개로 표현한거 set이 쭉있고 그래서 3개 표현하는 것을 얻을 수 있음.
 * 숫자를 연달아 쓴 것 들이 먼저 들어간 후 1,2 밑에 set들을 사용함
 * s(1)...s(8)까지 계산하다가 number값이 나오면 리턴하면 됨 안나오면 -1을 리턴
 * 집합의 리스트를 만들어야 한다, 1개쓰는 집합 2개쓰는 집합..등등

```groovy
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class NSet {

    public int solution(int N, int number){
        int answer = 0;
        /**
         * Set도 인터페이스이다. 실제로 구현하는게
         * set중에 1부터 사용하기 위해서
         *
         * 두개씩 쪼개서 사칙연산을 사용하는 방법을 함
         *
         */
        List<Set<Integer>> sets = new ArrayList<>();

        Set<Integer> set = new HashSet<>();

        sets.add(set);

        set = new HashSet<>();
        set.add(N); // s1에 해당함 숫자한번만 반복하는게 들어감 dp의 초기화
        sets.add(set); //n을 한개만쓰는것

        for (int i=1; i <=8;i++){
            set = new HashSet<>();

            int num = N;
            for (int j = 1; j < i; j++){
                num *=10;
                num += N;
            } //N을 여러번 반복하는거 n이 3이면 30에다가 33이됨

            set.add(num);

            for (int j=1; j< i/2+1; j++){
                for (int x: sets.get(j)){ //저장된 순서와 상관없이 가져옴
                    for (int y : sets.get(i-j)){
                        set.add(x+y);
                        set.add(x*y);
                        set.add(x-y);
                        set.add(y-x);

                        if(y!=0){
                            set.add(x/y);
                        }
                        if(x!=0){
                            set.add(y/x);
                        }

                        if(set.contains(number)){
                            return i;
                        }
                    }
                }
                sets.add(set);
            }
        }
        return  -1;
    }


    public static void main(String[] args) {
        NSet nSet= new NSet();
        System.out.println(nSet.solution(5,12));
        System.out.println(nSet.solution(2,11));
    }
}
```

