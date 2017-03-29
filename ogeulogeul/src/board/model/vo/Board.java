package board.model.vo;

import java.util.Date;

public class Board {

	private int boardNum;
	private String memberId;
	private int likeCount = 0;
	private int category;
	private Date uploadDate;
	private int boardWarning = 0;
	private String title;
	private String content;
	private String stillcut;
	private String producer;
	
	public Board() {
		
	}

	public Board(int boardNum, String memberId,
			int category, String title, String content,
			String stillcut, String producer) {
		this.boardNum = boardNum;
		this.memberId = memberId;
		this.category = category;
		this.uploadDate = new Date();
		this.title = title;
		this.content = content;
		this.stillcut = stillcut;
		this.producer = producer;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getBoardWarning() {
		return boardWarning;
	}

	public void setBoardWarning(int boardWarning) {
		this.boardWarning = boardWarning;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStillcut() {
		return stillcut;
	}

	public void setStillcut(String stillcut) {
		this.stillcut = stillcut;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	
}
