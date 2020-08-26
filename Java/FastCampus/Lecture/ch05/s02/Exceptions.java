package com.company.s02;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

/**
 * 오류 / 예외
 * 오류 - 메모리 부족 또는 프로그램 실행이 꼬이는 경우.
 *       더이상 어떻게 프로그램을 복구해야 할 지 알 수 없다.
 *       프로그램을 진행하기 어려운 상태 계속했다가 해킹을 할 수 있는 여지가 생긴다. 오류가발생하면 프로그램이 종료하는게 정상적인 방법이다.
 *       소스코드를 보고 버그픽스를 해서 문제를 해결하는 것이 해결방법이다.
 * 예외
 * - 오류에 비해서 심각도가 낮고, 프로그램의 정상적인 흐름만 방해하는 경우
 *    -> 파일을 읽으려 했으나, 해당 파일이 없음
 *    -> 네트워크 연결이 유실 끊겼을 때 다시 커넥션해야할 때
 * - 문제 상황을 처리하는 로직을 구현하여, 런타임에서 자연스럽게 해결 가능
 *
 * 예외 처리
 * - 예외가 발생했을 경우에, 이 상황을 '감지'하고 '처리'하는 코드
 * - try ~ catch, throws, throw, finally 키워드를 이용
 *
 * Throwable 클래스를 상속하는 자식 클래스들로 이러한 문제를 해결
 */
public class Exceptions {
    public static void main(String[] args) {
        //try ~ catch
        try{
            // 예외가 발생할 수 있는 코드를 작성.
            // 예외가 발생할 경우 예외 객체를 던짐(throw)
        }catch (Exception e){ // 던져진 예외를 받음 (catch)
               //exception 클래스 및 그 자식 클래스를 이용
            //예외 상황을 처리하는 코드

        }

//        try{ //array 내부 구현에서 허용된 길이보다 다른 길이가 입력이 되면 보내줌
//            int [] integers = new int[10];
//            integers[20] = 5;
//        }catch (ArrayIndexOutOfBoundsException e){ //메세지를 심어서 출력
//            System.out.println(e.getMessage());
//            e.printStackTrace(); //ide를 쓸 때 사용하는 버그 콘솔에 에러를 출력하는 다른 메소드를 사용하는 것 출력이 꼬이는 현상이 발생함.
//        }

//        //다중 예외 처리
//        try{
//            //아주아주 예민한 내용이 실행되는 부분.
//               // 특정 catch 구문에 선택되는 조건은
//               // 다형성에 의해서 결정된다.
//               // 즉, catch하고 있는 클래스의 자식 클래스의 객체면 catch 가능. 순서도 중요하다.
//        }catch (ArithmeticException e){ // 첫번째 캐치
//
//        }catch (FileAlreadyExistsException e){ //첫번째 캐치 후에 두번째 이루어짐
//
//        }catch (EOFException e){ //
//
//        }catch (IOException e){
//
//        }catch (Exception e){ // 나머지 모든 Exception을 모두 catch
//                              // 모든 Exception 객체의 조상.
//                              // 이것을 쓰는 것은 권장하지 않음 어쩔 수 없을 때 사용. 처리할 수 있을 때만 catch 함.
//
//        }


        // try ~ catch ~ finally
        try{
            int []integers = new int[10];
            integers[21] = 10; //예외가 발생해서 밑에는 던졌다는 실행안됨.
            System.out.println("던졌다!");
        }catch (Exception e){
            System.out.println("받았다!");
        }finally { // catch 발생 여부와 무관하게 무조건 실행
            System.out.println("마침내!");
            //try에서 생성한 리소스를 회수하기 위해서
        }

        FileInputStream file = null;
//        file = new FileInputStream("a.txt");
//        file.read(); //여기서 문제가생겼을때 close해야한다.

        /**
         * 예외의 종류 2가지
         *  - checked Exception
         *    Exception 클래스를 상속하고 있으면 checked Exception
         *    컴파일러에서 에러 발생 -> try ~ cath를 작성하지 않으면 아예 빌드 조차 할 수 없다.
         * - unchecked Exception
         *   RuntimeException 클래스를 상속하고 있으면 언체크드 익셉션
         *   try ~  catch를 작성하지 않더라도 빌드/ 실행이 가능
         *   ArrayIndexOutOfBoundsException
         *   ArithmaticException
         */
        try{
            file = new FileInputStream("a.txt");
            file.read(); //여기서 문제가생겼을때 close해야한다.
        }catch (IOException e){
            System.out.println("파일 처리 실패!");
        }finally {
            System.out.println("파이널리");
            if(file != null){ //finally내에서 꼭해줘야 함.
                try{
                    file.close();
                }catch (IOException e){
                    System.out.println("!!!!");
                }

            }
        }

        // try ~ with ~ resources 구문
        // java 1.7에서 추가된 기능
        // close를 명시적으로 실행하지 않은 이뉴는 autoclosable 인터페이스를 구현하는 리소스만 사용 가능
        try(FileInputStream file1 = new FileInputStream("b.txt")){
            file1.read();
        }catch (IOException e){
            System.out.println("파일처리실패");
        }
        System.out.println("Programm ended normaly");
    }
}


//thorws 키워드를 이용하여 예외처리 위임이 가능
class CheckedExceptionThrow{
    void methodA() throws IOException{  //메소드는 아이오익셉션을 던지는 메소드에요 thorows
        FileInputStream file2 = new FileInputStream("b.txt");
        file2.read();
        file2.close();

    }
    // 둘다 가능.
//    void methodB() throws IOException{
//        methodA();
//    }

//    void methodB(){
//        try{
//            methodA();
//        }catch (IOException e){
//            System.out.println("실패!!");
//        }
//
//    }
}

//Unchecked Exception의 경우에는 thorws 키워드를 사용하지 않아도 됨
class UncheckedExceptionThrow{
    void methodA(){ // 언첵트익셉션은 throws안해도 알아서 위임됨
        int x= 10/0; //아리스매틱익셉션 // 런타임익셉션
        //빨간줄이 안뜨는 에러기때문에 어디서 해도상관이없으니 까 안뜨는거래-----------> 무슨 의미지?

    }

    void methodB() { // 여기서해도 아래에서 해도 되서 에러가안뜸
        methodA();
    }

    public static void main(String[] args) {
        try{
            new UncheckedExceptionThrow().methodB();
        } catch (ArithmeticException e){
            System.out.println(e.getMessage()); /// by zero

        }
    }
}

class Foo{
    void methodA() throws IOException{}// Checked Exception
}

class BarOne extends Foo{
    void methodA() throws IOException {} // possible
}

class BarTwo extends Foo{
    void methodA() throws FileNotFoundException {}// 더 자식 Exception은 possible
    // FileNOtFoundException은 IOException의 자식 클래스이기 때문에 가능함
}

class BarThree extends Foo{
//    void methodA() throws Exception {}
//  부모 클래스의 메소드를 오버라이드 할때는
    // 부모 클래스의 메소드의 예외보다 더 조상인 예외는 던질 수 없다.
    // 오버라이딩할 때 구현하는 내용을 어느정도 제한하고 있는 부분
    // 파일 처리 익섹셥 하다가 갑자기 다른에러가 나온다는것은 다른 기능을 한다는 뜻이자나.
    // 그렇기에 비슷한 에러가 나오라고 제한하는 것
    // 오버라이딩 했으면 부모의 가업을 따라가라 - 엄격한집안
}

//오버라이딩 해도 다른 예외 발생할 수 있는데 구지 예외까지 상속해야되는이유는?
// 고슬링씨의 선택.. 오버라이딩할때 구현하는 내용을 어느정도 제한하는 언어라는 뜻