package com.hanul.iot;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import customer.CustomerServiceImpl;
import customer.CustomerVO;

// 고객관리 페이지처리 @Controller 생성
@Controller
public class CustomerController {

	@Autowired CustomerServiceImpl service;
	
	// 고객 정보 삭제 처리 요청
	@RequestMapping ("/delete.cu")
	public String delete(int id) {
		// 선택한 고객 정보를 DB에서 삭제 후 목록 화면으로 연결
		service.customer_delete(id);
		return "redirect:list.cu";	// 삭제 후 갱신된 목록 처리 -> list.jsp
	}
	
	// 고객 수정 정보 저장
	@RequestMapping ("/update.cu")
	public String update (CustomerVO vo) {
		// 화면에서 입력한 수정 정보를 DB에 저장한 후 상세화면으로 연결
		service.customer_update(vo);
		return "redirect:detail.cu?id=" + vo.getId();	// 수정 이후 해당 회원(id)의 수정된 상세 정보 화면 요청
	}
	
	
	// 고객 수정 정보 저장 요청
	@RequestMapping ("/modify.cu")
	public String update(int id, Model model) {
		// 화면의 해당 고객정보를 DB에서 조회해와 수정화면에 출력
		model.addAttribute("vo", service.customer_detail(id) ) ;
		return "customer/modify";
	}
	
	// 신규 고객 정보 저장처리 요청
	@RequestMapping("/insert.cu")
	public String insert(CustomerVO vo) {
		// 화면에서 입력한 정보를 DB에 저장 후 목록화면으로 연결
		service.customer_insert(vo);		
		return "redirect:list.cu";
	}
	
	// 신규 고객 등록 화면 요청
	@RequestMapping("/new.cu")
	public String new_customer() {
		return "customer/new";
	}
	
	// 고객 개인 정보 상세 화면 요청
	@RequestMapping("/detail.cu")
	public String detail(int id, Model model ) {
		
		// 선택한 고객 정보를 DB에서 조회
		model.addAttribute("vo", service.customer_detail(id) );
		return "customer/detail";
	}
	
	// 고객 관리 목록 요청
	@RequestMapping ("/list.cu")
	public String list(HttpSession session, Model model) {
		session.setAttribute("category", "cu");
		
		List<CustomerVO> list = service.customer_list();
		model.addAttribute("list", list);
		return "customer/list";
	}
	
}










