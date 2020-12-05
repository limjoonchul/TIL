package com.rubypapper.biz.user;

import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClientTest {

	public static void main(String[] args) {
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("business-layer.xml");
		
		UserService userService = (UserService) container.getBean("userService");
		
		if (userService != null) {
			System.out.println("userService LookUp 성공!!");
		}
		
		UserVO vo = new UserVO();
		vo.setId("aa");
		vo.setPassword("aa");
		
		UserVO user = userService.getUser(vo);
		System.out.println(user.getName() + "님 환영합니다.");
		
		container.close();

	}

}
