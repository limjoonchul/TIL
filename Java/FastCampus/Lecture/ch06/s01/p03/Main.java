package com.company.s01.p03;

import java.util.ArrayList;
import java.util.List;


/**
 * 옵저버 패턴 (Observer pattern)
 * - Observable 객체의 변화를 Observer에서 알 수 있도록 하는 패턴
 * - GUI에 많이 사용되는 패턴이다.
 * - 게임에도 많이 쓰임.
 * - 즉, 보통 Hierarchy가 있는 경우 (계층이 있는 경우) 많이 사용된다.
 * 필요할 때만 랜더링하는게 기본이고 변화가 생겼을 때만 한다. 캐릭터에 변화가생겼을 때 이게 타고올라가서 게임에서 감지를 하고 변화를 적용시킨다.
 * 틱택토에서 attach할 때 상호 레퍼런스를 할 때 확인해봐야함 꼭 상호 레퍼런스가 아닐 수 있음 그때마다 다름.
 */
// extends Observable 이있지만 deprecated 이다
class Subject{ // 관찰 대상인 클래스
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState(){
        return state;
    }

    public void setState(int state){
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        //변화가생기면 옵저버한테 알려준다.
        for (Observer observer : observers){
            observer.update();
        }
    }
}

abstract class Observer{
    protected Subject subject;
    public abstract void update();
}

class BinaryObserver extends Observer{
    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this); //상호 포함이 된다?? 이해해보자
    }

    @Override
    public void update() {
        int state = this.subject.getState();
        System.out.println(Integer.toString(state));
    }
}


class OctalObserver extends Observer{
    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this); //상호 포함이 된다?? 이해해보자
    }

    @Override
    public void update() {
        int state = this.subject.getState();
        System.out.println(Integer.toOctalString(state));
    }
}

class HexaObserver extends Observer{
    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this); //상호 포함이 된다?? 이해해보자
    }

    @Override
    public void update() {
        int state = this.subject.getState();
        System.out.println(Integer.toHexString(state));
    }
}

public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer observerOne = new HexaObserver(subject);
        // 상호 등록이 된다.
        Observer observerTwo = new BinaryObserver(subject);
        Observer observerThree = new OctalObserver(subject);

        subject.setState(10); //옵저버들이 서브젝트의 변화를 감지한다.
        subject.setState(20);
        subject.setState(40);

        // 16진수 2진수 8진수로 변환해서 출력된다.
    }
}
