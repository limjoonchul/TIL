package com.company.s04.p02;

/**
 * 클래스의 포함 관계 클래스를 조합해서 클래스를 만든다.  Class Composition
 * 상속하고 유사하지만, 한 클래스가 다른 클래스의 객체를 포함하는 관계
 * 내부에 포함하고 'HAS-A' 관계로 표현된다. 특별히 자바에선 헤즈에이 관계가 포함된 경우는 많이 없다 알게모르게 사용되고는 있다.
 */

// 메인머신 'HAS-A' String 이미 스트링을 컴포지션하고 있었다.
class MainMachine{
    String model;
    boolean isBroken = false;

    public MainMachine(String model){
        this.model = model;
    }
}

// developer 'HAS-A; MainMachine 개발자클래스가 메인머신의 객체 하나를 보유한다.
// 보유하고 있어서 메인커신의 속성을 건드릴 수 있다.
class Developer{
    String name;
    MainMachine mainMachine;

    public Developer(String name,MainMachine machine){
        this.name = name;
        this. mainMachine = machine;
    }

    public void writeCode(){
        if(mainMachine.isBroken) {
            System.out.println("코딩할 수 없습니다.");
        }else{
            System.out.println(mainMachine.model+ "으로 코딩하기");
        }

        if(Math.random() > 0.9){
            breakMachine();
        }
    }

    public void breakMachine(){
        mainMachine.isBroken = true;
    }
}

public class Main {
    public static void main(String[] args) {
        MainMachine mac = new MainMachine("MacBook Pro");

        Developer dev = new Developer("나개발",mac); // 메인머신을
//        dev.mainMachine.model 가능
//        dev.model 불가능.
//        일치하냐 보유하냐의 차이 (상속과 컴포지션의 차이)
        for (int i =0; i<10; i++){
            dev.writeCode();
        }
    }
}
