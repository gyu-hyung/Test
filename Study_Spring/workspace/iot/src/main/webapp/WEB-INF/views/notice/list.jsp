<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>[공지사항]</h3>
<div id="list-top">
	<div>
		<ul>
			<!-- 관리자로 로그인된 경우만 글쓰기 가능 -->
			<c:if test="${ loginInfo.admin eq 'y' }">
				<li><a class='btn-fill' href='new.no'>글쓰기</a></li>
			</c:if>
		</ul>
	</div>
</div>
<table>
	<thead>
		<tr>
			<th class="w-px70">번호</th>
			<th>제목</th>
			<th class="w-px100">작성자</th>
			<th class="w-px100">작성일자</th>
			<th class="w-px80">첨부파일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="vo" >
			<tr>
				<td>${vo.no}</td>
				<td class="left">
					<a href='detail.no?id=${vo.id}'>${vo.title}</a>
				</td>
				<td>${vo.name}</td>
				<td>${vo.writedate}</td>
				<td>${ empty vo.filename ? '' : '<img src="imgs/attach.png" class="file-img" />' }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>