package common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
	
	//dao가 필요없는상태 db가아니라 외부 api에서 가져오는거라
	/*
	 * try { URL url = new URL(apiURL); HttpURLConnection con =
	 * (HttpURLConnection)url.openConnection(); con.setRequestMethod("GET"); int
	 * responseCode = con.getResponseCode(); BufferedReader br;
	 * System.out.print("responseCode="+responseCode); if(responseCode==200) { // 정상
	 * 호출 br = new BufferedReader(new InputStreamReader(con.getInputStream())); }
	 * else { // 에러 발생 br = new BufferedReader(new
	 * InputStreamReader(con.getErrorStream())); } String inputLine; StringBuffer
	 * res = new StringBuffer(); while ((inputLine = br.readLine()) != null) {
	 * res.append(inputLine); } br.close(); if(responseCode==200) {
	 * out.println(res.toString()); } } catch (Exception e) { System.out.println(e);
	 * }
	 */
	
	//API 를 요청하는 메소드를 생성
	public String requestAPI( StringBuffer url ) {
		String result = "";
		try {
			/* URL url = new URL(apiURL); */
			//연결할 개체가 http 통신을 할 예정이므로 http 간의 연결 개체 1개를 만듬
		      HttpURLConnection con = (HttpURLConnection) new URL(url.toString()).openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      
		      // 여러가지 정보를 읽어 들이는데  버퍼를 통해 읽어 들이기 위해서 BufferReader 를 사용
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode);
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();	//실제값이 담겨진 변수 res 값을 리턴하여 보내줌.
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      con.disconnect();
		      result = res.toString();
		    } catch (Exception e) {
		      System.out.println(e.getMessage());	//error 메시지 처리
		    }
		
	return result;
	}
	
	
	// 접근 토큰을 이용하여 프로필 API 를 호출하기 위한 메소드를 생성
	public String requestAPI( StringBuffer url ,String property) {
		String result = "";
		try {
			/* URL url = new URL(apiURL); */
			//연결할 개체가 http 통신을 할 예정이므로 http 간의 연결 개체 1개를 만듬
		      HttpURLConnection con = (HttpURLConnection) new URL(url.toString()).openConnection();
		      con.setRequestMethod("GET");
		      // 요청 헤더명이 "Authorization" 이므로 property 를 바꾸고 , 파라미터 명을 기재 result -> property
		      con.setRequestProperty("Authorization", property);		//액세스 토큰과 토근타입이 들어있다
		      int responseCode = con.getResponseCode();
		      
		      // 여러가지 정보를 읽어 들이는데  버퍼를 통해 읽어 들이기 위해서 BufferReader 를 사용
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode);
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();	//실제값이 담겨진 변수 res 값을 리턴하여 보내줌.
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      con.disconnect();
		      result = res.toString();
		    } catch (Exception e) {
		      System.out.println(e.getMessage());	//error 메시지 처리
		    }
		
	return result;
	}
	
	
	
	
	
	
	
	
}
