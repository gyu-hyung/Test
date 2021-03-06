package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import dto.AndImgDTO;
@WebServlet("*.amg")
public class imgController extends HttpServlet {
	
	String resource = "mybatis/config.xml";
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	SqlSession session;
	
	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("utf-8");
		initMybatis();
		
		Gson gson = new Gson();
		String rtnData = gson.toJson(initMybatis());
		System.out.println(rtnData);
		
		//MultipartRequest multi = new MultipartRequest(req, req.getRealPath("/"),3000000);
		//안드로이드 부터 파라미터를 입력받게 되면 처리해야할 부분.↑
		PrintWriter writer = res.getWriter();
		writer.print(rtnData);
		
	}
	
	
	public List<AndImgDTO> initMybatis() {
		try {
			inputStream =  Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
			
			List<AndImgDTO> list = session.selectList("and.mapper.select");
			/*
			 * f
r(AndImgDTO andImgDTO : list) { System.out.println(andImgDTO.getImg_id());
			 * System.out.println(andImgDTO.getImg_url()); //System.out.println("이얍"); }
			 */
			
			session.close();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	

}
