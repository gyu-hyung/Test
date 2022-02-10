<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
   #login{
      width: 70%;
      padding: 50px 0;
      float: left;
   }
   #userid, #userpw{
      width: 30%;
      padding: 5px 10px;
      margin-bottom: 10px;
   }
   .btn-fill{
      margin: 0 auto; 
      width: 60%;
      height: 42px;
      line-height: 42px;
      box-shadow: none;
   }
   img.social{
      width: 200px;
      height: 45px;
      margin-top: 20px;
   }
   .join{
      float: right;
      width: 30%;
      padding: 210px 0;
      background: #c2c2c2;
   }
   .join div{
      position: absolute;
      top: 50%;
      left: 85%;
      transform: translate(-50%, -50%);
   }
</style>
</head>
<body>
   <div style="width: 700px; border: 1px solid #ccc;" class="center">
      <div id="login">
         <div>
            <a href='<c:url value="/" />'><img src="imgs/hanul_logo.png" /></a>
         </div>
         <div>
            <%-- 네이버 개발자 사이트에서 documents > 네이버로그인 > 개발가이드 > 로그인 버튼 사용 가이드 --%>
            <%-- 카카오 디벨로퍼 > 카카오로그인 > 문서 > 유용한 참고 정보 > 디자인 가이드 > 표준디자인 > 리소스 다운로드 --%>
            <a href=''><img src="imgs/naver_login.png" class="social"/></a>
            <a href=''><img src="imgs/kakao_login.png" class="social"/></a>
            <div style="width: 80%; margin: 25px auto; border: 1px solid #ccc" ></div>
            <input type="text" placeholder="아이디" id="userid" autofocus/><br/>
            <input type="password" placeholder="비밀번호" id="userpw"/><br/>
            <a class="btn-fill" onclick="go_login()">로그인</a>
         </div>
      </div>
      <div class="join">
         <div>Hello, Guest<br/>
            <a class="btn-fill">회원가입</a>
         </div>
      </div>
   </div>
   <script type="text/javascript">
   function go_login() {
	   if($('#userid').val() == '' ){	//아이디 입력값이 없으면
		  	alert('아이디를 입력하세요')
	   		$('#userid').focus();
		   return;
	   }else if($('#userpw').val() == ''){
		   alert('비밀번호를 입력하세요')
		   $('#userpw').focus();
		   return;
	   }
	   
	   // login 을 위한 ajax 통신 설정
	   $.ajax({
		   url : 'iotLogin'
		   ,data : {id:$('#userid').val() , pw: $('#userpw').val() }
	   	   , success : function () {
	   		   
	   	   }, error : function (req , text){
	   		   alert(text + ':' + req.status);
	   	   }
		  
	   });
	   
   }
   
   
   
   
   
   
   </script>
</body>
</html>