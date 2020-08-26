package com.company;

/**
 * 조건문 - 조건에 따라서 코드의 실행 흐름을 결정
 * if 계열
 * switch 계열
 *
 * [branch 문]  -기계어 수준에서 부르는 조건 실행문
 */

public class Main {

    public static void main(String[] args) {
	// write your code here
        //초보때는 용어를 알아두는게 중요하다.
        String snum = "510225-2055132";
        char c = snum.charAt(7);
        System.out.println(c);

        if(c == '1' || c == '3'){
            System.out.println("남자");
        }else{
            System.out.println("여자");
        }

        int x = 7; // 6은 완전한 수이다
        if (x % 2 ==0){
            System.out.println("짝수");
        }else{
            System.out.println("홀수");
        }

        System.out.println(x % 2 ==0? "짝수":"홀수"); //가독성 측면에서 고려해봐야 한다.
        System.out.println("");

        int score = 2;
        char grade;
        if(score >= 9){
            grade = 'A';
        } else if(score >= 7){
            grade = 'B';
        }else if(score >=5){
            grade ='C';
        } else  if(score >= 3){
            grade = 'D';
        }else{
            grade ='F';
        }
        System.out.println(grade);

        score = 90;
        boolean isLate = true;
        // late인 경우에는 하나 낮은 그레이드를 주기로 하였다.
        //Nested if 문 nest(둥지) 안쪽으로 계속쌓여가는것
        if (score >=90 ){
            if(isLate){
              grade = 'B';
            }else {
                grade ='A';
            }
        }else if(score >=80){
            if(isLate){
                grade = 'C';
            }else {
                grade ='B';
            }
        }else if(score >=70){
            if(isLate){
                grade = 'D';
            }else {
                grade ='C';
            }
        }
        else if(score >=60){
            if(isLate){
                grade = 'F';
            }else {
                grade ='D';
            }
        }else{
            grade='F';
        }
        System.out.println(grade);
        System.out.println("");

        // if문같은경우 조건식을 마음대로 쓸 수 있다
        //switch ~ case는 좀 다름 조건문은 '값'이 들어오게된다. boolean에 한정되 않습니다.
        // case범위가 될 수 없고, case도 값이어야 합니다.
        // break가없으면 다른 케이스로 계속 넘어감, fall-through가 발생함.
        // fall-through를 의도하지 않았다면 주석을 달아줘야한다.
        grade = 'F';
        switch (grade){
            case 'A':
                System.out.println("훌륭");
//                break;
//            case 'A':
//                System.out.println("훌륭"); //fall -through 의도한것이다.
            case 'B':
                System.out.println("멋집니다");
//                break;
            case 'C':
                System.out.println("노력하세요");
//            case 'C':
//            case 'D':
//                System.out.println("많이 노력하세요");
//                break; //이렇게도 사용가능.
            case 'D':
                System.out.println("많이 노력하세요");
//                break;
            default: //***무조건 마지막에 실행된다.***
                System.out.println("다음엔 잘하세요");
        }
        System.out.println("");
//        switch (grade){
//            default: //***무조건 마지막에 실행된다.***
//                System.out.println("다음엔 잘하세요");
//
//            case 'A':
//                System.out.println("훌륭");
//                break;
//            case 'B':
//                System.out.println("멋집니다");
////                break;
//            case 'C':
//                System.out.println("노력하세요");
//
//            case 'D':
//                System.out.println("많이 노력하세요");
////                break;
//
//        }
        



    }
}
