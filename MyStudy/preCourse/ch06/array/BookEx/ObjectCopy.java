package com.company.ch06.array.BookEx;

import com.company.ch06.array.BookEx.Book;

public class ObjectCopy {
    public static void main(String[] args) {
        Book[] libarary = new Book[5];
        Book[] copyLibrary = new Book[5];

        libarary[0] = new Book("태백산맥1","조정래");
        libarary[1] = new Book("태백산맥2","조정래");
        libarary[2] = new Book("태백산맥3","조정래");
        libarary[3] = new Book("태백산맥4","조정래");
        libarary[4] = new Book("태백산맥5","조정래");

        System.arraycopy(libarary,0,copyLibrary,0,libarary.length);
//        for (Book i : copyLibrary){
//            i.showBookInfo(); // showBookInfo()가 void형이고 안에 출력문이 있기 때문에
//            //System.out.println(i.showBookInfo()); 이런형식으로 쓰는게 안된다.
//            // 자꾸 생각없이 쓰니 이런 형식으로 쓰게 된다 주의하자!
//        }


        // 얕은 복사 인스턴스가 새로 생성되서 복사하는게 아니라 주소만 복사하는 것
        // 두개의 객체배열이 똑같은 주소를 가지고 있기 때문에 하나만 변경되도 같이 변경된다.
        // 가르키고 있는 주소의 값들을 변경하는 것이기 때문에.
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
