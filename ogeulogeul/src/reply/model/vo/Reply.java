package reply.model.vo;

import java.util.Date;

public class Reply {
private String memberId;
private int replyNum;
private int boardNum;
private String reply;
private int replyWarning;
private Date writeDate;

public Reply(){}
public Reply(String memberId, int replyNum, int boardNum, String reply, int replyWarning, Date writeDate) {
	super();
	this.memberId = memberId;
	this.replyNum = replyNum;
	this.boardNum = boardNum;
	this.reply = reply;
	this.replyWarning = replyWarning;
	this.writeDate = writeDate;
}
public String getMemberId() {
	return memberId;
}
public void setMemberId(String memberId) {
	this.memberId = memberId;
}
public int getReplyNum() {
	return replyNum;
}
public void setReplyNum(int replyNum) {
	this.replyNum = replyNum;
}
public int getBoardNum() {
	return boardNum;
}
public void setBoardNum(int boardNum) {
	this.boardNum = boardNum;
}
public String getReply() {
	return reply;
}
public void setReply(String reply) {
	this.reply = reply;
}
public int getReplyWarning() {
	return replyWarning;
}
public void setReplyWarning(int replyWarning) {
	this.replyWarning = replyWarning;
}
public Date getWriteDate() {
	return writeDate;
}
public void setWriteDate(Date writeDate) {
	this.writeDate = writeDate;
}
@Override
public String toString() {
	return "Reply [memberId=" + memberId + ", replyNum=" + replyNum + ", boardNum=" + boardNum + ", reply=" + reply
			+ ", replyWarning=" + replyWarning + ", writeDate=" + writeDate + "]";
}
}
