package com.rubypaper.biz.user;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.rubypaper.biz.user.UserService;
import com.rubypaper.biz.user.UserVO;

public class UserServiceClient {
	public static void main(String[] args) {
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("business-*.xml");
		
		UserService userService = (UserService) container.getBean("userService");
		// 클라이언트가 getbeandㄹ하기 때문에 보드서비스임플은 id가 필요하다.
		
		UserVO vo = new UserVO();
		vo.setId("aa");
		vo.setPassword("aa");
		
		UserVO user = userService.getUser(vo);
		if(user != null) {
			System.out.println(user.getName() + "님 환영합니다.");
		} else {
			System.out.println("로그인 실패");
		}
		container.close();
		
	}
}
