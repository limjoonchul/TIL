package com.rubypapper.biz.common;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;



@Service
@Aspect
public class AroundAdvice implements Advice{
	@Pointcut("execution(* com.rubypapper.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	@Around("getPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		
		System.out.println("[사전 처리] aroundLog() 호출");
		long start = System.currentTimeMillis();
		Object obj = pjp.proceed();
		
		long stop = System.currentTimeMillis();
		
		System.out.println(method + " 실행 시간 : " + (stop-start) + "(ms)초");
		
		System.out.println("[사후 처리] aroundLog() 호출");
		return obj;
	}

}
