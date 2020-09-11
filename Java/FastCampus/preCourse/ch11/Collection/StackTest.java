package com.company.ch11.Collection;

import java.util.ArrayList;

class MyStack {
    private ArrayList<String> arrayStack = new ArrayList<>();

    public void push(String data){
        arrayStack.add(data);
    }

    public String pop(){
        int len = arrayStack.size();
        if (len == 0){
            System.out.println("스택이 비었습니다");
            return null;
        }
       return arrayStack.remove(len-1);
    }
}

public class  StackTest{

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push("A");
        myStack.push("B");
        myStack.push("C");

        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());

    }
}
