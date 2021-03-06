package member.model.dao;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import member.model.vo.Member;

public class MemberDao {
	public Member loginMember(Connection con, 
			String memberId, String password) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		String query = "select * from member where " 
		+ "member_id = ? and password = ?";
		
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
		System.out.println(name +"," + id + "," + phone);
		String query = "select * from member where " + 
				"name = ? and member_id = ? and phone = ?" ;
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
	
	public int updateMember(Connection con, Member member) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update member set password = ?, " +
		 "email = ?, phone = ?, face = ? where member_id = ?";		

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());	
			pstmt.setString(4, member.getFace());
			pstmt.setString(5, member.getMemberId());			
			System.out.println(query);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMember(Connection con, String uid) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from member where member_id = ?";		

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, uid);						
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}
	

	public int insertMemo(Connection con, Member member) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update member set memo = ? "
				+ "where member_id = ?";		

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getMemo());
			pstmt.setString(2, member.getMemberId());			
			
			result = pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}
	
	public int isFavorite(Connection conn, String memberId, String favoriteMemberId) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		String query = "select * from FAVORITE_MEMBER "
				+ "where member_id = ? and favorite_member_id = ?";
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, favoriteMemberId);
			
			if ((rs = pstmt.executeQuery()).next())
				result = 1;
			else
				result = -1;
			
			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
		
		return result;
	}
	
	public int memberFavorite(String memberId, String favoriteMemberId, Connection conn) {

		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int likeCount = 1;
		
		String query = "select * from FAVORITE_MEMBER "
				+ "where member_id = ? and favorite_member_id = ?";

		String query_delete = "delete from FAVORITE_MEMBER "
				+ "where member_id = ? and favorite_member_id = ?";
		
		String query_insert = "insert into FAVORITE_MEMBER "
				+ "values (?, ?)";
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, favoriteMemberId);
			
			if ((rs = pstmt.executeQuery()).next()) {
				
				likeCount = -1;
				pstmt = conn.prepareStatement(query_delete);
				pstmt.setString(1, memberId);
				pstmt.setString(2, favoriteMemberId);
				
				if (pstmt.executeUpdate() > 0)
					result = -1;
				
				close(pstmt);
			}
			
			else {
				pstmt = conn.prepareStatement(query_insert);
				pstmt.setString(1, memberId);
				pstmt.setString(2, favoriteMemberId);
				if (pstmt.executeUpdate() > 0)
					result = 1;
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
public List<String> selectList(Connection conn, String memberId) {
		
		System.out.println(memberId);
		List<String> favoriteMemberList = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select FAVORITE_MEMBER_ID from FAVORITE_MEMBER "
				+ "where MEMBER_ID = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			while (rs.next())
				favoriteMemberList.add(rs.getString("FAVORITE_MEMBER_ID"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return favoriteMemberList;
	}

public Member selectMember(Connection con, String memberid) {
	// TODO Auto-generated method stub
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	Member member = null;
	
	String query = "select * from member where " + 
			"member_id = ?";
	
	try {
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, memberid);
					
		rset = pstmt.executeQuery();
		if(rset.next()){
			member = new Member();
			
			member.setMemberId(memberid);
			member.setPassword(rset.getString("password"));
			member.setName(rset.getString("name"));
			member.setGender(rset.getString("gender"));
			member.setEmail(rset.getString("email"));
			member.setPhone(rset.getString("phone"));
			member.setBirth(rset.getDate("birth"));
			member.setFace(rset.getString("face"));
			member.setMemberWarning(rset.getInt("member_warning"));
			member.setMemo(rset.getString("memo"));
			System.out.println(member+"멤버셀렉트");
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		close(rset);
		close(pstmt);
	}

	return member;
}

public String getFaceImage(Connection con, String memberid) {
	// TODO Auto-generated method stub
	String result =" ";
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String query = "select face from MEMBER "
			+ "where MEMBER_ID = ?";
	
	try {
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, memberid);
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
			
			if((result = rs.getString("face")) != null)
				System.out.println("널이 아님");
			else result = "default.png";
		}
		System.out.println(result);

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		close(rs);
		close(pstmt);
	}
	
	return result;
}
}
