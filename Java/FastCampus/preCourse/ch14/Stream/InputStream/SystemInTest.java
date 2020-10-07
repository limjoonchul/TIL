package com.company.ch14.Stream.InputStream;

import java.io.IOException;
import java.io.InputStream;

public class SystemInTest {
    public static void main(String[] args) {
//        System.out.println("입력:");
//        try {
//            int i = System.in.read();
//            System.out.println(i);
//            System.out.println((char) i);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // read는 한 바이트씩 읽는데 반환 값이 int인데 이 이유는?
        // 스트림의 끝에 더이상 읽을게 없다던가 파일의 끝에 -1을 반환을 하게 되어있다.
        // 이러한 이유로 반환을 int로 처리하게 되어 있다!!
        // 한바이트를 읽지만 4바이트로 처리를 한다.




    }
}
