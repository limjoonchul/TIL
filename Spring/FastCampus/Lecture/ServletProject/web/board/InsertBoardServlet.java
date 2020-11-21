package com.rubypaper.web.board;

import com.rubypaper.biz.board.BoardDAO;
import com.rubypaper.biz.board.BoardVO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "InsertBoardServlet")
public class InsertBoardServlet extends HttpServlet {
    private String encoding;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 0. 글로벌 파라미터 정보 추출
        ServletContext context = getServletContext(); // 부모로부터 상속됨 httpservlet에서 상송됨
        encoding = context.getInitParameter("boardEncoding");

        // 1. 사용자 입력 정보 추출
        request.setCharacterEncoding(encoding); // 마찬가지로 가져올때 포맷 설정
        String title = request.getParameter("title");
        String writer = request.getParameter("writer");
        String content = request.getParameter("content");

        // 2. DB 연동 처리
        BoardVO vo = new BoardVO();
        vo.setTitle(title);
        vo.setWriter(writer);
        vo.setContent(content);

        BoardDAO boardDAO = new BoardDAO();
        boardDAO.insertBoard(vo);

        // 3. 화면 네비게이션
        response.sendRedirect("getBoardList.do");

    }
}
