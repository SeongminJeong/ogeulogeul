package reply.controller;

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

import board.model.vo.Board;
import reply.model.service.ReplyService;
import reply.model.vo.Reply;

/**
 * Servlet implementation class ReplyListServlet
 */
@WebServlet("/ReplyListServlet")
public class ReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<Reply> replyList = null;
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
        int boardNum = Integer.parseInt(request.getParameter("boardNum"));
        
        replyList = new ReplyService().selectList(boardNum);
        
        for(Reply r : replyList){

			JSONObject job = new JSONObject();
			job.put("boardNum", r.getBoardNum() + "");
			job.put("memberId", r.getMemberId());
			job.put("reply", URLEncoder.encode(r.getReply(), "UTF-8"));
			job.put("replyNum", r.getReplyNum()+ "");
			job.put("replyDate", r.getWriteDate() + "");
			job.put("replyWarning", r.getReplyWarning() + "");
			
			
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
