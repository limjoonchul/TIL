package com.company.s05.p01;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 람다식 lambda expression
 * jdk 1.8에서 추가된 함수형 프로그래밍 기법
 * 함수형 프로그래밍이란? ->  객체지향 프로그래밍과 다르게, 비즈니스 로직만 빠르게 구현하는 방식
 * (비즈니스로직이란 : 미션 크리티컬(Mission Critical한 부분 <= 돈이 되는 부분?
 * 프로그램을 작성하고 서비스로 구현을하고 서비스가 돌아가고 하는 것은 어떻게 보면
 * 돈을 버는 일이기 때문에 프로그래밍해서 돈을 못벌면 프로그래밍을 하는 이유가 없다
 * 그래서 프로그램을 작성하고 프로그래밍 실제로 의미가 잇으려면 비즈니스로직을 갖춰야하고
 * 실질적인 기능을 수행하는 부분, 사업적인 핵심적인 내용들
 * ex) 얼굴인식 서비스를만들 때 서비스의 사업의 본질에 가까운 것을 비즈니스로직
 * 미션크리티컬이라고 부른다, 원하는 동작만 빠르게 동작하도록 구현하는 것을 함수형프로그래밍이라고 부른다.
 *
 * 다시 람다식이라는 것은 객체지향 언어인 java에서 메소드를 함수처럼 사용하는 방식이다.
 * java에는 함수라는게 없다. 그런데 함수형 프로그래밍을 하려면 1급 함수라는 개념이 필요하다.
 * 이것이 가능하게 하는 것이 람다식이다.
 * - 클래스 with 메소드 = 함수로 가정(메소드가 있는 클래스를 함수로 가정한다 일단 이정도만 이해해)
 * - 1급 함수 : 자바에서 모든 것이 객체 또는 기초자료형 (프리미티브타입)으로 이루어져있다.
 * 그래서 자바에선 1급 객체로 클래스를 통해서 생성한 객체가 모든 것의 기준이 된다.
 * 우리가 메소드 콜을 할 때 method(Foo foo); 객체를 넘겨주서 객체를 활용한다
 * 1급 객체로서 함수 ? 객체가 주가 되기도 하지만 함수라는 것이 객체와 동일시 될 수 있다
 * 자바에서는 객체 안에 메소드가 있어서 메소드가 무언가를 수행하는 역할을 한다.
 * 함수라는게 있어서 객체에 딸려있지 않고 자체로서 역할을 하는 메소드와 거의 유사한
 * 함수라는 것이 잇을 수 있다.
 *
 * 그래서 1급 함수는 함수 콜을 하되, func(Func func) 함수를 입력으로 받아서
 * 내부에서 함수를 활용을 할 수 있다 메소드에서 객체를 입력받아서 그안에서 객체를 사용하듯이
 * 이렇게 함수를 입력받아 사용할 수 있다 함수를 객체처럼 입력을 받아서 활용을 하는 것이
 * 1급 함수의 개념이다.
 */




public class Main {
    public static void main(String[] args) {
        // 람다식이 사용되는 대표적인 예
        // 배열의 정렬

        String [] strings = {"fast","campus","java","bacend","choigo","best","people"};
        System.out.println(Arrays.toString(strings));
        Arrays.sort(strings);// 정렬할 수 있다. 사전순으로 Arrays로 정렬하는거 하나 배움
        System.out.println(Arrays.toString(strings));

        // 방법 1. Comparator 클래스를 만들고, 객체를 생성하여 전달하는 방식이 있다.
        class MyComparator implements Comparator<String> {


            // string을 비교하기 위한 메소드를 인터페이스가 가지고 있어서
            // 인터페이스를 구현하면 sort에 넘겨주면 사전순으로 되어있던걸
            // 추가로 객체를 생성해서 넣어주면 새로 만든 메소드가 정렬하는 방식을 다르게 할 수 있다.

            @Override
            public int compare(String o1, String o2) {
                return o1.substring(1).compareTo(o2.substring(1));
                // 둘다 짤라서 시작문자가 1번 두번째 인덱스자리를 서로 비교하는 것
                //commpareto는 comaprapble 인터페이스에있는것 을가져옴 이미 스트링에 정의되어있음
            }
        }
        //기준을 바꾸고 싶을 때 방법은 새로운 comparator를 넣어줘서
        // 객체를 정렬하는 방법을 할 수 있다.
        Arrays.sort(strings, new MyComparator() );
        System.out.println(Arrays.toString(strings));

        // 방법2. 익명 내부 클래스를 이용할 수 있다.
        // 상속하고 싶은 인터페이스든 클래스를 적어준다음에 블록을 열어서
        // 오버라이드해주면 작성할 수 있다.
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.substring(2).compareTo(o2.substring(2)); //필요한 내용은 이것이다.
            }
        });
        System.out.println(Arrays.toString(strings));

        // 방법 3. 람다식을 이용한 방법.(클래스없이 작성이 가능한 익명내부클래스를 한번 더 줄임)
        // 람다식을 이용하는게 가장 간단하고 하기 편함
        // sort를 보면 하나만 입력을 받으면 자체를 하게되고 두개를 입력받으면 comparator??
        //3:40
        // 두번째 파라미터는 comparator를 구현한 람다식이라는 것을 알고 있다.

        Arrays.sort(strings, (o1, o2) -> o1.substring(3).compareTo(o2.substring(3)));
        // 이게 비즈니스로직 동작했으면 하는 코드만 작성하는 것

        System.out.println(Arrays.toString(strings));

        // 방법 4. comparable을 이용한 방법
        class Hansol implements Comparable<Hansol>{
            //String 을 상속이 안되서 따로 컴포지션해서 사용했다?
            String value;

            public Hansol(String value) {
                this.value = value;
            }

            @Override
            public int compareTo(Hansol o) {
                return value.substring(1).compareTo(o.value.substring(1));
                // 자기자신이갖고있는 value랑 외부에서들어온 객체 value랑 비교를 함.
                //
            }
            @Override
            public String toString(){
                return value;
            }
        }                      //스트링이 됬다면 이런 부부느 안해도 됬다.
        Hansol[] hansols = {new Hansol("fast"),new Hansol("campus"),new Hansol("backend"),new Hansol("java"),new Hansol("choigo"),new Hansol("best"),new Hansol("people")};
        Arrays.sort(hansols);
        System.out.println(Arrays.toString(hansols));

        // 방법 4 if-Story ~String 상속이 가능했다면~
        // 밑에 코드만 구현했으면 됬는데 string은 final로 되어있어서 상속이 안됨
//        class Fansol extends String{
//            @Override
//            public int compareTo(String o){
//
//            }
//        }

    }
}
