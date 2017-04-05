package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
	String uid = request.getParameter("userid");
		String upwd = request.getParameter("userpwd");
	/*	int age = Integer.parseInt(request.getParameter("age"));*/
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
	
		
		Member member = new Member();
	 member.setMemberId(uid);
		member.setPassword(upwd);
		/*member.setAge(age);*/
		member.setEmail(email);
		member.setPhone(phone);
/*		member.setAddress(address);*/
		
		int result = new MemberService().updateMember(member);
		
		RequestDispatcher view = null;
		if(result > 0){
			response.sendRedirect("/ogeul/index.jsp");		
		}else{
			view = request.getRequestDispatcher("views/member/memberError.jsp");
			request.setAttribute("message", "회원 정보 수정 실패");
			view.forward(request, response);
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
