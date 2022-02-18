var join = {
	//tag의 상태를 확인할 함수를 선언하고 받아오는 값을 tag 변수로 설정
	tag_status : function ( tag ){
		var data = tag.val(); 		// <- tag내 입력된 값을 data 변수로 할당
		tag = tag.attr('name');		// <- tag의 name속성의 값을 확인 ex) id, pw, emil, ...등등
				
		if( tag == 'id' )		return this.id_status ( data );
		else if (tag =='pw') 	return this.pw_status (data);
		else if (tag =='pw_ck') 	return this.pw_ck_status (data);
		else if (tag == 'email')	return this.email_status(data);
		
	}	
	, email_status(email){
		var reg = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		if(email == '')		return this.common.empty;
		else if(email.match(space))		return this.common.space;
		else if(reg.test(email))		return this.email.valid;
		else							return this.email.invalid;
	}
	, email : {
		valid 		: {code : 'valid' , desc : '유효한 이메일입니다.'}
		, invalid   : {code :'invalid' ,desc : '유효하지않은 이메일입니다.'}
	}
	, pw_ck_status (pw_ck){
		if(pw_ck == '')		return this.common.empty;
		else if(pw_ck == $('[name=pw]').val() )		return this.pw.equal;
		else										return this.pw.notEqual;
	}
	,pw_status : function( pw ){
		var reg = /[^A-Za-z0-9]/g;
		var upper = /[A-Z]/g, lower = /[a-z]/g, digit = /[0-9]/g;
		if(pw == '')		return this.common.empty;
		else if(pw.match(space))	return this.common.space;
		else if(reg.test(pw))		return this.pw.invalid;
		else if(pw.length < 5)		return this.common.min;
		else if(pw.length > 10)		return this.common.max;
		else if( !upper.test(pw) || !lower.test(pw) || !digit.test(pw) )	return this.pw.lack;
		else 						return this.pw.valid;
	}
	
	,pw : {
		invalid : {code : 'invalid' , desc : '비밀번호를 영문 대/소문자, 숫자를 모두 포함' }
		, valid : {code : 'valid' , desc : '사용 가능한 비밀번호 입니다.'}
		, lack : {code : 'invalid' , desc : '영문 대/소문자 숫자를 모두 포함해야 합니다.'}
		, equal : {code : 'valid' , desc : '비밀번호가 일치합니다.'}
		, notEqual : {code : 'invalid' , desc : '비밀번호가 일치하지 않습니다.'}
	}
	
	
	, id_status : function( id ){
		
		var reg = /[^a-z0-9]/g;	//소문자, 숫자 외 입력시...
		
		/* 입력값이 없을경우 */
		if(id =='') 		return this.common.empty;
		/* 입력값에 공백이 있을 경우*/
		else if(id.match(space))	 return this.common.space;
		/* 소문자, 숫자 외 문자 입력 여부 확인*/
		else if(reg.test(id))		return this.id.invalid;
		/* 5자 이하 입력 여부 확인 */
		else if(id.length < 5 ) 	 return this.common.min;
		/* 10자 이하 입력 여부 확인 */
		else if(id.length > 10)		 return this.common.max;
		else						return this.id.valid;
		
		
	}
	
	,id : {	//id의 기준 설정
		invalid : { code : 'invalid' , desc : '아이디는 영문 , 숫자만 입력 가능' }
		, valid  : { code : 'valid'   , desc : '아아디 중복 확인하세요.'}
		, usable : { code : 'valid'   , desc : '사용 가능한 아이디 입니다.'}
		, unUsable : {code: 'invalid' , desc : '이미 사용 중인 아이디 입니다.'}
	}
	, common 	: {
		empty 	: { code : 'invalid' , desc : '입력하세요'}
		, space : { code : 'invalid' , desc : '공백없이 입력하세요'}
		, max 	: { code : 'invalid' , desc : '최대10자 이하로 입력하세요'}
		, min 	: { code : 'invalid' , desc : '최소 5자 이상 입력하세요'}
	}
	, id_usable : function ( usable ) {
		if(usable)		return this.id.usable;
		else			return this.id.unUsable;
	}

}
var space = /\s/g;	//전체 문자 내 공백이 있는지...