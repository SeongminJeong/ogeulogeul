package member.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

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
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/minsert")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 전송값에 한글이 있을 경우 문자셋 인코딩 처리함
		request.setCharacterEncoding("utf-8");

		// 2. 응답시 컨텐츠 타입 지정
		response.setContentType("text/html; charset=utf-8");

		// 3. 전송값 추출해서 변수에 저장
		String memberId = request.getParameter("userid");
		String password = request.getParameter("pwd");
		String name = request.getParameter("username");
		
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
		java.util.Date birth2 = null;
	
		
				try {
					birth2 = formatter.parse(request.getParameter("birth"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

	
		System.out.println(birth2);
	
      java.sql.Date birth =new java.sql.Date(birth2.getTime());
      
      System.out.println(birth);

		String face = request.getParameter("face");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		int memberWarning = 0;
		String memo = "";
		// 4. vo 객체 생성하고 추출값 저장
		Member member = new Member(memberId, password, name, birth, gender, face, email, phone, memberWarning, memo);
	
		
		// 5. service 의 insertMember(member) 실행하고 결과받음
		int result = new MemberService().insertMemeber(member);

		// 6. 받은 정수값을 가지고, 성공/실패에 대한 뷰페이지 내보내
		if (result > 0) {
			//response.sendRedirect("/ogeul/views/member/loginForm.html");
			
			//회원 가입과 동시에 로그인 되는 기능
			Member loginUser = new MemberService().loginCheck(memberId, password);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			response.sendRedirect("/ogeul/index.jsp");
						
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("views/member/memberError.jsp");
			request.setAttribute("message", "회원가입 실패");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
