package com.rubypapper.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rubypapper.biz.board.BoardDAO;
import com.rubypapper.biz.board.BoardVO;
import com.rubypapper.biz.user.UserDAO;
import com.rubypapper.biz.user.UserVO;


public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DispatcherServlet() {
        System.out.println("===> DispatcherServlet ����");
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. ����� ��û path ������ �����Ѵ�.
		String uri = request.getRequestURI(); // ��ü�� url�̰� ��Ʈ��ȣ �ں��� �κ��� uri�̴�.
		System.out.println(uri);
		String path = uri.substring(uri.lastIndexOf("/")); // �� uri �� ������ �κп� /�ڰ� path
		System.out.println(path);
		
		// 2. ����� path�� ���� ��û�� �б�ó���Ѵ�.
		if (path.equals("/login.do")) {
			
			System.out.println("�α��� ��� ó��");
			 // 1. ����� �Է� ���� ����
			   String id = request.getParameter("id"); // �ۼ��� jsp������ servlet���� �ȿ� service�ȿ� ����  service�� request �Ű������� �����ϱ� ��� ����.
			   String password = request.getParameter("password");
			   
			   // 2. DB ���� ó��
			   UserVO vo = new UserVO();
			   vo.setId(id);
			   vo.setPassword(password);
			   
			   UserDAO userDAO = new UserDAO();
			   UserVO user = userDAO.getUser(vo);
			   
			   // 3. ȭ�� �׺���̼�
			   if(user != null){
				   
				   HttpSession session = request.getSession(); // jsp�� session�� �ٷ� �� �� ������ ������ �ٷ� �� �� ���� �����������.
				  
				   session.setAttribute("user", user); // ���⼭ ���ǿ� ������ ���
		
				   
				   response.sendRedirect("getBoardList.do"); // jsp�ΰ���ȵǰ� do�� ���ߤǵȴ�.
			   } else{
				   response.sendRedirect("login.html");
			   }
			
		} else if (path.equals("/logout.do")) {
			
			System.out.println("�α׾ƿ� ��� ó��");
			// �α׾ƿ��� ��û�� �������� �ش��ϴ� ���� ��ü�� ������ �����Ѵ�.
			HttpSession session = request.getSession();
			session.invalidate();
			
			response.sendRedirect("login.html");
			
		} else if (path.equals("/insertBoard.do")) {
			
			System.out.println("�Խñ� ��� ��� ó��");

			// 1. ����� �Է� ���� ����
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");

			// 2. DB ���� ó��
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(vo);
			
			// 3. ȭ�� �׺���̼�
			response.sendRedirect("getBoardList.do"); // �۵���� ������ ���ο� ������ �����ͼ�  �����ؾ��Ѵ�.
			// �� ���� ���� �Է��� �ϸ� ������ ����� �ؼ� ������ �Ѵ�.
			// �ٽ� �α������ؾ� �۸���� �ٽ� �Ѹ� �� �ִ�.
			
		} else if (path.equals("/updateBoard.do")) {
			
			System.out.println("�Խñ� ���� ��� ó��");
			
			 String seq = request.getParameter("seq");
			 String title = request.getParameter("title");
			 String content = request.getParameter("content");

			   BoardVO vo = new BoardVO();
			   vo.setSeq(Integer.parseInt(seq));
			   vo.setTitle(title);
			   vo.setContent(content);

			   
			   BoardDAO boardDAO = new BoardDAO();
			   boardDAO.updateBoard(vo);
			   
			   response.sendRedirect("getBoardList.do");
			
		} else if (path.equals("/deleteBoard.do")) {
			
			System.out.println("�Խñ� ���� ��� ó��");
			
			  String seq = request.getParameter("seq");

			   BoardVO vo = new BoardVO();
			   vo.setSeq(Integer.parseInt(seq));
			   
			   BoardDAO boardDAO = new BoardDAO();
			   boardDAO.deleteBoard(vo);
			   
			   response.sendRedirect("getBoardList.do");
			
		} else if (path.equals("/getBoard.do")) {
			
			System.out.println("�Խñ� �� ��ȸ ��� ó��");
			
			// Scriptlet
			String seq = request.getParameter("seq");

			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));

			BoardDAO boardDAO = new BoardDAO();
			BoardVO board = boardDAO.getBoard(vo);
			
			// 3. �˻� ����� ���ǿ� ����ϰ� jspȭ������ �̵��Ѵ�.
			HttpSession session = request.getSession();
			session.setAttribute("board", board);
			response.sendRedirect("getBoard.jsp");
			
		} else if (path.equals("/getBoardList.do")) {
			
			System.out.println("�Խñ� ��� ��� ó��");
			
			 //1. ����� �Է� ���� ����(�˻� ����� ����...)
			// request.setCharacterEncoding("EUC-KR");	
			String searchCondition = request.getParameter("searchCondition"); 
			String searchKeyword = request.getParameter("searchKeyword");
			
			// null check  �� �ΰ��� üũ�ϴ��� ����� ������ �˾ƺ���.
			// ó�� �α����� ���� �� �̰� �˻��� ���� �Ķ���͸� �������� ���̱� ������ ó���� ���� ���� 
			// �׷��� nullpointException�� �߻��ϱ� ������ null�϶� ���� �̷��� �����ؼ� �־��ִ� ���̴�. �̷��� �ϸ� ���� ����
			if(searchCondition == null) searchCondition = "TITLE";
			if(searchKeyword == null) searchKeyword = "";
			
			
			// 2. DB ���� ó��
			BoardVO vo = new BoardVO();
			vo.setSerachCondition(searchCondition);
			vo.setSerachKeyword(searchKeyword);
			
			BoardDAO boardDAO = new BoardDAO();
			List<BoardVO> boardList = boardDAO.getBoardList(vo);
			// System.out.println(boardList);
			// db���� �۸���� ������
			
			// 3. �˻� ����� ȭ��(JSP)���� ����� �� �ֵ��� ���ǿ� ����Ѵ�.
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList); // �۸���� ���ǿ� ����
			
			// �� ��� ȭ��(getBoardList.jsp)���� �̵��Ѵ�.
			response.sendRedirect("getBoardList.jsp"); // �̰� do�ιٲٸ� ���ѷ����� ������ ���ǿ���� �ٽ� do���ؼ� �� ���ǹ� ����...��� ��.
			// ���ǿ� �˻������ �����ϸ� ��ģ���̴�? �̷��� �ȵȴ� ������ ������ �����޸𸮿� ������Ǵ� ��ü�̴�. ���� ������� �α��� �ϸ� ������ �޸𸮿� ������ �����ȴ�.
			// �׷� ������ Ŀ�� �޸𸮸� ���̾���/ ����� ���ǿ� �����ϸ� �ȵǰ� request�� �����ؾ��Ѵ�. request�� ��û�̵����� �޸𸮿� request�� ���ٰ� �����ϰ� responser�� ȣ��Ǹ� �����
			// ������ ������ ������ �� ����ؼ� ���ִ´�. ���� �˻������ ���ǿ� ������ �ȵȴ�. �޸� ����.
			// ���ǿ��� ������ ���̵�, ���� ������ �����Ѵ�.
			
		} else {
			
			System.out.println("URL�� �ٽ� Ȯ�����ֽñ� �ٶ��ϴ�.");
		}
		

		
	}
	

}
