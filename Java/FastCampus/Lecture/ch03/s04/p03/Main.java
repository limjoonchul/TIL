package com.company.s04.p03;


/**
 * 메소드 재정의 method overriding
 * Override -> 덮어씌운다, 해킹해서 뭔가 달라지게 한다.
 * 기존의 있던걸 덮어씌워서 다른걸로 변형시키는 것
 * 다형성 (polymorphism)의 근간이 됩니다.
 *
 */

class Person{
    public void writeCode(){
        System.out.println("아무 코드나 일단 적어 보았다.");
    }
}
class Student extends Person{
    @Override // 이렇게 적어주는 것이 관례 필수는 아님(문법 오류는 아님)   @ - 에노테이션
    public void writeCode(){
        System.out.println("능숙하게 코드를 작성해 보았다.");
        System.out.println("답은 틀렸다.");
        System.out.println("하지만 무언가 또 배웠다.");
    }
}

class Developer extends Person{
    @Override // 다양하게 오버라이드 될 수 있음.
    public void writeCode(){
        System.out.println("코드 작성이 하기 싫어서 하지 않았다.");
    }
}

public class Main {
    public static void main(String[] args) {
        Student stud = new Student();
        stud.writeCode();

        Person person = new Person();
        person.writeCode();

        Developer dev= new Developer();
        dev.writeCode();


        // 화두정도만 던진다.맛보기
        //부모클래스 자료형으로 자식클래스를 받을 수 있다.
        Person p = (Person)dev; //dev을 person으로 캐스팅해서 넣음 사람이오긴하는데 개발자고일지 뭐가올지 몰라서 사람으로 선언을해줘서 사용함.
        // 껍데기는 공통된걸로 받고 내부를
        p.writeCode(); //결과 - 코드작성이 하기싫어서 하지 않았다. 부모클래스 자료형이지만 오버라이된 자식클래스 메소드가 동작.
        p = (Person)stud;
        p.writeCode();
        // 다형성의 구현 중 하나이다.

        Person [] people = new Person[] {new Developer(), new Student()};
        //원래 어레이는 똑같은 자료형만 가질 수 있는데 이런식으로 할 수 있다.
        for (Person person1 : people){
            person1.writeCode();
        }

        //부모클래스로 캐스팅이되면 자식클래스에만 있는 메소드는 실행이 안된다.

    }

}
