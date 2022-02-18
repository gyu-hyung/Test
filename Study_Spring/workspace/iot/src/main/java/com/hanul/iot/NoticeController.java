package com.hanul.iot;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import member.MemberService;
import member.MemberServiceImpl;
import member.MemberVO;
import notice.NoticeServiceImpl;
import notice.NoticeVO;

@Controller
public class NoticeController {

	@Autowired private NoticeServiceImpl service;
	@Autowired private MemberServiceImpl member;
	@Autowired private CommonService common;
	
	// 공지글 수정 저장 처리 요청
		@RequestMapping("/update.no")
		public String update(NoticeVO vo, String attach, MultipartFile file, HttpSession session ) {
			// 원래 공지글의 첨부파일 정보를 조회해 온다. (id를 통해)
			NoticeVO notice = service.notice_detail(vo.getId());
			String uuid = session.getServletContext().getRealPath("resources") + "/" + notice.getFilepath();
			
			// 원래 파일이 첨부된 경우 이전 파일을 삭제하고 변경한 파일을 저장
			// 파일을 첨부한 경우
			if ( ! file.isEmpty() ) {
				// 원래 첨부 파일이 없었는데 수정시 첨부한 경우
				vo.setFilename(file.getOriginalFilename());
				vo.setFilepath( common.fileUpload("notice", file, session));
				
				// 원래 첨부된 파일이 있었다면 물리적인 디스크에서 해당 파일 삭제
				// 원래 첨부 파일이 있었고 수정시 바꿔서 첨부한 경우 - 원래 파일을 물리적 영역에서 삭제
				if ( notice.getFilename() != null  ) {	// 서버에 파일이 있는지 파악
					File f = new File(uuid);			// 파일 정보를 File 형태의f 변수에 할당
					if (f.exists()) f.delete();			// 기존 첨부 파일이 있다면 삭제
				}
			} else {
			// 파일을 첨부하지 않은 경우
				// 1. 원래부터 첨부하지 않았고 수정시도 첨부하지 않은 경우
				//    원래 첨부된 파일이 있었는데 삭제한 경우 - 원래의 파일을 물리적 영역에서 삭제
				if ( attach.isEmpty() ) {
					if ( notice.getFilename() != null ) {
						File f = new File(uuid);			// 파일 정보를 File 형태의f 변수에 할당
						if (f.exists()) f.delete();			// 기존 첨부 파일이 있다면 삭제
					}
				} else {
					// 2. 원래 첨부된 파일을 그대로 사용하는 경우
					vo.setFilename(notice.getFilename());
					vo.setFilepath(notice.getFilepath());
				}			
			}
			// 화면에서 변경 입력한 정보를 DB에 변경 저장한 후 상세 화면으로 연결
			service.notice_update(vo);
			return "redirect:detail.no?id=" + vo.getId();
		}
	
		
		
		
		
		
	
	//공지글 수정화면 요청
	@RequestMapping("/modify.no")
	public String modify( int id , Model model) {
		//해당 공지글 정보를 DB에서 조회해와 수정화면에 출력
		model.addAttribute("vo" , service.notice_detail(id) ) ;
		
		return "notice/modify";
	}
	
	//공지글의 삭제요청
	@RequestMapping("/delete.no")
	public String delete(int id ,String attach , MultipartFile file , HttpSession session) {
		//첨부파일이 있는 글의 경우 디스크에서 첨부파일을 삭제한다
		NoticeVO notice = service.notice_detail(id);
		//D:\ Study_Spring\ workspace\ .metadata\ .plugins\ org.eclipse.wst.server.core\ tmp0\ wtpwebapps\ iot\ resources\ upload\ notice\ 2022\ 02\ 17		실제위치
		String uuid = session.getServletContext().getRealPath("resources") + "/" + notice.getFilepath();//첨부파일의 물리적위치찾아변수에담기
		//해당 공지글 정보를 DB에서 삭제 후 목록화면으로 연결
		service.notice_delete(id);
		return "redirect:list.no";	//select 뺴고는 리다이렉트
	}
	
	
	
	//공지글의 첨부파일 다운로드 요청
	@RequestMapping("/download.no")
	public void download(int id , HttpSession session , HttpServletResponse res) {
		// 해당 공지글의 첨부파일 정보를 DB에서 조회해와 해당 파일을 서버로부터 다운로드 한다.
		NoticeVO notice = service.notice_detail(id);	//상세정보를 조회해, NoticeVO 에담음
		common.fileDownload(notice.getFilename() , notice.getFilepath() , session , res);
	}
	
	
	//공지사항 상세조회 요청
	@RequestMapping("/detail.no")
	public String detail(int id , Model model) {
		// 상세화면 요청 전 조회수 증가
		service.notice_read(id);
		
		
		//선택한 공지사항 정보를 DB에서 조회해 와 상세 화면에 출력
		model.addAttribute("vo" , service.notice_detail(id) );
		model.addAttribute( "crlf" , "\r\n" );		//줄 바꿈 처리 _ \r\n ( 윈도우 ) \n (unix), \r (mac) 
		return "notice/detail";
	}
	

	//신규 공지사항 저장처리 요청
	@RequestMapping("/insert.no")
	public String insert(NoticeVO vo , MultipartFile file , HttpSession session  ) {
		MemberVO member = (MemberVO)session.getAttribute("loginInfo");
		vo.setWriter( member.getId() );
		
		// 로그인 된 사용자의 id를 가져와 글쓴이 (writer) 에 담기 위한 처리
		vo.setWriter(   ((MemberVO)session.getAttribute("loginInfo")).getId()   );
		
		if( !file.isEmpty() ){	//파일이 있는경우
			//파일 첨부처리 부분
			vo.setFilename( file.getOriginalFilename());		//전달받은 파일의 실제 이름을 vo에 할당
			vo.setFilepath( common.fileUpload("notice", file , session) );
		}
		//화면에서 입력한 정보를 DB에 저장한 후 화면으로 이동하고자
		service.notice_insert(vo);
		
		return "redirect:list.no";
	}
	
	
	
	//신규 공지사항 입력 화면 요청
	@RequestMapping("new.no")
	public String notice() {
		return "notice/new";
	}
	
	
	//공지사항 목록화면 요청
	@RequestMapping("list.no")
	public String list(HttpSession session , Model model) {
		session.setAttribute("category", "no");
		
		//공지사항 처리 시 임의로 로그인 하기
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", "admin");
		map.put("pw", "admin");		
		session.setAttribute("loginInfo", member.member_login(map) );	;
		
		//DB에서 공지글 목록을 조회해와 목록화면에 출력
		model.addAttribute("list" ,  service.notice_list() );
		
		return "notice/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
