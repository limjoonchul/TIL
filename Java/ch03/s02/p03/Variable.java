package com.company.p03;

/**
 * 클래스에 사용되는 변수들(variable)
 *  - 클래스 멤버 변수(static variable, class variable)
 *  - 인스턴스 멤버 변수(member variable, attribute...)
 *  - 로컬 변수(local variable)
 *  - 로컬 파라미터 변수 (local parameter variable)
 *                    (arguments)
 *   각각의 동작들을 암기하고 어떻게 사용되는지 실제사용방법을 보면서 이해하면됨.
 */

public class Variable {
    static int classVar; // 클래스 멤버 변수, 스태틱 변수(정적 변수) 바로 메모리에 올라감.
    int instanceVar; // 인스턴스 멤버 변수, 필드, 속성

    public void method(int paramVar){ //로컬 파라미터 변수
        System.out.println(paramVar);
        int localVar; //로컬 변수
//        System.out.println(localVar); //error 로컬변수는 초기화가 자동으로 안됨. **시험문제**
        localVar = 10;
        System.out.println(localVar); //이렇게 값을 대입해서 사용해야 한다.

        {
            localVar = 30;
            int localVar2 = 20;
        }
        System.out.println(localVar); //블록 내에서 수정한 것도 반영됨.
//        localVar2 = 40; // 접근 불가. 생명주기가 끝났다. Life-Cycle이 끝났다.
    }
}

class VariableTest{
    public static void main(String[] args) {
        System.out.println("클래스 변수");
        System.out.println(Variable.classVar); //0으로 초기화됨 (외워야 됨 어떤변수들은 초기화가 안이뤄지는 경우도 있음 **시험문제가능성**)
        // 클래스 변수는 클래스 이름으로 바로 접근 가능.
        Variable.classVar = 10; //클래스이름.변수명으로 접근 가능
        System.out.println(Variable.classVar);
        System.out.println("");

        System.out.println("인스턴스 멤버 변수");
        Variable var = new Variable(); // 인스턴스를 만들어야 실체가 있기 때문에
        System.out.println(var.instanceVar);  //0으로 초기화가 됨.외워야됨.
        var.instanceVar = 20;
        System.out.println(var.instanceVar);

        Variable var2 = new Variable();
        System.out.println(var2.instanceVar); //초기화 안됨 0으로 나옴.

//        System.out.println(var2.classVar); //클래스 변수에도 접근 가능 하지만 권장 x 언어에따라 아예 안되는 경우도 있음.
        // 클래스 변수는 클래스에 속하기 때문에 특정 객체에 속해서 출력하는 것은 권장하지 않음.
//        Variable.instanceVar 접근 불가
        System.out.println("로컬 변수");
        var.method(9);
    }
}
