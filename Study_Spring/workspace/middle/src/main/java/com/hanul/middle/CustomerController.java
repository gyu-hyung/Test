package com.hanul.middle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import customer.CustomerDAO;
import customer.CustomerVO;
@Controller
public class CustomerController {
@Autowired CustomerDAO dao;
	
	@ResponseBody // 응답 자체를 Controller에서 하기위한 처리 ( 어노테이션 ) ajax ( 페이지를 요청 x , 데이터를 요청 o )
	@RequestMapping ("/list.cu")
	public void list(HttpServletResponse res ) throws IOException {
	
		List<CustomerVO> list = dao.customer_list();
		PrintWriter writer = res.getWriter();
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");
		for (CustomerVO customerVO : list) {
			writer.println(customerVO.getName());
		}
		
	}
}
