package com.company.ch10.Object;
class Book implements Cloneable{
    String title;
    String author;

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return author+","+title;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        /**
         * 직접 불러서 사용하는 메소드는 아니다.
         * 이메소드는 이 객체가 heap메모리에서 해체될 때
         * gc에서 호출되는 메소드 이게 정의가 되어있으면 gc가 이 메소드 부분을 수행한다.
         * 주로 리소스해제, 안닫혔을 소켓을 닫는다.
         */
        super.finalize();
    }
}
public class ToStringTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Book book =  new Book("토지","박경리");
        System.out.println(book.toString());
        // 메모리주소가 출력됨.

        String str = new String("토지");
        System.out.println(str);
        //String 안에 이미 toString()메소드가 정의되어 있어서
        // String클래스 안에 있는 Chararcter의 배열을 출력하도록 되어 있다.

        Book book2 =(Book)book.clone();
        //book.clone()이렇게 했을 때 object 자료형으로 반환이되서
        //명시적으로 다운캐스팅을 해줘야하고 예외처리를 해줘야 한다.

        System.out.println(book2);
        //그냥하면 예외가 발생함
        // 이유 위에 cloneable 인터페이스를 implements 하지 않아서
        // 이런 인터페이스를 마크인터페이스라고 한다.
    }
}
