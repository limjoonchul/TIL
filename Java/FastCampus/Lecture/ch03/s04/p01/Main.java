package com.company.s04.p01;

/**
 * 상속 inhertience
 * 상속 : 어떤 클래스의 모든 멤버 변수 및 메소드를 계승하여, 새로운 클래스를 생성하는 것
 *
 * 부모클래스로부터 상속을 받은 자식클래스는 부모의 것을 다 가지고 자기의 것을 추가로 더 가지고 만들어짐.
 * 상속대상은 조상클래스, 부모클래스, 상위클래스, 슈퍼클래스 라고 한다.
 * 상속결과는 자손클래스, 자식클래스, 하위클래스, 서브클래스 라고 한다.
 *
 * 상속 관계를 흔히 'IS-A'관계라고 한다. 이즈 에이
 *
 */

class Person{
    String name;
    public void work(){
        System.out.println("일하기");
    }
    public void sleep(){
        System.out.println("잠자기");
    }
}

// person을 상속하는 자식 클래스 extends 키워드를 이용하여 상속
class Developer extends  Person{
    String mainLang;
    public  void  writeCode(){
        System.out.println("돈 받은 만큼 코딩하기");
    }
}

class Student extends  Person{
    String major;

    public void writeCode(){
        System.out.println("밤새 코딩하기");
    }

}


public class Main {
    public static void main(String[] args) {
        // 클래스를 상속하면, 모든 멤버 변수와 모든 메소드를 상속받는다. 그대로 가지고 옵니다.
        // 동일한 부모를 가진 여러 자식이 있을 수 있다 자식들은 추가적인 메소드들을 가지고 사용할 수 있다.
        // 부모의 메소드들도 사용할 수 있다.
        Developer dev = new Developer(); // Developer 이지만, person 이기도 하다.
                                         // developer 'IS-A' Person
        dev.name = "나개발";
        System.out.println(dev.name);
        dev.work();
        dev.sleep();

        dev.mainLang = "자바";
        dev.writeCode();

        Student s = new Student();// Student 이지만, Person 이기도 하다.
                                   // Student 'IS-A' Person
        s.major = "컴퓨터 공학";
        s.writeCode();
    }
}
