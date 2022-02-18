<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table tr td {text-align: left;}
input[name=addr] { width: calc(100% - 24px) }
.valid, .invalid { font-size: 13px; font-weight: bold; font-style: italic; }
.valid { color : green;}
.invalid { color: red; }
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
</head>
<body>
<h3>회원가입</h3>
<p class='w-pct40' style='margin: 0 auto; text-align: right; padding-bottom: 10px; font-size: 12px;'>* 은 필수 입력 항목입니다.</p>
<form action="join" method="post">
<table class='w-pct40'>
	<tr>
		<th>*성명</th>
		<td><input type="text" name='name'  /></td>
	</tr>
	<tr>
		<th>*아이디</th>
		<td><input type="text" name='id' class="chk"/>
			<a class='btn-fill-s' id='btn-id'>아이디 중복확인</a><br />
			<div class='valid'>아이디를 입력하세요(영문소문자, 숫자만 입력 가능)</div>
		</td>
	</tr>
	<tr>
		<th>*비밀번호</th>
		<td><input type="password" name="pw" class="chk"/>
			<div class='valid'>비밀번호를 입력하세요(영문대/소문자, 숫자를 모두 포함)</div>
		</td> 
	</tr>
	<tr>
		<th>*비밀번호 확인</th>
		<td><input type="password" name="pw_ck" class="chk"/>
			<div class='valid'>비밀번호를 다시 입력하세요</div>
		</td> 
	</tr>
	<tr>
		<th>*성별</th>
		<td>
			<label><input type="radio" name='gender' value="남" />남</label>
			<label><input type="radio" name='gender' value="여" checked />여</label>
		</td>
	</tr>	
	<tr>
		<th>*이메일</th>
		<td>
			<input type="text" name="email" class="chk"/>
			<div class='valid'>이메일을 입력하세요</div>
		</td>	
	</tr>
	<tr>
		<th>생년월일</th>
		<td><input type="text" name='birth' readonly />
			<a id='delete' 
			style="display : none; color: red; position: relative; right:40px; cursor: pointer" >
					<i class="fa-solid fa-circle-minus"></i></a>
		</td>
	</tr>	
	<tr>
		<th>전화번호</th>
		<td>
			<input type="text" name="tel" class='w-px40' maxlength="3" />
			- <input type="text" name="tel" class='w-px40' maxlength="4" />
			- <input type="text" name="tel" class='w-px40' maxlength="4" />
		</td>
	</tr>
	<tr>
		<th>주소</th>
		<td class='addr'><a class='btn-fill-s' onclick='daum_post()'>우편번호 찾기</a>
			<input type="text" name="post" class='w-px60' readonly /><br />
			<input type="text" name="addr" readonly /> <br />
			<input type="text" name="addr" /> 
		</td>

</table>
<div class='btnSet'>
	<a class='btn-fill' onclick="go_join()">회원가입</a>
	<a class='btn-empty' href=' <c:url value="/" /> '>취소</a>
</div>

</form>

<script type="text/javascript" src="js/join_check.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>



 <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>

<script type="text/javascript">

function go_join(){
	if ( $('[name=name]').val() == '' ) {	
	   $('[name=name]').focus();
	   return;
	}
	//중복확인 해서 이미 사용중인 경우
	if( $('[name=id]').hasClass('checked')){
		if($('[name=id]').siblings('div').hasClass('invaild')){
			alert('회원가입 불가!\n' + join.id.unUsable.desc);
			$('[name=id]').focus();
			return;
		}
	}else {
		//중복확인 하지 않은 경우
		if( !item_check( $('[name=id]') )) return;
		else{
			alert('회원가입 불가 \n' + join.id.valid.desc );
			$('[name=id]').focus();
			return;
		}
	}
	if(! item_check($('[name=pw]')) )return;
	if(! item_check($('[name=pw_ck]')) )return;
	if(! item_check($('[name=email]')) )return;
	
	$('form').submit();
}

function item_check( item ) {
	var data = join.tag_status(item);	
	if(data.code == 'invalid'){
		alert('회원가입불가! \n' + data.desc);
		item.focus();
		return false;
	}else return true;
	
}


// id = btn-id 클릭 시
$('#btn-id').on('click' , function (){
	id_check();
});

//id_check 함수 선언 및 실행
function id_check(){
	var $id = $('[name=id]');
	if($id.hasClass('checked') ) return;
	
	var data = join.tag_status($id);
	if( data.code == 'invalid'){
		alert('아이디 중복확인 불필요\n' + data.desc);
		$id.focus();
		return;
	}
	
	//DB에서 일치 여부 확인 
	$.ajax({
		url : 'id_check'
		,data : {id : $id.val()}
		,success : function ( response ) { // true : 사용 가능 / false : 이미 사용 중
			var data = join.id_usable( response );
			$id.siblings('div').text( data.desc ).removeClass().addClass(data.code);
			$id.addClass('checked');
			
		},error : function (req, text) {
			alert(text + ':' + req.status);
		}
	})
}

$('.chk').on('keyup' , function(e){
	
	//id 입력 후 Enter 키를 누르면 아이디 중복확인 버튼 실행
	if($(this).attr('name') == 'id' ) {
		if(e.keyCode == 13) id_check();
		else $(this).removeClass('checked');
	}

	
	
	
	var data = join.tag_status( $(this) );
	//반환된 결과값(data)엔 code 와 desc 이 있음.
	$(this).siblings('div').text(data.desc).removeClass().addClass(data.code);
});


// 생일이 선택되면 기호가 나타남.
$('[name=birth]').change(function (){
	$('#delete').css('display','inline');
});

// #delete 클릭시 생일 값 초기화.. 안보이게 ..
$('#delete').click(function (){
	$('[name=birth]').val('');
	$('#delete').css('display','none');
});

$( function() {
	// 나이 제한을 두기 위한 처리
	var today = new Date();
	var endDay = new Date( today.getFullYear() - 13, today.getMonth(), today.getDate() -1 );
	
    $("[name=birth]").datepicker({
    	dayNamesMin : ['일', '월', '화', '수', '목', '금', '토']
    ,   monthNamesShort : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ]
    ,   changeMonth : true
    ,   changeYear : true
    ,	dateFormat : 'yy-mm-dd'
    ,	showMonthAfterYear : true
    ,	maxDate : endDay	
    });
    	
    
  } );

function daum_post() {
    new daum.Postcode({
        oncomplete: function( response ) {
            // 조회된 우편번호를 입력하기 위한 제이쿼리 선언
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
            $('[name=post]').val( response.zonecode );
            
            var addr = response.userSelectedType == 'J' ? response.jibunAddress : response.roadAddress;
            
            if ( response.buildingName != '') addr += ' (' + response.buildingName + ')'; 
            // 건물명이 있을 경우 addr 에 추가하는 형태            
            $('[name=addr]').eq(0).val( addr );
        }
    }).open();
}
</script>

</body>
</html>












