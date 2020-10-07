package com.company.ch14.Stream.InputStream;

import java.io.IOException;
import java.io.InputStreamReader;

public class SystemInTest2 {
    public static void main(String[] args) {
//        System.out.println("입력:");
//        try {
//            int i;
//            // 왜 i = System.in.read() 이렇게 하는 걸까
//            // while위에 i=System.in.read()를 했을 때는 i는 계속 값을 읽어들이는 것이 되서
//            // 무한 루프에 빠지게 되는데 이건 이해를 했다.
//            // 그냥 입력되는 System.in.read()를 i에 저장해서 i값을 출력하기 위해서
//            // 이렇게 사용했다고 이해하면 될듯?
//            // i를 엔터를 칠 때 까지 읽어 들인다!
//            while ((i = System.in.read()) != '\n'){
//                System.out.print((char) i);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //Sytem.in 은 System 클래스 안에 static InputStream in으로
        // InputStream 클래스의 변수로서 in이 사용되어서 표준 입력스트림으로 사용된다.

        // read는 한 바이트씩 읽는데 반환 값이 int인데 이 이유는?
        // 스트림의 끝에 더이상 읽을게 없다던가 파일의 끝에 -1을 반환을 하게 되어있다.
        // 이러한 이유로 반환을 int로 처리하게 되어 있다!!
        // 한바이트를 읽지만 4바이트로 처리를 한다.


//        System.out.println("입력 후 `끝`이라고 쓰세요:");
//        try {
//            int i;
//
//            while ((i = System.in.read()) != '끝'){
//                System.out.print((char) i);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // 이렇게 했을 때 끝을 입력해도 종료가 되지 않는다
        // 이유는 in은 InputStream이니깐 아까 이건 한 바이트씩 읽어들인다고 했고,
        // 한글은 2~3바이트이기 때문에 한바이트식 읽은거와 한글이 같을 수가 없다
        // 그래서 끝을 입력해도 종료가 되지 않고 계속 입력을 받게된다.
        // 이럴 때 보조스트림을 사용해서 한글을 처리할 수 있다.

        System.out.println("입력 후 `끝`이라고 쓰세요:");
        try {
            int i;
            InputStreamReader isr = new InputStreamReader(System.in);
            // 보조스트림 - 항상 다른 스트림을 매개변수로 받는다. 다른 스트림의 기능을 부과해주는 것
            // 바이트를 문자로 바꿔주는 스트림
            while ((i = isr.read()) != '끝'){ //그래서 여기서 보조스트림.read()로 읽는다.
                System.out.print((char) i); //출력 될 때 끝은 출력이 되지 않음
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
