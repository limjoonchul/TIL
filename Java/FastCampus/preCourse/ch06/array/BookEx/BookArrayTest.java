package com.company.ch06.array.BookEx;

public class BookArrayTest {
    public static void main(String[] args) {
        Book[] libarary = new Book[5];

        libarary[0] = new Book("태백산맥1","조정래");
        libarary[1] = new Book("태백산맥2","조정래");
        libarary[2] = new Book("태백산맥3","조정래");
        libarary[3] = new Book("태백산맥4","조정래");
        libarary[4] = new Book("태백산맥5","조정래");


         for (int i = 0; i<libarary.length; i++){
             System.out.println(libarary[i]);
             //com.company.ch06.array.BookEx.Book@b4c966a
             //패키지명과 앞에가 클래스의 풀네임이고고 @뒤에가 address
             libarary[i].showBookInfo();
             //com.company.ch06.array.BookEx.Book@b4c966a
             //태백산맥1,조정래 위의주소가 밑의 인스턴스들을 가르키고 있고, 각각의 주소값이 들어가 있다.
             //
        }
        /**
         * 각각의 배열 자리에는 컴퓨터 주소의 크기가 들어감 32비트,64비트라면
         * 그 주소가 들어갈 자리만큼만 만들어짐. 4바이트 8바이트
         */
    }
}
