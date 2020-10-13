# 애노테이션(Annotation)

## 애노테이션이란
* 애노테이션의 사전적인 의미는 주석이다,
* JVM, 컴파일러, 프레임워크 등에게 전달하는 메타데이터로 사용된다.
* 프로그램에게 추가적인 정보를 제공하는 메타데이터이다.
   * 메타데이터란 데이터를 설명하는 데이터를 의미한다.
   
## 기본 애노테이션
| 애노테이션 | 설명 | 비고 |
| -------- | ---- | --- |
| @Override | 상속하여 오버라이드된 메소드 | |
| @Deprecated | 앞으로 사라질 예정임을 표기한다. | |
| @SuppressWarnings | 컴파일러에게 특정 경고 메세지를 무시하도록 한다. | ex- @SuppressWarnings("unused") |
| @FuntionalInterface | 함수형 인터페이스임을 표기(Lambda) | |

### 에노테이션 용도
* 컴파일러에게 코드 작성 문법 에러를 체크하도록 정보를 제공한다.
   * Ex) @Override - 메소드가 재정의 되었는지 확인할 수 있다.
   (코드를 작성하고 이 코드가 제대로 작성이 되었는지 컴파일러에게 체크할 수 있도록 정보를 제공한다., 뭔가를 지시하는 용도로 사용)

* 소프트웨어 개발 툴이 빌드나 배치시 코드를 자동으로 생성할 수 있도록 정보를 제공
  * 프로그램을 개발하고나서 압축을 할 때가 있는 jar, war상태로 압축할 수 잇는데 어떤 파일명
     확장명으로 압축을 해라라고 개발툴에게 지시를 할 때 어노테이션을 통해 할수 있다
  * Ex) XML 설정 파일을 자동 생성하거나 배포를 위한 JAR 압축 파일을 자동 생성할 수 있다.

* 실행시(런타임시) 특정 기능을 실행하도록 정보를 제공한다.
  * 객체를 만들었을 때 다른객체와 달리 특별한 역할을 해야할 경우 그객체에게 어노테이션을 기술해주면 
  어노테이션에 기술되어있는 데이터를 보고 특별한 역할을 할 수 있다.
  * Ex) 객체가 애플리케이션 내부에서 해야할 역할을 정의할 수 있다(서블릿, 컨트롤러...)

## 애노테이션 타입 정의와 적용
*  애노테이션 타입 정의
   * 소스 파일 생성 : AnnotationName.java
   * 소스 파일 내용
   ```groovy
    public @interface AnnotationName{
    }
   ```
* 애노테이션의 작성
  * 추상 메소드와 유사한 형태로 구현
```java
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE}) // 메타 애노테이션
@Retention(RetentionPolicy.SOURCE)                                     // 메타 애노테이션
public @interface SuppressWarnings {                                   // 애노테이션 선언
    String [] value();                                                 // 애노테이션 속성
}
```
* 애노테이션 타입 적용
  * `@AnnotationName`
  ```groovy
   @Override
   public void toString(){ .. }
  ```
* 애노테이션의 사용
  * key = value 형태로 애노테이션에 속성의 값 전달
```java
@SuppressWarnings(value = {"unused", "rawtypes"}) // 키 = 값 배열
@SuppressWarnings(value = "unused")               // 값이 하나인 경우 배열 생략 가능
@SuppressWarnings({"unused", "rawtypes"})         // 속성이 value 하나인 경우 키 생략 가능

@CustomAnnotation(key1 = "value1", key2 = {"value2", "value3"}) // 속성이 여러개인 경우 키를 여러개 사용
```

* 애노테이션의 엘리먼트 멤버
   * 어노테이션을 코드에 적용할 때 외부의 값(개발자가 입력한 값)을 입력받을 수 있도록 하는 역할
   * 엘리먼트 선언
   ```java
    public @interface AnnotationName{
      타입 elementName() [defalut 값(기본값, 생략가능)]; // 엘리먼트 선언
       ...
    } 
   ```
   * 엘리먼트의 타입은 기본타입과 참조 타입 모두 사용할 수 있다.
   ```groovy
    public @interface AnnotationName{
      String elementName1();
      int elementName2() default 5;
    }
   ```

* 어노테이션 적용시 엘리먼트 값을 지정하는 방법
   @AnnotationName(element="값", elementName2 = 3);

## 기본 엘리먼트 value
```groovy
public @interface AnnotationName{
  String value()  // 기본 엘리먼트 선언
  int elementName()  default 5;
}
```
* 애노테이션을 적용할 때 엘리먼트명을 생략가능
   * `@AnnotationName("값");` - 기본 엘리먼트인 value에 값이 들어간다
   ```groovy
    @WebServlet("/main")
    public class MainServlet { ... }
   ```

* 두 개 이상의 속성을 기술할 때에는 value =값 형태로 기술해야 한다.
   * `@AnnotationName(value="값", elementName=3);`


## 애노테이션 적용 대상
* 코드 상에서 애노테이션을 적용할 수 있는 대상
* java.lang.annotation.ElementType. 열거 상수로 정의되어 있다.

| ElementType 열거 상수 | 적용 대상 |
| ------------------- | ------- |
| TYPE  | 클래스, 인터페이스, 열거 타입 |
| ANNOTATION_TYPE | 어노테이션 |
| FIELD | 필드, 열거형 상수 |
| CONSTRUCTOR | 생성자 |
| METHOD | 메소드 |
| PARAMETER | 메소드의 입력 파라미터 |
| LOCAL_VARIABLE | 로컬 변수 |
| PACKAGE | 패키지 |
| MODULE | 모듈 |

* 애노테이션 적용 대상 지정 방법
   * @Target 애노테이션으로 적용 대상 지정
   * @Target의 기본 엘리먼트인 value의 타입은 ElementType 배열
   ```groovy
    @Target({ElementType.TYPE, ElementType.FLELD, ElementTypeMETHOD}) // TYPE,FELED,METHOD에 적용할 수 있다라는 의미이다.
    public @interface AnnotationName{
    }
    
    @AnnotationName // 클래스에 사용 가능
    public className{
       @AnnotationName // 필드에 사용 가능
       private String fieldName;
    
       @AnnotationName x  // 생성자에는 사용 불가
       public className(){
    
       }
    
      @AnnotationName // 메소드에는 사용 가능
       public  void methodName(){ }
    
    }
   ```

## 어노테이션 유지 정책
* 어노테이션 적용 코드가 유지되는 시점을 지정하는 것
* java.lang.annotation.RetentionPolicy 열거 상수로 정의되어 있다.

  | RetentionPolicy 열거 상수  | 설명 |
  | ----------------------------- | ----- |
  | SOURCE | 소스상에서만 어노테이션 정보를 유지한다. 소스코드를 분석할 때만 의미가 있으며, 바이트 코드 파일에는 정보가 남지 않는다. 컴파일러가 컴파일 할 때 어노테이션을 바이트코드에서 뺀다,바이트코드에서는 어노테이션이 유지되지 않는 정책이다 개발자가 소스에서 정보를 얻을 목적으로 유지하는 정책|
  | CLASS | 바이트 코드 파일까지 어노테이션 정보를 유지한다. 하지만 리플렉션을 이용해서 어노테이션 정보를 얻을 수는 없다.클래스상에만 존재하고 실제로 실행할 때는 그정보를 읽지못하는 유지 정책. |
  | RUNTIME | 바이트 코드 파일까지 어노테이션 정보를 유지하면서 리플렉션을 이용해서 런타임에 어노테이션 정보를 얻을 수 있다. 클래스를 이용할 때 어떤 어노테이션이 적용되었나를 보고 클래스의 역할과 실행방법을 정해줄 수 있기 때문에 대부분 RUNTIME 유지정책을 사용한다 |
  
  * 리플렉션(ReFlection) : 런타임에 클래스의 메타 정보를 얻는 기능
      * (클래스의 메타정보)클래스가 가지고 있는 필드, 필드의 종류, 필드의 타입 생성자, 메소드, 어노테이션의 정보 등등을 얻을 수 있다.
      * 런타임시에 어노테이션 정보를 얻으려면 유지 정책을 RUNTIME으로 설정해야 한다.

* 유지 정책 지정 방법
  * @Retention 어노테이션으로 유지 정책을 지정
  * @Retention의 기본 엘리먼트인 value의 타입은 RetentionPolicy
  ```java
    @Target({ElementType.TYPE, ElementType.FLELD, ElementTypeMETHOD}) // TYPE,FELED,METHOD에 적용할 수 있다라는 의미이다.
    @Retention(RetentionPolicy.RUNTIME) // 여기다가 위에 3가지중 하나를 넣어주면 됨. value = RetentionPolicy.RUNTIME과 같다
    public @interface AnnotationName{
    }
  ```

## 사용자 정의 애노테이션
 * 멤버 변수를 대상으로 하는, Reflection API에서 쓸 수 있ㄴㄴ 애노테이션
 * 애노테이션 속성은 String[]인 value와 "기본값" 기본값을 가지는 valueTwo로 이루어짐

* 사용자 정의 애노테이션 구현
```java
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation{
    String[] value(); // 애노테이션 속성 (기본 속성 이름은 value) 첫번째 애노테이션 속성은 value로해주는 게 좋다.
    String valueTwo() default "기본값";
}
```

* 사용자 정의 애노테이션 사용
```java
class AnnotationUsage{
    @MyAnnotation("game")// 바로밑에 한줄에 적용이된다. 게임네임에는 게임에 대한정보가들어가있다라는 의미이다.
    String gameName = "여러분의 틱택토";

    //value가 String[]이므로 value = String[]로 넣어 준다.
    @MyAnnotation(value = "server",valueTwo = "localhost") // value는 원래 배열이다
    String serverIP; // 내용이 들어가도 상관없다. 여기에쓰던지 valueTwo에쓰던지하는 것

    //value가 String[]이지만, 길이가 1이면 String 만 넣어주어도 됨.
    @MyAnnotation(value = "server",valueTwo = "8080")
    String serverPort;
    @MyAnnotation("game")
    String gameMode = "AI VS. AI";
    @MyAnnotation(value = "db",valueTwo = "localhost")
    String database;
}
```

* Reflection을 이용하여 애노테이션에 할당된 값 사용
```java
public class Main {
    public static void main(String[] args) throws IllegalAccessException {

        AnnotationUsage obj = new AnnotationUsage();
        Map<String, Object> gameProp = new HashMap<>();
        Map<String, Object> serverPrp = new HashMap<>();
        Map<String, Object> dbProp = new HashMap<>();

        Field[] fields =AnnotationUsage.class.getDeclaredFields(); // 필드 정보 가져오는 부분 (Reflection API를 사용하여 가져옴)
        for (Field filed : fields) {
            MyAnnotation annotation = filed.getDeclaredAnnotation(MyAnnotation.class); // 필드에서 Annotation 정보 가져오는 부분(Reflect API)
            if(filed.get(obj) == null){ // 필드 값이 비어있는 경우 valueTwo에서 가져온다.
                filed.set(obj, annotation.valueTwo()); // 값이 없으면 valueTwo를 채워줌
            }

            if (annotation.value()[0].equals("game")){
                gameProp.put(filed.getName(),filed.get(obj));
            } else  if (annotation.value()[0].equals("server")){
                serverPrp.put(filed.getName(),filed.get(obj));
            } else {
                dbProp.put(filed.getName(),filed.get(obj));
            }
        }

        System.out.println(gameProp);
        System.out.println(serverPrp);
        System.out.println(dbProp);
    }
}
```

