package com.company.s05.p12;

import java.util.function.BinaryOperator;

/**
 * BinaryOperator 인터페이스의 클래스 메소드
 * - minBy, maxBy : Comparator를 입력받아 min, max 출력
 */
public class Main {
    public static void main(String[] args) {
        BinaryOperator<String> minBy = BinaryOperator.minBy((o1,o2)-> o1.length() > o2.length() ? 1 : -1);
        BinaryOperator<String> maxBy = BinaryOperator.maxBy((o1,o2)-> o1.length() > o2.length() ? 1 : -1);
        // 엄밀히말하면 comparator가 아니다?? 5:45
//        BinaryOperator.minBy((String o1,String o2)-> o1.length()+o2.length()); 이것도 가능
        // 어떤걸 받아 줄건지 써줘야 한다.
        // 앞에 String타입을 넣어주면 뒤에서 o1,o2가 String 이라는 것을 추론할 수 있다.

        System.out.println(minBy.apply("abc","cd")); // 더 작은게 출력됨
        System.out.println(maxBy.apply("abc","cd")); // 더 큰게 출력됨
        System.out.println(minBy.apply("abc","cde")); // 5:48
        // 길이가 같을 경우에 조건이 랜덤에 의해서 근데 여긴 앞에꺼가 출력됨 ?



    }
}
