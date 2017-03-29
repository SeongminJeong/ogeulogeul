package board.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static common.JDBCTemplate.*;
import board.model.vo.Board;

public class BoardDao {
	
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
		}

		return boardNum;
	}
	
	public List<Board> selectList(Connection conn) {
		return null;
	}
	
	public List<Board> selectBoard(Connection conn, int memberId) {
		return null;
	}

	public int insertBoard(Connection conn, Board b) {

		int resultBoard = 0;
		int result = 0;
		String category = "";
		PreparedStatement pstmt = null;
		Statement stmt = null;
		
		switch (b.getCategory()) {
		case 1:
			category = "movie";
			break;
		case 2:
			category = "drama";
			break;
		case 3:
			category = "book";
			break;
		case 4:
			category = "music";
			break;
		case 5:
			category = "cartoon";
			break;
		}
		
		String query = "insert into board values ("
				+ b.getBoardNum() + ")";
		
		String query2 = "insert into " + category
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

			System.out.println("1");
			stmt = conn.createStatement();
			if ((resultBoard = stmt.executeUpdate(query)) > 0) {

				result = pstmt.executeUpdate();
				System.out.println("2");
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(stmt);
			close(pstmt);
		}
		
		return result;
	}

	public int deleteBoard(Connection conn, int boardNum) {
		return 0;
	}

	public int warningBoard(Connection conn, int boardNum) {
		return 0;
	}

	public int likeBoard(Connection conn, int boardNum) {
		return 0;
	}
}
