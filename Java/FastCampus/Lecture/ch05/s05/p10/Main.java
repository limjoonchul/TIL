package com.company.s05.p10;

import java.util.function.*;

/**
 * andThen 메소드, compose 메소드
 *
 * - andThen 메소드 :A.andThen(B) - >A부터 실행하고 B를 실행
 *   - Consumer 계열, Function, operator 계열의 default method로 구현
 * - compose 메소드 :A.compose(B) - > B부터 실행하고 A를 실행
 *   - Function, operator 계열의 default method로 구현
 */
public class Main {
    public static void main(String[] args) {
        // 정정 Consumer는 andThen만 있고 ,compose는 없다.
        Consumer<String> c0 = s -> System.out.println("c0 :" + s);
        Consumer<String> c1 = s -> System.out.println("c1 :" + s);
        Consumer<String> c2 = c0.andThen(c1);
        c2.accept("String"); //동일한 스트링을 출력한다.

        // Function 계열은 입력->출력 ==> 입력-> 출력 타입이 연쇄 되어야 한다.
        Function<String, Integer> func1 = s -> s.length();
        Function<Integer, Long> func2 = value -> (long)value;
        // func1의 입력이 string, 출력이 ineger이니깐 andThen의 입력은 integer여야 한다.
        Function<String,Long> func3 = func1.andThen(func2);
        System.out.println(func3.apply("four"));

        Function<String, Long> func4 = func2.compose(func1);
        System.out.println(func4.apply("four")); //?? 이해해야한다.





        ///////// 이 부분 다시 정리해서 업로드하자!!!!!!!!!!!!!!!!!!!!

        // 특이사항 있으면 내일 다시 언급
        BinaryOperator<String> op0 = (s1,s2) -> s1 + s2;
//      //BinaryOperator<String> op1 = (s1,s2) -> s1 + s2;
        // op0이 출력이 1개인데 op1은 입력값을 두개 받으니깐 안된다
        UnaryOperator<String> func0 = (s1) ->s1;
        UnaryOperator<String> op1 = (s1) -> s1+".";
        BiFunction<String,String, String> op2 = op0.andThen(op1); //5:20
        // bifunction왜쓰는지 이해해야한다. 중간과정에서 어느게들어있는지 antTehen이 뭔지 모른다
        // 중간에 Funthion계ㅒ열이 들어가 있을 수 있다.
        // s,s -> s 중간에 integer로바꿀수 있다 그런다음 -> i 일 수 있다
        // 그래서 전체가 OPERATOR 계열인지 확인 할 수 없다.
        // 그래서 BIfUNTION이여야 한다. operator로 조합했지만 bifunction으로 받아줘야한다.
        // Operator들을 섞어 쓰더라도... 중간에 Funtion 계열이 있을 수도 있기 때문에
        // 최종 조합 결과는 Funtion 계열로 받아 주어야 한다.
        // 이번 과제를 하면서 이해를 해보세요 쉐에에엣
        Function<String,String> op3 = op1.compose(func0); //입출력을 중요하게 생각해서 봐야할 듯!!

    }
}
