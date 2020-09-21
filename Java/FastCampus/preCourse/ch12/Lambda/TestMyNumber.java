package com.company.ch12.Lambda;

public class TestMyNumber {
    public static void main(String[] args) {
        MyMaxNumber max = (x,y)->(x>=y)? x: y; // {return (x>=y) ? x : y}
        System.out.println(max.getMaxNumber(10,20));
        // 위의 식 풀이
        // x,y 매개변수 두개가 넘어 올건데, MyMaxNumber 인터페이스의 메소드가 호출이 되면,
        // 함수이름은 쓰지않고 익명이고, 그래서 메소드가 하나여야 한다.
        // 두개의 변수가 넘어올때 더 큰 값을 반환해 줘라.
        IntComImpl intCom = new IntComImpl();
        System.out.println(intCom.Concat(10,20));

        StringConcat stringCon = new StringConImpl();
        stringCon.makeString("hello","world");

        // 인터페이스에서 바로 객체생성과동시에 구현할수있다. 함수가 변수에 대입되서 사용이 가능하다.
        StringConcat stringConcat = (s,v)-> {
            System.out.println(s+","+v);
            int x =300; int y =350;
            System.out.println(x);
            System.out.println(y);
        };
        stringConcat.makeString("good","boy");

        // 이걸 이해하는게 람다식을 이해기에 젤 좋은 것 같다~~!!!!
        StringConcat stringConcat2 = new StringConcat() {
            @Override
            public void makeString(String s1, String s2) {
                System.out.println(s1+","+s2);
            }
        };

        stringConcat2.makeString("good","girl");
    }
}
