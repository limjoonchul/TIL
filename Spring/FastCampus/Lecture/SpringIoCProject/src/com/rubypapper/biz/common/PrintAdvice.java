package com.rubypapper.biz.common;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class PrintAdvice implements Advice {
	@Pointcut("execution(* com.rubypapper.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Before("allPointcut()")
	public void printLog() {
		System.out.println("[사전 처리] printLog() 호출");
	}
}
