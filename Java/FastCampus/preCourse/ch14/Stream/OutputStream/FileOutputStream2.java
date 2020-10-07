package com.company.ch14.Stream.OutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStream2 {
    public static void main(String[] args) {
        // 파일이 없으면 자동생성되서 값이 써진다.
        // append를 true로하면 뒤에 계속 이어서써짐
        byte[] bs = new byte[26];
        byte data = 65;
        for (int i = 0; i < bs.length; i++) {
            bs[i] = data;
            data++;

        }
        try(FileOutputStream fos = new FileOutputStream("alpha.txt",true);
            FileInputStream fis = new FileInputStream("alpha.txt")) {
            fos.write(bs);
            int read;
            while((read = fis.read()) != -1){
                System.out.println((char)read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
