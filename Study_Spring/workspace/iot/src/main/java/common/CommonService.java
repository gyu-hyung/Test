package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CommonService {
//	try {
//	      URL url = new URL(apiURL);
//	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
//	      con.setRequestMethod("GET");
//	      int responseCode = con.getResponseCode();
//	      BufferedReader br;
//	      System.out.print("responseCode="+responseCode);
//	      if(responseCode==200) { // 정상 호출
//	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//	      } else {  // 에러 발생
//	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//	      }
//	      String inputLine;
//	      StringBuffer res = new StringBuffer();
//	      while ((inputLine = br.readLine()) != null) {
//	        res.append(inputLine);
//	      }
//	      br.close();
//	      if(responseCode==200) {
//	        out.println(res.toString());
//	      }
//	    } catch (Exception e) {
//	      System.out.println(e);
//	    }
	
	// API 를 요청하는 메소드 생성
	public String requestAPI(StringBuffer url) {
		String result = "";
		try {
			/* URL url = new URL(apiURL); */
		    // 연결할 개체가 HTTP 통신을 할 예정이므로 HTTP 간의 연결 개체 1개를 만듬.  
			  HttpURLConnection con = (HttpURLConnection) new URL(url.toString()).openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      
		      // 여러 가지 정보를 읽어 들이는데 버퍼를 통해 읽어 들이기 위해서 BufferReader 를 사용
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode);
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();	// 실제 값이 담겨진 변수 res 값을 리턴하여 보내줌.
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      con.disconnect();
		      result = res.toString();
		    } catch (Exception e) {
		      System.out.println(e.getMessage());	// error 메시지 처리
		    }
		return result;
	}
	
	// 접근 토큰을 이용하여 프로필 API 를 호출하기 위한 메소드
	public String requestAPI(StringBuffer url, String property) {
		String result = "";
		try {
			/* URL url = new URL(apiURL); */
		    // 연결할 개체가 HTTP 통신을 할 예정이므로 HTTP 간의 연결 개체 1개를 만듬.  
			  HttpURLConnection con = (HttpURLConnection) new URL(url.toString()).openConnection();
		      con.setRequestMethod("GET");
		      // 요청 헤더명이 "Authorization" 이므로 property 를 바꾸고, 파라미터 명을 기재 result -> property
		      con.setRequestProperty("Authorization", property);
		      int responseCode = con.getResponseCode();
		      
		      // 여러 가지 정보를 읽어 들이는데 버퍼를 통해 읽어 들이기 위해서 BufferReader 를 사용
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode);
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();	// 실제 값이 담겨진 변수 res 값을 리턴하여 보내줌.
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      con.disconnect();
		      result = res.toString();
		    } catch (Exception e) {
		      System.out.println(e.getMessage());	// error 메시지 처리
		    }
		return result;
	}

	//파일 업로드 처리
	// 업로드할 처리
	//D:\Study_Spring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\iot\resources
	public String fileUpload(String categoty , MultipartFile file , HttpSession session) {
		
		String resources = session.getServletContext().getRealPath("resources");
		String folder = resources + "/upload/" + categoty + "/"
				+ new SimpleDateFormat("yyyy/MM/dd").format(new Date()	);
		
		String uuid = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		
		File dir = new File( folder );
		
		if( !dir.exists() ) dir.mkdirs();
		try {
			file.transferTo( new File(folder , uuid) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 		upload/notice/2022/02/17		/		afdadsfghsxcfweasjJ_abc.txt	
		return folder.substring(resources.length() + 1) + "/" + uuid;
	}

	
	//파일 다운로드 처리
	public void fileDownload(String filename, String filepath, HttpSession session, HttpServletResponse res) {		
		//실제 파일의 위치와 파일을 찾아 file 처리
		File file = new File( session.getServletContext().getRealPath("resources") + "/" + filepath );
		
		String mime = session.getServletContext().getMimeType(filename);
		
		res.setContentType(mime);  //응답처리할 Mime 타입 설정
		try {
			
			filename = URLEncoder.encode(filename , "utf-8").replaceAll("\\+", "%20");
			res.setHeader("content-disposition", "attachment; filename=" + filename);
		
			
			ServletOutputStream out = res.getOutputStream();
			FileCopyUtils.copy( new FileInputStream(file) , out );
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}













