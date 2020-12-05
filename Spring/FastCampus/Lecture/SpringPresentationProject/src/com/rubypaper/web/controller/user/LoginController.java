package com.rubypaper.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rubypaper.biz.user.UserService;
import com.rubypaper.biz.user.UserVO;

@Controller
public class LoginController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login.do", method=RequestMethod.GET)
	public String login(UserVO vo) {
		System.out.println("�α��� ��� ó��");
//		System.out.println(9/0);
		vo.setId("admin");
		vo.setPassword("admin"); // request
		
		return "login";
		
		// ���� ���� ���̵� ��й�ȣ�� �Է� ���ص� �ȴ�?? �˾Ƽ� admin���� ä������.
	}

	@RequestMapping(value = "/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, HttpSession session) {
		System.out.println("�α��� ��� ó��");
		UserVO user = userService.getUser(vo);
		if(user != null) {
			session.setAttribute("user", user);
			return "forward:getBoardList.do"; // �� �����带 ����??
		} else {
			return "login"; // �丮������ Ÿ���Ѵ� jsp�� ���� �ļ� �����ϱ� board������ �Ű����ϱ�
		}

//		 // 1. ����� �Է� ���� ����
//		   String id = request.getParameter("id"); // �ۼ��� jsp������ servlet���� �ȿ� service�ȿ� ����  service�� request �Ű������� �����ϱ� ��� ����.
//		   String password = request.getParameter("password");
//		   
//		   // 2. DB ���� ó��
//		   UserVO vo = new UserVO();
//		   vo.setId(id);
//		   vo.setPassword(password);
//		   
//		   UserDAOJDBC userDAO = new UserDAOJDBC();
//		   UserVO user = userDAO.getUser(vo);
//		   
//		   // 3. ȭ�� �׺���̼�
//		   ModelAndView mav = new ModelAndView();
//		   
//		   if(user != null){
//			   
//			   HttpSession session = request.getSession(); // jsp�� session�� �ٷ� �� �� ������ ������ �ٷ� �� �� ���� �����������.
//			  
//			   session.setAttribute("user", user); // ���⼭ ���ǿ� ������ ���
//			   
//			   // forward:�̳� redirect: �� ���̸� �տ� ���̸� ViewResolver�� �����Ѵ�.
//			   mav.setViewName("forward:getBoardList.do");
//			   
////			   response.sendRedirect(".do"); // jsp�ΰ���ȵǰ� do�� ���ߤǵȴ�.
//		   } else{
//			   mav.setViewName("redirect:login.jsp"); //
////			   return "login";
////			   response.sendRedirect("login.html");
//		   }
//		return mav;
		   
		
	}
	
	// �̷��� �ص� ��.
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("�α׾ƿ� ��� ó��");
		session.invalidate();
		
		return "redirect:index.jsp"; // �� �����̷�Ʈ��?
	}

}
