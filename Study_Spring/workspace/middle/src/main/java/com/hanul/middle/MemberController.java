package com.hanul.middle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import common.CommonService;
import employees.EmployeeVO;
import member.MemberDAO;
import member.MemberVO;

@Controller
public class MemberController {

	@Autowired private MemberDAO dao;
	@Autowired private CommonService service;
	Gson gson = new Gson();

	public void logout(HttpSession session) {
		// Android에서는 공통으로 사용할 static변수를 null로 비우고 페이지를 로그인 전 화면으로 이동시키거나
		//로그인 화면으로 이동만 하면 됨.
		//사용자가 로그아웃 한 시간을 체크하기 위해서는 Spring을 통해서 DB에 시간을 저장하기 위함.
	}
	
	
	// 로그인 처리 요청
	@ResponseBody
	@RequestMapping("/andLogin")
	public void login(HttpServletRequest req , HttpServletResponse res) throws IOException {
		// 화면에서 전송된 아이디, 비밀번호가 일치하는 회원 정보를 DB에서 조회해 옴
		PrintWriter writer = res.getWriter();
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", req.getParameter("id") + "");
		map.put("pw",  req.getParameter("pw") + "");		
		MemberVO vo = dao.member_login(map);
		System.out.println(vo);
	
		String data = gson.toJson(vo);
		writer.print(data);
	}
	
	@ResponseBody	//호출된곳에 그대로 보내주는곳 
	@RequestMapping("/kakaoidcheck")
	public void kakaoidcheck(String email , HttpServletResponse res ) throws IOException {
		PrintWriter writer = res.getWriter();
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");
		
		System.out.println(email);
		
		MemberVO vo = dao.kakaoidcheck(email);
		String data = gson.toJson(vo);
		writer.print(data);
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/kakaoLogin" , produces="application/json;charset=UTF-8" )
	public String kakaoLogin(HttpServletRequest req , HttpServletResponse res) {
		return "한글데이터?";
	}
	
	
	@RequestMapping("/joinInsert")
	public String joinInsert(HttpServletRequest req , HttpSession session) {
		  String sVo = req.getParameter("vo");
		  MemberVO vo = gson.fromJson(sVo, MemberVO.class); dao.member_join(vo);
//		  dao.member_join(vo);
		
		MultipartRequest mulRequest = (MultipartRequest) req;
		MultipartFile file = mulRequest.getFile("file");
		if(file != null) {
			System.out.println("파일들어옴");
			String path = service.fileUpload("and" , file , session);
			String server_path = "http://" + req.getLocalAddr() + ":" + req.getLocalPort()
											+ req.getContextPath() + "/resources/" ;
			System.out.println(server_path + path);
			vo.setImg_path(server_path + path);
			
		}else {
			System.out.println("파일안들어옴");
		}
		
		dao.member_join(vo);
		return "";
	}
	
	
	

}









