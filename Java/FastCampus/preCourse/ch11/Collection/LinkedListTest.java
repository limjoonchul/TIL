package com.company.ch11.Collection;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> mylist = new LinkedList<>();
        mylist.add("A");
        mylist.add("B");
        mylist.add("C");
        System.out.println(mylist);

        mylist.add(1,"D");
        System.out.println(mylist);
        mylist.removeLast();
        System.out.println(mylist);

        for (int i=0; i<mylist.size(); i++){
            String s = mylist.get(i);
            System.out.println(s);
        }


        ArrayList<String> myarray = new ArrayList<>();
        myarray.add("A");
        myarray.add("B");
        myarray.add("C");
        myarray.add(1,"D");
        System.out.println(myarray);
    }
}
