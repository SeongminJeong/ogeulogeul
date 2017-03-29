package board.model.service;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDao;
import board.model.vo.Board;

import static common.JDBCTemplate.*;

public class BoardService {

	public int assignBoardNum() {
		Connection conn = getConnection();
		int boardNum = new BoardDao().assignBoardNum(conn);
		close(conn);
		return boardNum;
	}
	
	public List<Board> selectList() {
		return null;
	}
	
	public List<Board> selectBoard(int memberId) {

		return null;
	}

	public int insertBoard(Board b) {
		Connection conn = getConnection();
		int result = new BoardDao().insertBoard(conn, b);
		close(conn);
		System.out.println(result);
		return result;
		
	}

	public int deleteBoard(int boardNum) {
		return 0;
	}

	public int warningBoard(int boardNum) {
		return 0;
	}

	public int likeBoard(int boardNum) {
		return 0;
	}
}
