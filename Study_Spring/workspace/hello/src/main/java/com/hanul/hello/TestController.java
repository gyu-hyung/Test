package com.hanul.hello;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.MemberVo;

//컨트롤러 객체 생성 @Controller
@Controller //어노테이션 을 붙임 
public class TestController {
	
	//어떤 요청에 대해 연결할 것인지 지정@RequestMapping()
	@RequestMapping("/first")
	public String view(Model model) {
		
		model.addAttribute("today", new SimpleDateFormat("yyyy년 MM월 dd일 ss초").format(new Date()));		
		return "index";
	}
	
	
	@RequestMapping("/second")
	public ModelAndView view () {
		
		// ModelAndView 를 활용하여 화면 전환 및 데이터 표시
		ModelAndView mav = new ModelAndView();
		mav.addObject("now", new SimpleDateFormat("a hh시 mm분 ss초").format(new Date()) );
		
		//second가 index.jsp로 연결되게끔 지정
		mav.setViewName("index");
		return mav;
	}
	
	
	@RequestMapping("/join")//클라가 조인이란 요청을헀을때 member폴터안에 join이란폴더 호출
	public String join() {
		return "member/join";
	}
	
	// ① HttpServletRequest 의 타입으로 파라미터 접근 
	@RequestMapping("/joinRequest")
	public String member(HttpServletRequest request , Model model) {
		//전송된 form태그의 값을 info 화면에 출력할 수있도록 데이터를 모델에 담는다.
		model.addAttribute( "name" , request.getParameter("name") );
		model.addAttribute( "gender" , request.getParameter("gender") );
		model.addAttribute( "email" , request.getParameter("email") );
		return "member/info";
	}
	
	// ② @RequestParam 의 타입으로 파라미터 접근 
	@RequestMapping("/joinParam")
	public String member(String name, String gender, String email, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("gender", gender);
		model.addAttribute("email", email);
		return "member/info";
	}
		
	// ③ 데이터객체 (VO) 로 파라미터 접근
	@RequestMapping("/joinDataObject")
	public String member (MemberVo vo , Model model) {
		model.addAttribute("member", vo);				//vo를 그대로 모델에 보냄
		model.addAttribute("method", "데이터객체");
		return "member/info";
	}
	
	// ④ @PathVariable 로 데이터(파라미터) 접근
	@RequestMapping("/joinPath/{name}/{gender}/{email}")
	public String member(Model model, String name, String gender, String email) {
		model.addAttribute("method", "@PathVariable 방식");
		model.addAttribute("name", name);
		model.addAttribute("gender", gender);
		model.addAttribute("email", email);
		return "member/info";
	}
	
	// 로그인 (/login_result)처리 페이지 요청
	@RequestMapping("/login_result")
	public String login(String id, String pw) {
		//id 를 'hong' pw 를 '1234'라고 했을 때
		if( id.equals("hong") && pw.equals("1234") ) {
			return "home";			//forward방식
//			return "redirect:/";		//redirect방식
			
		}else {
			return "member/login";		//forward방식 
//			return "redirect:/login";   //redirect방식
		}
		
	}
	
	
	
	
	// 로그인(login) 페이지 요청
	@RequestMapping("/login")
	public String login() {
		
		
		return "member/login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
