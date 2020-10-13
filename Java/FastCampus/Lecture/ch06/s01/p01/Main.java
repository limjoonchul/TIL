package com.company.s01.p01;

/**
 * 디자인 패턴 (Design patterns)
 * - 자주 발생하는 문제를 쉽게 해결하기 위해 제시된 재사용 가능한 해결책
 *   - Don't reinvent the wheel 바퀴를 다시 발명하지 마라
 * - 이미해결된방식은 이미 방식대로해결하고 새롭게 맞딱드린 문제를 해결하자
 * - 소프트웨어 설계 문제를 쉽게 해결할 수 있도록 패턴화된 설계 방식
 * - 팀원들과의 소통을 위해 디자인 패턴 학습이 필요
 *    - 팀원들이 싱글톤으로 해결 해주세요 할 때 이 패턴에 대해서 알고 있어야 하겠지?
 *
 * - 어떤 상황에서 쓰는지 문맥 -> 문제가 정의가 되고, 이 상황에서 어떤 문제가 있는지 어떤 안좋은 점이 있는지 서술을하게되어있고 -> 어떤 해결방법이 있는지 서술하게 되어있다.
 *
 */

/**
 * SingleTon 싱글톤 패턴
 * - 단 하나의 객체만 존재하도록 하는 클래스
 * - 프로그램 전체의 '상태'를 정의하는 데에 많이 사용한다.
 * - 예를 들면 게임의 진행 상태, GUI 전체를 통솔할 때 사용.
 */

class Singleton{
    // Lazy 객체화 사용하는 이유
    // 싱글톤객체를 처음 생성하면 객체에 많은 내용을 담고 있을 때 느려지고 사용하지 않게 되면 낭비가 된다.
    // 그래서 만들지 않고 있다가 처음 사용할 때 만들어 사용하게 되는 것이다 필요없는 메모리 소모를 줄여준다.  로딩속도 개선, 메모리 낭비 방지
    private static Singleton instance;

    private Singleton(){ } // 다른곳에서 생성을 못하게 생성자를 private으로 해놓음
    public static Singleton getInstance(){
        if (instance == null){ // Lazy instanciation (객체화)
            instance = new Singleton();
        }
        return  instance;
    }
}


/**
 * 팩토리 패턴
 * - 구상 클래스 객체(추상 클래스를 구현한 것, Concrete class 추상의 반대의미 구상)를 전담하여 생성하는 클래스를 구현하는 패턴
 *
 */

public class Main {
}
