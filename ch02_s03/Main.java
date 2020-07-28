package com.company;

import java.util.Scanner;

/**
 * 자료 입출력
 * 출력 - print println printf(포맷팅 방법
 * 입력 - 키보드 입력 받기
 */

public class Main {

    public static void main(String[] args) {
	// write your code here
        // 출력 메소드
//        System.out.println("string can be printed");
//        System.out.println('c'); //문자
//        System.out.println(40234);  //정수
//        System.out.println(1.23f);
//        System.out.println(1523.222); //ln - new line
//
//        System.out.print("123121\r\n"); // r = 캐리지 리턴  첫글자자리로로 보냄 n = newline
//        System.out.print("");
//
//        System.out.printf("%s is for string\n","string"); //new line이 자동으로 안붙여짐
//        System.out.printf("%s %s %s\n","123","456","789");
//        System.out.printf("%b\n", true);
//        System.out.printf("%d 0x%x 0X%X 0%o\n", 15, 15, 15,15); // %d = 정확히 10진수가 맞다 decimal %0 =8진수 앞에 0x,0 붙이는거 신경써야한다.
//        System.out.printf("%f\n",14.23);
//        System.out.printf("%f\n",14.23f); //꼭 float은 아니다 f가 flaot이 아니라 floating number float과 double 둘다 나타내야기 때문에
//        System.out.printf("%e\n",14.423); //지수표현
//        System.out.printf("%c %c\n",'U','\u0042'); // U B
//        System.out.printf("%n");
//
//        // 정수 포맷팅
//        System.out.printf("%5d.\n", 10); //5d 5자리 확보 ...10
//        System.out.printf("%-5d.\n", 10); // 뒤에 3칸이 확보됨 10   .
//        System.out.printf("%05d\n",234); //앞에 0이 채워짐
//        System.out.printf("%3d\n",104294); //자릿수가 더크면 그대로 출력이된다  3칸은 최소한 확보하는 것
//        System.out.println("");
//
//        // 실수 자릿수 표현
//        System.out.printf("%5.4f\n", 152523.456228); //소수점 아래에 4자리까지만 출력 5는 최소한 5칸 확보 소숫점도 한칸 차지한다.
//        System.out.printf("%5.2f.\n",1.425); //소수점 포함 자릿수를 만든다.
//        System.out.printf("%-5.2f.\n",1.425);

        // 입력 메소드
        System.out.println("Input method");
        Scanner sc = new Scanner(System.in); //system.in 기본 키보드 입력

//        String s1 = sc.next(); // next는 스캐너에서 입력을 받는데 공백으로 구분된 String을 입력 받는다.
//        System.out.println(s1);

        //this is string
        // 공백으로 구분되는 문자열
        // next() 메소드는 입력받을 내용이 있을 때 까지 기다립니다. = Blocking 메소드라고 부룬다 (<-> non-blocking 메소드)
//        System.out.println(sc.next()); // "this"
//        System.out.println(sc.next()); // "is"
//        System.out.println(sc.next()); // "string"

        // 한줄 전체를 받음 next는 공백으로 구분이 됬다면 nextLine은 \n으로 구분이 되는 String을 입력 받는다. 키보드에서는 엔터
        System.out.println(sc.nextLine());
        System.out.println(sc.nextLine());
        System.out.println(sc.nextLine());

        // 공백으로 구분되는 정수
//        System.out.println(sc.nextInt());
//        System.out.println(sc.nextInt());
//        System.out.println(sc.nextInt()); // 자료형 안맞으면 에러 발생



    }
}
