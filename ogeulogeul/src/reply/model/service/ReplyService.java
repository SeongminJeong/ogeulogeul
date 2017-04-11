package reply.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDao;
import reply.model.dao.ReplyDao;
import reply.model.vo.Reply;

public class ReplyService {

	public List<Reply> selectList(int boardNum) {
		// TODO Auto-generated method stub
		   Connection con = getConnection();
		   List<Reply> replyList = 
		            new ReplyDao().selectList(con, boardNum);
		      close(con);
		      return replyList;
	}

	public int assignReplyNum() {
		Connection conn = getConnection();
		//Connection conn = null;
		int replyNum = new ReplyDao().assignReplyNum(conn);
		close(conn);
		return replyNum;
	}
	
	public int insertReply(Reply reply) {
		// TODO Auto-generated method stub
		   Connection con = getConnection();
		  int result = 
		            new ReplyDao().insertReply(con, reply);
		      close(con);
		      return result;
		
	}

	public int deleteReply(int replyNum) {
		 Connection con = getConnection();
		  int result = 
		            new ReplyDao().deleteReply(con, replyNum);
		      close(con);
		      return result;
	}

}
