package com.rubypaper.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

// around는 사전처리와 사후처리를 모두 할 수 잇다. 서블릿의 filter와 동일한 개념
@Service // 따로 빈등록안하고 애노테이션 방법으로 사용함.
@Aspect
// 아이디가 필요없다? xml에 참조하기 위해서 필요했던 것이다. 
public class AroundAdvice {
	
	// 주의사항 joinpoint를 쓰면 에러가난다 왜냐하면 조인포인트에는 proceed메소드가 없기때문에
	// ProceedingJoinPoint가 joinpoint의 자식 클래스이다. 그래서 around는 ProceedingJoinPoint해야만 한다.
	// 근데 logadciec에서 ProceedingJoinPoint이걸 사용하면 에러가 난다 무조건 around에서만 사용가능하다.
	@Around("BoardPointcut.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint jp) throws Throwable {  
		String method = jp.getSignature().getName(); // 클라이언트가 호출한 메소드 이름
		
		Object obj = null;
//		System.out.println("----Before Logic -----");
		StopWatch watch = new StopWatch();
		watch.start();
		
		obj = jp.proceed();
		
//		System.out.println("----After Logic -----");
		watch.stop();
		
		System.out.println(method + "() 메소드 수행에 소요된 시간 : " + watch.getTotalTimeMillis() + "(ms)초");
		return obj;
	}
	// 리턴타입이 절대 void이면 안된다. 매개변수도 정해져 있다. 애는 비즈니스로직에 대한 사전처리 사후처리이다.
	// 만약 around를 void로 했을 때 사전 사후 처리는 하는데 proceed를 만나서 리턴값이 있는 메소드를 실행시키면 반환 값을 받아서 aroundLog가 받아와도
	// 여기서 return을 해줄 값이 없기 때문에 예외가 발생하고 화면에 출력할 값이 없어진다.

}
