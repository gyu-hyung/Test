<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>new.jsp</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp"/>
<div id="content">
<h3>신규 고객 입력</h3>
<form action="insert.cu" method="post">
<table class="w-pct60">
	<tr>
		<th class="w-px120">고객명</th>
		<td><input type="text" name="name" /></td>
	</tr>
	<tr>
		<th>성별</th>
			<td>
			<label><input type="radio" name="gender" value="남" checked/>남</label>
			<label><input type="radio" name="gender" value="여" />여</label>
			</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><input type="text" name="email" /></td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td><input type="text" name="phone" /></td>
	</tr>
</table>
</form>
<div class="btnSet">
	<a class="btn-fill" onclick=' $("form").submit() '>저장</a><!-- 제이쿼 폼태그에 있는 액션취하겠다? -->
	<a class="btn-empty" href="list.cu">취소</a>
</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>