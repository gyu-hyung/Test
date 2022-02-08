package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oreilly.servlet.MultipartRequest;


//Spring @Controller <-
@WebServlet("*.list")
public class ListController extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		Gson gson = new Gson();
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("utf-8");
		System.out.println("안드로이드 미들웨어 접근성공");
		System.out.println(req.getServletPath());
		System.out.println("시소");
		
		
		MultipartRequest multi
		= new MultipartRequest(req, req.getRealPath("/"),3000000);
		String listString = multi.getParameter("list");
		System.out.println(listString);
		
		ArrayList<MemberDTO> list = gson.fromJson(listString
				,new TypeToken<List<MemberDTO>>() {}.getType() );
		
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getId());
		}
		
		list = new ArrayList<MemberDTO>();
		for(int i=0;i<20; i++) {
			list.add(new MemberDTO(i,"middle" +i ,  "middlename" +i, "middle"+i));
		}
		PrintWriter writer = res.getWriter();
		String rtnData = gson.toJson(list);
		
		writer.print(rtnData);
		
		
	}

}