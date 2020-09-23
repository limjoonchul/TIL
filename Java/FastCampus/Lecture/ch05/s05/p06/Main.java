package com.company.s05.p06;

import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

/**
 * Supplier 계열
 *  - 아무런 입력을 받지 않고, 값을 하나 반환 하는 함수형 인터페이스.
 *  - 자료를 '공급'하는 공급자 역할을 한다.
 */
public class Main {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "A String";//입력을 받지 않기때문에 ()이 필요하다.
        System.out.println(supplier.get()); // get()을 해서 출력을 한다.
        // BiSupperlier는 입력은 여러개할 수 있지만 출력은 하나밖에 못하기 때문에 없다.

        //Supplier는 P Type 계엘에서 getAsP 메소드로 정의가 된다. primitive
        // 메소드가 다르다. getAsInt()...
        BooleanSupplier boolsup = () -> true;
        System.out.println(boolsup.getAsBoolean()); // 이것은 getAsBoolean()으로 출력한다.
        // IntSupplier, LongSupplier, DoubleSupplier

        IntSupplier rollDice = () -> (int)(Math.random() * 6);
        //0~6까지 나와서 6은 나오지 않음 0~5까지만 실제 값이 나온다.
        for (int i = 0; i < 10; i++) {
            System.out.println(rollDice.getAsInt());
        }

        int x = 4;
        IntSupplier intSupp = () -> x; //로컬변수에도 접근할 수 있다.
        // 람다식을 활용할 때 모든 변수에 접근하여 활용할 수 있다.
        // 고정되어있는 값뿐만아니라ㅏ 동적으로도 주변 값들을 공급할 수 있다.
        // 그래서 supplier가 나름대로의 의미가 있다??
        System.out.println(intSupp.getAsInt());

    }
}
