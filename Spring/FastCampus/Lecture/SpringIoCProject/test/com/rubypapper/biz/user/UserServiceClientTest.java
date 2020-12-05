package com.rubypapper.biz.user;

import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClientTest {

	public static void main(String[] args) {
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("business-layer.xml");
		
		UserService userService = (UserService) container.getBean("userService");
		
		if (userService != null) {
			System.out.println("userService LookUp ����!!");
		}
		
		UserVO vo = new UserVO();
		vo.setId("aa");
		vo.setPassword("aa");
		
		UserVO user = userService.getUser(vo);
		System.out.println(user.getName() + "�� ȯ���մϴ�.");
		
		container.close();

	}

}
