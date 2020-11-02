package com.company.p01;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * file->moudles에가서 test 디렉토리를 test를 클릭하면 테스트폴더로 변화한다.
 * 그리고 원래 메인클래스의 메소드에서 그 메소드를 클릭하고  code->generate를 하면 해당 메소드를 test디렉토리에 테스트클래스로 자동으로 만들어줌
 * 그리고 junit 임포트를 해주면 된다!
 */
public class ScoreCollectionTest {
    @Test
    public void arithmeticMeanOfFiveAndSevenResultsInSIx(){
        // 주석이 없어진 이유는 메소드 명에 들어가 있기 때문에 메소드명에 이렇게 써준다.
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);
        int actualResult = collection.arithmeticMean();
        assertEquals(6,actualResult);
        // assert 반드시 이렇다 단언하는 것, 결과가 같다고 해주는 것
        // TEST 애노테이션을 달아주면 알아서 테스트를 해주고 assert에 대한 결과값을 준다.

    }
    @Test
    public void arithmeticMeanOfTenAndTwentyResultsInFifteen(){
        // 주석이 없어진 이유는 메소드 명에 들어가 있기 때문에 메소드명에 이렇게 써준다.
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 10);
        collection.add(() -> 20);
        int actualResult = collection.arithmeticMean();
        assertEquals(15,actualResult);
        // assert 반드시 이렇다 단언하는 것, 결과가 같다고 해주는 것

    }



}