package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;


//Spring @Controller <-
@WebServlet("*.cos")
public class CosController extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("utf-8");
		System.out.println(req.getParameter("key"));
		MultipartRequest multi
		= new MultipartRequest(req, req.getRealPath("/"),3000000);
		String dtoString = multi.getParameter("dto");
		Gson gson = new Gson();
		MemberDTO dto = gson.fromJson(dtoString , MemberDTO.class);
		System.out.println( dto.getId() );
		System.out.println( dto.getPw() );
		System.out.println( dto.getName() );
		System.out.println( dto.getAddr() );
		/*
		 * int recvcnt = Integer.parseInt( multi.getParameter("param") ); String recv =
		 * multi.getParameter("param0"); PrintWriter writer = res.getWriter(); for(int i
		 * =0 ; i < recvcnt ; i++) { String recvData = multi.getParameter("param"+i);
		 * writer.print(recvData + "middle"); System.out.println(recvData); }
		 * System.out.println(multi.getParameter("key"));
		 */
		
		MemberDTO resDto = new MemberDTO(999, "pwpw", "Middle", "MiddleAddr");
		PrintWriter writer = res.getWriter();
		String rtnData = gson.toJson(resDto);
		
		writer.print(rtnData);
		
	}

}
