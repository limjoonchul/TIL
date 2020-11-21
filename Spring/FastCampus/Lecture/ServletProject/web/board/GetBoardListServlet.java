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
import java.io.PrintWriter;
import java.util.List;

public class GetBoardListServlet extends HttpServlet {
    private String encoding;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardDAO boardDAO = new BoardDAO();
        List<BoardVO> boardList = boardDAO.getBoardList();

        response.setContentType("text/html;charset=euc-kr"); // 이걸해야 브라우저에서도 글씨가 안깨짐
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=EUC-KR'>");
        out.println("<title>글 목록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
        out.println("<h1>게시글 목록</h1>");
        out.println("<h3>테스트님 로그인 환영합니다......");
        out.println("<a href='logout.do'>Log-out</a></h3>");

        out.println("<!-- 검색 시작 -->");
        out.println("<form action='getBoardList.do' method='post'>");
        out.println("<table border='1' cellpadding='0' cellspacing='0' width='700'>");
        out.println("<tr>");
        out.println("<td align='right'>");
        out.println("<select name='searchCondition'>");
        out.println("<option value='TITLE'>제목");
        out.println("<option value='CONTENT'>내용");
        out.println("</select>");
        out.println("<input name='searchKeyword' type='text'/>");
        out.println("<input type='submit' value='검색'/>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</form>");
        out.println("<!-- 검색 종료 -->");

        out.println("<table border='1' cellpadding='0' cellspacing='0' width='700'>");
        out.println("<tr>");
        out.println("<th bgcolor='orange' width='100'>번호</th>");
        out.println("<th bgcolor='orange' width='200'>제목</th>");
        out.println("<th bgcolor='orange' width='150'>작성자</th>");
        out.println("<th bgcolor='orange' width='150'>등록일</th>");
        out.println("<th bgcolor='orange' width='100'>조회수</th>");
        out.println("</tr>");

        for (BoardVO board : boardList) {
            out.println("<tr>");
            out.println("<td>" + board.getSeq() + "</td>");
            out.println("<td align='left'><a href='getBoard.do?seq=" + board.getSeq() + "'>" + board.getTitle() + "</a></td>");
            out.println("<td>" + board.getWriter() + "</td>");
            out.println("<td>" + board.getContent() + "</td>");
            out.println("<td>" + board.getCnt() + "</td>");
            out.println("</tr>");
        }


        out.println("</table>");
        out.println("<br>");
        out.println("<a href='insertBoard.html'>새글 등록</a>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");

        out.close();

    }
    
}
