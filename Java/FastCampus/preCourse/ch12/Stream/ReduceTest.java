package com.company.ch12.Stream;

import java.util.Arrays;
import java.util.function.BinaryOperator;


class CompareString implements BinaryOperator<String>{

    @Override
    public String apply(String s, String s2) {
        if (s.getBytes().length >= s2.getBytes().length){
            return s;
        }else{
            return s2;
        }
    }
}
public class ReduceTest {
    public static void main(String[] args) {
        String[] greetings = {"안녕하세요---","hello","good morning","반갑습니다"};
        // indentity 애랑 비교를 하는 것이다 비교를 하는 기준?이 되는 것 같다. 각 자료형에 따라 다르게 줘야 하는 것 같다.
        String str1 = "안녕하세요---";
        String str2 = "good morning";
        System.out.println(str1.getBytes().length);
        System.out.println(str2.getBytes().length);
        System.out.println(str1.length());
        System.out.println(str2.length());
        // 한글은 한글자당 2바이트로 취급하기 때문에 바이트의 길이로 따졌을 때와 그냥 length로 했을 때 차이가 생긴다.

        //System.out.println(Arrays.stream(greetings).reduce("",(s1, s2)->s1.getBytes().length>s2.getBytes().length? s1:s2));
        System.out.println(Arrays.stream(greetings).reduce("",new CompareString()));
        // 이렇게 클래스를 만들어서 객체를 넣어도 된다!!


    }
}
