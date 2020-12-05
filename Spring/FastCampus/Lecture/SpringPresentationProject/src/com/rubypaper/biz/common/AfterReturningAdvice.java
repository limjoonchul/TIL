package com.rubypaper.biz.common;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import com.rubypaper.biz.user.UserVO;

@Service
@Aspect
public class AfterReturningAdvice {
//	@Pointcut("execution(* com.rubypaper.biz..*Impl.get*(..))")
//	public void getPointcut() {}
//	
	@AfterReturning(pointcut="BoardPointcut.getPointcut()", returning="returnObj")
	public void afterLog(Object returnObj) {
//		System.out.println("<���� ó��> ����Ͻ� ���� ���� �� ����");
		System.out.println("<���� ó��> ����Ͻ� �޼ҵ� ���� �� : " + returnObj.toString());
		
		if (returnObj instanceof UserVO) {
			UserVO user = (UserVO) returnObj;
			if (user.getRole().equals("������")) {
				System.out.println(user.getName() + "���� ������ ȭ������ �ٷ� �̵�...");
			}
		}
		// �����ڷ� �α������� �� �ٸ� ȭ���� �����ְ� �� �� �̷��� �α������� �� ���������� Ȯ���ؼ� ȭ�鿡 ����ϸ鼭 �ٸ� ���ҵ� �� �߰������� �� �� �ִ�.
	}
	

}
