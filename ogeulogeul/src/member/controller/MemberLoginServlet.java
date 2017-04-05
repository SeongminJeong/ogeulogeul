package member.controller;

import java.io.IOException;

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
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				System.out.println("LoginServlet 의 doPost() 연결 작동됨.");
				
				//1. 전송값에 한글이 있을 경우, 인코딩처리함
				//request.setCharacterEncoding("utf-8");
				//2. 응답할 페이지에 대한 컨텐츠타입과 한글에 인코딩처리함
				response.setContentType("text/html; charset=utf-8");
				
				//3. 전송값 꺼내서 변수에 저장하기
				String memberId = request.getParameter("memberid");
				String password = request.getParameter("password");

				//System.out.println("아이디 : " + userId + ", 암호 : " + userPwd);
				
				//4. 비즈니스 로직 처리용 모델의 로그인 처리용 메소드를 실행함
				Member loginUser = new MemberService().loginCheck(memberId, password);
				
				System.out.println(loginUser);
				//5. 받은 결과를 가지고, 성공/실패에 대한 뷰 페이지를 내보냄
				if(loginUser != null){
					//로그인 상태 확인용 세션 객체 생성함
					HttpSession session = request.getSession();
					System.out.println("세션 아이디 : " + 
								memberId + " : " + session.getId());
					session.setAttribute("loginUser", loginUser);
					
					response.sendRedirect("index.jsp");
					System.out.println("로그인 완료");
				}else{
					//response.sendRedirect("views/member/memberError.jsp");
					
					//지정하는 뷰 페이지에서만 결과 데이터를 받아서 처리하게 할 경우
					//대상 뷰로 request 객체에 데이터를 저장하고 포워딩처리함
					
					//getRequestDispatcher("상대경로만 사용할 수 있음");
					//절대경로 사용하면 에러남("/context root 명/대상이름")
					RequestDispatcher rd = 
							request.getRequestDispatcher("/views/member/memberError.jsp");
					request.setAttribute("message", "로그인 실패");
					rd.forward(request, response);
				
	}

}
}
