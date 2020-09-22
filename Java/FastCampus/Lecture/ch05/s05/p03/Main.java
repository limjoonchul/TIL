package com.company.s05.p03;

/**
 * 다양한 람다식의 표현 형식
 */


@FunctionalInterface
interface Runner{
    String run(String x);

}

@FunctionalInterface
interface RunnerTwo{
    String run();

}


public class Main {

    static void useRunner(String x, Runner runner){
        System.out.println(runner.run(x));
    }

    static void useRunnerTwo(RunnerTwo runner){
        System.out.println(runner.run());
    }

    public static void main(String[] args) {
         // 람다식의 표준 형식
        useRunner("안녕하세요!",(String x) -> {return x;}); //입력 파라미터의 자료형 입력 (String x) = Runner run {run(String x)} , {return x;} = run(String x)??{ return x;}
        //
        useRunner("안녕하세요!", x -> {return x;});// 입력파라미터가 1개면 () 생략 가능
        useRunnerTwo(() -> {return "안녕";}); //  입력 파라미터가 없으면 () 생략 불가
        useRunner("안녕하세요!",(x)->{
            return x; // 세미콜론이 들어가는 경우(여러 줄 작성할 때) 중괄호 필수이다. 이 대 return도 필요하다.
        });
        useRunner("안녕",x->x); // Expression을 바로 쓰면 알아서 return을 해준다.(expression labmda)
        // 한줄로만처리되는 expression이라면 세미콜런없이 처리가능.

    }
}
