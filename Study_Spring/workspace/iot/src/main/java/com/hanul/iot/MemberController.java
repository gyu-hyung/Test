package com.hanul.iot;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	// 로그인 처리 요청
	@RequestMapping("/iotLogin")
	public void login() {
		
	}
	
	
	//로그인 화면 요청
	@RequestMapping("/login")
	public String login (HttpSession session ) {
		session.setAttribute("category", "login");
		return "member/login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
