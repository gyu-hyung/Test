package com.hanul.middle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import employee.EmployeeDAO;
import employee.EmployeeVO;
import employee.EmployeeVO2;


@Controller
public class EmployeeController {
	@Autowired EmployeeDAO dao;
	Gson gson = new Gson();
	
	@ResponseBody//응답자체를 Controller에서 하기위한 처리(	
	@RequestMapping("/list.hr")
	public void list(HttpServletRequest req , HttpServletResponse res) throws IOException  {
		List<EmployeeVO> list = dao.employee_list();
		PrintWriter writer = res.getWriter();
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");
		String data = gson.toJson(list);
		writer.print(data);
	
	}
	
	@ResponseBody
	@RequestMapping("/select.hr")
	public void select(HttpServletRequest req , HttpServletResponse res) throws IOException {
		String search = req.getParameter("search");
		List<EmployeeVO2> list = dao.employee_select(search);
		PrintWriter writer = res.getWriter();
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");
		String data = gson.toJson(list);
		writer.print(data);		
	}
	
	
}
