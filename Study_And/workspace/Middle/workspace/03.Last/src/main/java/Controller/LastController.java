package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
@WebServlet("*.Last")
public class LastController extends HttpServlet {
	
	String resource = "mybatis/config.xml";
	InputStream inputStream ;
	SqlSessionFactory sqlsessionFactory;
	SqlSession session ;
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		req.setCharacterEncoding("utf-8");
		System.out.println("dfdfdf");
		System.out.println(req.getServletPath());
		
		
		
		
		initMybatis();
	
		PrintWriter writer = resp.getWriter();
		writer.print("ddddddd");
		
		
		
		
		
		
		
	}
	public void initMybatis() {
		try {
			inputStream =  Resources.getResourceAsStream(resource);
			sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlsessionFactory.openSession();
			int testInt = session.selectOne("last.mapper.select");
			System.out.println(testInt);
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
