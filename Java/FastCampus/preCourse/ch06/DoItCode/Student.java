package com.company.ch06.DoItCode;

import java.util.ArrayList;

public class Student {
    public String studentName;
    ArrayList<Book> list;
    public Student(String studentName){
        this.studentName = studentName;

        list = new ArrayList<Book>();
        //학생이 생성될 때 학생이 읽은 책의 리스트도 같이 생성해주기 위해서 이때 생성함.
    }

    public void addBook(String bookName,String author){
        Book book = new Book(bookName,author);
        list.add(book);
    }

    public void showBookInfo(){
        System.out.print(studentName+"학생이 읽은 책은 :");
        for (int i=0; i<list.size(); i++){
            System.out.print(" "+list.get(i).getBookName());
        }
        System.out.println("입니다.");

    }
}
