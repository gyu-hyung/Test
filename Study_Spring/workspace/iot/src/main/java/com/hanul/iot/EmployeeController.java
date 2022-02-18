package com.hanul.iot;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import employee.EmployeeServiceImpl;

@Controller
public class EmployeeController {

	@Autowired private EmployeeServiceImpl service;
	
	// 선택한 사원의 상세 정보 조회 및 화면 출력
	@RequestMapping ("/detail.hr")
	public String detail(int id, Model model) {
		model.addAttribute("vo", service.employee_detail(id) );
		return "employee/detail";
	}
	
	// 사원목록 화면 요청
	@RequestMapping("/list.hr")
	public String list (HttpSession session, Model model, String dept_name) {
		session.setAttribute("category", "hr");
		// 사원이 소속된 부서명 목록을 조회해와 목록화면에 출력
		model.addAttribute("depts", service.employee_department() );
		model.addAttribute("dept_id", dept_name); // select 태그 내 선택된 값
		
		if (dept_name == null || dept_name.equals("all"))
			// 사원 정보 전체 조회
			model.addAttribute("list", service.employee_list() );
		else
			// 해당 부서명 사원 정보 조회
			model.addAttribute("list", service.employee_list(dept_name));
		return "employee/list";
	}
}

