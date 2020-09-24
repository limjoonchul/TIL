package com.company.s13.p01;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Stream API
 * JAVA 8 추가된 Java.util.stream 패키지
 * 컬렉션 요소를 람다식으로 처리할 수 있도록 돕는 함수형 프로그래밍 도구
 * - 간결한 코드로 작성할 수 있다.
 * - 데이터 소스에 대한 공통된 접근 방식 제공.
 *    - Stream으로 변경해주고 나면, List, Set,Map 모두 동일한 방식으로 데이터를 처리할 수 있다는 장점이 있다.
 *    - 컬렉션프레임워크에서는 나눠서 각각해줘야한다.
 *
 */

/**
 * 강사님 IntStream 메소드 보던 중에 allMatch라는 메소드가 있는데
 * if문으로 index 하나하나 비교하는것과 효율성은 동일한가요??
 * 효율성으로는 큰차이없고 코드양이 줄어든다.
 * 함수형프로그래밍했을 때 동작이 빨라지진 않는다 개발이 빨라진다? 정도
 */
public class Main {
    public static void main(String[] args) {
        // 기존 방법 java 7 방식
        List<String> list = Arrays.asList("fast","campus","rocks");
        List<String> newList = new ArrayList<>();

        for (String s : list){
            newList.add(s.toUpperCase());
        }
        for (String s : newList){
            System.out.println(s);
        }

        System.out.println();
        // java 8 - Stream API -> 훨씬 더 간결한 코드로 작성할 수 있다.
        List<String> list1 = Arrays.asList("fast","campus","rocks");
        Stream<String> stream = list1.stream(); //스트림으로 변환해준다.

        // 스트링에서 스트링으로 매핑해준다.
        //uppercase를 string에 매핑 각각의 요소에 이런것들을 적용해라
        stream.map(String::toUpperCase)
                .forEach(System.out::println); // foreach컨슈머를 받는다
        // 각각의 요소를 하나씩 출력한다.

        //긴코드는 그냥 작성할 수 있는데 간결한 방식은 많은 고민이 필요하다

        // 스트림 생성 방식1.
        // 컬렉션의 인스턴스 메소드 stream()
        Stream<String> stream1 = list1.stream();

        System.out.println();
        // 스트림 생성 방식2.
        // foreach를 붙여서 못쓰네?--------------------
        int[] ints = {4, 6, 2, 19, 2, 58, 4, 6, 5};
        // Arrays 클래스의 클래스 메소드 stream 을 이용해서 만든다.
        IntStream intStream = Arrays.stream(ints);//함수형 인터페이스랑 비슷함 ptype
        intStream.forEach(System.out::println);
        //LongStream, DoubleStream 도 있다.

        // 두개의 다른점은 스트림 인테저로할경우 프리미티브타입이들어올떼ㅐ 오토박싱으로변경되고
        // 다시 언방식이 된데 이럴때 오버헤드가 발생한다
        // 인트스트림을 사용할경우 이런 오버헤드 필요없이,웨퍼클래스없이 사용가능  그래서 효율적

        // 스트림 생성 방식3.
        // Stream 클래스의 클래스 메소드 of를 이용해서  컬렉션을 걸치지 않고도 스트림을 생성가능
        DoubleStream doubleStream = DoubleStream.of(0.4, 0.6, 0.2, 1.2, 0.94);
        doubleStream.forEach(System.out::println);

        // range를 이용한 스트림 -> fori문 (for(int i=0...))을 대체하는 스트림
        IntStream intStream1 = IntStream.range(0,10); // 0~9까지 10은포함되지 않는다.
        intStream1.forEach(System.out::println);

        IntStream intStream2 = IntStream.rangeClosed(0,10); // 0~10까지 10포함된다.
        intStream2.forEach(System.out::println);
        // LongStream도 range, rangeClosed 가 있다.

        // 위에는 우리가원하는데로 순서대로 뱉어준다면 아래건 랜덤으로 뽑아준다.

        // Random 객체를 이용한 스트림이 있다. 자바유틸안에있음
        Random random = new Random();
//        LongStream longStream =  random.longs();
//        longStream.forEach(System.out::println); // 개수 제한 없이 무한히 출력
//        // 3가지 오버라이딩이 되어있다. // ints longs doubles..

        // 개수 제한 가능
        LongStream longStream1 =  random.longs(100);
        longStream1.forEach(System.out::println); // 개수를 정해질 수있다 100개

        // 개수제한 + 범위 제한 가능 젤 많이 사용함
        LongStream longStream2 =  random.longs(100,0,1000);
        longStream2.forEach(System.out::println); // 개수를 정해질 수있다 0~1000까지 100개를 출력




        // 중간처리 메소드 - 스트림을 반환
        Stream<String> stringStream = Stream.of("Java","Is","Fun","Isn't","It","?","Java");
        // 스트림은 한번밖에 사용 못한다 내부적으로 몇번째 동작을 하고 있는지 그런겍들어가있다.

        // 필터링 메소드-----------------
//        stringStream.forEach(System.out::println); 기본형 중복이 포함되서 출력됨.
        // distinct() - 스트림에 같은 요소가 있을 경우 하나만 남기고 삭제하는 메소드
        stringStream.distinct().forEach(System.out::println);
        // stringStream.distinct() 여기까지 스트림// foreach는 최종처리 메소드

        // filter() : Predicate 계열을 입력을 받아, true인 요소만 남긴다.
        stringStream = Stream.of("Java","Is","Fun","Isn't","It","?");
        stringStream.filter(s->s.length()>=3).forEach(System.out::println);

        // 자르기(cutting)--------------------
        // skip(long n): 스트림의 처음부터 n개를 생략하는 메소드 n개 스킵한다.
        // limit(long maxsize) : 스트림의 최대 요소 개수를 maxsize를 제한
            // 맥스사이즈보다 크면 뒤에있는 값은 짤림

        // 정렬(sorting)--------------- 스트림상태에서도 정렬을 할 수 있다!
        // Comparable 인터페이스의 compareTo 메소드로 정렬하게 된다.
        stringStream = Stream.of("abc","fwf","twtie","dnmov","work");
        stringStream.sorted().forEach(System.out::println); // 사전순으로 정렬됨
        System.out.println();

        // Comparator 인터페이스를 람다식으로 구현하여 정렬한다.
        stringStream = Stream.of("abc","fwf","twtie","dnmov","work");
        stringStream.sorted((o1, o2) -> o1.length() - o2.length()).forEach(System.out::println); //compreator를 람다식으로 작성해줄 수 있다
        // 길이가 짧은 것부터 나온다.

        System.out.println();
        // 매핑(Mapping) - 입력 1 : 1 출력-------------------------------------
        // Funtion 계열의 인터페이스를 사용하여 스트림의 각 요소를 매핑
        stringStream = Stream.of("abc","fwf","twtie","dnmov","work");
        // Funthion 계열로 string -> integer 로 변환하는 매핑 Funthoin(<String,Intger)와 같은 형식을 람다식에 넣어준 것이다.
        Stream<Integer> stream2 = stringStream.map(s->s.length()) ;//입력은 string 출력은 원하는대로 할수 잇다
        stream2.forEach(System.out::println);

        // PStream (기본형 타입의 스트림)은 Operator 계열로 처리(자료형 반환x)
        // 입출력 값이 똑같기 때문에
        IntStream intStream3 = IntStream.of(5,2,30,8,0,2,-34);
        IntStream intStream4 = intStream3.map(value -> value * 10); // 형 변환이 되지 않고 입력이 출력이 된다.
        intStream4.forEach(System.out::println);

        // flatMap 계열 매핑 - 입력1 : n출력 (스트림 형태로 출력)
        // s.split("") : "java" -> {"j","a","v","a"}
        List<String> list2 = Arrays.asList("java","backend","best","course");
        list2.stream().flatMap(s -> {
           return Arrays.stream(s.split(""));
        }).forEach(System.out::println); //foreach를 사용하면 스트림이 끝나게 된다.

        // 조회 (Peek)- 중간 결과를 출력해 볼 수 있다(디버깅 가능)------------
        // peek() -> Consumer 계열의 람다식 입력을 받아 입력 요소를 소비
        // peek()는 입력받아 스트림과 동일한 스트림을 다시 출력
        System.out.println(list2.stream().flatMap(s -> {
            return Arrays.stream(s.split(""));
        }).peek(s-> System.out.println("flatMap():"+s))
                .distinct().peek(s-> System.out.println("distinct():"+s))
                .count()); // 함수형 프로그래밍은 선언형이어서 어떻게 해라라고 말해야한다.
        // flatmap은 모든 요소를 다 나타내고 distinct는 중복된것을 없애고 하나만 출력됨.
        // peek - 스트링 중간중간에 출력을해줘서 스트림 디버깅을 위해 출력을볼수있다.



        // 최종처리 메소드 - 스트림을 반환하지 않고, 스트림이 다끝나는 것 void일 수 있고, 무언가를 리턴할 수 있다

        // 매칭 계열 - boolean 타입의 값을 리턴한다. ------------------------
        // allMatch(), anyMatch(), noneMatch()
        // Predicate 계열 람다식을 입력받아
        // allMatch(Predicate<T> predicate) : 모든 요소가 true일 경우 true를 리턴한다.
        // anyMatch(Predicate<T> predicate) : 하나라도 요소가 true 일 경우 true를 리턴한다.
        // noneMatch(Predicate<T> predicate) : 모든 요소가 false이면 true를 리턴한다.

        Stream<String> st0 = Stream.of("abc","cde","efg");
        System.out.println(st0.allMatch(s -> s.equals("abc"))); //false
        st0 = Stream.of("abc","cde","efg");
        System.out.println(st0.anyMatch(s -> s.equals("cde"))); //true
        st0 = Stream.of("abc","cde","efg");
        System.out.println(st0.noneMatch(s -> s.equals("abcde")));//true

        // 집계 (통계)----------------------
        // 기본형 스트림(Int, Long, Double) - count(), sum(), average(), min(), max()
        // Stream<T>타입 스트림 - count(), min(), max() -> (min,max는 Comparator 구현이 필요하다) (o1,o2)->~~~~

        // reduce() 메소드 -> 사용자 정의 집계 메소드
        System.out.println(IntStream.range(0,10).reduce(0,(value1,value2)->value1+value2)); //sum()
        // 1,2와 더한걸 3이랑 더하고 12 와 3을 더한걸 4 와 더함 .. 누적해서 더하는 것이다.
        // identity가 사용이 되는 이유는 처음 identitiy와 1을 더한값과 2를계산하기위해서
        // 0 [0,1,2,3,4,5,6,7,8,9] 0+0 =0 -> 0+1->1 1+2-> 3 과같은 연산 0~9까지 더한 연산
        // sum()으로도 나타낼 수 있다 sum,min,max등등 도 reduce로 구현되어 있다
        System.out.println();

        System.out.println(IntStream.range(0,10).reduce(Integer.MAX_VALUE,(value1,value2)->value1<value2? value1:value2));// min()

        // 반복 - 소비------------------------
        // forEach() - Consumer 계열의 람다식을 입력받아, 각 요소를 소비
        // forEach()는 void를 출력


        // 수집 - Collection으로 변환하는 collect() 메소드
        // Stream API는 JCF을 STREAM으로 변환해서 처리하고 결과를 내는 것이다
        // 결과가 출력일 수도 있고 값일 수도 있고, 다시 컬렉션으로 만들고 싶을 수 있다.
    }
}
