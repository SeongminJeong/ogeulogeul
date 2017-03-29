package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/BoardInsertServlet")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		//response.getWriter().append("업로드가 성공적으로 완료되었습니다.");
		
		String answer;
		int category = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		content = content.equals("")?" ":content;
		String stillcut = request.getParameter("stillcut");
		String producer = request.getParameter("producer");
		String memberId = request.getParameter("memberId");
		
		BoardService bs = new BoardService();
		
		if (bs.insertBoard(new Board(bs.assignBoardNum(), 
				memberId, category, title, content, stillcut, producer)) > 0)
			answer = "업로드가 성공적으로 완료되었습니다.";
		else
			answer = "업로드에 실패했습니다.";

		System.out.println(answer);
		
		response.getWriter().append(answer);
		
		/*
		PrintWriter out = response.getWriter();
		out.print("업로드가 성공적으로 완료되었습니다.");
		out.flush();
		out.close();
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
