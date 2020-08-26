package com.company.ch10.ClassEX;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class StringClassTest {
    public static void main(String[] args) throws ClassNotFoundException {

        Class c3 = Class.forName("java.lang.String");
        //동적로딩
        Constructor[] cons = c3.getConstructors();
        for(Constructor con : cons){
            System.out.println(con);
        }

        String str = new String();

        System.out.println();
        Method[] methods = c3.getMethods();
        for (Method method : methods){
            System.out.println(method);
        }



    }
}
