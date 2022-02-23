package com.hanul.iot;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
	private String naver_client_id = "LSSsyEfIix901IZr3X1n";
	
	// 회원가입 처리 요청
	@ResponseBody
	@RequestMapping(value = "/join" , produces = "text/html; charset=utf-8")
	public String join (MemberVO vo, HttpServletRequest req) {
		// 회원 가입 화면에서 입력한 정보를 DB에 저장
		StringBuffer msg = new StringBuffer("<script>");
		if ( service.member_join(vo) ) {
			msg.append("alert('회원가입을 축하드립니다.'); location='")
			.append(req.getContextPath()).append("'");
		} else {
			msg.append("alert('회원 가입 실패'); location='member' ");
		}
		msg.append("</script>");
		
		return msg.toString();
	}
	
	
	// 아이디 중복확인 요청
	@ResponseBody
	@RequestMapping("/id_check")
	public boolean id_check(String id) {
		return service.member_id_check(id);
	}
	
	
	
	
	// 회원가입 페이지 이동
	@RequestMapping("/member")
	public String member(HttpSession session) {
		// 타이틀에 회원가입 명시하기 위함
		session.setAttribute("category", "join");
		return "member/join";
	}
	
	
	
	// 네이버 로그인 요청
	@RequestMapping("/naverLogin")
	public String naverLogin(HttpSession session) {
		
		// 개발자 가이드 의 3.4.2 네이버 로그인 연동 URL 생성하기 참고
		String state = UUID.randomUUID().toString();
		
		session.setAttribute("state", state);
		
		// https://nid.naver.com/oauth2.0/authorize?response_type=code
		// &client_id=CLIENT_ID
		// &state=STATE_STRING
		// &redirect_uri=CALLBACK_URL
		
		StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/authorize?response_type=code");
		url.append("&client_id=").append(naver_client_id);	// client_id 값을 부여
		url.append("&state=").append(state);
		url.append("&redirect_uri=http://localhost/iot/navercallback");
		return "redirect:" + url.toString();
	}
	
	// 네이버 콜백 메소드 선언
	// 3.4.3 네이버 로그인 연동 결과 Callback 정보 참고
	// Callback 응답 정보

	// API 요청 성공시 : http://콜백URL/redirect?code={code값}&state={state값}
	// API 요청 실패시 : http://콜백URL/redirect?state={state값}&error={에러코드값}&error_description={에러메시지}
	@RequestMapping("/navercallback")
	public String navercallback(@RequestParam(required = false) String code, String state
			, @RequestParam(required = false) String error, HttpSession session) {
		
		// state 값이 맞지 않거나 error 가 발생해도 토큰 발급 불가
		if ( ! state.equals(session.getAttribute("state")) || error != null) {
			return "redirect:/";	// 메인 페이지로 이동
		}
		
		// 상태 토큰도 일치하고, error 도 발생하지 않은 상태
		// 3.4.4 접근 토큰 발급 요청 참고
		// https://nid.naver.com/oauth2.0/token?grant_type=authorization_code
		// &client_id=jyvqXeaVOVmV
		// &client_secret=527300A0_COq1_XV33cf
		// &code=EIc5bFrl4RibFls1
		// &state=9kgsGTfH4j7IyAkg  
		
		StringBuffer url = new StringBuffer("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
		url.append("&client_id=").append(naver_client_id);
		url.append("&client_secret=YublBCpIFX");
		url.append("&code=").append(code);
		url.append("&state=").append(state);
		
		// 3.4.5 접근 토큰을 이용하여 프로필 API 호출하기
		// common.requestAPI() 를 통해서 출력 받는 값의 형태가 json 임.
		// json 객체를 사용하기 위한 라이브러리를 pom.xml에 주입
		
		JSONObject json = new JSONObject( common.requestAPI(url) );
		
		String token = json.getString("access_token");
		String type = json.getString("token_type");
		
//		curl  -XGET "https://openapi.naver.com/v1/nid/me" \
//	      -H "Authorization: Bearer AAAAPIuf0L+qfDkMABQ3IJ8heq2mlw71DojBj3oc2Z6OxMQESVSrtR0dbvsiQbPbP1/cxva23n7mQShtfK4pchdk/rc="
		
		url = new StringBuffer("https://openapi.naver.com/v1/nid/me");
		json = new JSONObject( common.requestAPI(url, type + " " + token) );
		
		if (json.getString("resultcode").equals("00") ) {
			json = json.getJSONObject("response");
			
			MemberVO vo = new MemberVO();
			vo.setSocial_type("naver");
			vo.setId(json.getString("id"));
			vo.setSocial_email(json.getString("email"));
			vo.setName(json.getString("name"));
			vo.setGender(json.getString("gender").equals("F") ? "여" : "남");
			
			session.setAttribute("loginInfo", vo);
			
			// 네이버 최초 로그인인 경우 회원정보 저장 (insert)
			// 네이버 로그인 이력이 있어 회원정보가 있다면 변경 저장
			if ( service.member_social_email(vo)) {
				service.member_social_update(vo);
			} else {
				service.member_social_insert(vo);
			}
			
			
		}
		
//		{
//			  "resultcode": "00",
//			  "message": "success",
//			  "response": {
//			    "email": "openapi@naver.com",
//			    "nickname": "OpenAPI",
//			    "profile_image": "https://ssl.pstatic.net/static/pwe/address/nodata_33x33.gif",
//			    "age": "40-49",
//			    "gender": "F",
//			    "id": "32742776",
//			    "name": "오픈 API",
//			    "birthday": "10-01"
//			  }
//		}
		
		
		
		
		
		return "redirect:/";	// 소셜 로그인시 루트로 이동
	}
	
	
	// 로그아웃 처리 요청
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		// 세션에 담긴 로그인 정보를 삭제함
		session.removeAttribute("loginInfo"); // session 에 담긴 "loginInfo" 값을 삭제
		return "redirect:/";	// 로그아웃 시 루트(home.jsp)로 이동
	}
	
	
	// 로그인 처리 요청
	@ResponseBody
	@RequestMapping("/iotLogin")
	public boolean login(String id, String pw, HttpSession session) {
		// 화면에서 전송된 아이디, 비밀번호가 일치하는 회원 정보를 DB에서 조회해 옴
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);		
		MemberVO vo = service.member_login(map);
		session.setAttribute("loginInfo", vo);
		return vo == null ? false : true;	// 결과값이 null 이면 false / null 이 아니면 true
	}
	
	// 로그인 화면 요청
	@RequestMapping("/login")
	public String login(HttpSession session) {
		session.setAttribute("category", "login");
		return "member/login";
	}	
}









