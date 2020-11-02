package com.company.p02;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.*;
// assertThat을 쓸수있는데 어떻게 표현할것인지를 작성할 수있다.
import static org.junit.Assert.*;

/**
 * AAA (Triple-A)
 * Arrange(준비) - 테스트를 하기 위해서 시스템이 적절한 상태에 있는지 확인,
 *                객체 생성, 객체와의 소통, API 호출
 * Act (실행) - 실제로 테스트 코드를 실행하는 과정
 * Assert (단언) - 실행한 코드의 결과를 기대하는 값과 비교
 *
 * + @
 * After (사후) - 중간과정에서 자원을 할당한 경우에는 이를 해제 특수한 경우에만 사용.
 */
public class AccountTest {
    private Account account;
    // before -> test1 -> before -> test2
    // beforeClass -> before -> Test1 ->(After) -> before -> test2 -> (After) -> (AfterClass)
    @BeforeClass
    public static void classSetUp(){ //static으로 만들어야한다 맨 처음 한번 실행 오래되고 딱한번만 하면 되는 것들 여기다 작성

    }

    @Before
    public void setUpBySetBalanceOneHundred(){ //셋업메소드라고 부른다.
        account = new Account(100); // 1. Arrange
//        각테스트를 실행할 때마다 매번 다시 실행된다. 초기화가 된다는 의미인듯
    }
    @Test
    public void answerIsMinusWithNegativeBalance(){
        // 어렌지와 액트를 구분해줄 필요가 있다 구분하지 않을 때 테스트에 어렌지를 다 구현해줘야하는 문제가 생긴다 그래서 따로 만들어줄수있다.
//        Account account = new Account(100); // 1. Arrange

        account.withdraw(150); // 2. Act
        boolean actualResult = account.isMinus();
        // boolean에 대한 assertion은 assertTure, assertFalse를 쓰면 좋다.
//        assertFalse(actualResult);
        // 이건 실패 성공에 대한 것만나오고 내용이 나오지 않는데 assertThat은 틀렸을 때 정보를 볼 수 있음
        // assertture, false가있다,
//        assertThat(actualResult,equalTo(true));
        // 기대값과 출력하려고 하는 값에 대한 정보를 표현할 수 있다 (확인할 수 있음)
        // 단, 실패했을 때 정보를 잘 표현하기 위해서는
        // hamcrest의 CoreMachers에 구현된 Matcher를 쓰는 것이 좋다.

        assertThat(actualResult,is(equalTo(true))); // 3. Assert
        // 인지적으로 일기 좋게 애튜철 리절트 is equalto false로 문장처럼 읽히게 해준다.

        assertThat(actualResult,not(equalTo(false))); // is <-> not
        // not에 경우엔 not일 때 동작을 하게 된다. 이때 테스트가 통과됨.
    }
    @Test
    public void answerIsNotMinusWithPositiveBalance(){
        account.withdraw(50);
        boolean actualResult = account.isMinus();

        assertThat(actualResult,is(not(equalTo(true))));
    }

    //가능하면 모든 메소드르 테스트하되 우선순위를 정해서 하는것이좋다.

    @Test
    public void checkPositiveBalanceAfterWithdrawal(){
        account.withdraw(80);
        int actualResult = account.getBalance();
        assertThat(actualResult,is(equalTo(20)));
    }

    @Test
    @Ignore("This will be tested later") //테스트를 무시한채 다른 테스트를 하는것 테스트를 안할것을 따로 빼놀 때 사용, 이것을 남겨두고 커밋하지마세요
    //임시로만 사용해야 한다.
    public void checkNegativeBalanceAfterWithdrawal(){
        account.withdraw(130);
        int actualResult = account.getBalance();
        assertThat(actualResult,is(equalTo(20)));
    }

    // ArithmeticException이 발생하는지 assert하는 테스트

    // 간단하다는 장점이 있지만, 인지적으로는 별로 좋지 않음 테스트 메소드 내부에 assert가 드러나지 않는다.
    @Test(expected = ArithmeticException.class) // 아리스메틱익셉셥을 넣어줘서 아리스메틱익셉션인지 확인
    public void checkExceptionByAnnotation(){
        account.throwExcept();
    }

    // 인지적으로 더 개선되나, 코드가 매우 복잡해진다.
    @Test
    public void checkExceptionByTryCatch(){
        try{
//            account.throwExcept();
            fail(); // 익셉션이 발생하지않아도 통과되서 익셉션이 발생하지 않은경우에 강제로 fail시켜버리는 것이다
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
        thrown.expect(ArithmeticException.class); // 아리스메틱익셉션을 기대한다고 써놔서 인지적으로 더 잘 이해가능
        // 이렇게 해줬을 때 프레임워크에 가서 이 익셉션이 프레임워크에 올라가서 메소드가 동작할 때 익셉션이 발생했을 때 캐치해준다.
        account.throwExcept();
    }




}