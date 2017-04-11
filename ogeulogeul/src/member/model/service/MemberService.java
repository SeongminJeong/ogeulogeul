package member.model.service;
import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {

   public Member loginCheck(String memberId, String password) {
      Connection con = getConnection();
      Member loginUser = 
            new MemberDao().loginMember(con, memberId, password);
      close(con);
      return loginUser;
   }

   public Member findId(String name, java.sql.Date birth2, String email) {
      // TODO Auto-generated method stub
      Connection con = getConnection();
      Member member = new MemberDao().findId(con, name, birth2, email);
      close(con);
      return member;
   }

   public Member findPwd(String name, String id, String phone) {
      // TODO Auto-generated method stub
      Connection con = getConnection();
      Member member = new MemberDao().findPwd(con, name, id, phone);
      close(con);
      return member;
   }

   public int insertMemeber(Member member) {
      Connection con = getConnection();
      int result = new MemberDao().insertMember(con, member);
      
      if(result > 0)
         commit(con);
      else
         rollback(con);
      
      close(con);

      return result;
   }

   public int updateMember(Member member) {
      // TODO Auto-generated method stub
      Connection con = getConnection();
      int result = new MemberDao().updateMember(con, member);
      
      if(result > 0)
         commit(con);
      else
         rollback(con);
      
      close(con);

      return result;
   }

   public int deleteMember(String uid) {
      // TODO Auto-generated method stub
      Connection con = getConnection();
      int result = new MemberDao().deleteMember(con, uid);
      
      if(result > 0)
         commit(con);
      else
         rollback(con);
      
      close(con);

      return result;
   }

public int insertMemo(Member member) {
	// TODO Auto-generated method stub
    Connection con = getConnection();
    int result = new MemberDao().insertMemo(con, member);
    
    if(result > 0)
       commit(con);
    else
       rollback(con);
    
    close(con);

    return result;
}
	public int isFavorite (String memberId, String favoriteMemberId) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().isFavorite(conn, memberId, favoriteMemberId) ;
		   
		close(conn);
		
		return result;
	}
	
   
   public int memberFavorite(String memberId, String favoriteMemberId) {

	   Connection con = getConnection();
	   
	   int result = new MemberDao().memberFavorite(memberId, favoriteMemberId, con);
	   
	   if(result != 0)
		   commit(con);
	   else
		   rollback(con);
	   
	   close(con);

	   return result;
   }
   public List<String> selectList(String memberId) {
	   Connection conn = getConnection();
	   List<String> favoriteMemberList = new MemberDao().selectList(conn, memberId) ;
	   close(conn);
	   return favoriteMemberList;
   }

public Member selectMember(String memberid) {
		Connection con = getConnection();
		Member member = new MemberDao().selectMember(con, memberid);
		close(con);
		return member;
	}

public CharSequence getFaceImage(String memberid) {
	Connection con = getConnection();
	String faceImage= new MemberDao().getFaceImage(con, memberid);
	close(con);
	return faceImage;
}

}