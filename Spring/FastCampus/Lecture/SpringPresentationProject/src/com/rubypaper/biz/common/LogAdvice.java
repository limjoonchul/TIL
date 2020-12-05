package com.rubypaper.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service // 애노테이션으로할 때 이렇게 설정을 해줘야 한다.
@Aspect // Aspect = Pointcut + Advice 
public class LogAdvice {
//	@Pointcut("execution(* com.rubypaper.biz..*Impl.*(..))")
//	public void allPointcut() {} // 어떤 기능을 수행할 목적이 아닌 구분을 위해서 식별자형태로 사용하는 것이 참조형 메소드이다.
	// 이것이 필요한 이유는 여러 포인트컷이 실행구문을 넣어 줄 때 그것을 구분하기 위해서 포인트컷을 두개 설정해주고 id로 구분해주듯이 이것을 메소드로 구분해줬다. 
//	@Pointcut("execution(* com.rubypaper.biz..*Impl.get*(..))") // 외부 클래스의 메소드를 참조한다.
//	public void getPointcut() {}
	
	@Before("BoardPointcut.allPointcut()") 
	public void printLog(JoinPoint jp) { //JoinPoint 애만 받을 수 있다.
		String method = jp.getSignature().getName(); // 클라이언트가 호출한 메소드 이름
		Object[] args = jp.getArgs();                // 클라이언트가 전달한 인자 정보 로그인한 회원의 정보들이 담긴다..
		System.out.println("<사전 처리>" + method + "() 메소드 args 정보 : " + args[0].toString());
//		System.out.println("<사전 처리> 비즈니스 로직 수행 전 동작");
	}

}
