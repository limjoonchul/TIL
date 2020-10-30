package com.company.s07;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 정규 표현식 (Regular Expression)
 * - 문자열을 다루는 패턴화된 작업을 정의하는 수식
 * - 문자열 매칭, 템플릿 일치 여부 확인, 템플릿 매칭 검색(문서가 있을 때 이메일만 뽑는 작업을 할 때 등등)
 * - 정규표현식은 느리기 때문에 남용하면 안된다. 많은 동작을 돌리면 안된다. 알고리즘 문제를 정규표현식을 푸는건 적합하지 않음
 * - 특정 조건에 맞는 문자열을 검색(search) / 치환(Replace)를 하는 패턴 기반의 식( 패턴: 전화번호 같은 것, 이메일)
 * - 이메일, 전화번호, 등 특정한 형식에 맞게 적혀 있는지 validation하는데 사용 가능
 * - Crawling(자료를 긁어 모으는 것) 등 날 것의 자료를 긁어 모았을 때 정리하는 데에 유용
 *  유튜브 댓글이 있으면 형식에 따라 분석을하고 구분을 할 수 있다.
 * https://regexper.com/
 */

public class Main {
    public static void main(String[] args) {


        //정규표현식의 기본 메타 문자
        //regex 정규표현식의 약자
        // 내가 만드는게 좋다 남이 만든건 해석하기 어려움

        // 문자 하나만
        //^x : x로 시작한다라는 의미.
        // ^ :  시작 , $ : 끝 을 의미
        // . : 아무문자를 의미(딱 한개) .을 의미하고싶으면 \.
        // x+ : 한번 이상 x가 반복됨
        // x? : 있을 수도 있고 없을 수도 있고
        // x* : 없을 수도 있고 하나만 잇을 수도 있고 여러개 있을 수도 있고
        // x | y: x 또는 y 가 있다.

        // 여러개는 괄호를 이용하면된다.
        // x{2} : 두번 반복됨 2, 두번이상 반복 2,10 은 2번이상, 10번 이하 반복됨
        // [] : 괄호안에 있는 값들 중 하나  [a -z] : a~z중 하나  "" , _ 도 포함한다.
        // 한글은 [가-힣]로하면 한글의 모든 문자를 포함한다.
        //[^a] : a가 아닌것
        //^[^a-zA-Z] : 영문자가 아닌것, ^밖이랑 []안이랑 의미가 다름.


        /**
         * 축약문자
         * 스페이스도 의미가 있으니 띄우면 안됨! \^\.
         * 대문자를 쓰면 반대의 의미를 갖는다
         * \d = [0-9] \D는 숫자가 아닌 것
         * \w = 아이디를 확인할 때 사용하면 편리하다 소문자, 대문자 알파벳, 숫자,_ 까지 포함
         */
        String regex = "(xyz)+";
        String str = "xyzxyz";

        System.out.println(Pattern.matches(regex,str)); // 정규표현식을 넣고 문자열을 넣어서 일치하는지 확인

        // boundary는 문자열의 시작, 끝, 공백, 문장기호로 구분되는지 여부 확인
        Pattern pattern = Pattern.compile("\\bJava\\b"); // 자바에서는 문자열을 쓸려면 \\두개를 해야한다.
        Pattern pattern2 = Pattern.compile("\\bJava\\b",Pattern.MULTILINE); // 뒤에 플래그를 설정할 수 있음 여기선 멀티라인.
        Matcher matcher = pattern.matcher("Java is Java and Java will be Java.");

        while (matcher.find()){ // find는 리턴이 boolean 앞에서부터 일치하는 것을 찾아준다. 더이상찾지 못하면 false를 리턴
            System.out.println(matcher.start() + ", "+matcher.end()); //찾은것의 시작,찾은것의 끝을  알려줌
        }

        Pattern pattern4 = Pattern.compile("(\\w+):(\\w+)\\.(\\w)$");
        Pattern pattern5 = Pattern.compile("^(?<filed>\\w+):(?<name>\\w+)\\.(?<extesion>\\w)$");
        Matcher matcher2 = pattern4.matcher("filename:tmp.png");
        matcher2.find();
        System.out.println(matcher2.group()); // 매칭된 전체가 출력
        System.out.println(matcher2.group(0)); // 매칭된 전체가 출력 위에랑 같음
        System.out.println(matcher2.group(1)); // 첫번째 그룹
        System.out.println(matcher2.group(2)); //
        System.out.println(matcher2.group(3)); //

        System.out.println(matcher2.group("filed")); //
        System.out.println(matcher2.group("name")); //
        System.out.println(matcher2.group("extension")); //



        Pattern pattern3 = Pattern.compile("ID:\\s?");
        Matcher matcher1 = pattern3.matcher("ID: Ssamzang");

        System.out.println(matcher1.lookingAt()); // 패턴으로 시작하는지 확인
        String string = matcher1.replaceFirst("");
        System.out.println(string);
    }
}
