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
@WebServlet("*.test")
public class TestController extends HttpServlet {
	
	String resource = "mybatis/config.xml";
	InputStream inputStream ;
	SqlSessionFactory sqlsessionFactory;
	SqlSession session ;
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		req.setCharacterEncoding("utf-8");
		System.out.println("�ȵ���̵� �̵���� ������.");
		System.out.println(req.getServletPath());
		
		initMybatis();
		//oracle.jdbc.driver.OracleD
		//SqlSessionFactory
		//���̺귯�� ������ �ڵ��ϼ��ȴ�
		PrintWriter writer = resp.getWriter();
		writer.print("servlet => g");
		
		if(req.getServletPath().equals("/asdf.te")) {
			
			//Android���� �ʿ��Ѱ� View x  ���� - �ʿ��� �����͸� �ִ°�
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