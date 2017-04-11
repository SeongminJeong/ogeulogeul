package board.controller;

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

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/BoardListServlet")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Board> boardList = null;
		String stillcut, content, producer;
		
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		System.out.println(request.getParameter("flag"));
		
		int flag = Integer.parseInt(request.getParameter("flag"));
		String memberId = request.getParameter("memberId");
		
		if (flag == 0)
			boardList =  new BoardService().selectAllList();

		else if (flag == 1)
			boardList =  new BoardService().selectMemberBoardList(memberId);
		
		else if (flag == 2)
			boardList =  new BoardService().selectLikedBoardList(memberId);
		
		else if (flag == 3) {
			boardList =  new BoardService().selectSortedBoardList(request.getParameter("type"));
		}
		
		for(Board b : boardList){
			//한 개의 객체 정보를 json 객체에 저장함
			JSONObject job = new JSONObject();
			job.put("boardNum", b.getBoardNum() + "");
			job.put("memberId", b.getMemberId());
			job.put("likeCount", b.getLikeCount() + "");
			job.put("category", b.getCategory()+ "");
			job.put("uploadDate", b.getUploadDate() + "");
			job.put("boardWarning", b.getBoardWarning() + "");
			job.put("title", URLEncoder.encode(
					b.getTitle(), "UTF-8"));
			job.put("content", URLEncoder.encode(
					(content = b.getContent())==null? " ":content, "UTF-8"));
			job.put("stillcut", b.getStillcut());
			job.put("producer", URLEncoder.encode(
					(producer = b.getProducer())==null? " ":producer, "UTF-8"));
			
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
