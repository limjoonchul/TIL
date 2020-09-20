package com.company.ch12;
class OutClass{
    private int num = 10;
    private static int sNum = 20;
    private InClass inClass;

    public OutClass() {
        inClass = new InClass(); // 아우터클래스의 constructor를 생성할 때
        // 이너클래스도 같이 생성해준다.
    }

    class InClass{ // 인스턴스 내부 클래스
        int iNum = 100;
        //static int sInNum = 200;
        // 인스턴스 내부 클래스는 아우터클래스의 객체 생성이후에 생성이 되기때문에
        // static 변수,메소드를 만들 수 없다.

        void inTest(){
            System.out.println(num);
            // 아우터 클래스 안에 인스턴스 내부 클래스가 생성이 되는 것이기 때문애
            // 아우터클래스의 private 변수를 사용할 수 있다.
            System.out.println(sNum); // 아우터 클래스의 static 변수는 인스턴스 내부 클래스의
            // 메소드 호출전에 이미 생성되어 있으니깐 가능

        }
    }
    static class InStaticClass{
        // 아우터 클래스 내부에 구현을 하지만 아우터클래스의 객체가 생성이 된후에 생성이 되는 것은 아니다.
        int inNum = 100;
        static int sInNum = 200;
        void inTest(){
            System.out.println(inNum);
            System.out.println(sInNum);
            System.out.println(sNum);//아우터클래스의 정적변수
        }

        static void inStaticTest(){
//            System.out.println(inNum); 정적메소드안에서 인스턴스 멤버변수는 사용할 수 없다.
            System.out.println(sInNum);
            System.out.println(sNum);//아우터클래스의 정적변수
        }


    }

    public void usingInner(){
        inClass.inTest();
    }
}
public class InnerTest {
    public static void main(String[] args) {
        OutClass outClass = new OutClass();
        outClass.usingInner();

        OutClass.InClass myInClass = outClass.new InClass();
        myInClass.inTest();
        // 자바 파일에 가면 OuterClass$InClass.class 이런 형식으로 저장이 되어있다.
        // 외부에서 인스턴스 내부 클래스를 직접 객체 생성을 할 수 있는데
        // 그럴 때 위의 코드 처럼 아우터클래의 객체를 만든다음에 객체명.new 이너클래스() 형식으로 해야한다.

        // 잘 사용하지 않음 왜냐하면 인스턴스 이너 클래스는 아우터클래스의 내부에서 사용할려고 만든 것이기 때문에
        // 바깥 외부에서 사용할려고 만들려고 한다면 외부에서 따로 클래스를 만드는게 맞다.
        // 그래서 내부에서만 진짜 사용한다라고 하면 내부클래스에 private 을 써주는게 맞다.

        System.out.println();
         OutClass.InStaticClass sInClass = new OutClass.InStaticClass();
         // 클래스 내부 클래스의 객체 생성
        sInClass.inTest(); // 클래스 내부 클래스의 인스턴스 메소드 호출
        OutClass.InStaticClass.inStaticTest();// 클래스 내부 클래스의 정적 메소드 호출

    }
}
