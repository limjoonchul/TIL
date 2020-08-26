package com.company.p04;
/**
 * 메소드 (Methods)
 * 객체가 하는 동작(행위)을 정의하는 작업을 수행하는 코드의 집합이자 나열
 * 코드의 중복을 방지, 유지보수성을 향상, 코드의 가독성을 개선하는 역할을 한다.
 */
class Foo{
    int value;
}
class Bar{
    // 인스턴스 메소드, 메소드 라고도 부름
    //return type(출력의 자료형)
    public  int add(int x, int y)// 여기까지는 선언 // 입력 파라미터.
    {
        return  x + y; // 반환값 (Return value)
    }
    // 선언(Declaration) - ~한 것이 있다. 실제 구현은 x
    // 정의(Definition) - 선언 + 구현(초기화)

    // 정적 메소드, 클래스 메소드 라고 부름.
    // void 리턴 타입은 아무 것도 반환하지 않는다.
    public static void classMethod(){
        System.out.println("클레스메소드 호출");
        //return 적어도 되고 안적어도됨.
    }

    public static void swapPrimitive(int x, int y){
        //int x, int y의 사본이 넘어온다. 외부와 전혀 상관없는 값이 된다. 밖에 있는 값에 영향을 주지 못한다.
        // 기본형 타입인 경우에 해당.
        // call by value : 메소드 호출을 할 때, 값을 복사해서 넘긴다.
        int temp = x;
        x = y;
        y = temp;
    }
    public static void swapReference(Foo x, Foo y){
        // Foo - > class, class는 참조형 변수
        // 참조형 변수이기 때문에 x,y에 참조가 넘어오게 된다.
        int temp = x.value;
        x.value = y.value;
        y.value = temp;
    }


    public static int sumAll(int...params){ // 여러개의 int를 입력 받는다 라는 의미.
        // 입력받은 결과는 배열로 주어진다. 배열로 넘어옴.(가변 인자 variable arguments)
        int sum = 0;
        for (int value : params){
            sum += value;
        }
        return sum;
    }

    // 메소드 오버로딩. 함수명은 같고, 입력 인자는 달라야 합니다.
    // 입력 인자의 개수도 다를 수 있다.
    public static float sumAll(float...params){ // 여러개의 int를 입력 받는다 라는 의미. 입력받은 결과는 배열로 주어진다. 배열로 넘어옴.
        float sum = 0;
        for (float value : params){
            sum += value;
        }
        return sum;
    }
}

class Person{
    static String korWord = "사람"; //바로 할당됨
    boolean isHungry = true; // 인스턴스 멤버 변수 객체를 만들 때 생성

    // 클래스 메소드  메소드라는 것은 변수와 다루는 것이 다름.
    public static  void describe(){ //클래스로 만들기 때문에 각각의 객체와 상관없음. 공통?
        System.out.println(korWord+"입니다."); //스태틱 변수는 사용할 수 있음 접근, 수정 가능.
        // 클래스 메소드는 클래스 변수를 사용할 수 있다.
        // 단, 객체에 속하는 속성은 사용할 수 없다.
    }
    //인스턴스 메소드
    public void eat(){
        isHungry = false;
    }
}

public class Methods {
    public  static  void classMethod(){
        System.out.println("클래스 메소드 호출");
    }
    public void instanceMethod(){
        System.out.println("인스턴스 메소드 호출");
    }
    public static void main(String[] args) {
        Bar.classMethod();
//        Bar.add(1,2); 콜이 안됨
        Bar bar = new Bar(); //인스턴스를 만들어서 인스턴스 메소드를 호출함.
        System.out.println(bar.add(1,2));
//        bar.classMethod(); 가능하나 권장하지 않는다.

        Person p1 = new Person(); // 변수에 new Person() 이 할당 되어있는 것, Person 이라는 자료형.
        Person p2 = new Person();

        p1.eat();
        System.out.println(p1.isHungry); //인스턴스 메소드는 객체의 속성을 변화시킨다. 반드시는 아니고 가능성이 있는 것
        System.out.println(p2.isHungry);

        // 클래스 메소드는 객체의 속성과 상관이 없다.
        Person.describe(); //클래스 메소드는 클래스에 대한 동작을하고 특정한 객체에 대해서 바꾸지 않음. 객체에 변하지 않음.

        int x = 10;
        int y = 20;
        Bar.swapPrimitive(x,y); // 메소드를 사용해서 하면 10,20이라는 값이 전달됨 변수가 전달되는게 아니라 사본이 넘어가는 것이다.
//        int temp = x; // 이렇게 하면 잘 뒤집어짐
//        x = y;
//        y = temp;
        System.out.println(x+ ","+y);

        Foo f1 = new Foo();
        Foo f2 = new Foo();

        f1.value = 10;
        f2.value = 20;
        Bar.swapReference(f1,f2);
        System.out.println(f1.value +","+f2.value);


        System.out.println(Bar.sumAll(2,5,2,10,6,-2));
        System.out.println(Bar.sumAll(2.2f,0.2f,0.2f));

        classMethod(); // 같은 클래스의 클래스메소드를 바로 호출 가능. 동일 클래스에 속한 클래스 메소드 호출.
        Methods.classMethod(); // 이렇게도 호출 가능.
        Methods m = new Methods();
        m.instanceMethod(); //인스턴스 메소드 호출. //퀴즈에 나올 문제. 중요***

    }
}
