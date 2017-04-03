package board.model.service;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDao;
import board.model.vo.Board;

import static common.JDBCTemplate.*;

public class BoardService {

	public int assignBoardNum() {
		Connection conn = getConnection();
		//Connection conn = null;
		int boardNum = new BoardDao().assignBoardNum(conn);
		close(conn);
		return boardNum;
	}
	
	public List<Board> selectList() {
		Connection conn = getConnection();
		List<Board> boardList = new BoardDao().selectList(conn) ;
		close(conn);
		return boardList;
	}
	
	public List<Board> selectBoard(int memberId) {

		return null;
	}

	public int insertBoard(Board b) {
		Connection conn = getConnection();
		int result = new BoardDao().insertBoard(conn, b);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
		
	}

	public int deleteBoard(int boardNum) {
		return 0;
	}

	public int warningBoard(int boardNum) {
		return 0;
	}

	public int isLiked(int boardNum, String memberId) {
		Connection conn = getConnection();
		int result = new BoardDao().isLiked(conn, boardNum, memberId) ;
		close(conn);
		return result;
	}
	
	public int likeBoard(int boardNum, int category, String memberId) {
		Connection conn = getConnection();
		int result = new BoardDao().likeBoard(conn, boardNum, category, memberId) ;
		close(conn);
		return result;
	}
}
