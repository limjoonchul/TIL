package com.rubypaper.biz.user;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.rubypaper.biz.user.UserService;
import com.rubypaper.biz.user.UserVO;

public class UserServiceClient {
	public static void main(String[] args) {
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("business-*.xml");
		
		UserService userService = (UserService) container.getBean("userService");
		// Ŭ���̾�Ʈ�� getbeand���ϱ� ������ ���弭�������� id�� �ʿ��ϴ�.
		
		UserVO vo = new UserVO();
		vo.setId("aa");
		vo.setPassword("aa");
		
		UserVO user = userService.getUser(vo);
		if(user != null) {
			System.out.println(user.getName() + "�� ȯ���մϴ�.");
		} else {
			System.out.println("�α��� ����");
		}
		container.close();
		
	}
}
