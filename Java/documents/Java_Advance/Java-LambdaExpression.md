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


          