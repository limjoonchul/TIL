package com.company;

/**
 * 소프트웨어 디자인 패턴에서 싱글턴 패턴(Singleton pattern)을 따르는 클래스는,
 * 생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나이고
 * 최초 생성 이후에 호출된 생성자는 최초의 생성자가 생성한 객체를 리턴한다.
 * 이와 같은 디자인 유형을 싱글턴 패턴이라고 한다.
 */
public class SingletonPattern {
    private static SingletonPattern instance = new SingletonPattern();
    private SingletonPattern(){};

    public static SingletonPattern getInstance(){
        return instance;
    }

}
class SingletonPatternTest{
    public static void main(String[] args) {
        SingletonPattern instanceOne = SingletonPattern.getInstance();
        SingletonPattern instanceTwo = SingletonPattern.getInstance();

        System.out.println(instanceOne == instanceTwo); // should be true
    }
}