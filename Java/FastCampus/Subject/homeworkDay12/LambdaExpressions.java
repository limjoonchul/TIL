package com.company;



import java.util.ArrayList;
import java.util.Collections;

/**
 * 람다식을 이용하여 알고리즘 정답을 테스트하는 프로그램을 작성하시오.
 *
 * 주어진 interface와 실행 메소드를 이용하여 알고리즘 정답을 테스트하는 프로그램을 작성하시오.
 * 이 테스트 방식을 이용하여 실제 알고리즘 문제를 하나 이상 풀이하시오.
 *
 */

// R자료형으로 반환 groundtrth와 같은 자료형
interface Solution<T, R> {
    R solve(T t);
}

class Algorithm<T, R> {
    boolean test(T input, R groundtruth, Solution<? super T, ? super R> solution) {
            if(solution.solve(input).getClass() == (groundtruth).getClass()){
                return true;
            }else return false;

    }
}
public class LambdaExpressions {

    public static void main(String[] args) {
        // test를 호출해서 값을 넣어주는 것 같음
        Algorithm<int[],int[]> algorithm = new Algorithm<>();

        int [] numbers = {5,0,2,7};

        int [] result = {2,5,7,9,12};

        System.out.println(algorithm.test(numbers,result,t->{
            int [] answer = {};
            ArrayList<Integer> list = new ArrayList<>();
            ArrayList<Integer> answerlist = new ArrayList<>();

            for (int i = 0; i < numbers.length; i++) {
                for (int j=i+1; j<numbers.length; j++){
                    list.add(numbers[i] + numbers[j]);
                }
            }

            for (int num : list){
                if(!answerlist.contains(num)){
                    answerlist.add(num);
                }
            }

            Collections.sort(answerlist);

            answer = new int[answerlist.size()];
            for (int i = 0; i < answerlist.size(); i++) {
                answer[i] = answerlist.get(i);
            }
            return answer;
            }));
    }
}
