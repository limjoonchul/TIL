package com.company.ch06.array.BookEx;

import com.company.ch06.array.BookEx.Book;

public class ObjectCopy2 {
    public static void main(String[] args) {
        Book[] libarary = new Book[5];
        Book[] copyLibrary = new Book[5];

        libarary[0] = new Book("태백산맥1","조정래");
        libarary[1] = new Book("태백산맥2","조정래");
        libarary[2] = new Book("태백산맥3","조정래");
        libarary[3] = new Book("태백산맥4","조정래");
        libarary[4] = new Book("태백산맥5","조정래");


        // 깊은 복사 새로운 객체를 생성해줘서 값을 넣어주는 것.
        copyLibrary[0] = new Book();
        copyLibrary[1] = new Book();
        copyLibrary[2] = new Book();
        copyLibrary[3] = new Book();
        copyLibrary[4] = new Book();
        for (int i =0; i<libarary.length; i++){
            copyLibrary[i].setAuthor(libarary[i].getAuthor());
            copyLibrary[i].setTitle(libarary[i].getTitle());
        }
        System.out.println("++++++++++++++++++");
        libarary[0].setTitle("나목");
        libarary[0].setAuthor("박완서");
        for (Book book : libarary){
            book.showBookInfo();
        }

        System.out.println("=================");

        for (Book book : copyLibrary){
            book.showBookInfo();
        }
    }

}
