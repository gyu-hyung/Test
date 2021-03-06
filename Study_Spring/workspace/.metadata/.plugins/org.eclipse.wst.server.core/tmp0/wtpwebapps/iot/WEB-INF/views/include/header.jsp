<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel='stylesheet' type='text/css' href='css/common.css?v=<%=new Date().getTime() %>' >

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" /><!-- 제이쿼리 cdn방식 -->

</script>

<style>
header ul, header ul li {
	margin : 0;
	padding: 0;
	display: inline;
}
header .category{
	font-size: 18px;
}

header .category ul li:not(:first-child) {	/* 첫번째 li(로고)만 빼고 지정 */
	padding-left : 30px;
}

header .category ul li a:hover , header .category ul li a.active{
	font-weight: bold;
	color: #0000cd;
}

</style>

<header style="text-align: left; border-bottom: 1px solid #ccc; padding: 15px 0; ">
	<div class='category' style="margin-left:200px;">
		<ul>
			<li><a href='<c:url value="/" />'><img src='imgs/hanul.logo.png'/></a></li>
			<li><a href='list.cu' ${category eq 'cu' ? "class='active'" : '' } >고객 관리</a></li>
			<li><a href='list.hr' ${category eq 'hr' ? "class='active'" : '' } >사원 목록</a></li>
			<li><a href='list.no' ${category eq 'no' ? "class='active'" : '' } >공지 사항</a></li>
			<li><a href='list.bo' >방명록</a></li>
			<li><a href='list.da' >공공 데이터</a></li>
		</ul>
	</div>
</header>