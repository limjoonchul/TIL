package com.company.ch14.Stream.Reader;

import java.io.*;

public class FileReaderTest {
    public static void main(String[] args) throws IOException {
        // byte 단위로 읽음
//        FileInputStream fis = new FileInputStream("alpha.txt");
//        InputStreamReader isr = new InputStreamReader(fis);
//        int read;
//        while ((read = isr.read()) != -1){
//            System.out.print((char)read);
//        }
//        isr.close();
        // 보조스트림을 클로우즈하면 다 클로우즈가된다.
        // 소켓같은경우에 byte단위로 읽게되는데 이때 이처럼 InputStreamReader을 사용하면 된다.


        // char 단위로 읽음
        FileReader fr = new FileReader("alpha.txt");
        int read;
        while ((read = fr.read()) != -1){
            System.out.print((char)read);
        }
        fr.close();
    }
}
