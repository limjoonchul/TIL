package com.company;

import javax.crypto.spec.PSource;

/**
 * 생성자에 private를 넣으면됨.
 */
class MyMath{
    static double PI =3.1415927;
    static double E = 2.718281;

    private MyMath(){};

    static int min(int...params){
        int min = params[0];
        for (int i =1; i<params.length; i++){
            if(min > params[i]){
            min = params[i];
            }
        }

        return min;
    }
   static double min(double...params){
        double min = params[0];
        for (int i =1; i<params.length; i++){
            if(min > params[i]){
                min = params[i];
            }
        }

        return min;
    }
    static int max(int...params){
        int max = params[0];
        for (int i=1; i<params.length; i++){
            if(max < params[i]){
                max = params[i];
            }
        }

        return max;
    }
    static double max(double...params){
        double max = params[0];
        for (int i=1; i<params.length; i++){
            if(max < params[i]){
                max = params[i];
            }
        }

        return max;
    }

    static int abs(int num1){
        if(num1 < 0){
            num1 = -1 * num1;
        }
      return  num1;
    }
    static double abs(double num1){
        if(num1 < 0){
            num1 = -num1;
        }

        return num1;
    }
    static double floor(double num){

        return Math.floor(num);
    }
    static double ceil(double num){

        return Math.ceil(num);
    }


}
public class MyMathTest {

    public static void main(String[] args) {
//         System.out.println(MyMath()); // should fail
        System.out.println(MyMath.PI);
        System.out.println(MyMath.E);
        System.out.println(MyMath.min(2, 3, -4, 6));
        System.out.println(MyMath.max(7, 0, 6, 16, -4));
        System.out.println(MyMath.abs(5));
        System.out.println(MyMath.abs(-2.3));
        System.out.println(MyMath.floor(-1.5232));
        System.out.println(MyMath.ceil(4.6452));
    }

}
