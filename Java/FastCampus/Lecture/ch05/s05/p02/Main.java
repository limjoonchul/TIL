package com.company.s05.p02;

/**
 * 람다식을 사용하기 위한 조건
 */

// 아까는 comparator 인터페이스를 구현햇는데 이번엔 직접 작성
// 단 하나의 추상 메소드를 가지고 있다.
@FunctionalInterface // 필수는 아니다. 하지만 적어주면 interface가 적합한지 확인해준다.
interface Runner<T>{
    T run(); // 추상메소드는 단 하나만 있어야 한다. runner라는 객체를 넣어줫을때
    // run()을 실행할 때 우리가 값을 하나만 넣어서 보낼때 하나만잇어야 밑에 문장이 run()라고 확신 할수있다.
    // T runTwo() // 추상메소드가 2개 이상이면 오류 발생
    default void method(){} // 디폴트메소드가 구현이 되어 있더라도 상관이 없다.
}


public class Main {
    // 4:20                  // ? 라고 적어주면 어떤 것이든 상관이 없다.
    static void useRunner(Runner<?> runner){
        System.out.println(runner.run());
    }

    public static void main(String[] args) {
                                  //? 랑 같은 타입이여야 하는데 ?로써져문 머가 들어오든 상관 없음
//        class MyRun implements Runner<String>{
//
//            @Override
//            public String run() {
//                return null;
//            }
//        }
//        useRunner(new MyRun());
        useRunner(() -> "This is how to use runner.");
    }
}
