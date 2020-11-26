# AOP(Aspect oriented Programming) 
* 관점 지향 프로그래밍이라고 부른다.
* AOP는 OOP의 대체재로 나온 것이 아니라 OOP의 단점을 극복할 수 있도록 도와주는 프로그래밍 기법이다.
* OOP를 OOP답게 더 강화시켜주는 개념
* 여러 객체에서 공통적으로 작성해야 하는 부분들을 횡단 관심이라고 하는데, 이 횡단 관심을 제거하여 핵심 관심(비즈니스 로직)만 집중할 수 있도록 하고자 하는 프로그래밍 기법이 AOP이다.
## 핵심 관심(Core Concerns) 과 횡단 관심(Crosscutting Concerns)

![핵심관심과횡단관심](/Java/documents/images/핵심관심횡단관심.jpg)

* 위의 그림처럼 각 기능을 하는 여러 메소드가 있다고 할 때, 그 메소드의 비즈니스 로직 말고 
로그 출력, 예외처리, 트랜잭션 핸들하는 등의 코드들이 들어가 있을 수 있다.
문제는 여러 메소드들에 똑같이 로그 출력, 예외처리, 트랜잭션 핸들해주는 코드들이 들어가 있다고 하면
해당 메소드들은 비즈니스 로직만 존재해야 하는데 다른 기능들이 들어가 있다고 하면 메소드의 응집도가 떨이진다.
응집도가 떨어지게 되면 코드를 분석하는 성능이 떨어지게 된다.
그리고 똑같은 코드들을 여러 메소드들에도 똑같이 작성해야 하니깐 코드낭비가 된다.
* 이런 점을 해결하기 위해 AOP를 이용하여 실제 비즈니스 로직을 제외한 여러 곳에서 사용되는 로직들을 분리시켜
각 기능을 하는 독립된 클래스로 만들어서 필요한 여러 메소드들에서 사용할 수 있게 만드는 것이다.

* 핵심관심코드가 비즈니스로직에 해당하고 횡단관심 코드가 비즈니스 로직이 아닌 메소드마다 반복되는 로직들을 의미한다.

### 구현 코드
```groovy
package com.rubypapper.biz.common;

public class LogAdvice {
	public void printLog() {
		System.out.println("<사전 처리> 비즈니스 로직 수행 전 동작");
	}

}
``` 
```xml
<!-- 횡단 관심에 해당하는 동통 클래스를 bean 등록한다. -->
<bean id="log" class="com.rubypapper.biz.common.LogAdice"></bean>
	
<!-- AOP 설정 -->
<aop:config>
    <aop:pointcut id="allPointcut" expression="execution(* com.rubypaper.biz..*Impl.*(..))" />
    <aop:aspect ref="log">
       <aop:before pointcut-ref="allPointcut" method="printLog"/>
    </aop:aspect>
</aop:config>
```
* LogAdice라는 클래스를 만들어서 횡단관심 코드를 분리했다.
이렇게 작성을 해놓으면 컨테이너가 자동으로 클래스 명에 Impl이 들어가 있는 모든 클래스의 메소드들에 `printLog()` 메소드의 기능을 수행할 수 있게 넣어준다. 
그래서 일일히 코드들을 수정할 필요 없고, xml 설정만 바꿔주면 된다/

* 각 클래스들은 각자의 기능(비즈니스 로직)만 집중하면 된다. 핵심관심코드와 횡단관심코드들을 분리시키고,
연결고리가 없게 구현을 해야 한다 그래야 나중에 코드를 변경할 때에도 서로에 영향을 주지 않고 편리하게 바꿀 수 있다.
 
### AOP 용어 정리
* 조인포인트(JoinPoint)
  * 클라이언트가 호출하는 모든 비즈니스 메소드
* 포인트 컷(PointCut)
  * 필터링된 조인 포인트
  ![포인트컷](/Java/documents/images/포인트컷.jpg)
  
* 어드바이스(Advice)
  * 횡단 관심에 해당하는 공통 기능의 코드
  ![어드바이스](/Java/documents/images/어디바이스.jpg)
  
* 위빙(Weaving)
  * 포인트컷으로 지정한 핵심 관심 세도가 호출될 때, 어드바이스에 해당하는 횡단 관심 메소드를 결합하는 것
  ![위빙](/Java/documents/images/위빙.jpg)
  
* 애스팩트(Aspect)
  * 포인트컷과 어드바이스의 결합
  * 애스팩트 설정에 따라 AOP의 동작 방식이 결정된다.
  ![애스팩트](/Java/documents/images/애스팩트.jpg)
  

     