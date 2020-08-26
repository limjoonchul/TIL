package com.company.ch06.DoItCode;

public class StudentTest {
    public static void main(String[] args) {
        Student studentLee = new Student("Lee");
        studentLee.addBook("태백산맥1","조정래");
        studentLee.addBook("태백산맥2","조정래");

        Student studentKim = new Student("kim");
        studentKim.addBook("토지1","박경리");
        studentKim.addBook("토지2","박경리");
        studentKim.addBook("토지3","박경리");

        Student studentCho = new Student("cho");
        studentCho.addBook("해리포터1","조지앤롤링");
        studentCho.addBook("해리포터2","조지앤롤링");
        studentCho.addBook("해리포터3","조지앤롤링");studentCho.addBook("해리포터1","조지앤롤링");
        studentCho.addBook("해리포터4","조지앤롤링");
        studentCho.addBook("해리포터5","조지앤롤링");
        studentCho.addBook("해리포터6","조지앤롤링");


        studentLee.showBookInfo();
        studentKim.showBookInfo();
        studentCho.showBookInfo();


    }
}
