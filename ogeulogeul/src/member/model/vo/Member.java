package member.model.vo;

import java.sql.Date;

public class Member implements java.io.Serializable {

	private String memberId;
	private String password;
	private String name;
	private String face;
	private String email;
	private String phone;
	private String memo;
	private int memberWarning;
	private Date birth;
	
	
	public Member(){}


	public Member(String memberId, String password, String name, String face, String email, String phone, String memo,
			int memberWarning, Date birth) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.face = face;
		this.email = email;
		this.phone = phone;
		this.memo = memo;
		this.memberWarning = memberWarning;
		this.birth = birth;
	}


	public String getMemberId() {
		return memberId;
	}


	public String getPassword() {
		return password;
	}


	public String getName() {
		return name;
	}


	public String getFace() {
		return face;
	}


	public String getEmail() {
		return email;
	}


	public String getPhone() {
		return phone;
	}


	public String getMemo() {
		return memo;
	}


	public int getMemberWarning() {
		return memberWarning;
	}


	public Date getBirth() {
		return birth;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setFace(String face) {
		this.face = face;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	public void setMemberWarning(int memberWarning) {
		this.memberWarning = memberWarning;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}


	@Override
	public String toString() {
		return this.memberId + ", " + this.password  + ", " + this.name  + ", " + this.face  + ", " +  this.email 
				 + ", " +  this.phone  + ", " + this.memo  + ", " + this.memberWarning  + ", " +  this.birth;
	}
	
	
	
	
}
