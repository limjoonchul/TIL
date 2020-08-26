package com.company.ch13.ThrowsException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ThrowsException {
    public Class loadClass(String fileName, String className) throws FileNotFoundException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(fileName);
        Class c = Class.forName(className);
        return c;
    }
    public static void main(String[] args) {
        ThrowsException te = new ThrowsException();
        // 두개의 예외가 함께 넘어왔을 때 먼저 선언된 예외가 예외처리가 됨. 뒤에껀 예외처리 출력이 안됨.
//        try {
//            te.loadClass("b.txt","java.lang.string");
//
//        } catch (FileNotFoundException e) {
//            System.out.println(e);
//        } catch (ClassNotFoundException e) {
//            System.out.println(e);
//        }


        // 한 구문에서 같이 예외처리를 할 수 있음. 근데 해보니 앞에 해당하는 오류만 출력이됨... 둘다 나오게 하는 방법은 없나

//        try {
//            te.loadClass("b.txt","java.lang.string");
//        } catch (ClassNotFoundException| FileNotFoundException e) {
//            e.printStackTrace();
//        }

        // 모든 예외를 포함하고 있는 클래스 Exception 그래서 이걸 맨 먼저 쓰면 안된다 순서 중요***
        // 먼저쓰면 다른 예외처리가 문법적 오류가 남.
        try {
            te.loadClass("b.txt","java.lang.strng");
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }catch (Exception e){
            System.out.println(e);
        }


    }
}
