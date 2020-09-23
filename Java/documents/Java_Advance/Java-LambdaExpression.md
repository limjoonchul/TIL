# 람다식(Lambda Expression)
## 람다식
* Java1.8에서 추가된 함수형 프로그래밍 기법이다.
* 객체지향 프로그래밍과 다르게, 비즈니스 로직만을 빠르게 구현하는 특징.
* 객체지향 언어인 Java에서 메소드를 함수처럼 사용하는 형식.

## 람다식의 예
### 배열의 정렬
* 기본 정렬 방식을 이용한 정렬
```groovy
  String [] strings = {"fast","campus","java","bacend","choigo","best","people"};
  System.out.println(Arrays.toString(strings));
  Arrays.sort(strings);// 정렬할 수 있다. 사전순으로 Arrays로 정렬하는거 하나 배움
  System.out.println(Arrays.toString(strings));
```

* Comparator 클래스를 만들고, 객체를 생성하여 전달하는 방식
```groovy
class MyComparator implements Comparator<String> {

   // string을 비교하기 위한 메소드를 인터페이스가 가지고 있어서
   // 인터페이스를 구현하면 sort에 넘겨주면 사전순으로 되어있던걸
   // 추가로 객체를 생성해서 넣어주면 새로 만든 메소드가 정렬하는 방식을 다르게 할 수 있다.

   @Override
   public int compare(String o1, String o2) {
       return o1.substring(1).compareTo(o2.substring(1));
       // 둘다 짤라서 시작문자가 1번 두번째 인덱스자리를 서로 비교하는 것
       //commpareto는 comaprapble 인터페이스에있는것 을가져옴 이미 스트링에 정의되어있음
   }
 }
 //기준을 바꾸고 싶을 때 방법은 새로운 comparator를 넣어줘서
 // 객체를 정렬하는 방법을 할 수 있다.
 Arrays.sort(strings, new MyComparator() );
 System.out.println(Arrays.toString(strings));
```
* 익명 내부 클래스를 이용할 수 있다.
  * 상속하고 싶은 인터페이스든 클래스를 적어준 다음에 블록을 열어서 재정의 해주면 작성할 수 있다.
```groovy
Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.substring(2).compareTo(o2.substring(2)); //필요한 내용은 이것이다.
            }
        });
        System.out.println(Arrays.toString(strings));
```

* 람다식을 이용한 방법
  * 클래스없이 작성이 가능한 익명내부클래스를 한번 더 줄임
  * 람다식을 이용하는게 가장 간단하고 하기 편함
  * sort를 보면 하나만 입력을 받으면 자체를 하게되고 두개를 입력받으면 comparator??
    두번째 파라미터는 comparator를 구현한 람다식이라는 것을 알고 있다.
```groovy
 Arrays.sort(strings, (o1, o2) -> o1.substring(3).compareTo(o2.substring(3)));
        // 이게 비즈니스로직 동작했으면 하는 코드만 작성하는 것

        System.out.println(Arrays.toString(strings));
```

* Comparable 인터페이스를 이용한 방법
```groovy
class Hansol implements Comparable<Hansol>{
     //String 을 상속이 안되서 따로 컴포지션해서 사용했다?
     String value;
  
     public Hansol(String value) {
           this.value = value;
     }
  
     @Override
     public int compareTo(Hansol o) {
         return value.substring(1).compareTo(o.value.substring(1));
         // 자기자신이갖고있는 value랑 외부에서들어온 객체 value랑 비교를 함.
         
     }
     @Override
     public String toString(){
        return value;
     }
}                      //스트링이 됬다면 이런 부부느 안해도 됬다.
Hansol[] hansols = {new Hansol("fast"),new Hansol("campus"),new Hansol("backend"),new Hansol("java"),new Hansol("choigo"),new Hansol("best"),new Hansol("people")};
Arrays.sort(hansols);
System.out.println(Arrays.toString(hansols));
```
* String 이 상속이 가능했다면 이용한 방법
```groovy
// 밑에 코드만 구현했으면 됬는데 string은 final로 되어있어서 상속이 안됨
class Fansol extends String{
    @Override
    public int compareTo(String o){

    }
}
```
## 람다식을 사용하기 위한 조건
* 아까는 Comparator 인터페이스를 구현 했는데 이번엔 직접 작성한 것이다.
* 람다식으로 사용하기 위해서는 단 하나의 추상 메소드를 가지고 있어야 한다.
  * 추상메소드가 2개이상이면 오류가 발생한다.
  * 디폴트 메소드는 구현이 되어 있더라도 상관이 없다.
  
```groovy
 @FunctionalInterface // 필수는 아니다. 하지만 적어주면 interface가 적합한지 확인해준다.
 interface Runner<T>{
     T run(); // 추상메소드는 단 하나만 있어야 한다. runner라는 객체를 넣어줫을때
     // run()을 실행할 때 우리가 값을 하나만 넣어서 보낼때 하나만잇어야 밑에 문장이 run()라고 확신 할수있다.
     // T runTwo() // 추상메소드가 2개 이상이면 오류 발생
     default void method(){} // 디폴트메소드가 구현이 되어 있더라도 상관이 없다.
 }


 public class Main {
                           // ? 라고 적어주면 어떤 것이든 상관이 없다.
     static void useRunner(Runner<?> runner){
         System.out.println(runner.run());
     }

     public static void main(String[] args) {
                                  //? 랑 같은 타입이여야 하는데 ?로써지면 뭐가 들어오든 상관 없음
 //         class MyRun implements Runner<String>{ 익명 내부 클래스
 // 
 //             @Override
 //             public String run() {
 //                 return null;
 //             }
 //         }
 //         useRunner(new MyRun());
         useRunner(() -> "This is how to use runner.");
     }
 }
```
## 다양한 람다식 표현 형식
```groovy
@FunctionalInterface
interface Runner{
    String run(String x);

}

@FunctionalInterface
interface RunnerTwo{
    String run();

}
public class Main {

    static void useRunner(String x, Runner runner){
        System.out.println(runner.run(x));
    }

    static void useRunnerTwo(RunnerTwo runner){
        System.out.println(runner.run());
    }

    public static void main(String[] args) {
         // 람다식의 표준 형식
        useRunner("안녕하세요!",(String x) -> {return x;}); //입력 파라미터의 자료형 입력 (String x) = Runner run {run(String x)} , {return x;} = run(String x)??{ return x;}

        // 입력 파라미터가 1개면 () 생략하는 형식
        useRunner("안녕하세요!", x -> {return x;});

        //  입력 파라미터가 없으면 () 생략 형식
        useRunnerTwo(() -> {return "안녕";}); 
 
        // 세미콜론이 들어가는 경우(여러 줄 작성할 때) 중괄호 필수이다. 이 때 return도 필요하다.
        useRunner("안녕하세요!",(x)->{
            return x; 
        });
 
        // Expression을 바로 쓰면 알아서 return을 해준다.(expression labmda)
        // 한줄로만처리되는 expression이라면 세미콜론 없이 처리가능.
        useRunner("안녕",x->x); 
    }
}
```

## 람다식과 익명 클래스 객체가 동일한 것은 아니다라는 것의 증명
* 람다식과 익명 내부 클래스가 비슷한 것이라고 했는데 각각 구현을 해서 this를 출력해보니 결과 값이 다르게 나온다.
  * 이유는 익명 내부 클래스로 구현을 하면 익명 내부 클래스의 객체가 생성이 되서 this가 그 객체를 가리키지만,
  람다식은 다르게 객체가 만들어지지 않아 Main의 객체가 만들어진다. 
* 이 부분만 다르고 완전히 똑같다고 생각하면 된다.  
```groovy
 interface IFoo{
     String method();
 }
 
 public class Main {
     static void funtionalMethod(IFoo foo){
         System.out.println(foo.method());
     }
 
     void methodA(){
         // 그외에는 둘이 완전히 똑같다고 생각하면된다 this만 다르다고 생각하면 된다.
         funtionalMethod(()->{
             System.out.println("this"+ this); // 이 this가 왜 main의 객체?
             // 익명클래스와 다르게 람다식은 익명클래스와 달리 클래스가 만들어지지 않는다.
             // 함수형 인터페이스로 동작을 한다.
             System.out.println("OuterClass.this"+Main.this);
             return "Lambad expression used.";
         });
        // 위 아래 출력 값이 다르다. this의 출력값이 다르다
         funtionalMethod(new IFoo() {
             @Override
             public String method() {
                 System.out.println("this"+ this); // 익명 클래스의 객체가 this가 된다.
                 // 익명클래스를사용할 때 실제로 익명클래스가 만들어지고 사용이되서 넣어진다는게 증명이된다.
                 System.out.println("OuterClass.this"+Main.this); //외부 클래스인 Main 객체의 this
                 return "Anonymous local inner class used.";
             }
         });
     }
 
     public static void main(String[] args) {
         new Main().methodA();
     }
 }
```
## 표준 함수형 인터페이스
* 자주 사용되는 함수형 인터페이스를 정의해 둔 API
* Consumer, Supplier, Function, Operation, Predicate 계열이 있다.

| 계열 |	입력 | 출력 |	메소드 |	설명 |
| --- | --- | --- | ----- | ---- |
| Consumer | O | X | void accept(T) | 입력을 소비 |
| Supplier | X | O | T get() | 출력을 공급 |
|Function|	O|	O|	T apply(R)|	입력 -> 출력 함수 매핑 |
|Operation|	O|	O|	T apply(T)|	입력을 연산하여 동일 타입의 출력으로 리턴 |
|Predicate|	O|	boolean| boolean test(T)| 입력을 판단 |

### 표준 함수형 인터페이스의 종류
#### Consumer 계열
 * 파라미터 입력을 받아서 그것을 소비하는 Funtional Interface이다.
   * 소비라는 것은 함수가 이용된다 라고 생각하면 된다. 리턴이 되지 않고
    함수 내에서 사용이 되고 새로운 출력으로 되는게 아니고 없어진다.
    그래서 소비라고 의미를 부여한 것이다.
     
| 인터페이스 | 메소드 |
| -------- | ----- |
| Consumer<T> |	void accept(T t) |
| BiConsumer<T, U> | void accept(T t, U u) |
| IntConsumer |	void accept(int value) |
| LongConsumer | void accept(long value) |
| DoubleConsumer | void accept(double value) |
| ObjIntConsumer<T> | void accept(T t, int value) |
| ObjLongConsumer<T> |	void accept(T t, long value) |
| ObjDoubleConsumer<T> | void accept(T t, double value) |
```groovy
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Consumer<String> consumer = (s)-> System.out.println(s);
        consumer.accept("A String.");

        BiConsumer<String, String> biConsumer = (t,u) -> System.out.println(t+","+u);
        biConsumer.accept("Hello","world");

        // 오토박싱/ 언방식 사용하면 비효율적이다.
        Consumer<Integer> integerConsumer = (x) -> System.out.println(x);
        integerConsumer.accept(10); // 값이 들어갈 땐 오토박싱 출력할 때 언박싱

        // 효율적으로 하기 위해서 IntConsumer 제네릭이 아니다 기본형 타입
        // 기본형 입력을 하려고 할 경우, PConsumber (p: primitive type)을 사용 가능.
        // 주의! 오버로딩이 아니고 별도의 인터페이스이다. 최적화를 위해서 불편하더라도 별도로 만들어 놓은 것이다.
        IntConsumer intConsumer = (x) -> System.out.println(x);
        intConsumer.accept(5); // 객체가 아니라 값을 입력을 받는 것이다. 기본자료형이니깐
        //LongConsumer, DoubleConsumer

        // t는 <>안에 값 x는 objIntconsumber의 int의 자료형이 들어간다.
        ObjIntConsumer<String> objIntConsumer = (t,x) -> System.out.println(t + ": "+ x);
        objIntConsumer.accept("x",1024);
        // ObjLongConsumer,ObjDoubleConsumer
        // 총 4가지 타입이 있다.
    }
}
```
#### Supplier 계열
* 아무런 입력을 받지 않고, 값을 하나 반환하는 함수형 인터페이스이다.
* 자료를 '공급'하는 공급자 역학을 한다.

| 인터페이스 | 메소드 |
| -------- | ----- |
| Supplier<T> | T get() |
| BooleanSupplier | boolean getAsBoolean() |
| IntSupplier | int getAsInt() |
| LongSupplier | long getAsLong() |
| DoubleSupplier |	double getAsDouble() |

```groovy
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

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
```

#### Funtion 계열
* Mapping : 입력 ->  출력을 연결하는 함수형 인터페이스
* 입력 타입과 출력 타입은 다를 수 있다.(다를 수 있다라는건 같을 수도 있다는 말도 된다.)

| 인터페이스 | 메소드 |
| -------- | ----- |
| Function<T, R> |	R apply(T t) |
| BiConsumer<T, U, R> |	R apply(T t, U u) |
| PFunction<R> |	R apply(p value) |
| PtoQFunction |	q applyAsQ(p value) |
| ToPFunction<T> |	p applyAsP(T t) |
| ToPBiFunction<T, U> |	p applyAsP(T t, U u) |

* P, Q 는 기본 자료형(Primitive Type) : Int, Long, Double
* p, q 는 기본 자료형(Primitive Type) : int, long, double

```groovy
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Function<String,Integer> func = (s) -> s.length();
        // s 는 String타입, s.length() 는 Integer
        System.out.println(func.apply("Strings")); //이것은 apply로 출력한다

        // Bi가 붙으면 '입력'을 2개 받을 수 있다는 의미이다.
        BiFunction<String,String,Integer> biFunction = (s,u) -> s.length() + u.length();
        System.out.println(biFunction.apply("one","two")); //6

        // IntFunction<R>은 리턴 자료형
        // P type Funtion은 입력을 P타입으로 받는다.
        IntFunction<String> intFunction = (value) -> String.valueOf(value);// "" + value도 가능.
        System.out.println(intFunction.apply(512));

        //ToP Type Function은 출력을 P타입으로 한다.
        ToIntFunction<String> funcFour = (s) -> s.length(); // 4:21
        System.out.println(funcFour.applyAsInt("abcde"));
        // 출력이 P타입인 경우에는 AsP가 들어간다.!!!
        //ToIntBiFunction<String,String>// int 출력을 하는 Bi 함수
        // P: Int, Long, Double

        // int 에서 double로 바꾸는 함수 PTOQFunction : P -> Q로 매핑하는 함수
        IntToDoubleFunction funcfive;
        // IntToIntFunction은 없다. 동일한 것에 대해서는 다른게 있다 4:23
    }
}
```
#### Operator 계열
* 입력받은 타입과 동일한 타입의 출력을 하는 함수형 인터페이스
* Funtion 계열과 달리 입출력 타입이 다를 수 없다.

| 인터페이스 | 메소드 |
| -------- | ----- |
| UnaryOperator<T> | T apply(T t) |
| BinaryOperator<T> | T apply(T t1, T t2) |
| IntUnaryOperator | int applyAsInt(int value) |
| LongUnaryOperator | long applyAsLong(long value) |
| DoubleUnaryOperator |	double applyAsDouble(double value) |
| IntBinaryOperator | int applyAsInt(int value1, int value2) |
| LongBinaryOperator | long applyAsLong(long value, long value2) |
| DoubleBinaryOperator | double applyAsDouble(double value, double value2) |

```groovy
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        // 그냥 operator는 없다.
        //입력이 1개 인 것을 Unary를 붙여서 표현
        UnaryOperator<String> operator = s -> s+"."; //리턴타입을 따로 입력받지 않는다 입출력이 같으니깐
        System.out.println(operator.apply("왔다")); // apply() 사용.

        BinaryOperator<String> operator1 = (s1,s2) -> s1 + s2;//타입은 하나만 입력받게 되어있다. 출력은 동일한 타입이여야 하니깐?
        System.out.println(operator1.apply("나","왔다"));

        IntUnaryOperator op = value -> value*10; //타입을 받지 않는다 어차피 int입력 int출력이니
        System.out.println(op.applyAsInt(5));
        // LongUnaryOperator, DoubleUnaryOperator

        IntBinaryOperator ibo = (v1,v2) -> v1 * v2;
        System.out.println(ibo.applyAsInt(10,20));
        //LongBinaryOperator, DoubleBinaryOperator
    }
}
```

#### Predicate 계열
* 논리 판단을 해주는 함수형 인터페이스
* 입력을 받아서 boolean 타입 출력을 반환한다.

| 인터페이스 | 메소드 |
| -------- | ----- |
| Predicate<T> | boolean test(T t) |
| BiPredicate<T, U> | boolean test(T t, U u) |
| IntPredicate | boolean test(int value) | 
| LongPredicate | boolean test(long value) |
| DoublePredicate | boolean test(double value) |

```groovy
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<String> predicate = (s) -> s.length() == 4; // 조건식이 들어가야 한다.
        System.out.println(predicate.test("Four")); // test()를 사용한다 true or false 값 출력
        System.out.println(predicate.test("six"));

        BiPredicate<String, Integer> pred2 = (s,v) -> s.length() ==v;
        System.out.println(pred2.test("abcd",23));
        System.out.println(pred2.test("abc",3));

        IntPredicate pred3 = x -> x > 0;
        //LongPredicate, DoublePredicate asP출력은 존재하지 않는다.

    }
}
```

### 표준 함수형 인터페이스의 메소드
#### andThen(), compose()
* 두 개 이상의 함수형 인터페이스를 연결하기 위해서 사용한다.
  * `A.andThen(B)` : A를 먼저 실행하고 B를 실행, Consumer, Function, Operator 계열의 default method로 구현
  ```groovy
   Consumer<String> c0 = s -> System.out.println("c0 :" + s);
   Consumer<String> c1 = s -> System.out.println("c1 :" + s);
   Consumer<String> c2 = c0.andThen(c1);
   c2.accept("String"); //동일한 스트링을 출력한다.
  ```
  
  * `A.compose(B)` : B를 먼저 실행하고 A를 실행, Function, Operator 계열의 default method로 구현
  ```groovy
   // Function 계열은 입력->출력 ==> 입력-> 출력 타입이 연쇄 되어야 한다.
   Function<String, Integer> func1 = s -> s.length();
   Function<Integer, Long> func2 = value -> (long)value;
   // func1의 입력이 String, 출력이 Integer이니깐, andThen()의 입력은 Integer여야 한다.
   Function<String,Long> func3 = func1.andThen(func2);
   System.out.println(func3.apply("four"));
   
   Function<String, Long> func4 = func2.compose(func1);
   System.out.println(func4.apply("four"));
  ```
#### and(), or(), negeate(), isEual()
* Predicate 계열의 기본 메소드
  * and(), or(), negate()
  * && , ||, ! 연산자의 동작을 한다.
  ```groovy
   // 객체에서 메소드로 접근한다.
   DoublePredicate p0 = x -> x > 0.5;
   DoublePredicate p1 = x -> x < 0.7;
   DoublePredicate p2 = p0.and(p1);
   DoublePredicate p3 = p0.or(p1); 
   DoublePredicate p4 = p0.negate();
   System.out.println(p0.test(0.9)); //true
   System.out.println(p1.test(0.9)); // false
   System.out.println(p2.test(0.9)); // false
   System.out.println(p3.test(0.9)); // true
   System.out.println(p4.test(0.9)); // false not p0
  ```
* Predicate 계열의 클래스 메소드
  * isEqual()
  ```groovy
   Predicate<String> eq = Predicate.isEqual("String");
   // 함수형 인터페이스를 사용할 수 있다. 람다식 사용 x
   // 들어오는 String이랑 eq랑 같은지 테스트해주는 함수형 인터페이스를 리턴해줌
   System.out.println(eq.test("String")); // true
   System.out.println(eq.test("String!")); // false
  ```
#### minBy(), maxBy()
* BinaryOperator 클래스의 클래스 메소드
  * Comparator<T>를 파라미터로 받아 최소값/최대값을 구하는 BinaryOperator<T>를 리턴
  ```groovy
  public class Main {
      public static void main(String[] args) {
          BinaryOperator<String> minBy = BinaryOperator.minBy((o1,o2)-> o1.length() > o2.length() ? 1 : -1);
          BinaryOperator<String> maxBy = BinaryOperator.maxBy((o1,o2)-> o1.length() > o2.length() ? 1 : -1);
          
          // BinaryOperator.minBy((String o1,String o2)-> o1.length()+o2.length()); 이것도 가능
          // 어떤걸 받아 줄건지 써줘야 한다.
          // 앞에 String타입을 넣어주면 뒤에서 o1,o2가 String 이라는 것을 추론할 수 있다.
  
          System.out.println(minBy.apply("abc","cd")); // 더 작은게 출력됨
          System.out.println(maxBy.apply("abc","cd")); // 더 큰게 출력됨
          System.out.println(minBy.apply("abc","cde")); // abc가 출려됨
      }
  }
  ```
#### 람다식에 메소드/ 생성자 사용하는 예
* 람다식에 기존에 구현되어 있는 내용을 재사용하고자 할 때 사용
  * 함수형 인터페이스를 재사용하지 못하는 단점을 보완하기 위해서 사용한다.
##### ClassName::instanceMethod
* 첫번째 파라미터를 객체로, 두번째 파라미터를 메소드 입력으로 사용한다.
```groovy
String[] strings = { "A", "B", "D", "C" };
Arrays.sort(strings, String::compareTo);
```
##### ClassName::classMethod
* 클래스 메소드의 입력으로 모든 파라미터가 사용됨
```groovy
Function<String, Integer> parser = Integer::parseInt;
```

##### instance::instanceMethod
* 주어진 객체의 메소드를 호출
```groovy
String string = "StringA";
Predicate<String> pred = string::equals;
System.out.println(pred.apply("StringA"));
```

##### ClassName::new
* 생성자를 이용하여 객체를 생성하는 람다식
```groovy
IntFunction<String> func = String::new;
String string = func.apply(10);
```

##### ClassName[]::new
* 배열을 생성하는 람다식
```groovy
IntFunction<String[]> func = String[]::new;
String [] strings = func.apply(100);
```


