package reply.model.dao;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import board.model.vo.Board;
import member.model.vo.Member;
import reply.model.vo.Reply;

public class ReplyDao {

	public List<Reply> selectList(Connection con, int boardNum) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Reply reply = null;
		List<Reply> replyList = new ArrayList<Reply>();
		
		String query = "select * from reply where " 
		+ "board_num = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNum);
		
			
			rset = pstmt.executeQuery();
			while (rset.next()){
				reply = new Reply();
				
				reply.setBoardNum(boardNum);
				reply.setMemberId(rset.getString("member_id"));
				reply.setReply(rset.getString("reply"));
				reply.setWriteDate(rset.getDate("reply_date"));
				reply.setReplyNum(rset.getShort("reply_num"));
				reply.setReplyWarning(rset.getInt("reply_warning"));

				System.out.println(reply);
			
				replyList.add(reply);
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return replyList;
	}

	public int assignReplyNum(Connection conn) {
		
		int replyNum = 0;
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "select reply_num "
				+ "from (select * from reply "
				+ "order by reply_num desc) "
				+ "where rownum = 1 ";
		
		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if (rs.next())
				replyNum = rs.getInt("reply_num") + 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}

		commit(conn);
		
		return replyNum;
	}
	
	public int insertReply(Connection con, Reply reply) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "insert into reply values(?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, reply.getMemberId());
			pstmt.setInt(2, reply.getBoardNum());
			pstmt.setString(3, reply.getReply());
			pstmt.setDate(4, new Date(reply.getWriteDate().getTime()));
			pstmt.setInt(5, reply.getReplyWarning());
			pstmt.setInt(6, reply.getReplyNum());
		
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public int deleteReply(Connection con, int replyNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from reply where reply_num = ?";		

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, replyNum);						
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}

}
