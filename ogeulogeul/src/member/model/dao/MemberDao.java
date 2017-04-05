package member.model.dao;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import member.model.vo.Member;

public class MemberDao {
	public Member loginMember(Connection con, 
			String memberId, String password) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		String query = "select * from member where " + 
				"member_id = ? and password = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, password);
			
			rset = pstmt.executeQuery();
			if(rset.next()){
				member = new Member();
				
				member.setMemberId(memberId);
				member.setPassword(password);
				member.setName(rset.getString("name"));
				member.setBirth(rset.getDate("birth"));
				member.setGender(rset.getString("gender"));
				member.setFace(rset.getString("face"));
				member.setEmail(rset.getString("email"));
				member.setPhone(rset.getString("phone"));
				member.setMemberWarning(rset.getInt("member_warning"));
				member.setMemo(rset.getString("memo"));
			
				System.out.println(member);
			}
			
			commit(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

	public Member findId(Connection con, String name, Date birth2, String email) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		System.out.println(name);
		System.out.println(email);
		String query = "select * from member where " + 
				"name = ? and email = ? and birth = ?" ;
		System.out.println(birth2);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setDate(3, birth2);
			rset = pstmt.executeQuery();
			if(rset.next()){
				member = new Member();
				
				member.setMemberId(rset.getString("member_id"));
	member.setPassword(rset.getString("password"));
				member.setName(name);
				member.setBirth(birth2);
				member.setGender(rset.getString("gender"));
				member.setFace(rset.getString("face"));
				member.setEmail(email);
				member.setPhone(rset.getString("phone"));
				member.setMemberWarning(rset.getInt("member_warning"));
				member.setMemo(rset.getString("memo"));
			
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
System.out.println(member);
		return member;
	}

	public Member findPwd(Connection con, String name, String id, String phone) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		System.out.println(name);
		System.out.println(id);
		String query = "select * from member where " + 
				"name = ? and member_id = ? and phone = ?" ;
		System.out.println(phone);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
            pstmt.setString(2, id);
            pstmt.setString(3, phone);
			rset = pstmt.executeQuery();
			if(rset.next()){
				member = new Member();
				
				member.setMemberId(id);
	             member.setPassword(rset.getString("password"));
				member.setName(name);
				member.setBirth(rset.getDate("birth"));
				member.setGender(rset.getString("gender"));
				member.setFace(rset.getString("face"));
				member.setEmail(rset.getString("email"));
				member.setPhone(phone);
				member.setMemberWarning(rset.getInt("member_warning"));
				member.setMemo(rset.getString("memo"));
			
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
System.out.println(member);
		return member;
	}
	
	public int insertMember(Connection con, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into member values (?, ?, ?, ?, ?, ?, ?, ?, default, null)";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setDate(4, member.getBirth());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getFace());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getPhone());

			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}
}
