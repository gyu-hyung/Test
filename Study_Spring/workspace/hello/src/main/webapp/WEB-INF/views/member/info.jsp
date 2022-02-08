<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>info.jsp</title>
</head>
<body>
<h3>회원가입 정보 [${method }]</h3>
성명	: ${name}	<br>
성별	: ${gender}	<br>
이메일	: ${email}	<br>
<hr>
<h3>회원가입 정보 [MemberVO member]</h3>
성명	: ${member.name}	<br>
성별	: ${member.gender}	<br>
이메일	: ${member.email}	<br>


<a href='<c:url value="/join" />'>회원가입으로~~~~~~~~~~~</a>
</body>
</html>