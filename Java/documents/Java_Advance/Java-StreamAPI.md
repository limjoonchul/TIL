# 스트림 API(Stream API)
## 스트림 API
* Java 8에서 추가된 Java.util.stream 패키지
* 컬렉션의 요소를 람다식으로 처리할 수 있도록 하는 함수형 프로그래밍 도구
### 스트림의 특징
* 스트림은 한번밖에 사용 못한다. 내부적으로 몇번째 동작을 하고 있는지 체크하는 것이 들어가있다.
  * 그래서 사용할 때마다 다시 정의해주고 사용해야 한다.
* 간결한 코드로 작성할 수 있다.
```groovy
// 기존의 Java 7방식의 작성 방법
List<String> list = Arrays.asList("fast","campus","rocks");
List<String> newList = new ArrayList<>();

for (String s : list){
    newList.add(s.toUpperCase());
}
for (String s : newList){
    System.out.println(s);
}
```
```groovy
// java 8 - Stream API -> 훨씬 더 간결한 코드로 작성할 수 있다.
List<String> list1 = Arrays.asList("fast","campus","rocks");
Stream<String> stream = list1.stream(); //스트림으로 변환해준다.

// 스트링에서 스트링으로 매핑해준다.
//uppercase를 string에 매핑 각각의 요소에 이런것들을 적용해라
stream.map(String::toUpperCase).forEach(System.out::println); // foreach는 Consumer를 받는다,각각의 요소를 하나씩 출력한다.
// 긴코드는 그냥 작성할 수 있는데 간결한 방식은 많은 고민이 필요하다
```
* 데이터 소스에 대한 공통된 접근 방식 제공.
  * Stream으로 변경해주고 나면, List, Set, Map 모두 동일한(표준화된) 방식으로 데이터를 접근 가능.
  * 컬렉션 프레임워크는 각각을 다른 방식으로 데이터를 처리 해줘야 한다.
  * 특히 Stream::sorted() 메소드는 공통된 정렬 방식을 제공한다.
* 중간 처리와 최종 처리를 지원한다.
  * Mapping, Filtering, Sorting 등 중간 처리 지원(여러 개 사용이 가능하다.)
  * Iteration, Count, Average, Summation, Reduce 등 최종 처리 지원(마지막에 하나 사용 가능하다.)
* 데이터 소스와 처리 결과를 분리
  * 원본 데이터를 유지하여 처리에 의한 부작용을 방지한다.
  * 처리 결과를 새로운 컬렉션으로 저장이 가능하다.
## 스트림 API를 이용한 자료 처리
### 스트림 API의 종류

| 종류 |	처리 대상 |
| --- | ------- |
| `Stream<T>` | 일반적인 객체를 처리 |
| `IntStream` | 기본 자료형 `int`를 처리 | 
| `LongStream` | 기본 자료형 `long`을 처리 |
| `DoubleStream` | 기본 자료형 `double`을 처리 |
### 스트림 객체 생성 메소드
* 데이터 소스로부터 스트림 생성

| 데이터 소스 | 메소드 |
| --------- | ------- |
| Collection |	default Stream<E> stream() |
| Collection |	default Stream<E> parallelStream() |
| Arrays |	public static <T> Stream<T> stream(T[] array) |
| Arrays |	public static <T> Stream<T> of(T ... values) |
| Arrays |	public static IntStream stream(int[] array) |
| Arrays |	public static IntStream of(int ... values) |
| Arrays |	public static LongStream stream(long[] array) |
| Arrays |	public static LongStream of(long ... values) |
| Arrays |	public static DoubleStream stream(double[] array) |
| Arrays |	public static DoubleStream of(double ... values) |

* 정수 범위와 java.util.Random으로부터 생성

| 구분 |	메소드 |
| ---- | ---- |
| int형 범위 | public static IntStream range(int startInclusive, int endExclusive) |
| int형 범위 | public static IntStream rangeClosed(int startInclusive, int endInclusive) |
| long형 범위 | public static LongStream range(long startInclusive, long endExclusive) | 
| long형 범위 | public static LongStream rangeClosed(long startInclusive, long endInclusive) |
| Random p형 값 | public PStream ps() |
| Random p형 값 | public PStream ps(long streamSize) |
| Random p형 값 | public PStream ps(long streamSize, p origin, p bound) |

#### 스트림 생성 방식 예제
* 컬렉션의 인스턴스 메소드 stream()
```groovy
Stream<String> stream1 = list1.stream();
```
*  Arrays 클래스의 클래스 메소드 stream() 을 이용
```groovy
 // 함수형 인터페이스랑 비슷함 P Type
 // LongStream, DoubleStream 도 있다.
 int[] ints = {4, 6, 2, 19, 2, 58, 4, 6, 5};
 IntStream intStream = Arrays.stream(ints);
 intStream.forEach(System.out::println);
```

* 위의 두 개의 다른점
 * `Stream<Integer>`로 할 경우, `Primitive Type`이들어올 때 오토박싱으로 변경되고 다시 출력할 때 언방식이 되는데 이럴 때 오버헤드가 발생한다.
 * `IntStream`을 사용 할경우, 오버헤드 필요 없이, `WrapperClass`없이 사용 가능하다. 그래서 효율적이다.
 
* Stream 클래스의 클래스 메소드 `of()`를 이용해서 `Collection`을 걸치지 않고도 스트림을 생성 가능
```groovy
DoubleStream doubleStream = DoubleStream.of(0.4, 0.6, 0.2, 1.2, 0.94);
doubleStream.forEach(System.out::println);
```
* range()를 이용한 스트림 -> for i문 (for(int i=0...))을 대체하는 스트림
* rangeClosed() : 0~10까지 10을 포함한다.
* 순서대로 값을 출력한다.
```groovy
IntStream intStream1 = IntStream.range(0,10); // 0~9까지 10은포함되지 않는다.
intStream1.forEach(System.out::println);

IntStream intStream2 = IntStream.rangeClosed(0,10); // 0~10까지 10포함된다.
intStream2.forEach(System.out::println);
// LongStream도 range, rangeClosed 가 있다.
```

* Random 객체를 이용한 스트림이 있다. Java.util 패키지 안에 있다.
```groovy
Random random = new Random();
// LongStream longStream =  random.longs();
// longStream.forEach(System.out::println); // 개수 제한 없이 무한히 출력

// 개수 제한 가능
LongStream longStream1 =  random.longs(100);
longStream1.forEach(System.out::println); // 개수를 정해질 수있다 100개

// 개수제한 + 범위 제한 가능 젤 많이 사용함
LongStream longStream2 =  random.longs(100,0,1000);
longStream2.forEach(System.out::println); // 개수를 정해질 수있다 0~1000까지 100개를 출력
```

### 중간 처리 메소드
* 중간 처리 메소드 이후에 최종 처리 메소드를 붙여서 사용하는 것 같다.
| 동작 |	메소드 |
| --- | ----- |
| 필터링 | dinstict(), filter() |
| 자르기 | skip(), limit() |
| 정렬 |	sorted() |
| 매핑 |	flatMap(), flatMapToP(), map(), mapToP(), asDoubleStream(), asLongStream() |
| 조회 |	peek() |

#### 필터링
##### 필터링은 스트림의 일부 요소를 제거하는 역할을 한다.
```groovy
Stream<String> stringStream = Stream.of("Java","Is","Fun","Isn't","It","?","Java");
stringStream.forEach(System.out::println); // 기본형 중복이 포함되서 출력됨.
```
* distinct() : 스트림에 같은 요소가 있을 경우 하나만 남기고 삭제하는 메소드
```groovy
stringStream.distinct().forEach(System.out::println);
// stringStream.distinct() 여기까지 중간처리메소드, foreach는 최종처리 메소드
```        
* filter() : Predicate 계열을 입력을 받아, true인 요소만 남긴다.
```groovy
stringStream = Stream.of("Java","Is","Fun","Isn't","It","?");
stringStream.filter(s->s.length()>=3).forEach(System.out::println);
```
         
#### 자르기
##### 자르기는 스트림의 일부 요소를 한번에 생략한다.
* skip(long n) : 스트림의 처음부터 n개의 생략하는 메소드 n개 자른다.
* limit(long maxsize) : 스트림의 최대 요소 개수를 maxsize를 제한한다, maxsize의 요소보다 뒤에 있는 값들은 짤린다.

#### 정렬
##### 스트림 요소의 `compareTo()` 또는 입력받은 `Comparator`를 이용해 정렬한다.
```groovy
// Comparable 객체를 정렬한 스트림 반환
stringStream = Stream.of("abc","fwf","twtie","dnmov","work");
stringStream.sorted().forEach(System.out::println); // 사전순으로 정렬됨
```
```groovy
// Comparator 인터페이스를 람다식으로 구현하여 정렬한다.
// Comparator를 이용하여 정렬된 스트림 반환
stringStream = Stream.of("abc","fwf","twtie","dnmov","work");
stringStream.sorted((o1, o2) -> o1.length() - o2.length()).forEach(System.out::println); 
//compreator를 람다식으로 작성해줄 수 있다, 길이가 짧은 것부터 나온다.
```

#### 매핑
##### Funtion 인터페이스를 이용해 요소의 값을 변환한다.
* map 계열 - 입력 1 : 1 출력
  * `<R> Stream<R> map(Function<? super T, ? extends R> mapper)` : 기존 스트림의 T 타입 요소를 R 타입으로 변환하여 새로운 스트림 반환
  ```groovy
   // Funtion 계열의 인터페이스를 사용하여 스트림의 각 요소를 매핑
   stringStream = Stream.of("abc","fwf","twtie","dnmov","work");
   // Funthion 계열로 string -> integer 로 변환하는 매핑 Funthoin(<String,Intger)와 같은 형식을 람다식에 넣어준 것이다.
   Stream<Integer> stream2 = stringStream.map(s->s.length()) ;//입력은 string 출력은 원하는대로 할수 잇다
   stream2.forEach(System.out::println);
   ```
  * `PStream mapToP(ToPFunction<? super T> mapper)` : R이 기본형 타입으로 제한된 map()
  ```groovy
  // PStream (기본형 타입의 스트림)은 Operator 계열로 처리(자료형 반환x)
  // 입출력 값이 똑같기 때문에
  IntStream intStream3 = IntStream.of(5,2,30,8,0,2,-34);
  IntStream intStream4 = intStream3.map(value -> value * 10); // 형 변환이 되지 않고 입력이 출력이 된다.
  intStream4.forEach(System.out::println);
  ```
* flatMap 계열 - 입력 1 : n 출력(스트림 형태로 출력한다.)
  * `<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)` : 스트림의 T 타입 요소가 n개의 R 타입 요소로 매핑된 새로운 스트림을 반환
  * `PStream flatMapToP(Function<? super T, ? extends PStream> mapper)` : R이 기본형 타입으로 제한된 flatMap()
  ```groovy
  // s.split("") : "java" -> {"j","a","v","a"}
  List<String> list2 = Arrays.asList("java","backend","best","course");
  list2.stream().flatMap(s -> {
      return Arrays.stream(s.split(""));
  }).forEach(System.out::println); //foreach를 사용하면 스트림이 끝나게 된다.
  ```
#### 조회
##### 스트림 처리 과정에 영향을 미치지 않으며, 중간 결과를 확인할 수 있으며 입력받은 스트림과 동일한 스트림을 반환한다.
* Stream<T> peek(Consumer<? super T> action)
  * peek() -> Consumer 계열의 람다식 입력을 받아 입력 요소를 소비
  * peek()는 입력받아 스트림과 동일한 스트림을 다시 출력
```groovy
System.out.println(list2.stream().flatMap(s -> {
     return Arrays.stream(s.split(""));
}).peek(s-> System.out.println("flatMap():"+s))
           .distinct().peek(s-> System.out.println("distinct():"+s))
           .count()); // 함수형 프로그래밍은 선언형이어서 어떻게 해라라고 말해야한다.
// flatmap은 모든 요소를 다 나타내고 distinct는 중복된것을 없애고 하나만 출력됨.
```
### 최종 처리 메소드

| 동작 |	메소드 |
| --- | ----- |
| 매칭 |	allMatch(), anyMatch(), noneMatch() |
| 수집 |	collect() |
| 루핑 |	forEach() |
| 집계 |	count(), max(), min(), average(), sum(), reduce() |
| 조사 |	findFirst(), findAny() |

#### 매칭
##### Predicate 계열을 이용해 스트림 요소들이 특정 조건에 만족하는지 조사하는 메소드

* boolean allMatch(Predicate<? super T> predicate) : 스트림의 모든 요소가 Predicate를 만족하면 true를 반환
* boolean anyMatch(Predicate<? super T> predicate) : 스트림의 요소 중 하나라도 Predicate를 만족하면 true를 반환
* boolean noneMatch(Predicate<? super T> predicate) : 스트림의 요소 중 하나라도 Predicate를 만족하지 않으면 true를 반환

```groovy
        Stream<String> st0 = Stream.of("abc","cde","efg");
        System.out.println(st0.allMatch(s -> s.equals("abc"))); //false
        st0 = Stream.of("abc","cde","efg");
        System.out.println(st0.anyMatch(s -> s.equals("cde"))); //true
        st0 = Stream.of("abc","cde","efg");
        System.out.println(st0.noneMatch(s -> s.equals("abcde")));//true
```

#### 집계 (통계)
* 기본 집계 메소드
   * 기본형 스트림의 통계 : count(), sum(), average(), min(), max()
   * T 타입 스트림의 통계 : count(), min(), max() (min, max의 경우 Comparator 필요)
* reduce() 메소드 : 사용자 정의 집계 메소드
  * Optional<T> reduce(BinaryOperator<T> accumulator) : accumulator를 수행하고 Optional<T> 타입 반환
  * T reduce(T identity, BinaryOperator<T> accumulator) : identity를 초기값으로 하여, accumulator를 이용해 집계 연산
  * <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) : combiner를 이용해 병렬 스트림 결합

```groovy
System.out.println(IntStream.range(0,10).reduce(0,(value1,value2)->value1+value2)); //sum()
// 1,2와 더한걸 3이랑 더하고 12 와 3을 더한걸 4 와 더함 .. 누적해서 더하는 것이다.
// identity가 사용이 되는 이유는 처음 identitiy와 1을 더한값과 2를계산하기위해서
// 0 [0,1,2,3,4,5,6,7,8,9] 0+0 =0 -> 0+1->1 1+2-> 3 과같은 연산 0~9까지 더한 연산
// sum()으로도 나타낼 수 있다 sum,min,max등등 도 reduce로 구현되어 있다

System.out.println(IntStream.range(0,10).reduce(Integer.MAX_VALUE,(value1,value2)->value1<value2? value1:value2));// min()
```
* java.util.Optional<T>
  * T 타입 객체의 null 여부에 따라 다르게 동작하는 Wrapper 클래스
  * Optional 클래스의 정적 메소드를 이용해 Optional 객체 생성
    * `public static <T> Optional<T> of(T value)` : value가 null인 경우 NullPointerException을 발생시키는 Wrapping 메소드
    * `public static <T> Optional<T> ofNullable(T value)` : value가 null인 경우 empty()의 결과를 리턴하는 Wrapping 메소드
    * `public static <T> Optional<T> empty()` : 값이 비어있는 Optional 객체를 리턴
  * Optional 객체를 처리하는 메소드
    * `public T get()` : Optional의 값을 리턴하며, null일 경우 NullPointerException 발생
    * `public void ifPresent(Consumer<? super T> consumer)` : Optional 값이 null이 아닐 경우 consumer를 이용해 소비한다.
    * `public T orElse(T other)` : Optional의 값이 null일 경우 other를 반환한다.
    * `public T orElseGet(Supplier<? extends T> other)` : Optional의 값이 null일 경우 Supplier를 통해 공급받은 값을 반환한다.
    * `public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X` : Optional의 값이 null일 경우 exceptionSupplier에서 공급받은 예외를 throw
#### 반복
##### forEach() 메소드로 스트림 요소를 순차적으로 Consumer<T>를 이용해 소비
* `void forEach(Comsumer<? super T> action)` : 스트림의 각 요소를 action으로 소비
#### 조사
##### 첫번째 요소 또는 아무 요소를 조사하는 최종 처리 메소드. 필터링 등으로 인해 스트림에 요소가 남아있는지 확인할 수 있다.
* `Optional<T> findFirst()` : 스트림의 첫 요소 또는 empty Optional 객체를 반환
* `Optional<T> findAny()` : 스트림의 아무 요소나 가지는 Optional 객체를 반환

    
    