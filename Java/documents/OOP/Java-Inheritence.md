# Java 2-3
# 상속(Inheritence)
## 상속이란
* 어떤 클래스의 모든 멤버 변수 및 메소드를 계승하여, 새로운 클래스를 생성하는 것
* 부모클래스로부터 상속을 받은 자식클래스는 부모의 것을 다 가지고 자기의 것을 추가로 더 가지고 만들어짐.
* 상속대상은 조상클래스, 부모클래스, 상위클래스, 슈퍼클래스 라고 한다.
* 상속결과는 자손클래스, 자식클래스, 하위클래스, 서브클래스 라고 한다.
* 상속 관계를 흔히 'IS-A'관계라고 한다.

## 클래스의 관계
### 클래스의 상속
* 부모 클래스
````java
class Person{
    String name;
    public void work(){
        System.out.println("일하기");
    }
    public void sleep(){
        System.out.println("잠자기");
    }
}
````
* 자식 클래스
````java
// person을 상속하는 자식 클래스 extends 키워드를 이용하여 상속
class Developer extends  Person{
    String mainLang;
    public  void  writeCode(){
        System.out.println("돈 받은 만큼 코딩하기");
    }
}
````
````java
class Student extends  Person{
    String major;

    public void writeCode(){
        System.out.println("밤새 코딩하기");
    }
}
````
## 클래스의 포함 
* 관계 클래스를 조합해서 클래스를 만든다.
* 상속하고 유사하지만, 한 클래스가 다른 클래스의 객체를 포함하는 관계
* 내부에 포함하고 'HAS-A' 관계로 표현된다.
* 특별히 자바에선 'HAS-A' 관계가 포함된 경우는 많이 없음, 알게모르게 중
* 클래스 컴포지션 (Composition)이라 부른다.
````java
// 메인머신 'HAS-A' String 이미 스트링을 컴포지션하고 있었다.
class MainMachine{
    String model;
    boolean isBroken = false;

    public MainMachine(String model){
        this.model = model;
    }
}
````
````groovy
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

MainMachine mac = new MainMachine("MacBook Pro");

    Developer dev = new Developer("나개발",mac); // 메인머신을
//  dev.mainMachine.model 가능
//  dev.model 불가능.
//  일치하냐 보유하냐의 차이 (상속과 컴포지션의 차이)      
    for (int i =0; i<10; i++){
       dev.writeCode();
    }
````

## 메소드 재정의
### 메소드 재정의(Method Overriding)란?
* Override -> 덮어씌운다, 해킹해서 뭔가 달라지게 한다.
* 기존의 있던걸 덮어씌워서 다른걸로 변형시키는 것
* 다형성 (polymorphism)의 근간이 됩니다.
````java
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
````
### super 키워드
 * this 가 자신의 객체를 참조하듯, super 는 부모 객체를 참조한다.
 * 다만 super.super 라는 식으로 부모의 부모는 참조할 수 없다. 바로 윗 부모만 가능.
 * 자식 객체를 생성을 하면, 부모 객체를 먼저 생성하고, 그 다음에 자식 객체를 생성하게 된다.
 * 자식 객체를 생성할 때마다 부모 객체를 따로따로 만들어서 가지고 있음.
 * 호출할 때 this와 마찬가지로 첫줄에 써야한다.
 * 멤버 변수명이 부모와 겹치면 재정의 하지만, 부모가 사라지는 것은 아니다.
 * 부모클래스에 기본 생성자를 사용하는 경우에는 호출 안해줘도 됨. 파라미터 생성자일 때만
   호출해주면 된다.
 * 부모클래스 생성자와 다르게 파라미터 변수명을 해줘야 한다.
 * 자식클래스 생성자를 만들 때 부모 클래스 생성자도 같이 호출해줘야한다.

````java
class Foo{
    String x = "Foo";

    public Foo(String x) {
        this.x = x;
    }
}
class Bar extends  Foo{
    String x = "Bar"; //멤버 변수명이 부모와 겹치면 재정의 하지만 부모가 사라지는 것은 아님.

    //부모클래스에 기본 생성자를 사용하는 경우에는 호출 안해주어도 된다.
    //부모클래스에 파라미터 생성자가 있으면 호출해 줘야 한다. super 사용.
    public Bar(String x, String x1) { //부모클래스 생성자와 다르게 파라미터 변수명을 다르게 해줘야 한다. x,x1
        super(x); //this와 마찬가지로 첫줄에 써야한다. 부모클래스 생성자 호출.
        //부모 객체를 먼저 생성하고, 그 다음에 자식 객체를 생성.
        this.x = x1; //자식클래스 생성자를 만들때 부모클래스 생성자도 같이 호출해줘야한다.
    }

    public void method(){
        //로컬변수명과 멤버변수명은 같을 수 있다.
//        String x = "method"; 로컬변수가 없어지면 멤버변수를 참조, 멤버변수도 없어지면 super클래스의 변수사용.
        System.out.println(x);//로컬변수 -> 멤버변수 -> 부모의 멤버변수 순으로 접근
        System.out.println(this.x); // 자기 자신의 객체에 접근가능 멤버변수 - > 부모의 멤버변수
        System.out.println(super.x); //부모 객체에 접근 가능    부모의 멤버변수

    }
}
````