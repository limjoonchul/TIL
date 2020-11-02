package com.company.p01;

import java.util.ArrayList;
import java.util.List;

/**
 * 유닛 테스트(Unit Test)
 * - 단위 테스트라고도 부르며, 최소 단위의 테스트를 말한다.
 * - 최소 단위 : 메소드 단위를 말한다. (어느정도 객체단위라는 것도 포함하고 있다.)
 *   - 메소드는 객체의 속성을 변화시키는 '부작용(side-effect)'을 가지고 있기 때문에
 *      - 순수함수는 값을여러번 넣어도 똑같고, 외부에 영향을 주지 않는 것이다. 여기서 부작용은 순수함수에서  벗어나는거, 주변에 영향을 주는거  영향을 받는 것을 이미
 *   - 환경 셋업, 환경 정리를 반드시 같이 해줘야 한다.
 *
 * 통합 테스트(Integeration Test)
 * - 전체 시스템의 동작을 확인하는 테스트
 *    - 내가 작업한걸 깃에 머지하면서 전체시스템이 잘 동작하는지 체크
 */



public class ScoreCollection {
    private final List<Scorable> scoreList = new ArrayList<>();

    public void add(Scorable item){
        scoreList.add(item);

    }
    public int arithmeticMean(){
        int total = scoreList.stream().mapToInt(Scorable::getScore).sum();
        return total / scoreList.size();
    }
}

/**
 * 기존의 임시적인 테스트 방법
 * - 문제점?
 *   - 여러개의 테스트를 작성하기 어렵다. (여러개를 넣엇해야하는데 테스트내용이 구분이 안간다)
 *   - 여러가지 테스트를 할 때 '부작용'이 발생하기 쉽다.
 *      - 실행 순서에 따라 결과가 달라진다..
 *   - 테스트 결과가 성공적인지 파악하기 어렵다
 *      - 일일이 예상값을 두고 눈으로 파악해야 하는데, 수천개가 넘는 테스트를 눈으로 파악하기 어려우니깐
 *   - Production 코드와 Test 코드가 섞이게 된다.
 */
class Main{
    public static void main(String[] args) {
        ScoreCollection collection = new ScoreCollection();
        // Test1. 5와 7의 평균 6
        collection.add(() -> 5);
        collection.add(() -> 7);
        System.out.println(collection.arithmeticMean());
        // Test2. 10 20 의 평균 15
        // 그런데 Test1의 영향을 받아서 원하는 테스트 출력을 얻지 못했다.
        // Test1과 Test2의 순서가 달라지면, 또 결과가 달라진다.
        collection = new ScoreCollection(); // 초기화해서 원하는 값을 얻을 수 있다.
        collection.add(() -> 10);
        collection.add(() -> 20);
        System.out.println(collection.arithmeticMean());

    }
}