package com.rubypaper.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BoardPointcut {
	@Pointcut("execution(* com.rubypaper.biz..*Impl.*(..))")
	public void allPointcut() {} // 어떤 기능을 수행할 목적이 아닌 구분을 위해서 식별자형태로 사용하는 것이 참조형 메소드이다.
	@Pointcut("execution(* com.rubypaper.biz..*Impl.get*(..))")
	public void getPointcut() {} // 어떤 기능을 수행할 목적이 아닌 구분을 위해서 식별자형태로 사용하는 것이 참조형 메소드이다.
	@Pointcut("execution(* com.rubypaper.biz.user*Impl.*(..))")
	public void userPointcut() {} // 어떤 기능을 수행할 목적이 아닌 구분을 위해서 식별자형태로 사용하는 것이 참조형 메소드이다.
	@Pointcut("execution(* com.rubypaper.biz.board*Impl.*(..))")
	public void boardPointcut() {} // 어떤 기능을 수행할 목적이 아닌 구분을 위해서 식별자형태로 사용하는 것이 참조형 메소드이다.
}
