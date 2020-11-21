package com.rubypaper.web.user;

import com.rubypaper.biz.board.BoardDAO;
import com.rubypaper.biz.board.BoardVO;
import com.rubypaper.biz.user.UserDAO;
import com.rubypaper.biz.user.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 사용자 입력 추출
        String id = request.getParameter("id"); // 대소문자 구분된다 구분해서 넣자.
        String password = request.getParameter("password");

        // 2. DB 연동 처리
        UserVO vo = new UserVO();
        vo.setId(id);
        vo.setPassword(password);
        System.out.println(vo.toString());
        // 밑의 두 코드가 왜 이런식으로 하는지 확 감은 안온다.
        UserDAO userDAO = new UserDAO();
        UserVO user = userDAO.getUser(vo);

        // 3. 화면 네비게이션
        if (user != null){
            System.out.println("success");
            response.sendRedirect("getBoardList.do");
        } else {
            System.out.println("fail");
            response.sendRedirect("login.html");
        }
    }

}
