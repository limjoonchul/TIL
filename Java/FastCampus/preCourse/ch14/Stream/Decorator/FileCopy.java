package com.company.ch14.Stream.Decorator;

import java.io.*;
import java.net.Socket;

public class FileCopy {
    public static void main(String[] args) throws IOException {
        long milliseconds = 0;
        try(FileInputStream fis = new FileInputStream("Input.txt");
            FileOutputStream fos = new FileOutputStream("Output.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos)){

            milliseconds = System.currentTimeMillis();

            int read;
            while ((read = bis.read()) != -1){
                bos.write(read);
            }

            milliseconds = System.currentTimeMillis()- milliseconds;

        } catch (IOException e){
            e.printStackTrace();
        }

        Socket socket = new Socket();
        BufferedReader isr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        isr.read();

        System.out.println(milliseconds);
    }
}
