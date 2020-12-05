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
		System.out.println("로그인 기능 처리");
//		System.out.println(9/0);
		vo.setId("admin");
		vo.setPassword("admin"); // request
		
		return "login";
		
		// 내가 따로 아이디 비밀번호를 입력 안해도 된다?? 알아서 admin으로 채워진다.
	}

	@RequestMapping(value = "/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, HttpSession session) {
		System.out.println("로그인 기능 처리");
		UserVO user = userService.getUser(vo);
		if(user != null) {
			session.setAttribute("user", user);
			return "forward:getBoardList.do"; // 왜 포워드를 쓰지??
		} else {
			return "login"; // 뷰리졸버를 타야한다 jsp가 직접 쳐서 못들어가니깐 board밑으로 옮겼으니깐
		}

//		 // 1. 사용자 입력 정보 추출
//		   String id = request.getParameter("id"); // 작성한 jsp파일은 servlet파일 안에 service안에 들어간다  service에 request 매개변수가 있으니깐 사용 가능.
//		   String password = request.getParameter("password");
//		   
//		   // 2. DB 연동 처리
//		   UserVO vo = new UserVO();
//		   vo.setId(id);
//		   vo.setPassword(password);
//		   
//		   UserDAOJDBC userDAO = new UserDAOJDBC();
//		   UserVO user = userDAO.getUser(vo);
//		   
//		   // 3. 화면 네비게이션
//		   ModelAndView mav = new ModelAndView();
//		   
//		   if(user != null){
//			   
//			   HttpSession session = request.getSession(); // jsp은 session을 바로 쓸 수 있지만 서블릿은 바로 쓸 수 없음 선언해줘야함.
//			  
//			   session.setAttribute("user", user); // 여기서 세션에 유저를 담고
//			   
//			   // forward:이나 redirect: 을 뷰이름 앞에 붙이면 ViewResolver를 무시한다.
//			   mav.setViewName("forward:getBoardList.do");
//			   
////			   response.sendRedirect(".do"); // jsp로가면안되고 do로 가야ㅗ된다.
//		   } else{
//			   mav.setViewName("redirect:login.jsp"); //
////			   return "login";
////			   response.sendRedirect("login.html");
//		   }
//		return mav;
		   
		
	}
	
	// 이렇게 해도 됨.
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("로그아웃 기능 처리");
		session.invalidate();
		
		return "redirect:index.jsp"; // 왜 리다이렉트지?
	}

}
