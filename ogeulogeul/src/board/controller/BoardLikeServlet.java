package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class BoardLikeServlet
 */
@WebServlet("/BoardLikeServlet")
public class BoardLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html; charset=utf-8");

		int result = 0;
		int flag = 0;
		
		if ((flag = Integer.parseInt(request.getParameter("flag"))) == 0) {
			if ((result = new BoardService().isLiked(
					Integer.parseInt(request.getParameter("boardNum")),
					request.getParameter("memberId"))) == 1)
				response.getWriter().append("like");

			else if (result == -1)
				response.getWriter().append("unlike");
		}
		
		else if (flag == 1) {
			System.out.println("like / unlike ");
			if ((result = new BoardService().likeBoard(
					Integer.parseInt(request.getParameter("boardNum")),
					Integer.parseInt(request.getParameter("category")),
					request.getParameter("memberId"))) == 1)
				response.getWriter().append("like");
			else if (result == -1)
				response.getWriter().append("unlike");
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
