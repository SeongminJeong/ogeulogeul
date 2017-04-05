package member.model.service;
import static common.JDBCTemplate.*;

import java.sql.Connection;


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

}