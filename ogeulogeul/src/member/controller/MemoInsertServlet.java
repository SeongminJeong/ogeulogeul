package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemoInsertServlet
 */
@WebServlet("/memoinsert")
public class MemoInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemoInsertServlet() {
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
		
	String memo = request.getParameter("memo");
	String memberid = request.getParameter("memberid");
/*	String password = request.getParameter("password");
	String date = request.getParameter("birth");
	String phone = request.getParameter("phone");
	String email = request.getParameter("email");
	String gender = request.getParameter("gender");
	String warning = request.getParameter("warning");
	String face= request.getParameter("face");
	String name = request.getParameter("name");*/
	/*
	int memberwarning=Integer.parseInt(warning);
	 SimpleDateFormat formatter = 
				new SimpleDateFormat("yyyyMMdd");
	  java.util.Date birth2 = null;

 
         try {
			birth2 = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 

      System.out.println(birth2);

      java.sql.Date birth =new java.sql.Date(birth2.getTime());
 
      System.out.println(birth);
	System.out.println(memo);

	*/
Member member = new Member();
 member.setMemberId(memberid);
 member.setMemo(memo);

	/*	member.setAge(age);
		
		member.setAddress(address);*/
		
		int result = new MemberService().insertMemo(member);
		
		
		RequestDispatcher view = null;
		if(result > 0){
			response.sendRedirect("/ogeul/myinfo?memberid=" + memberid + "&flag=0");
		/*	HttpSession session = request.getSession();*/
	/*		System.out.println("세션 아이디 : " + 
						memberId + " : " + session.getId());
			session.setAttribute("loginUser", member);
			response.sendRedirect("views/member/memberDetailView.jsp");		*/
		}
		else{
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
