package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("*.te")
public class TestController extends HttpServlet {
	String resource = "mybatis/config.xml";
	InputStream inputStream ;
	SqlSessionFactory sqlsessionFactory;
	SqlSession session ;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//super.service(req, resp);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		req.setCharacterEncoding("utf-8");
		System.out.println("안드로이드 미들웨어 접근함.");
		System.out.println(req.getServletPath());
		
		initMybatis();
		//oracle.jdbc.driver.OracleD
		//SqlSessionFactory
		//라이브러리 없으면 자동완성안댐
		PrintWriter writer = resp.getWriter();
		writer.print("servlet => g");
		
		if(req.getServletPath().equals("/asdf.te")) {
			
			//Android에서 필요한건 View x  응답 - 필요한 데이터를 주는것
		}
		
	
	}
	public void initMybatis() {
		try {
			inputStream =  Resources.getResourceAsStream(resource);
			sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlsessionFactory.openSession();
			int testInt = session.selectOne("mybatis.test.select");
			System.out.println(testInt);
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
