package com.hanul.iot;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonService;
import member.MemberServiceImpl;
import member.MemberVO;

@Controller
public class MemberController {
	
	@Autowired private MemberServiceImpl service;
	@Autowired private CommonService common;
	
	// Client_id 값을 필드 선언
	private String naver_client_id = "6qlDOXLBKzQkw1eBhMsi";
	
	

	
	
	//네이버 로그인 요청
	@RequestMapping("/naverLogin")
	public String naverLogin(HttpSession session) {
		
		String state = UUID.randomUUID().toString();
		//개발자 가이드 의 3.4.2 네이버 로그인 연동URL 생성하기 참고
		
		session.setAttribute("state", state);
		//https://nid.naver.com/oauth2.0/authorize?response_type=code
		//&client_id=CLIENT_ID
		//&state=STATE_STRING
		//&redirect_uri=CALLBACK_URL
		
		StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/authorize?response_type=code");
		url.append("&client_id=").append("naver_client_id");	//append이어간다 라는뜻 client_id 값을 부여
		url.append("&state=").append(state);
		url.append("&redirect_uri=http://localhost/iot/navercallback");
		return "redirect:" + url.toString();
	}
	
	
	//네이버 콜백 메소드 선언
	//3.4.3 네이버 로그인연동 결과 Callback 정보 참고
	//Callback 응답정보
	//API 요청 성공시 : http://콜백URL/redirect?code={code값}&state={state값}
	//API 요청 실패시 : http://콜백URL/redirect?state={state값}&error={에러코드값}&error_description={에러메시지}
	@RequestMapping("/navercallback")
	public String navercallback(@RequestParam(required = false) String code, String state 
			, @RequestParam(required = false) String error, HttpSession session) {
		//state값이 맞지 않거나 error 가 발생핻도 토큰 발급 불가
		if( !state.equals(session.getAttribute("state")) || error != null) {
			return "redirect:/";		//메인 페이지로 이동
		}
		//상태 토큰도 일치, error 없는상태
		//3.4.4 접근토큰 발급요청 !!! 참고하셍
		
		//https://nid.naver.com/oauth2.0/token?grant_type=authorization_code
		//&client_id=jyvqXeaVOVmV
		//&client_secret=527300A0_COq1_XV33cf
		//&code=EIc5bFrl4RibFls1
		//&state=9kgsGTfH4j7IyAkg  
		
		StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
		url.append("&client_id=").append(naver_client_id);
		url.append("&client_secret=Z_Iah8QPKJ");
		url.append("&code=").append(code);
		url.append("&state=").append(state);
		
		//3.4.5 접근 토큰을 이용하여 프로필 API 호출하기 		common패키지 생성 이것때매 
		// common.requestAPI() 를 통해 출력받는 타입이 json이라 json 객체 사용위한 lib 를 추가
		
		JSONObject json = new JSONObject( common.requestAPI(url) );
		//json 안에잇는 토큰이라 ㅇ 이것저것을 추출
		String token = json.getString("access_token");
		String type = json.getString("token_type");
		
		//curl  -XGET "https://openapi.naver.com/v1/nid/me" \
	    //-H "Authorization: Bearer AAAAPIuf0L+qfDkMABQ3IJ8heq2mlw71DojBj3oc2Z6OxMQESVSrtR0dbvsiQbPbP1/cxva23n7mQShtfK4pchdk/rc="
		
		return "";
	}
	
	
	
	//로그아웃 화면 요청
		@RequestMapping("/logout")
		public String logout(HttpSession session) {
			//세션에 담긴 로그인 정보를삭제함
			session.removeAttribute("loginInfo"); //session에 담긴 "loginInfo" 값을 삭제
			return "redirect:/";		//로그아웃 시 루트(home.jsp)로 이동
		}
	
	
	// 로그인 처리 요청
	@ResponseBody
	@RequestMapping("/iotLogin")
	public boolean login(String id , String pw , HttpSession session) {
		//화면에서 전송된 아이디 , 비밀번호가 일치하는 회원정보를ㄹ DB에서 조회해 옴
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		MemberVO vo = service.member_login(map);
		session.setAttribute("loginInfo", vo);		//
		return vo == null ? false : true;	//결과값이 null 이면 false / null 이 아니면 true
	}
	
	
	//로그인 화면 요청
	@RequestMapping("/login")
	public String login (HttpSession session ) {
		session.setAttribute("category", "login");
		return "member/login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
