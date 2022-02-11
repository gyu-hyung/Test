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
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;

import customer.CustomerDAO;
import customer.CustomerVO;
@Controller
public class CustomerController {
@Autowired CustomerDAO dao;
	Gson gson = new Gson();	

	@ResponseBody // 응답 자체를 Controller에서 하기위한 처리 ( 어노테이션 ) ajax ( 페이지를 요청 x , 데이터를 요청 o )
	@RequestMapping ("/list.cu")
	public void list(HttpServletResponse res ) throws IOException {
	
		List<CustomerVO> list = dao.customer_list();
		PrintWriter writer = res.getWriter();
		
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");
		
		
		String data = gson.toJson(list);
		writer.print(data);
		
	}
	
	@ResponseBody
	@RequestMapping("/update.cu")
	public void update_customer(HttpServletRequest req	) {
		System.out.println("안드에서 접근함");
		String getData = req.getParameter("vo");
		System.out.println(getData);
		// List형태로 바꿀때 gson <=TypeToken
		//VO(Object)형태로 바꿀떄 Class<T>
		CustomerVO vo = gson.fromJson(getData , CustomerVO.class);
		System.out.println(vo.getEmail());
		dao.customer_update(vo);
		
	}
	
	@ResponseBody
	@RequestMapping("/delete.cu")
	public void delete_customer(HttpServletRequest req	) {
		System.out.println("delete.cu 안드에서 접근함 ");
		int getData = Integer.parseInt( req.getParameter("id"));
		System.out.println(getData);
		dao.customer_delete(getData);
		
	}
	
	
	
	
	
}
