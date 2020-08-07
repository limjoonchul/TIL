package com.company.p05;

/**
 * 생성자 (Constructor)
 * 클래스에서 인스턴스를 생성할 때 사용하는 메소드
 * new 키워드를 사용할 때 호출되는 메소드.
 *
 * 기본 생성자(Default constructor)
 * 파라미터 생성자(Parameter)
 * --> 여러개의 생성자를 오버로딩할 수 있음.
 */


public class Constructor {
    int x;
    int y;
    String z;

    public Constructor(){ // 기본 생성자, 구현하지 않아도 알아서 생긴다.
        this(0,0);
//        x = 1;
//        y = 2;
//        z = "초기화";
    }
//    private Constructor(){} //외부에서 사용할 수 없음 호출이 불가능한 생성자.


      // 파라미터 생성자
    public Constructor(int x, int y, String z){ //오버로딩
        this.x = x; // this는 멤버 변수를 표기하기 위해 사용될 수 있다.
        this.y = y;
        this.z = z;
    }

    public Constructor(int a, int b){
        this(a,b,""); // 자기 자신을 가르키는 애 위에 함수가 호출됨. this는 무조건 첫줄에만 쓰일 수 있다.(단한번) 규칙입니다.
//        x = a;
//        y = b;
//        z = "";
    }
}

class ConstructorTest{
    public static void main(String[] args) {
        Constructor c = new Constructor(); // 기본 생성자 호출
        System.out.println(c.x + "," + c.y + ","+ c.z);
        // z의 경우, string은 클래스이기 때문에 null로 초기화가 된다.
        // null -> 아무것도 참조하고 있지 않다.

        Constructor c1 = new Constructor(10,20,"파라미터생성자");
        System.out.println(c1.x+ "," + c1.y + ","+ c1.z);

        Constructor c2 = new Constructor(10,20);
        System.out.println(c2.x+ "," + c2.y + ","+ c2.z);
    }
}
