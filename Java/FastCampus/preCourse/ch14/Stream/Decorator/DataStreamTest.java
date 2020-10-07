package com.company.ch14.Stream.Decorator;

import java.io.*;

public class DataStreamTest {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("data.txt");
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeByte(100);
        dos.writeChar('A');
        dos.writeUTF("안녕하세요");

        FileInputStream fis = new FileInputStream("data.txt");
        DataInputStream dis = new DataInputStream(fis);
        System.out.println(dis.readByte());
        System.out.println(dis.readChar());
        System.out.println(dis.readUTF());

        dos.close();
    }
}
