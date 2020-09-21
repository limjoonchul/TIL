package com.company.ch12.Lambda;

// 함수형 프로그래밍을 만드는데 메소드를 어딘가 선언을 해야하는데
// 선언하기 위해 인터페이스를 많이 사용
// 함수형 인터페이스(unctionalInterface)는 메소드를 2개 이상 선언할 수 없다.
// 여기서 매개변수가 똑같이 두개여서 어느 함수가 호출되는지 모른다
// 익명함수로 선언되기때문에
// 함수형 인터페이스라는 애노테이션이 없으면 상관 없는데 있으면 메소드 하나만 사용할 수 있다.
@FunctionalInterface
public interface MyMaxNumber {
    int getMaxNumber(int x, int y);
//    void add(int x, int y);
}

