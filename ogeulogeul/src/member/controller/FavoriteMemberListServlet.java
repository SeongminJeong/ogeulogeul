package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import board.model.service.BoardService;
import board.model.vo.Board;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class FavoriteMemberListServlet
 */
@WebServlet("/FavoriteMemberListServlet")
public class FavoriteMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		List<String> favoriteMemberList =  new MemberService().selectList(
				request.getParameter("memberId"));
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(String s : favoriteMemberList){
			//한 개의 객체 정보를 json 객체에 저장함
			JSONObject job = new JSONObject();
			job.put("favoriteMemberId", s);
			System.out.println(s);
			jarr.add(job);
		}

		json.put("list", jarr);

		response.setContentType("application/json: charset=utf-8");
		PrintWriter out = response.getWriter();
		//최종 json 객체를 문자열로 전송함
		out.print(json.toJSONString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
