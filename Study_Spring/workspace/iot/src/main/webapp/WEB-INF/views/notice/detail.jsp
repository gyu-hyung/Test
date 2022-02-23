<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공지글 안내</h3>
<table>
	<tr>
		<th class='w-px120'>제목</th>
		<td class='left' colspan='5'>${vo.title }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${vo.name }</td>
		<th class='w-px100'>작성일자</th>
		<td>${vo.writedate }</td>
		<th class='w-px100'>조회수</th>
		<td>${vo.readcnt }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td class='left' colspan='5'>${fn:replace(vo.content, crlf, '<br>') }</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td class='left' colspan='5'>${vo.filename }
			<c:if test="${ ! empty vo.filename }">
				<a href='download.no?id=${vo.id }'><i class="fa-solid fa-floppy-disk"></i></a>
			</c:if>		
		</td>
	</tr>
</table>
<div class='btnSet'>
	<a class='btn-fill' href='list.no?curPage=${page.curPage}&search=${page.search}&keyword=${page.keyword}'>목록으로</a>
	<!-- 목록 버튼 클릭시 현재 선택한 페이지값과 검색 항목 그리고 키워드를 가진 상태에서 목록 화면으로 이동 -->
	
	<c:if test="${vo.writer eq loginInfo.id }">
		<a class='btn-fill' href='modify.no?id=${vo.id }'>수정</a>
		<a class='btn-fill' onclick =" if( confirm('정말 삭제?') ) { href='delete.no?id=${vo.id }' }">삭제</a>
	</c:if>
	<!-- 로그인된 경우만 답글쓰기가능 -->
	<c:if test="${ ! empty loginInfo }">
		<a class="btn-fill" href="reply.no?id=${vo.id}">답글쓰기</a>
	</c:if>
	
</div>
</body>
</html>













