package com.hanul.middle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.ResolverUtil.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;

import customer.CustomerDAO;
import customer.CustomerVO;
import employees.EmployeeDAO;
import employees.EmployeeVO;
@Controller
public class EmployeesController {
	@Autowired EmployeeDAO dao;
	Gson gson = new Gson();
	
	@ResponseBody // 응답 자체를 Controller에서 하기위한 처리 ( 어노테이션 ) ajax ( 페이지를 요청 x , 데이터를 요청 o )
	@RequestMapping ("/list.hr")
	public void list(HttpServletRequest req , HttpServletResponse res ) throws IOException {
		String search = req.getParameter("search");
		System.out.println(search);
		List<EmployeeVO> list = dao.employee_list(search);
		PrintWriter writer = res.getWriter();
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");

		String data = gson.toJson(list);
		writer.print(data);
		
		
	}
	
	
}
