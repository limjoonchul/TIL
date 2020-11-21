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

@WebServlet(name = "GetBoardServlet")
public class GetBoardServlet extends HttpServlet {
    private String encoding;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        encoding = context.getInitParameter("boardEncoding");

        request.setCharacterEncoding(encoding);

        String seq = request.getParameter("seq");

        BoardVO vo = new BoardVO();
        vo.setSeq(Integer.parseInt(seq));

        // boardList에서 제목을 누르면 해당 seq값이 전송되서 이 페이지로 넘어오게 된다
        // 그럼 seq를 추출해서 vo에 넣고 이 vo를 BoardDAO에 getUser()에 넣는다 그러면 이 getUser는 vo객체를 받아서 데이터베이스에 저장하고
        // 다시 vo객체를 반환해준다.
        // 그럼 새로운 vo 객체를 만들어서 거기에 대입시켜주고 이 vo 객체를 가지고 화면을 출력 시켜준다.

        BoardDAO boardDAO = new BoardDAO();
        BoardVO board = boardDAO.getBoard(vo); //

        response.setContentType("text/html;charset=euc-kr");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=EUC-KR'>");
        out.println("<title>글 상세</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<center>");
        out.println("<h1>글 상세</h1>");
        out.println("<a href='logout.do'>Log-out</a></h3>");
        out.println("<hr>");
        out.println("<form action='updateBoard.do' method='post'>");
        out.println("<input type='hidden' name='seq' value='" + board.getSeq() + "'/>"); // 이부분이 중요함 어떤걸 수정하는지 보내주기위해 히든으로 넣었다.
        out.println("<table border='1' cellpadding='0' cellspacing='0'>");
        out.println("<tr>");
        out.println("<td bgcolor='orange' width='70'>제목</td>");
        out.println("<td align='left'><input name='title' type='text' value='"+ board.getTitle() +"'/></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td bgcolor='orange'>작성자</td>");
        out.println("<td align='left'>"+ board.getWriter() +"</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td bgcolor='orange'>내용</td>");
        out.println("<td align='left'><textarea name='content' cols='40' rows='10'>"+ board.getContent() +"</textarea></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td bgcolor='orange'>등록일</td>");
        out.println("<td align='left'>"+ board.getRegDate() +"</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td bgcolor='orange'>조회수</td>");
        out.println("<td align='left'>"+ board.getCnt() +"</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td colspan='2' align='center'>");
        out.println("<input type='submit' value='글 수정'/>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</form>");
        out.println("<hr>");
        out.println("<a href='insertBoard.html'>글등록</a>&nbsp;&nbsp;&nbsp;");
        out.println("<a href='deleteBoard.do?seq="+ board.getSeq() +"'>글삭제</a>&nbsp;&nbsp;&nbsp;");
        out.println("<a href='getBoardList.do'>글목록</a>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");


        out.close();




    }
}
