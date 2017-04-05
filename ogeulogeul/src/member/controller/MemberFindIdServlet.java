package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.sun.glass.ui.Pixels.Format;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberFindIdServlet
 */
@WebServlet("/findid")
public class MemberFindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFindIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
*/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//System.out.println("LoginServlet 의 doPost() 연결 작동됨.");
		
		//1. 전송값에 한글이 있을 경우, 인코딩처리함
		request.setCharacterEncoding("utf-8");
		//2. 응답할 페이지에 대한 컨텐츠타입과 한글에 인코딩처리함
		//response.setContentType("text/html; charset=utf-8");
		//3. 전송값 꺼내서 변수에 저장하기
		
		
		String name = request.getParameter("name");
		String date = request.getParameter("year")+request.getParameter("month")+request.getParameter("date");
		String email = request.getParameter("email_id")+"@"+request.getParameter("email_domain");
	
		System.out.println(name);
		System.out.println(date);
		System.out.println(email);


		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
		java.util.Date birth = null;
	   
				try {
					birth = formatter.parse(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		System.out.println(birth);

		java.sql.Date birth2 =new java.sql.Date(birth.getTime());
      
    	
		//4. 비즈니스 로직 처리용 모델의 로그인 처리용 메소드를 실행함
		Member member = new MemberService().findId(name,birth2, email);
		
	 	RequestDispatcher view = null;
	/*	System.out.println(loginUser);*/
		//5. 받은 결과를 가지고, 성공/실패에 대한 뷰 페이지를 내보냄
	if(member != null){
			//로그인 상태 확인용 세션 객체 생성함
			view = request.getRequestDispatcher(
					"views/member/memberFindid.jsp");
			request.setAttribute("member", member);
			view.forward(request, response);
		//	System.out.println("로그인 완료");
		
	//	JOptionPane.showMessageDialog(null, member.getMemberId());
	

		}else{

			RequestDispatcher rd = 
					request.getRequestDispatcher("/ogeul/views/member/memberError.jsp");
			request.setAttribute("message", "로그인 실패");
			rd.forward(request, response);
		}
	}
	}
	
