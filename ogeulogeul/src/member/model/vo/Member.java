package member.model.vo;

import java.sql.Date;

public class Member implements java.io.Serializable {

   private String memberId;
   private String password;
   private String name;
   private Date birth;
   private String face;
   private String email;
   private String phone;
   private int memberWarning;
   private String memo;
   private String gender;
   
   
   public Member(){}

   
   public Member(String memberId, String password, String name, Date birth, String gender, String face,  String email, String phone, 
         int memberWarning, String memo) {
      super();
      this.memberId = memberId;
      this.password = password;
      this.name = name;
      this.birth = birth;
      this.gender = gender;
      this.face = face;
      this.email = email;
      this.phone = phone;
      this.memberWarning = memberWarning;
      this.memo = memo;

   }


   public String getMemberId() {
      return memberId;
   }


   public void setMemberId(String memberId) {
      this.memberId = memberId;
   }


   public String getPassword() {
      return password;
   }


   public void setPassword(String password) {
      this.password = password;
   }


   public String getName() {
      return name;
   }


   public void setName(String name) {
      this.name = name;
   }


   public Date getBirth() {
      return birth;
   }


   public void setBirth(Date birth) {
      this.birth = birth;
   }


   public String getFace() {
      return face;
   }


   public void setFace(String face) {
      this.face = face;
   }


   public String getEmail() {
      return email;
   }


   public void setEmail(String email) {
      this.email = email;
   }


   public String getPhone() {
      return phone;
   }


   public void setPhone(String phone) {
      this.phone = phone;
   }


   public int getMemberWarning() {
      return memberWarning;
   }


   public void setMemberWarning(int memberWarning) {
      this.memberWarning = memberWarning;
   }


   public String getMemo() {
      return memo;
   }


   public void setMemo(String memo) {
      this.memo = memo;
   }


   public String getGender() {
      return gender;
   }


   public void setGender(String gender) {
      this.gender = gender;
   }


   @Override
   public String toString() {
      return this.memberId + ", " + this.password  + ", " + this.name  + ", " + this.birth + ", " + this.face  + ", " +  this.email 
             + ", " +  this.phone  + ", " + this.memberWarning  + ", " + this.memo  + ", " +  this.gender;
   }
   
   
   
   
}