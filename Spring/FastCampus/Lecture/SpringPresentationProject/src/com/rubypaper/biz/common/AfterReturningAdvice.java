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
//		System.out.println("<사후 처리> 비즈니스 로직 수행 후 동작");
		System.out.println("<사후 처리> 비즈니스 메소드 리턴 값 : " + returnObj.toString());
		
		if (returnObj instanceof UserVO) {
			UserVO user = (UserVO) returnObj;
			if (user.getRole().equals("관리자")) {
				System.out.println(user.getName() + "님은 관리자 화면으로 바로 이동...");
			}
		}
		// 관리자로 로그인했을 때 다른 화면을 보여주게 할 때 이렇게 로그인했을 대 관리자인지 확인해서 화면에 출력하면서 다른 역할도 또 추가적으로 할 수 있다.
	}
	

}
