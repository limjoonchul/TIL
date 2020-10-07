package com.company.ch14.Stream.InputStream;

import java.io.*;
import java.util.Arrays;

public class FileInputStream3 {
    public static void main(String[] args) {
//        try(FileInputStream fis= new FileInputStream("Input2.txt");) {
//            int read;
//            while ((read = fis.read()) != -1){
//                System.out.print((char) read);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 버퍼를 사용할 때는 가비지가 남을 수 있다는걸 유의해야 한다.
        try(FileInputStream fis = new FileInputStream("Input2.txt")){
            int read =0;
            byte[] bytes = new byte[10];
            while ((read = fis.read(bytes)) != -1){
                for (int i = 0; i < read; i++) {
                    System.out.print((char)bytes[i]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
