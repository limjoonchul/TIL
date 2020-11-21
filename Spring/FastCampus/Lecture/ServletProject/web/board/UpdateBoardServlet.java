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

@WebServlet(name = "UpdateBoardServlet")
public class UpdateBoardServlet extends HttpServlet {
    private String encoding;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext(); // 부모로부터 상속됨 httpservlet에서 상송됨
        encoding = context.getInitParameter("boardEncoding");
        request.setCharacterEncoding(encoding);

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String seq = request.getParameter("seq");

        BoardVO vo = new BoardVO();
        vo.setTitle(title);
        vo.setContent(content);
        vo.setSeq(Integer.parseInt(seq));

        BoardDAO boardDAO = new BoardDAO();
        boardDAO.updateBoard(vo);

        response.sendRedirect("getBoardList.do");

    }
}
