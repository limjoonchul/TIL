package com.company.ch06.array;

import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        // 총갯수는 size로 꺼낼수 있는데 배열은 length로 꺼낼 수 있는데
        // size와 length의 차이점
        // 배열은 길이가 10개고 요소가 3개만 들어있어도 length는 10이지만,
        // size는 요소가 들어가 있는 개수만큼이다.
        for (int i =0; i<list.size(); i++){
            System.out.println(list.get(i));
        }

        for (String s : list){
            System.out.println(s);
        }
    }
}
