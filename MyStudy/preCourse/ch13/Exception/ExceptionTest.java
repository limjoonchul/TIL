package com.company.ch13.Exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionTest {
    public static void main(String[] args) {
        FileInputStream file = null; //스트림을 열었으면 반드시 닫아야한다.
       // 오픈에서 클로우즈할때 null인상태에서 오픈이안되고 클로우즈하면 nullpointexception이뜸

        try {
            file = new FileInputStream("a.txt");
//            file.close(); // 정상적으로했다하면 여기다 하고
        } catch (FileNotFoundException e) {
            System.out.println(e);
//            file.close(); //아니면 여기서해야되고 밑에 catch가 있으면 다 넣어줘야함.
            //return; //end가 출력이 안되는데 finally 수행되고 return이 수행이된 것이기 때문에.
        }finally {
            // 여기에 file.close를 작성하는데 close오류가 날 수 있어서 한번더
            // try~catch문 사용.
            try {
                file.close();
                System.out.println("finally");
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        System.out.println("end");

        //try with resource
        try(FileInputStream files = new FileInputStream("a.txt")){

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
