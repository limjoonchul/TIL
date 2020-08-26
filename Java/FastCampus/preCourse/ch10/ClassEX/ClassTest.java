package com.company.ch10.ClassEX;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ClassTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {

        Person person = new Person("James");
        System.out.println(person);
        //person이 로컬에 있는 경우.


        Class c1 = Class.forName("ClassEx.Person");
        Person person1 = (Person) c1.newInstance();
        // newInstance() 반환하면 object

        Class[] parameterTypes = {String.class};
       Constructor cons = c1.getConstructor(parameterTypes);




    }
}
