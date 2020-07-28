package com.company;


/**
 * 연산자 operator
 * 연산자 (operator), 피연산자(operand) - > 연산식(Expression) expression:현업에서 많이 쓰이는 말
 * 사칙 연산자 : + / - *
 * 비교 연산자 :  > < >= <=
 * 논리 연산자 :
 * 증감 연산자
 * 삼항 연산자
 * 비트 연산자
 */
public class Main {

    public static void main(String[] args) {
	// write your code here
        // 사칙연산
//        int x = 10, y = 20, z;
//        z = x + y; // +: 연산자, x, y는 피연산자 x+y는 연산식 expression,
//                  // =: 연산자 z:피연산자, x+y:피연산자
//        // 대입 연산자가 우선순위가 가장 낮음 맨 마지막에
//        System.out.println(z);
//
//        System.out.println("정수형 사칙 연산");
//        System.out.println(20 -5);
//        System.out.println(4 * 5);
//        System.out.println(8+8);
//        System.out.println(150 / 8); // 정수 나누기 -> 몫
//        System.out.println(150 % 8); //modulus연산  나머지 값이 나옴
//        System.out.println("");
//
//        System.out.println("실수형 사칙 연산");
//        System.out.println(10.0 + 52.3);
//        System.out.println(10.5f + 12.3); //float, double 같이 연산되면 double로 변환 후 연산
//        System.out.println(10.4-50); // 실수형, 정수형을 같이 연산하면 실수형으로 변환 후 연산
//        System.out.println(10.2 * 4.2);
//        System.out.println(150 / 8.0); //실수로 나누면 소숫점까지 계산이 이루어짐
//        System.out.println(5.2 / 1.2);// 몫이 아니고 실수 값으로 계산 됨
//        System.out.println(5.2 % 1.2); // 실수 나눗셈도 modulus 연산으로 나머지 계산 가능 연산 이루어짐
//        System.out.println("");
//
//        //어려워짐
//        System.out.println("사칙연산의 주의사항");
//        System.out.println(Integer.MAX_VALUE /2 * 3); // -1073741827 오버플로우
//        System.out.println(Integer.MAX_VALUE); //2147483647
//        System.out.println(Integer.MAX_VALUE + 1); //-2147483648 가장 작은 값이됨 가장 큰 값에서 overflow 발생 시  가장 작은 값이 됨
//
//        int maxVal = 0b01111111111111111111111111111; //b다음에 0이면 양수 1이면 음수 32비트
//        int maxVal1 =0b10000000000000000000000000001; //2의 보수
//        int minVal = 0b10000000000000000000000000000; //1의보수
//
//
//        System.out.println((6-5.9) * 10); // 0.9999999999999964
//        System.out.println(Math.floor((6-5.9) * 10)); // 0.0 내림연산했을때 우리는 안에 계산식이 1이나오길 (예상)기대하지만 0.9999가나와서 내림연산했을때 0.0이 나옴
//                                                      // (정밀도 문제 6과 관련했을 때 많이 발생)
//        int [] arr = {0,1,2,3,4,5,6,7};
//        //floor의 사용예제- arr[Math.floor(arr.length/2.0)];
//
//        System.out.println(40/0.0); //infinity  수식은 문제 없지만 값에서 문제가 생길경우 발생 연산 도중에 한번 infinity가 발생하면 최종적으로 NaN이뜸
//        System.out.println(40 % 0.0); //NaN = not a number

        // 대입 연산자
//       int z = x + y;
//        z += 10; // z = z + 10
//        z -= 10;
//        z *= 10;
//        z /= 10;
//        z %= 10;
        // 논리 연산자, 비트 연산자 등등 다 됨

        // 비교 연산자
        System.out.println("비교 연산자"); // 출력이 boolean
        System.out.println(10 > 20);
        System.out.println(10 < 20);
        System.out.println(10 >= 10);

        int x = 10;
        int y = 10;
        System.out.println(x == y);
        System.out.println(x != y);
        System.out.println("");

        // 논리 연산자 - 입출력이 모두 boolean
        // a AND b : a,b 모두 참일때만 참
        // a OR b : a,b 둘중 하나만 참이어도 참
        // a XOR b : a 또는 b 둘중 하나만 참이어야 참 //exclusive or, 배타적 OR
        // NOT a : a가 참이면 거짓, 거짓이면 참 -> 단항연산자

        System.out.println(10 < 20 & 40 >= 30); // AND
        System.out.println(40 < 2 | 1 >0); // OR
        System.out.println(!true);
        System.out.println(!(10>20)); // NOT
        System.out.println(10 > 2 ^ 5 < 2); // XOR true
        System.out.println("");

        // short-circuit
        System.out.println(10 < 20 && 4 < 2); // 앞에만 검사해서 (true,false)값이나오면 뒤에는 검사안하고 넘어감? 꼭 boolean이 아니어도 괜찮다?  && 두개쓰는게  & 하나쓰는 것보다 속도가 빠르다
        System.out.println(2 > 8 || 2 < 5);

        // 증감 연산자 - 단항 연산자
        int val = 0;
        System.out.println(val++); // val = 0으로 먼저 Expression 평가 후에 val += 1 적용
        // sout(val);
        // val +=1;
        System.out.println(++val); // val += 1 먼저 계산한 후에 Expreesion 평가
        // val +=1;
        // sout(val);
        System.out.println(val--);
        // sout(val);
        // val -=1;
        System.out.println(--val);
        // val -=1;
        // sout(val);

        val = 5;
        int new_val = val++ * 10 + --val; //5 * 10 -(6-- : 5) = 55; 좋지 않은 코드 절대 하지 마세요.
//        int new_val = ++val * 10 + --val; =65;
        System.out.println(new_val);
        System.out.println("");

        // 삼항 연산자
        // (condition)? (true Expression):(false Expression)
        // boolean             값                 값
        System.out.println(true? 1 :0);
        System.out.println(false? 1:0);

        x = 10;
        y = 13;

        System.out.println(x > y? x: y); // max function
        System.out.println(x < y? x: y); // min function

        // 비트 연산자
        // 정수형 연산에 사용
        System.out.printf("b%32s\n", Integer.toBinaryString(8));                  //b                            1000
        System.out.printf("b%32s\n", Integer.toBinaryString(8 >> 1)); //shift 연산자 b                             100
        System.out.printf("b%32s\n", Integer.toBinaryString(7));      // b                             111
        System.out.printf("b%32s\n", Integer.toBinaryString(7 >> 1)); // b                              11

        System.out.printf("b%32s\n", Integer.toBinaryString(1423));
        System.out.printf("b%32s\n", Integer.toBinaryString(1423 >>2));
        System.out.printf("b%32s\n", Integer.toBinaryString(1423 >> 4));
        System.out.printf("b%32s\n", Integer.toBinaryString(1423 << 2));
        System.out.printf("b%32s\n", Integer.toBinaryString(1423 << 4)); // 새로 추가되는 비트는 0으로 채워짐

        System.out.printf("b%32s\n", Integer.toBinaryString(-1));
        System.out.printf("b%32s\n", Integer.toBinaryString(-1 >> 1));
        System.out.printf("b%32s\n", Integer.toBinaryString(-1 >>> 1)); //앞에 0으로 시작 sing비트와 무관하게 0으로 채운다.

        int intVal = 4123;
        intVal >>= 2; //intVal = intVal >>2; shift 연산자도 대입연산자 가능
        intVal |= 412; // intVal = intVal | 412; bitwise 연산자도 대입연산자 가능

        System.out.printf("b%32s\n", Integer.toBinaryString(1252));
        System.out.printf("b%32s\n", Integer.toBinaryString(15234));
        System.out.printf("b%32s\n", Integer.toBinaryString(1252 & 15234));
        System.out.printf("b%32s\n", Integer.toBinaryString(1252 | 15234));
        System.out.printf("b%32s\n", Integer.toBinaryString(1252 ^ 15234));
        System.out.printf("b%32s\n", Integer.toBinaryString(~1252)); // 논리 연산자처럼 보이지만 비트 연산자이다.
        System.out.println("");
        //연산자 우선순위 헷갈리면 ()를 쳐라





    }

}
