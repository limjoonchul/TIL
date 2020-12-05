package com.rubypapper.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterReturning {
	@Pointcut("execution(* com.rubypapper.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	@org.aspectj.lang.annotation.AfterReturning(pointcut = "getPointcut()", returning = "returnObj")
	public void afterReturningLog(Object returnObj) {
		System.out.println("[사후 처리] afterReturningLog() 호출 , 반환값  : " + returnObj);
	}

}
