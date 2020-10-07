package com.company.ch14.Stream.InputStream;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStream2 {
    public static void main(String[] args) {
        try(FileInputStream fis= new FileInputStream("Input.txt");) {
            int read;
            while ((read = fis.read()) != -1){
                System.out.println((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
