package com.company.s10;

import java.util.*;

/**
 * Java Collections Framework (JCF)
 * - java.util에 속한 일련의 클래스, 자료구조를 담당
 *   - 자료구조 : 자료의 집합 또는 그 집합의 동작을 정의하는 수학적 모델
 *   자료가 어떻게 모여있는지 모여있는 자료에대해 동작을 정의하는거 sort나 w자료의 입력이나 제거
 *   두개의 자료의 자리를 바꾸는 것 등등 맵 셋 등등이 동작에 해당한다.
 * - 제네릭 클래스로 되어 있어, 다양한 객체를 요소(Element)로 담을 수 있다.
 *   - 요소: 자료구조를 구성하는 하나하나의 자료
 */

public class Main {
    public static void main(String[] args) {
        // List 인터페이스
        // - Collection 인터페이스 상속
        // - 순서가 있는 데이터의 집합, 데이터 중복 허용
        // - 데이터의 순서(index)가 유일한 데이터의 구분자(identifier)로 사용
        // [1,4,2,5,6,2,1] 이런 데이터가 있을 수 있다. -> 같은 값이 있으나. index가 다름
        List<String> stringList = new ArrayList<>(); // 가장 많이 쓰이는, 배열 기반의 리스트
        List<String> stringList2 = new LinkedList<>(); // 노드의 연결로 구성된 리스트 자료를 하나 넣을 때 노드형태로 넣는다
        // 양방향이어서 앞의 노드와 뒤의 노드를 기리킨다.
        List<String> stringList3 = new Vector<>(); // 싱크로나이즈드 - 멀티스레드 환경에서 안전하게 동작하게 되어있는 것, 무지 느리다( 잘 안쓴다)
        List<String> stringList4 = new Stack<>(); // Stack 자료구조 구현 -> 자료구조 때 봅시다.

        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            intList.add(i); // List의 맨 뒤에 자료를 추가한다.
                            // List는 맨 뒤에 자료 추가(순차 처리)가 가장 빠르다.
                            // o(1) 뒤에 하나하나넣으니 앞에서 넣는건 o(n) 그래서 뒤에 넣는게 더 빠르다.
        }
        System.out.println(intList);
        System.out.println(intList.size()); //intList의 길이를 나타내준다.
        intList.add(2,678493); //  인덱스에 해당하는 위치에 값을 넣게되고 그위치에있던 값들은 뒤로 밀림
        // 중간 index에 add할 경우 자료를 뒤로 한칸씩 민다.

        System.out.println(intList);
        System.out.println(intList.size());
        // 당연한게 아니고 뒤로밀리는게 특별한 것이다. 수학적개념이있어서 되는것이다

        List<Integer> intList2 = new LinkedList<>();
        for (int i = 5; i < 10; i++) {
            intList2.add(i);
        }
        System.out.println(intList2);

        // 입력된 컬렉션 내용 전체를 한번에 add하는 메소드
        // index를 입력받아 위치도 지정 가능.
        intList.addAll(intList2); //컬렉션에 있느낸용을 통째로 집어넣는것이다
//        intList.addAll(1,intList2);
        System.out.println(intList);
        // intlist 뒤에 intList2 를 더해줌 한번에 합치는 것이다. 인덱스를 입력해서 할 수도 있음

        System.out.println("ind 3: " + intList.get(3)); // 인덱스의 값을 가져올 수 있다.
        System.out.println(intList.indexOf(9)); //객체를 입력해서 리스트의 어느 위치에 있는지 리턴해준다
        // 객체를 찾아 가장 첫번째 인덱스를 반환한다.

        System.out.println(intList.lastIndexOf(9)); // 객체를 찾아 가장 마지막 인덱스를 리턴한다.

        // remove()메소드는 index에서 obj를 제거하고 그것을 반환한다.
        // 요소가 제거되면, 그 뒤 요소들은 모두 index가 하나씩 앞으로 당겨진다. 중간이 비어있지 않는다.(순서가 있기 때문에 빈칸이 있을 수 없다)
        System.out.println("index 9 was: "+ intList.remove(9)); // 인덱스를 입력해서 해당 인덱스의 값을 `제거하고` 반환해준다.
        System.out.println(intList);

        // 배열처럼 index의 값을 value로 대체한다.
        intList.set(1,100); // 인덱스의 위치의 값을 입력받는 값으로 대체한다
        System.out.println(intList);

        // 앞에껀 inclusive, 뒤는 exclusive 2번 인덱스 에서 4번 인덱스까지 가져옴
        List<Integer> list3 = intList.subList(2,5);
        System.out.println(list3);

        // fori를 이용한 접근
        for (int i = 0; i < list3.size(); i++) {
            System.out.println(list3.get(i));
        }

        // foreach를 이용한 접근
        for (int value: list3) {
            System.out.println(value);
        }

        // listIterator를 이용한 접근 - foreach문은 사실 이것을 짧게 쓴 것
        ListIterator<Integer> iter = list3.listIterator(); //iterator에 스트림처럼 값이 담겨져있다.
        // 다만 값을 복사해오지 않는다 원본 컬렉션에 잇는 자료를 그대로 가져오는 역할을 한다.
        // 스트림 api는 원본을 보존한다.
        while (iter.hasNext()){// true일 때만 다음 요소가 있음
           Integer integer = iter.next(); // 다음 요소를 반환. 값을 가져오는데 더가져오지않으면 exceptiio이 발생한다
            System.out.println(integer);
        }
//        for (ListIterator<Integer> it = iter; it.hasNext();){
//
//        }


        // Set 인터페이스
        // - Collection 인터페이스 상속
        // - 순서가 없는 데이터 `집합`을 다루는 인터페이스 집합이다
        // - 중복되는 데이터를 효율적으로 제거하는데에 사용 가능.
        // - 중복을 검사하는 수단 1. hashCode(), 2. equals() 이퀄이 같다면 해쉬코드가 같아야 한다는 것 을기반
        // - hash를 빠르게 계싼해서 hash만 비교
        // - 그 다음에 판정이 안나면 equlas()로 추가 비교

        Set<String> stringSet1 = new HashSet<>(); // 기본적인 집합
        stringSet1.add("A");
        stringSet1.add("B");
        stringSet1.add("B");
        stringSet1.add("F");
        System.out.println(stringSet1);
        // List에 있는것 중 중복되는 것을 제거하고싶을 때
        // 이중 for문을 이용해서 모두 비교해서 같은게 있으면 삭제한다 O(n^2)
        // Set을 이용하면 List에 있는걸 Set으로 한번씩만 옮기면 중복이 제거가 된다.
        // 1중 for문을 이용해서 비교할 필요 없이 알아서 삭제 됨 O(n)

        NavigableSet<String> stringSet2 = new TreeSet<>(); // 이진 탐색 트리
        // 정렬을 자동으로 해주는 기능이 있다.
        // 네비게이블셋으로 받아주면 모든 기능을 받을 수 있고
        // 셋으로 받으면 모든 기능을 받지 못함 셋이 네비게이션셋의 부모여서
        // 네비게이션셋에 정의되어있는건 셋에서 사용못함
        // 자료를 넣어놓고 내가원하는 자료를 빨리 찾고싶을 때 사용한다.
        // 들어오는 값을 Comparable 한던지, Comparator를 구현해서 넣어주던지 해야한다
        // 값이 어떤것이 더 큰지 판단해서 정렬해서 넣어줄 수 있다 - 이진 탐색 트리



        class Foo{
            int x, y;

            public Foo(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public String toString(){
                return x + ", "+ y;
            }
        }
        NavigableSet<Foo> set = new TreeSet<>(Comparator.comparingInt(o -> o.x));
        // 자동으로 소팅이되서 값이 들어가게 된다 따로 소팅할 필요 없음
        // 여기서 x를 비교해서 넣게 구현해놨음

        set.add(new Foo(1,100));
        set.add(new Foo(4,50));
        set.add(new Foo(0,170));
        set.add(new Foo(-2,3300));

        System.out.println(set.first()); // 정렬된것중 가장 첫번째 요소가 출력됨
        System.out.println(set.last()); // 정렬된것중 가장 마지막번째 요소가 출력됨
        System.out.println(set.lower(new Foo(1,500)));
        // 1보다 작은 값을 찾는다 1가없어도 작은 값을 찾는다? 이거보다 하나 작은걸 찾아서 반환한다.
        System.out.println(set.higher(new Foo(2,500)));
        // 2보다 하나 큰 거를 반환
        System.out.println(set.floor(new Foo(1,500)));
        // floor는 같은 값이 있으면 같은 값을 반환해준다 lower는 하나 작은걸 반환
        System.out.println(set.ceiling(new Foo(1,500)));
        // ceiling도 같은게 있으면 같은 값을 반환해주고 없으면 위에거반환


        // poll은 셋에서 삭제도 같이한다. 처음거를 가져오는데 처음걸 가져오면서 삭제한다.
        // 더 지울게 없으면 null이 나온다. 보통 객체를 반환할 때 없으면 null이나오고 자연수는 -1이 반환
        System.out.println(set.pollFirst());
        System.out.println(set.pollFirst());
        System.out.println(set.pollFirst());
        // log n - 원래는 일자로 0-0-0-0-0 이렇게 되어있는걸 이진탐색트리 그림처럼
        // 일자가 logn 이라면 이진탐색트리는 log2 n 이된다 선택에 의해서 더 짧은 길이를 가게 된다
        //5:36



        // Map 인터페이스
        // - Collection 인터페이스를 상속하지 않는다.
        // - key, value 쌍으로 이루어진 자료의 집합을 다루는 인터페이스
        //   - Map.Entry<k,v> 맵의 엔트리라는 제네릭클래스로 구성되어 있다
        // - key를 기준으로보면 셋이랑 비슷함, 중복될 수 없으며 value는 중복이 가능
        //   - key가 indetifier 역할을 한다.

        Map<String, Integer> map = new HashMap<>();
//        NavigableMap<String, Integer> map2 = new TreeMap<>(); // 트리셋과 비슷함
        // 키를 기준으로봤을 때 맴이랑 셋이 걱의 비슷하다.
        // 해쉬가 비교대상이 되고 벨류가 붙여서 이루어진다.

        // PUT은 기존에 동일 키 값이 있었으면 기존 value를 반환, 없었으면 null
        System.out.println(map.put("ABCDE",5)); // null
        System.out.println(map.put("CDEF",1023)); // null
        System.out.println(map.put("ABCDE",1023)); // 5

        System.out.println(map.get("CDEF")); // 키라는 기준으로 검색을해서 값을 찾아온다 이런걸 쿼리(query,질의)라고 부름
        System.out.println(map.getOrDefault("CDEF",0)); // 가져올 키가 있으면 기존 값을 가져오고 없으면 defaultValue를 반환


        // 이렇게 기존 값이 없을 때 0 등으로 기본 값을 설정하고 싶으면 편리함
        map.put("ABCDE", map.getOrDefault("ABCDE",0)+1);
        // 값이 있으면 기존 값 +1 알고리즘 할 때 도움이 많이 된다. 꿀팁!!


        // keySet 사용 키로 구성된 셋을 받아온다
        for (String key : map.keySet()){
            System.out.println(key+" : "+ map.get(key));
        }

        // entrySet 사용 키와 벨류를 같이 받와와서 함 속도가 조금 더 빠름 이게 더 좋은 방법
        for (Map.Entry<String,Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        NavigableMap<String, Integer> map2 = new TreeMap<>(); //Comparator를 구현해서 넣어주면 된다 비교할 때
        map2.put("a",10);
        map2.put("g",12);
        map2.put("z",14);
        map2.put("z",114);
        map2.put("c",165);

        System.out.println(map2.ceilingKey("b")); //b보다 큰것 키 값만 반환
        System.out.println(map2.ceilingEntry("b").getValue());// 엔트리 전체를 받아와서 value를 찾는다.
        System.out.println(map2.pollFirstEntry().getValue());
        System.out.println(map2.pollFirstEntry().getValue());
        System.out.println(map2.pollFirstEntry().getValue());
        System.out.println(map2.pollFirstEntry().getValue());
        // 메모리상에서도 삭제한다 값을 삭제


        // 중복이 허용되지 않아서 값을 뒤에 넣은 값으로 넣어서 출력이됨
        System.out.println(map2);



        // 번외로 알아두세요
        Map<String,Integer> map3 = new Hashtable<>(); // Vector처럼 옛날 구현 싱크로나이즈드
        Properties prop = new Properties(); // 해쉬테이블<String,String> 을 상속하고 있다.
        System.getProperties(); // System의 property가 이 형식으로 제공됨.

    }
}
