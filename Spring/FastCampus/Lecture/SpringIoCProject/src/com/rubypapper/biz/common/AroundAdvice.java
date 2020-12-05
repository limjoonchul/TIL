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
		
		System.out.println("[���� ó��] aroundLog() ȣ��");
		long start = System.currentTimeMillis();
		Object obj = pjp.proceed();
		
		long stop = System.currentTimeMillis();
		
		System.out.println(method + " ���� �ð� : " + (stop-start) + "(ms)��");
		
		System.out.println("[���� ó��] aroundLog() ȣ��");
		return obj;
	}

}
