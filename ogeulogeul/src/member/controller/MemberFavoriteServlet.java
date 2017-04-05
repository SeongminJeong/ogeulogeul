package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberFavoriteServlet
 */
@WebServlet("/MemberFavoriteServlet")
public class MemberFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFavoriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int result = 0;
		int flag = 0;

		if ((flag = Integer.parseInt(request.getParameter("flag"))) == 0) {
			System.out.println("like / unlike ");
			if ((result = new MemberService().isFavorite(
					request.getParameter("favoriteMemberId"),
					request.getParameter("memberId"))) == 1)
				response.getWriter().append("like");
			else if (result == -1)
				response.getWriter().append("unlike");
		}
		
		else if (flag == 1) {
			
			if ((result = new MemberService().memberFavorite(
					request.getParameter("favoriteMemberId"),
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
