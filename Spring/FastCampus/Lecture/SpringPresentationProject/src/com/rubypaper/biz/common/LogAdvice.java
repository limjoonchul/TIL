package com.rubypaper.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service // �ֳ����̼������� �� �̷��� ������ ����� �Ѵ�.
@Aspect // Aspect = Pointcut + Advice 
public class LogAdvice {
//	@Pointcut("execution(* com.rubypaper.biz..*Impl.*(..))")
//	public void allPointcut() {} // � ����� ������ ������ �ƴ� ������ ���ؼ� �ĺ������·� ����ϴ� ���� ������ �޼ҵ��̴�.
	// �̰��� �ʿ��� ������ ���� ����Ʈ���� ���౸���� �־� �� �� �װ��� �����ϱ� ���ؼ� ����Ʈ���� �ΰ� �������ְ� id�� �������ֵ��� �̰��� �޼ҵ�� ���������. 
//	@Pointcut("execution(* com.rubypaper.biz..*Impl.get*(..))") // �ܺ� Ŭ������ �޼ҵ带 �����Ѵ�.
//	public void getPointcut() {}
	
	@Before("BoardPointcut.allPointcut()") 
	public void printLog(JoinPoint jp) { //JoinPoint �ָ� ���� �� �ִ�.
		String method = jp.getSignature().getName(); // Ŭ���̾�Ʈ�� ȣ���� �޼ҵ� �̸�
		Object[] args = jp.getArgs();                // Ŭ���̾�Ʈ�� ������ ���� ���� �α����� ȸ���� �������� ����..
		System.out.println("<���� ó��>" + method + "() �޼ҵ� args ���� : " + args[0].toString());
//		System.out.println("<���� ó��> ����Ͻ� ���� ���� �� ����");
	}

}
