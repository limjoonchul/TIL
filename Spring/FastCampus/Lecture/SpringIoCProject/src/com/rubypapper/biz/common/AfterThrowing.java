package com.rubypapper.biz.common;

import java.sql.SQLException;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowing implements Advice{
	@Pointcut("execution(* com.rubypapper.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@org.aspectj.lang.annotation.AfterThrowing(pointcut = "allPointcut()", throwing = "exception")
	public void afterThrowingLog(Exception exception) {
		if(exception instanceof IllegalArgumentException) {
			System.out.println("IllegalArgumentException 惯积");
		} else if (exception instanceof SQLException) {
			System.out.println("SQLException 惯积");			
		} else if (exception instanceof NullPointerException) {
			System.out.println("NullPointerException 惯积");			
		} else {
			System.out.println("巩力 惯积!!!");
		}
	}

}
