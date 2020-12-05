package com.rubypaper.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BoardPointcut {
	@Pointcut("execution(* com.rubypaper.biz..*Impl.*(..))")
	public void allPointcut() {} // � ����� ������ ������ �ƴ� ������ ���ؼ� �ĺ������·� ����ϴ� ���� ������ �޼ҵ��̴�.
	@Pointcut("execution(* com.rubypaper.biz..*Impl.get*(..))")
	public void getPointcut() {} // � ����� ������ ������ �ƴ� ������ ���ؼ� �ĺ������·� ����ϴ� ���� ������ �޼ҵ��̴�.
	@Pointcut("execution(* com.rubypaper.biz.user*Impl.*(..))")
	public void userPointcut() {} // � ����� ������ ������ �ƴ� ������ ���ؼ� �ĺ������·� ����ϴ� ���� ������ �޼ҵ��̴�.
	@Pointcut("execution(* com.rubypaper.biz.board*Impl.*(..))")
	public void boardPointcut() {} // � ����� ������ ������ �ƴ� ������ ���ؼ� �ĺ������·� ����ϴ� ���� ������ �޼ҵ��̴�.
}
