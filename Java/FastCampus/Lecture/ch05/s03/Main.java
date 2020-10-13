package com.company.s03;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static java.lang.annotation.ElementType.*;

/**
 * 애노테이션 (Annotation)
 * - 애노테이션의 사전적 의미는 주석이다
 * - jvm이나 컴파일러, 프레임워크 등에서 사용할 수 있도록 전달하는 메타데이터이다. 이런걸 전달하는게 애노테이션이다
 *
 * 기본 애노테이션
 * - @Override @Deprecated, @SupperessWarnings, @FuntionalInterface
 * - Deprecated : 앞으로 사용되지 않을 클래스/메소드/변수 .. 요소들이다. 사용하지 말라는 의미이다.
 * - SupperessWarnings : 특정 경고 메세지를 무시하도록 컴파일러에 전달하는 애노테이션
 *    - @SupperessWarning("unused")  - 컴파일러에서 경고를 내보내는 것을 막을 수 있다.
 *
 *  애노테이션을 구현할 때 public @interface SuppressWarnings @interface을 사용한다.
 */


/**
 * @Target, @Retentaion : 메타 애노테이션이다
 * @Target : 애노테이션을 사용할 수 있는 대상을 설정하는 것이다.
 * TYPE : 클래스, 인터페이스, 애노테이션, 열거형까지 포함하고 있다.
 * FIELD : 필드(멤버 변수), 열거형 상수
 * METHOD : 메소드
 * PARAMETER : 메소드의 입력 파라미터
 * CONSTRUCTOR : 클래스의 생성자
 * LOVAL_VARIABLE : 로컬 변수
 * MODULE : 모듈
 * ANNOTATIN_TYPE : 애노테이션만 따로 설정할 수 있다.
 * PACKAGE : 패키지
 *
 *
 * @Retation
 * 애노테이션 정보를 어디까지 유지할 것인가를 결정하는 Policy 정책
 * SOURCE : 컴파일러가 사용한다. 컴파일러는 .java -> .class로 변경하는 과정에서 쓰임 .class 파일에는 포함되지 않는다. 가장 빨리사라지는 정책
 * 컴파일러에서 쓰고 버린다. 사용법만 익혀서 사용하면 된다.
 * CLASS : .class에 포함된다. jvm에 올라오지는 않는다.
 * RUNTIME : .class에도 포함이되고, jvm에 올라와서 Reflection API에서 사용 가능. 만들어서 사용하는건 RUNTIME이다.
 *   * 리플렉션
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
@Retention(RetentionPolicy.SOURCE)
@interface SuppressWarningsClone {
    /**
     * The set of warnings that are to be suppressed by the compiler in the
     * annotated element.  Duplicate names are permitted.  The second and
     * successive occurrences of a name are ignored.  The presence of
     * unrecognized warning names is <i>not</i> an error: Compilers must
     * ignore any warning names they do not recognize.  They are, however,
     * free to emit a warning if an annotation contains an unrecognized
     * warning name.
     *
     * <p> The string {@code "unchecked"} is used to suppress
     * unchecked warnings. Compiler vendors should document the
     * additional warning names they support in conjunction with this
     * annotation type. They are encouraged to cooperate to ensure
     * that the same names work across multiple compilers.
     * @return the set of warnings to be suppressed
     */
    String[] value();
}

/**
 * 멤버 변수를 대상으로 하는, Reflection API에서 쓸 수 있ㄴㄴ 애노테이션
 * 애노테이션 속성은 String[]인 value와 "기본값" 기본값을 가지는 valueTwo로 이루어짐
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation{
    String[] value(); // 애노테이션 속성 (기본 속성 이름은 value) 첫번째 애노테이션 속성은 value로해주는 게 좋다.
    String valueTwo() default "기본값";
}

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

//@Target(ElementType.METHOD)- 메소드에서만 쓸 수 잇다.
//@Retention(RetentionPolicy.SOURCE)
//public @interface Override {
//}

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        @SuppressWarnings("unused")
         int x;


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
