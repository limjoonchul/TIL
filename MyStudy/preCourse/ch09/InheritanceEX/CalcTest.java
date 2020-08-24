package com.company.ch09.InheritanceEX;

public class CalcTest {
    public static void main(String[] args) {
        CompleteCalc completeCalc = new CompleteCalc();
//        Calc completeCalc = new CompleteCalc();
//        Calculator completeCalc = new CompleteCalc();
        //위에 두개 모두 가능.
        int n1 = 10;
        int n2 = 2;
        System.out.println(completeCalc.add(n1,n2));
        System.out.println(completeCalc.substract(n1,n2));
        System.out.println(completeCalc.times(n1,n2));
        System.out.println(completeCalc.divide(n1,n2));
        completeCalc.showInfo();

        completeCalc.description(); //가까운 메소드를 찾아가서 출력

        int[]arr ={1,2,3,4,5};
        int sum = Calc.total(arr);
        System.out.println(sum);
    }
}
