package com.company.ch14.Stream.OutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStream1 {
    public static void main(String[] args) {
        // 파일이 없으면 자동생성되서 값이 써진다.
        // append를 true로하면 뒤에 계속 이어서써짐
        try(FileOutputStream fos = new FileOutputStream("output.txt",true)) {
            fos.write(65);
            fos.write(66);
            fos.write(67);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
