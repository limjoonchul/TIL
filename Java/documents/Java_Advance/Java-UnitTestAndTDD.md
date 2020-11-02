# UnitTest
## 유닛 테스트(Unit Test)
* 단위 테스트라고도 불리며, 최소 단위의 테스트를 말한다.
* 최소 단위는 메소드 단위를 말하는데 어느 정도의 객체 단위라는 것을 포함하고 있다.
   * 객체 단위를 포함하고 있다는 의미는 메소드는 객체의 속성을 변화시키는 side-effect를 가지고 있기 때문이다.
* 환경 셋업과 환경 정리를 반드시 같이 해줘야 한다.

### 통합 테스트(Integration Test)
* 유닛 테스트는 가장 작은 단위로 테스트를 하고 통합 테스트는 시스템 전체적인 테스트를 진행한다.
* 전체 시스템의 동작을 확인하는 테스트 
  * 내가 작업한 것을 git에 merge해서 전체 시스템이 잘 동작하는지 체크한다.
 
### 기존 임시적인 테스트 방식
* 임시적인 테스트의 문제점!
   * 여러 개의 테스트를 작성하기 어렵고, 여러 개의 테스트를 넣어야 하는데 테스트 내용의 구분이 안간다.
   * 여러가지 테스트를 할 때 '부작용(side-effect)'이 발생하기 쉽다.
      * 밑의 Test2 처럼 Test1의 결과에 영향을 받아서 기대했던 값이 아닌 다른 값이 출력되는 등의 부작용이 발생한다.
   * 테스트 결과가 성공적인지 여부를 파악하기 어렵다
      * 일일이 예상 값을 두고 눈으로 파악해야 하는데, 실제로 테스트를 할 때는 수천 개가 넘는 테스트를 눈으로 파악하기 어렵다.
   * Production 코드와 Test 코드가 섞이게 된다.
```java
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
```

## JUnit
* 위의 임시적인 테스트 방법의 문제점을 해결하기 위한 테스트 프레임워크가 있다.
* 인텔리제이, 이클립스에도 다 포함되어 있는 프레임워크로, Production 코드와 Test 코드를 분리하여 테스트 할 수 있다.

* JUnit 설정방법!
  * 왼쪽 상단에 file -> Project Structure -> moudles에 간다.
  * 테스트를 만들 디렉토리 선택하면 그 디렉토리의 하위 디렉토리들이 나오는데 Test할 디렉토리를 선택하고
  Mark as 부분에 Test를 클릭 해주면 테스트 폴더로 변화한다.
  * 원래 Production 코드의 화면에서 마우스 우클릭을 해서 Generate -> Test를 누르고 확인을 누르면 테스트 클래스로 자동으로 만들어준다.
  
### JUnit을 사용한 테스트 1번
* Test라는 Annotation을 달아줘서 테스트라는걸 인식하게 하고 assert에 값을 넣어줘서 기대값에 맞는지 테스트한다.
* assert 반드시 이렇다 단언하는 것, 결과가 같다고 해주는 것
```java
package com.company.p01;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreCollectionTest {
    @Test
    public void arithmeticMeanOfFiveAndSevenResultsInSIx(){
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);
        int actualResult = collection.arithmeticMean();
        assertEquals(6,actualResult);
        
    }
    @Test
    public void arithmeticMeanOfTenAndTwentyResultsInFifteen(){
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 10);
        collection.add(() -> 20);
        int actualResult = collection.arithmeticMean();
        assertEquals(15,actualResult);
    }
}
```
### POJO
* POJO : Plain Old Java Object 자바 클래스를 의미하는 것인데 클래스 파일 하나가 이런 자바만으로 이루어진 파일이 있을 수 있고 스프링이 묻어 있는 애들이 있을 수 있어서 순수 자바만 이용된걸 포조라고 부른다.
  * 이렇게 불러야 사람들이 그럴듯해 보여서 자바 클래스를 쓰는 것이다.
  * 프로그래밍 언어들은 사람들의 인지적인 관점을 적용하려 애쓴다. 그래서 이러한 용어들을 만들어내고 하는 것이다. 어이없지만 생각보다 중요한 것들이다.
  * 실무에서 사용하는 언어이다 어떤 것인지 알고 있으면 될 것 같다!

### JUnit을 사용한 테스트 2번
#### AAA (Triple-A)
 * Arrange(준비) - 테스트를 하기 위해서 시스템이 적절한 상태에 있는지 확인,
    * 객체 생성, 객체와의 소통, API 호출
 * Act (실행) - 실제로 테스트 코드를 실행하는 과정
 * Assert (단언) - 실행한 코드의 결과를 기대하는 값과 비교
 * `+@` After (사후) - 중간 과정에서 자원을 할당한 경우에는 이를 해제 특수한 경우에만 사용.

```java
package com.company.p02;

public class Account {
    private  int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void withdraw(int value){
        balance -= value;
    }

    public void deposit(int value){
        balance += value;
    }

    public boolean isMinus(){
        return balance < 0;
    }

    public void throwExcept(){
        throw new ArithmeticException();
    }
}
```
* 실행 순서
   * before -> test1 -> before -> test2
   * beforeClass -> before -> Test1 ->(After) -> before -> test2 -> (After) -> (AfterClass)
* `import static org.hamcrest.CoreMatchers.*` - EqualTo 이런 메소드들을 사용할 수 있게 import 해줘야한다.
* `@Ignore("This will be tested later")` - 테스트를 무시한 채 다른 테스트를 하는것 테스트를 안할 것을 따로 빼놀 때 사용.
* `fail()` - 익셉션이 발생하지 않아도 통과되서 익셉션이 발생하지 않은 경우에 강제로 fail 시켜버리는 것이다
* `assertThat(actualResult,equalTo(true));`
    * 기대값과 출력하려고 하는 값에 대한 정보를 표현할 수 있다. (확인할 수 있음)
    * 단, 실패했을 때 정보를 잘 표현하기 위해서는
    * hamcrest의 CoreMatchers에 구현된 Matcher를 쓰는 것이 좋다.
```java
package com.company.p02;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;


public class AccountTest {
    private Account account;
    
    @BeforeClass
    public static void classSetUp(){ //static으로 만들어야한다 맨 처음 한번 실행 오래되고 딱한번만 하면 되는 것들 여기다 작성

    }

    @Before
    public void setUpBySetBalanceOneHundred(){ //setUp()메소드라고 부른다.
        account = new Account(100); // 1. Arrange
//        각 테스트를 실행할 때마다 매번 다시 실행된다. 초기화가 된다는 의미인듯
    }
    @Test
    public void answerIsMinusWithNegativeBalance(){
        // 어렌지와 액트를 구분해줄 필요가 있다 구분하지 않을 때 테스트에 어렌지를 다 구현해줘야하는 문제가 생긴다 그래서 따로 만들어 줄 수있다.
        // setUp() 메소드를 만든다.

//        Account account = new Account(100); // 1. Arrange

        account.withdraw(150); // 2. Act
        boolean actualResult = account.isMinus();
        // boolean에 대한 assertion은 assertTure, assertFalse를 쓰면 좋다.
//        assertFalse(actualResult);
        // 이건 실패 성공에 대한 것만 나오고 내용이 나오지 않는데 assertThat()은 틀렸을 때 정보를 볼 수 있음
        // assertTure, False가 있다.

        assertThat(actualResult,is(equalTo(true))); // 3. Assert
        // 인지적으로 일기 좋게 actualResult is equalTo false로 문장처럼 읽히게 해준다.

        assertThat(actualResult,not(equalTo(false))); // is <-> not
        // not에 경우엔 not일 때 동작을 하게 된다. 이때 테스트가 통과됨.
    }
    @Test
    public void answerIsNotMinusWithPositiveBalance(){
        account.withdraw(50);
        boolean actualResult = account.isMinus();

        assertThat(actualResult,is(not(equalTo(true))));
    }

    // 가능하면 모든 메소드르 테스트하되 우선순위를 정해서 하는 것이 좋다.

    @Test
    public void checkPositiveBalanceAfterWithdrawal(){
        account.withdraw(80);
        int actualResult = account.getBalance();
        assertThat(actualResult,is(equalTo(20)));
    }

    @Test
    @Ignore("This will be tested later") // 테스트를 무시한 채 다른 테스트를 하는것 테스트를 안할 것을 따로 빼놀 때 사용, 이것을 남겨두고 커밋하지마세요
    // 임시로만 사용해야 한다.
    public void checkNegativeBalanceAfterWithdrawal(){
        account.withdraw(130);
        int actualResult = account.getBalance();
        assertThat(actualResult,is(equalTo(20)));
    }

    // ArithmeticException이 발생하는지 assert하는 테스트

    // 간단하다는 장점이 있지만, 인지적으로는 별로 좋지 않음 테스트 메소드 내부에 assert가 드러나지 않는다.
    @Test(expected = ArithmeticException.class) // ArithmeticException을 넣어줘서 ArithmeticException인지 확인
    public void checkExceptionByAnnotation(){
        account.throwExcept();
    }

    // 인지적으로 더 개선되나, 코드가 매우 복잡해진다.
    @Test
    public void checkExceptionByTryCatch(){
        try{
//            account.throwExcept();
            fail(); // 익셉션이 발생하지 않아도 통과되서 익셉션이 발생하지 않은 경우에 강제로 fail시켜버리는 것이다
        } catch (ArithmeticException e){
            assertThat(e.getClass(), equalTo(ArithmeticException.class));
//            아리스메틱 클래스의 클래스 클래스(클래스의 정보를 가지고 있는)를 가져온다. 그래서 그것이 맞는지 비교.
//            e.getClass() == ArithmeticException.class
        }
    }

    // Rule을 이용하면 메소드 코드에 excepted exception이 드러나서 인지적으로 개선 가장 추천함.
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void checkExceptionByRule(){
        thrown.expect(ArithmeticException.class); // ArithmeticException을 기대한다고 써놔서 인지적으로 더 잘 이해가능
        // 이렇게 해줬을 때 프레임워크에 가서 이 익셉션이 프레임워크에 올라가서 메소드가 동작할 때 익셉션이 발생했을 때 캐치해준다.
        account.throwExcept();
    }
}
```

### 좋은 테스트의 조건(FIRST)
#### Fast
* 전형적인 자바 시스템의 테스트 케이스는 수천 단위 (유닛테스트 기준)
* 평균 200ms가 걸린다 했을 때 (느린 경우를 예로), 5000개를 수행한다 했을 때  16분이 소요된다.
   * 우리가 코드를 1번 수행할 때 마다 매번 16분의 테스트를 수행해야 한다.(굉장히 안좋음)
   * 메소드에 테스트용 우회 코드를 넣거나, stub 객체를 활용
   
#### Isolated : 고립된
* 테스트는 순서와 시간에 영향을 받지 않아야 한다.
* 고립되지 않은 테스트는 실패했을 때 원인을 찾기 어렵다.(고립되지 않은 경우를 다봐야하므로 어렵다 후폭풍이 엄청난다 외부환경과 격리를 시켜야한다)
* 테스트를 하면서 다른 테스트에 영향을 받지 않아야 한다.

#### Repeatable : 반복 가능한
* 테스트를 반복하면 결과가 같아야 한다.
* 테스트 코드 자체만으로 그 내용을 설명할 수 있어야 한다.
   * 외부 환경에 영향을 받지 않아야 한다.
* 코드 내에서 코드가 작동한 시간, 이런 것에 따라 동작이 달라지는 것에 영향을 받지 않아야 한다.
#### Self-vlidating : 스스로 검증 가능한(굉장히 중요함)
* 테스트는 반드시 기대하는 바를 단언해야 한다.
   * ex) main 메소드에서 이것 저것 프린트해보고 동작시켜보기
* 테스트는 스스로 검증하며, 테스트를 준비하는 것도 스스로 한다. arranged와 assert를 스스로한다
* Continuous Integration(CI) 도구를 활용하기 위해 이것이 꼭 이루어져야 한다
   * 코드가 통합될 때마다 자동으로 모든 테스트를 수행하고, 상태를 점검하는 프레임워크를 CI도구라고 한다.
#### Timely : 적시의, 제때
* 변화하는 코드는 테스트 코드를 항상, 꾸준히 작성해야 한다.(변화하는 코드이니깐 테스트를 작성해야 한다.)
* 리뷰 시스템을 통해서 , 혹은 CI도구를 이용해서 테스트 코드 작성을 강제하기도 한다.
   -> peer Pressure 동료에게 드러내야 하기 때문에 압박감을 받음 테스트가 없으면 커밋이 안됨
* 큰 결함 없이 기존의 잘 동작하는(변경 예정이 없는) 코드 보다는
  말썽을 일으키는, 계속해서 변하고 있는 동적인 코드에 대한 테스트를 먼저 작성해야한다.
  (현실적으로 힘들어서 이렇게 명시를 하는 것이다.)

### JUnit을 사용한 테스트 3번 - Stub 이용
* Stub : 실제 코드나 아직 준비되지 못한 코드를 미리 정해진 답변으로 가장하는 것
   * dummy 객체가 마치 실제로 동작하는 것처럼 보이도록 만들어 놓은 것
````java
package com.company.p04;

import java.io.IOException;
import java.net.MalformedURLException;

public interface Http {
    String get(String targetUrl) throws IOException;
}
````
```java
package com.company.p04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpImpl implements Http{
    @Override
    public String get(String targetUrl) throws IOException {
        URL url = new URL(targetUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();
        System.out.println("Response code : " + responseCode);

        return response.toString();
    }
}
```
* dependency injection
   * 테스트 케이스로 인해서 더 테스트하기 좋은 코드로 리팩토링했다. 이부분이 중요함!!!!
```java
package com.company.p04;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebParser {
    private Http http;

    // dependency injection di를하기위해 리팩토링을함
    // 테스트 케이스로 인해서 더 테스트하기  좋은 코드로 수정하였다. 이부분이 중요함!!!!
    public WebParser(Http http) {
        this.http = http;
    }

    public int countImageFromWebPage(String url) throws IOException {
        String text = http.get(url); // DI 적용
//        String text = new HttpImpl.get(url); 원래 코드

        Pattern pattern = Pattern.compile("(\\w+.(png|jpg|gif))");
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()){
            count++;
        }

        System.out.println(text);
        return count;
    }
}
```
```java
package com.company.p04;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class WebParserTest {
    // 이렇게 테스트하면 문제점
    // 1. Fast에 맞지 않는다 느리다. 구글에 요청을 보내서 받아오는데 http 반응 속도가 느려서 그런 것이다. 인터넷 연결이 순간 안될 수도 있다.
    // 2. 웹페이지 내용이 변할 수 있다. (기대값이 변할 수 있다.)

//    @Test
//    public void countImageFromGoogleDotCom() throws IOException {
//        WebParser parser = new WebParser();
//        int actualResult =parser.countImageFromWebPage("http://google.com");
//
//        assertThat(actualResult, equalTo(5));
//    }

    private WebParser parser;
    @Before
    public void setUpUsingPageWithThreeImages(){
        // DI를 이용해 Http 객체의 stub을 구현하여 넣어준다. 실제로 웹파서를 이용할 때 HttpImpl을 만들어서 넣어주면 된다.
        parser = new WebParser((targetUrl) -> {
            return "<html><meta content=a.png> <meta content=b.png> <meta content=c.png> </html>"; // 테스트 하고자하는 상황을 더 넣으면 더 의미가 있어짐
        }); // 이미지가 3개 나온다는걸 설정을 해줬다. 테스트가 많아질수록 의미가 있어진다.

    }
    @Test
    public void countImageFromThreeImagePageStub() throws IOException {
        int actualResult = parser.countImageFromWebPage("http://google.com");
        assertThat(actualResult, is(equalTo(3)));
    }

}
```
# TDD(Test-Driven-Development) - 테스트 주도 개발
 * '실패하는' 테스트 케이스를 먼저 작성하고, 이것을 통과시키는 방식으로 코드를 구현하는 방식
 * 테스트 케이스 작성 -> 코드 구현 -> (커밋) -> (리팩토링) -> 테스트 케이스 작성 .. 을 빠르게 반복
    *  1~2분 간격으로 매우 빠른 호흡으로 진행
    *  매번 커밋을 해줘서 돌아갈 수 있는 때를 확보를 하는 것이다.
 * 도메인 지식이 없어도 테스트 케이스를 작성할 수 있다는 관점에서 시작.
    * 도메인 지식이란 : 프로그래밍하고 상관없이 서비스할 분야에 대한 전반적인 지식을 의미한다.
```java
package com.company.p05;

/**
 * I-> 1
 * II -> 2
 * III -> 3
 * IV -> 4...
 */

public class RomanConverter {
    private String roman = "";

    public void setRoman(String roman) {
        this.roman = roman;
    }

    // 들여쓰기가 2번넘게들어가면 리팩토링을 하는게 맞다.
    public int transform() {
        if (roman.equals("")){
            throw new ArithmeticException();
        }
        if (roman.contains("X")){
            return 10;
        }
        int count = 0;
        boolean beforeV = roman.contains("V") ? true : false;
        for (char c: roman.toCharArray()){
            if (c == 'I'){
                count += beforeV ? -1 : 1;
            }
            // 4
            if (c == 'V'){
                beforeV = false;
                count += 5;

            }
            // 6을 했을때 I가 앞에나오는지 뒤에나오는지 구별을해야하는 상황이됬다
        }
        return count;
        // 여기서는 로직을 생각하지 않음?
    }
}
```
```java
package com.company.p05;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class RomanConverterTest {
    RomanConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new RomanConverter();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void exceptionWhenRomanNotSet(){
        thrown.expect(ArithmeticException.class);
        int actualResult =  converter.transform();
    }

    @Test
    public void convertI(){
        converter.setRoman("I");
        int actualResult = converter.transform();
        assertThat(actualResult, is(equalTo(1)));
    }

    @Test
    public void convertX(){
        converter.setRoman("X");
        int actualResult = converter.transform();
        assertThat(actualResult, is(equalTo(10)));
//        테스트는 매번 다실행하는게 맞다.
    }

    @Test
    public void convertIII(){
        converter.setRoman("III");
        int actualResult = converter.transform();
        assertThat(actualResult, is(equalTo(3)));
    }

    @Test
    public void convertIV(){
        converter.setRoman("IV");
        int actualResult = converter.transform();
        assertThat(actualResult, is(equalTo(4)));
    }

    @Test
    public void convertVI(){
        converter.setRoman("VI");
        int actualResult = converter.transform();
        assertThat(actualResult, is(equalTo(6)));
    }
}
```