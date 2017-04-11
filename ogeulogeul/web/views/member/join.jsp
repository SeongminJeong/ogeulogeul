<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오글 - art and sensitivity</title>
<!-- <script type="text/javascript" src="/ogeul/js/join.js"></script> -->
<script type="text/javascript" src="/ogeul/js/jquery-3.1.1.min.js"></script>
<script>
   window.onload = function() {
      document.getElementById("name").focus();

      document.getElementById("face").onchange = function() {
         readImg('face', 'imgout');
      }

      //이메일 셀렉트 선택 기능
      $("#email_domain").attr("readonly", false);

      $("#email_select").change(function() {
         var selectVar = $("#email_select option:selected").val();
         if (selectVar == 'insertEmail') { // 직접입력
            $("#email_domain").focus();
            $("#email_domain").attr("readonly", false);
            $("#email_domain").val('');
            }else if(selectVar == 'selectEmail'){   // 선택
               $("#email_domain").attr("readonly", true);
               $("#email_domain").val('');
            }else { // 메일 선택
            $("#email_domain").attr("readonly", true);
            $("#email_domain").val(selectVar);
         }
      });

      $('#btn_submit')
            .click(
                  function() {

                     //생년월일 하나로 합친값을  date 값으로  formatting
                     var year = $('#year').val();
                     var month = $('#month').val();
                     var date = $('#date').val();
                     if(month.length = 1){
                        month = "0" + month;
                     }
                     if(date.length = 1){
                        date = "0" + date;
                     }
                 /*     var birth = year + month + date;
                     $('#birth').val(birth); */

                     //이메일 포멧팅
                     var email_id = $('#email_id').val();
                     var email_domain = $('#email_domain').val();
                     var email = email_id + "@" + email_domain;
                     $('#email').val(email);

                     //핸드폰 번호 포멧팅
                     var phone_pre = $('#phone_pre').val();
                     var phone_middle = $('#phone_middle').val();
                     var phone_tail = $('#phone_tail').val();

                     var phone = phone_pre + "-" + phone_middle + "-"
                           + phone_tail;
                     $('#phone').val(phone);

                     if (phone_tail == null || phone_tail == "") {
                        document.getElementById("phone_alert").innerHTML = "<span>!!</span> 휴대폰 번호는 필수입력 항목 입니다.";
                        $("#phone_tail").focus();
                     }

                     if (phone_middle == null || phone_middle == "") {
                        document.getElementById("phone_alert").innerHTML = "<span>!!</span> 휴대폰 번호는 필수입력 항목 입니다.";
                        $("#phone_middle").focus();
                     }

                     if (phone_pre == null || phone_pre == "") {
                        document.getElementById("phone_alert").innerHTML = "<span>!!</span> 휴대폰 번호는 필수입력 항목 입니다.";
                        $("#phone_pre").focus();
                     }

                     //가입 재확인 기능
                     var confirmChk = confirm("회원가입을  하시겠습니까?");
                     if (confirmChk == true) {

                        var regProfileUrl = "/ogeul/minsert";
                    
                        $('#frm').attr('action', regProfileUrl);
                

                        $('#frm').attr('method', "post");

                        $("#frm").submit();
                     }
                     
                     //
                     
                     
                  });
   }
</script>

<script>
   //확장자 제한
   function getExtend(path) {
      var str = path.substring(path.lastIndexOf(".") + 1);
      return str;
   }

   window.addEventListener(
               "load",
               function() {
                  var title_photo = document.getElementById("face");
                  title_photo
                        .addEventListener(
                              "change",
                              function() {
                                 var ext = getExtend(this.value);
                                 var result = (ext.toLowerCase() == "jpg"
                                       || ext.toLowerCase() == "jpeg"
                                       || ext.toLowerCase() == "gif" || ext
                                       .toLowerCase() == "png")
                                 if (!result) {
                                    alert("알림 : 지원하지 않는 미디어 형식입니다.\n\njpg, gif, png 확장자를 가진 이미지 파일만 올려주세요.");
                                    title_photo.value = null;
                                    var output = document
                                          .getElementById('imgout');
                                    output.removeAttribute('src');
                                    return;
                                 } else {
                                    readImg('face', 'imgout');
                                    $("#facecheck").val("1");
                                 }
                              });
               });

   function imgoutClick() {
      $('#face').click();
   }

   function readImg(inputId, outputId) {
      var file = document.getElementById(inputId).files[0];
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function() {
         var output = document.getElementById(outputId);
         output.src = reader.result;
      }
      reader.onerror = function(e) {
         alert("읽기 오류:" + e.target.error.code);
         return;
      }
   }
</script>

<style>
.button {
   font-size: 11pt;
   color: white;
   font-family: "아리따-돋움(TTF)";
   background-color: #353535;
   border-color: white;
   border-width: 0px;
   padding: 5px;
   border-radius: 5px;
   margin-top: 2px;
}

.button:hover {
   cursor: pointer;
}

.container {
   align: center;
   width: 1000px;
   height: 700px;
   border: 1px solid red;
   top: 0;
   left: 0;
   right: 0;
   bottom: 0;
   position: absolute;
   margin: auto;
}
</style>
</head>
<body style="font-family: '아리따-돋움(TTF)';">
   <form action="/ogeul/minsert" method="post" id="frm"  enctype="multipart/form-data" >
      <div class="container">
         <table>
            <tr>
               <td>이름*</td>
               <td><input type="text" id="name" name="username"></td>
               <td width="80px;" height="50px;" rowspan="7"
                  style="border: 1px solid red">
                  <!-- 이미지 사진이 첨부되는 부분 -->
                  <div id="background_img">
                     <img id="imgout" alt="" src="/ogeul/images/default.png" width="300" height="300">
                  </div>
               </td>
               <td></td>
            </tr>
            <tr></tr>
            <tr>
               <td>ID*</td>
               <td><input type="text" id="id" name="userid"></td>
               <td></td>
               <td></td>
            </tr>
            <tr></tr>
            <tr>
               <td>PASSWORD*</td>
               <td><input type="password" id="password" name="pwd"></td>
               <td></td>
               <td></td>
            </tr>
            <tr>
               <td>PASSWORD 확인*</td>
               <td><input type="password" id="password2" name="pwd2"></td>
               <td>
               <td></td>
               <td></td>
            </tr>
            <tr></tr>
            <tr>
               <td>생년월일*</td>
               <td><input type="hidden" id="birth" name="birth"><input
                  type="text" size="4" maxlength="4" id="year" name="year"
                  placeholder="2017">년 <input type="text" size="2"
                  maxlength="2" id="month" name="month" placeholder="01">월 <input
                  type="text" size="2" maxlength="2" id="date" name="date"
                  placeholder="01">일</td>
               <td></td>
               <td></td>
            </tr>
            <tr></tr>
            <tr>
               <td>성별*</td>
               <td><input type="radio" name="gender" value="M">남자 <input
                  type="radio" name="gender" value="F">여자</td>
               <td></td>
               <td></td>
            </tr>
            <tr></tr>
            <tr>
               <td>EMAIL*</td>
               <td><input type="hidden" id="email" name="email"><br> <input
                  type="text" id="email_id" name="email_id" size="15"> @ <input
                  type="text" id="email_domain" name="email_domain" size="15"> &nbsp; <select
                  id="email_select">
                     <option value="insertEmail">직접 입력</option>
                     <option value="naver.com">naver.com</option>
                     <option value="hanmail.net">hanmail.net</option>
                     <option value="gmail.com">gmail.com</option>
               </select></td>
               <td></td>
               <td></td>
            </tr>
            <tr></tr>
            <tr>
               <td>핸드폰 번호*</td>
               <td><input type="hidden" id="phone" name="phone"> <input
                  type="text" id="phone_pre" name="" size="3" maxlength="3">
                  - <input type="text" id="phone_middle" name="" size="4"
                  maxlength="4"> - <input type="text" id="phone_tail"
                  name="" size="4" maxlength="4">
                  <div id="phone_alert" style="color: red;"></div></td>
               <td><input type="file" name="face" id="face"
                  accept=".jpg, .jpeg, .gif, .png" required ></td>
               <td></td>

            </tr>
            <tr></tr>
            <tr>
               <td colspan="2">
            </tr>
            <tr>
               <td colspan="2" align="right"><input type="button"
                  class="button" id="btn_submit" value="JOIN"> &nbsp;
                  <button class="button"
                     onclick="alert('회원가입을 취소합니다');location.href='loginForm.html';">취소</button>
               </td>
            </tr>
         </table>
      </div>
   </form>
   
</body>
</html>