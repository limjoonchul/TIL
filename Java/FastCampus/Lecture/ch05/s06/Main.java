package com.company.s06;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Object 클래스
        // 모든 클래스의 조상 클래스 - 클래스의 기본 기능을 제공
        // 필요한 경우에 Object 클래스에 구현된 메소드를 override 해서 사용하고 있다. 대표적인게 equals() 메소드
        Object obj = new Object();
        Object obj1 = obj;
        Object obj2 = new Object();
        System.out.println(obj.getClass()); // Class 클래스 객체를 반환하게 된다.

        // equals() 메소드
        System.out.println(obj.equals(obj1)); // 동일한 객체를 가리키는지 여부 true 같은 레퍼런스를 가리키는지 여부를 확인함
        System.out.println(obj.equals(obj2)); // 동일한 객체를 가리키는지 여부 false

        System.out.println();
        String str1 = "abc"; // 힙안에 있는 스트링 풀에 들어감
        String str2 = str1;
        String str3 = "abc"; //이미 들어가있는 스트링풀을 가리킨다. 그래서 동일한 객체가 된다.
        String str4 = new String("abc"); // new로 만들경우는 새로운 객체를 명시적으로 생성해줬기 때문에 다르다

        System.out.println(str1== str2); // 동일한 객체인지를 비교한다. true
        System.out.println(str1== str3); // true
        System.out.println(str1== str4); //false
        System.out.println();

        // String의 경우 내용까지 (효울적으로) 비교하게끔 Override 를 하고 있다.
        System.out.println(str1.equals(str2));// true String의 equals는 안에 내용만 같아도 true를 반환한다는 특징이 있다.
        System.out.println(str1.equals(str3));// true
        System.out.println(str1.equals(str4));// true

        // hashCode() 메소드
        // - 객체를 구분하기 위해서 사용하는 정수값(int를)을 반환
        // - 각 객체마다 프로그램 내에서 유일하기 때문에 주소값처럼 사용 가능하다.(실제 주소값은 아니다)
        // - hashCode()는 native이기 때문에 정의를 볼 수 없다.
        //           - native :  c 또는 c++등 외부 언어로 작성된 메소드 속도 최적화를 위해서 자바로 구현되어 있지 않다! jvm에 종속되어있어야하고 그런문제가 있어서
        // - hashCode()를 Override 할 때 제약사항 (3가지 조건)
        //   - 한 객체의 hashCode()를 여러번 호출할 경우,
        //     해당 객체의 equals() 내부에서 사용이되는 값이 변하지 않았다면
        //     hashCode()의 반환 값은 동일해야 한다.(필수)  //어떤 객체가 있을 대 해쉬가 변하지 않았다면 동일한 객체여야하는거고 해쉬상으로 유지가 되어야 한다?
        //
        //   - equals() 메소드가 같다고 판단한 두 객체의 hashCode() 값은 항상 같아야 한다.(필수)
        //   -  equals() 메소드에서 다르다고 판단하는 두 객체는 항상 hashCode()값이 다를 필요는 없으나,
        //               다르면 Hash기반 자료구조의 성능이 향상된다. (권고사항) 가능하면 다른게 좋다.
        // -> 즉, 같으면 hashCode()는 반드시 같고, 다르다고  hashCode()가 반드시 다른 것은 아니다. 다를수도 있고 같을 수도 있다.
        class Foo{

        }
        Foo foo = new Foo();
        System.out.println(foo.hashCode());
        // 일반적으로 오브젝트로 구현된 해쉬코드를 그대로 쓰는 것이 좋다.
        // 왠만헤선 해쉬코드를 건들지 않는게 좋다. 해쉬코드가 겹치는걸 막는게 쉽지 않다 그래서 그대로 사용하는게 무난하다.

        // getClass() : 클래스 정보를 담고 있는 class 객체를 반환.
        class Bar{
            public void methodA(){
                System.out.println("method A  is called");
            }
        }

        // Class 클래스,Method 클래스의 활용
        // Reflection API를 이용한다.
        Bar bar = new Bar();
        Class barClass = bar.getClass();

        System.out.println(barClass.getName());//s06.p01.Main$1Bar 내부클래스를 표현 그래서 내부클래스는 앞에 숫자로 구분을 해준다.
        System.out.println(barClass.getDeclaredMethods()); //[Ljava.lang.reflect.Method;@74a14482 특정메소드를 가리키고 있는게 출력된다.
//        barClass.getDeclaredMethod("methodA").invoke(); //특정 메소드를 지칭할 수 있다 받아올 수 잇다. invoke 는 메소드를 호출하는 것// 지금은 클래스에서 메소드에 접근한 상태이다

        barClass.getDeclaredMethod("methodA").invoke(bar); //그래서 객체를 넣어줘야한다.


        // System 클래스
        // OS와 interact하기 위한 클래스
        // 정적 변수,메소드만으로 구성된 클래스

        //System.in System.out
        System.out.println(System.out);// PrintStream 이라는 객체   의 클래스
        System.out.println(System.err);// PrintStream 이라는 객체   의 클래스
        System.out.println(System.in);// inputStream 이라는 객체 의 클래스 스트림 api랑은 다른 것이다!!!
        // Stream API와는 다른,  I/O Stream이다 전혀 연관이 없다!!!!!

        System.out.println("f"); // 표준 출력을 처리하기 위한 객체
        System.err.println("w"); // 요류를(표준 출력장치에) 출력하기 위한 객체        같은 객체여서 메소드가 같은게 있다.
        Scanner sc = new Scanner(System.in); // 표준 입력장치를 사용하기 위한 객체, 스캐너가 인풋스트림을 입력받게 되어있다.


        // arraycopy() - 이것도 native로 구현되어 있다. 시스템에 구현되어있는게 native 여서 이다. os적으로 최적화가 들어가 있다 그래서 for문쓰는것보다 효율적이다.
        int[] ints = {1,2,3,4};
//        IntStream intStream = IntStream.range(1,5);
        int[] ints1 = new int[10];
        System.arraycopy(ints,0,ints1,0,ints.length);

        // currentTimeMills(), nanoTime() 시간에 관련된 것들
        System.out.println(System.currentTimeMillis()); //어떤 기준으로 밀리초마다 카운터하는게 있다
        System.out.println(System.currentTimeMillis()); // 두번연속 출력해도 차이가없다
        System.out.println(System.nanoTime()); // 어떤 기준으로 나노초마다 카운터하는게 있다. 0으로끝나있어서 1나노까지 정밀하게 구현되어있지 않다. 리얼타임오에스(RTOS)가 아니기대문에 정확하지 않을 수 있다.
        System.out.println(System.nanoTime());// 두번연속하면 차이가 생긴다.


        System.exit(0);// 프로그램 강제 종료
        // status = 0 : 정상 종료를 의미 status!=0 : 비정상 종료(1을 많이씀)
        // Process finished with exit code 0 런타임했을 때 맨마지막에 나왔었던 것이다.

        System.gc(); // Garbage Collection  우리가 직접 실행해줄 필요는 없다. 다만 실행해주면 좀 더 원하는 타이밍에 빨리 동작하게 할 수 있다.

        System.out.println(System.getenv("JAVA_HOME")); // 내 피시에 있는 환경 변수들을 가져온다. 원하는 환경변수들을 가져올 수도 있다.
        // System이 os와 통신을 하는 것이기 때문에 가능하다.
        System.out.println(System.getProperties()); // 프로퍼티를 가져옴
        System.out.println(System.getProperty("user.country")); // 위치정보가 나온다 한국사람이다 영문인지 한국어인지 결정할 수 있다
        System.out.println(System.getProperty("java.io.tmpdir"));// 디렉토리를 나오게함
        System.out.println(System.getProperty("line.separator")); // window \r\n, unix \n
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("file.separator"));// window \ unix /

    }
}
