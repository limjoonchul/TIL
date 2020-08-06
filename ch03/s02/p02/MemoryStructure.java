package com.company.p02;

/**
 * 클래스와 객체의 메모리 구조
 *  - 클래스 영역(class area, method area, code area, static area) 클래스의 공통된 부분이 들어감 어떤 필드가 있는지 어떤 타입을 갖는다던지
 *  어떤메소드가 있고 어떤동작을 하는 코드라던지. 프로그램이 돌아가면 계속 존재함.
 *   -> field 정보, method 정보, type 정보, constant pool
 *  - 스택 영역 메소드콜이 이뤄질때 메소드에 의해 이뤄지는 임시적인 영역
 *   -> method 호출 시 선언된 로컬 변수(호출시 임시로 있는 공간 (사라짐))
 *  - 힙 영역 new키워드로 발생함.
 *   -> new 키워드로 생성된 객체
 *   -> garbage collection이 동작하는 영역 : 더 이상 사용하지 않는 메모리를 알아서 반환하는  jvm의 기능을 의미한다.(GC)
 *
 */
public class MemoryStructure { // 클래스 영역
    int x, y; // 힙 영역 객체에 속하기 때문에 객체를 콜할 때는 힙 영역에 들어감. 객체에 속하는 속성들이므로.
    // 클래스가 아니기 때문에 값 자체가 담긴다.(32비트 값을 잡아서 쓰여져있음)

    String string = "String!!"; // 힙영역, 상수풀에도 생성 애는 상수풀에 생성됨. 클래스를 만드는 클래스는 힙영역에있고 내용은 상수풀에 들어감.

    public void method(int value){ // 클래스 영역에 만들어 짐 (메소드의 정의)
        // int value = > 스택 영역
        char c = 'w'; // 스택 영역
    }
    //대부분의 주소값은 64비트 운영체제가 대부분 64비트이기때문에 거기에 맞춰지는 것.

}
