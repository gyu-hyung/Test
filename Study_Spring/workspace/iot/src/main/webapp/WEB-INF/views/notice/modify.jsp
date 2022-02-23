<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src='js/file_check.js'></script>
</head>
<body>
<h3>공지글 수정</h3>
<!-- 파일 첨부하여 submit() 하는 경우
	1. method 는 post 로 지정
	2. enctype='multipart/form-data'  지정 -->
<form action="update.no" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="${vo.id }" />
<table>
	<tr>
		<th class='w-px120'>제목</th>
		<td>
			<input type="text" name="title" value="${vo.title }" class='chk' title='제목' />
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea name="content" class='chk' title="내용">${vo.content }</textarea>
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td class='left'>
			<label>
				<a><img src='imgs/select.png' class='file-img' /></a>
				<input type="file" id='attach-file' name='file' />
			</label>
			<span id='file-name'>${vo.filename }</span>
			<a id='delete-file' style='display : ${empty vo.filename ? "none" : "inline"}'><i class="fa-solid fa-circle-xmark"></i></a>
		</td>
	</tr>
</table>
<input type="hidden" name="attach" />   <!-- 첨부파일을 저장시 사용  -->   
</form>
<div class='btnSet'>
	<a class='btn-fill' onclick="if ( emptyCheck() ) { $('[name=attach]').val( $('#file-name').text());  $('form').submit() }">저장하기</a>
	<a class='btn-empty' href='detail.no?id=${vo.id }'>취소</a>
</div>
</body>
</html>















