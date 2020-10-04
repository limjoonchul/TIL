package com.company.ch12.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ArrayListStreamTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("one","two","three","four","five");
        // 컬렉션같은 경우 바로 stream을 쓰면 된다.
        Stream<String> stream = list.stream();
        stream.filter(s->s.length()>3).forEach(System.out::println);
        list.stream().filter(s->s.length()>3).map(s->s.length()).forEach(System.out::println);


    }
}
