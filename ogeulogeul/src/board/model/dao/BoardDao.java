package board.model.dao;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import board.model.vo.Board;

public class BoardDao {
	
	public String category(int category) {
		
		String category_str = null;
		
		switch (category) {
		case 1:
			category_str = "movie";
			break;
		case 2:
			category_str = "drama";
			break;
		case 3:
			category_str = "book";
			break;
		case 4:
			category_str = "music";
			break;
		case 5:
			category_str = "comic";
			break;
		}
		
		return category_str;
	}
	
	public int assignBoardNum(Connection conn) {
		
		int boardNum = 0;
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "select board_num "
				+ "from (select * from board "
				+ "order by board_num desc) "
				+ "where rownum = 1 ";
		
		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if (rs.next())
				boardNum = rs.getInt("board_num") + 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}

		commit(conn);
		
		return boardNum;
	}
	
	public List<Board> selectList(Connection conn) {

		List<Board> boardList = new ArrayList<Board>();
		Statement stmt = null;
		ResultSet rs = null;
		
		int boardNum;
		String memberId;
		int likeCount;
		int category;
		Date uploadDate;
		int boardWarning;
		String title;
		String content;
		String stillcut;
		String producer;
		Board board;
		
		String query = "select * from movie union "
				+ "select * from book union "
				+ "select * from music union "
				+ "select * from drama union "
				+ "select * from comic "
				+ "order by 6";
		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				boardNum = rs.getInt("board_num");
				memberId = rs.getString("member_id");
				likeCount = rs.getInt("like_count");
				category = rs.getInt("category");
				uploadDate = rs.getDate("upload_date");
				boardWarning = rs.getInt("board_warning");
				title = rs.getString("title");
				content = rs.getString("content");
				stillcut = rs.getString("stillcut");
				producer = rs.getString("producer");
				
				board = new Board(boardNum, memberId,
						category, title, content, stillcut, producer);
				
				board.setLikeCount(likeCount);
				board.setUploadDate(uploadDate);
				board.setBoardWarning(boardWarning);
				
				boardList.add(board);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
				
		return boardList;
	}
	
	public List<Board> selectBoard(Connection conn, int memberId) {
		return null;
	}

	public int insertBoard(Connection conn, Board b) {

		int result = 0;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		
		String query = "insert into board values ("
				+ b.getBoardNum() + ", " + b.getCategory() + ")";
		
		String query2 = "insert into " + 
				category(b.getCategory())
				+ " values("
				+ "?, ?, ?, 0, 0, ?, ?, ?, ?, ?)";
		
		try {

			pstmt = conn.prepareStatement(query2);
			pstmt.setInt(1, b.getBoardNum());
			pstmt.setInt(2, b.getCategory());
			pstmt.setString(3, b.getMemberId());
			pstmt.setDate(4, new Date(b.getUploadDate().getTime()));
			pstmt.setString(5, b.getTitle());
			pstmt.setString(6, b.getContent());
			pstmt.setString(7, b.getStillcut());
			pstmt.setString(8, b.getProducer());

			stmt = conn.createStatement();
			if (stmt.executeUpdate(query) > 0) 
				result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(stmt);
			close(pstmt);
		}

		commit(conn);
		
		return result;
	}

	public int deleteBoard(Connection conn, int boardNum) {
		return 0;
	}

	
	public int warningBoard(Connection conn, int boardNum) {
		return 0;
	}

	public int isLiked(Connection conn, int boardNum, String memberId) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		String query = "select * from like_board "
				+ "where board_num = ? and member_id = ?";
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNum);
			pstmt.setString(2, memberId);
			if ((rs = pstmt.executeQuery()).next())
				result = 1;
			else
				result = -1;
				
			commit(conn);
			
			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
		
		return result;
	}
	
	public int likeBoard(Connection conn, int boardNum, int category, String memberId) {
		
		String category_str = category(category);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int likeCount = 1;
		int result = 0;
		
		String query1 = "select * from like_board "
				+ "where board_num = ? and member_id = ?";
		
		String query2 = "update " + category_str
				+ " set like_count = "
				+ "(select like_count from " + category_str
				+ " where board_num = ?) + ? "
				+ "where board_num = ?";
		
		String query_delete = "delete from like_board "
				+ "where member_id = ? and board_num = ?";
		
		String query_insert = "insert into like_board "
				+ "values (?, ?)";
		
		try {

			pstmt = conn.prepareStatement(query1);
			pstmt.setInt(1, boardNum);
			pstmt.setString(2, memberId);
			if ((rs = pstmt.executeQuery()).next()) {
				pstmt.close();
				likeCount = -1;
				pstmt = conn.prepareStatement(query_delete);
				pstmt.setString(1, memberId);
				pstmt.setInt(2, boardNum);
				if (pstmt.executeUpdate() > 0)
					System.out.println("like_board 테이블 변경 성공 (삭제 )");
				result = -1;
				pstmt.close();
			}
			else {
				pstmt = conn.prepareStatement(query_insert);
				pstmt.setString(1, memberId);
				pstmt.setInt(2, boardNum);
				if (pstmt.executeUpdate() > 0)
					System.out.println("like_board 테이블 변경 성공 (삽입 ) ");
				result = 1;
				pstmt.close();
			}
			
			pstmt = conn.prepareStatement(query2);
			pstmt.setInt(1, boardNum);
			pstmt.setInt(2, likeCount);
			pstmt.setInt(3, boardNum);
			if (pstmt.executeUpdate() > 0)
				System.out.println("movie/drama/book/music/comic 테이블 변경 성공 ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
			
		return result;
	}
}
