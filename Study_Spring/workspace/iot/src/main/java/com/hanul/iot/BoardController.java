package com.hanul.iot;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import board.BoardPage;
import board.BoardServiceImpl;
import board.BoardVO;
import common.CommonService;
import member.MemberVO;

@Controller
public class BoardController {
	
	@Autowired private BoardServiceImpl service;
	@Autowired private BoardPage page;
	@Autowired private CommonService common;

	
	
	
	//방명록 상세화면 용청~
	@RequestMapping("/detail.bo")
	public String detail(int id) {
		//해당 방명록 글의 조회수를 증가
		service.board_read(id);
		
		//해당 방명록 글을 DB에서 조회해와 상세화면에 출력
		service.board_detail(id);	//게시글을 봣으면 조회수가 바뀌니까 먼저 해당글에 
		return ""; 
	}
	
	

	//방명록 신규 글 저장 처리 요청
	@RequestMapping("/insert.bo")
	public String insert(BoardVO vo, MultipartFile file , HttpSession session) {
		//파일 정보가 있다면 -첨부파일 처리부
		if( ! file.isEmpty() ) {
			vo.setFilename( file.getOriginalFilename() );
			vo.setFilepath( common.fileUpload("board", file, session) );
		}
		
		vo.setWriter( ( (MemberVO)session.getAttribute("loginInfo") ).getId() );
		
		//화면에서 입력한 정보를 DB에 신규 저장한 후 목록화면 연결
		service.board_insert(vo);//writer , 첨부파일 처리안댐
		return "redirect:list.bo";
	}
	
	
	
	
	//방명록 신규 글 작성 화면 요청
	@RequestMapping("/new.bo")
	public String board() {
		return "board/new";
	}
	
	
	
	//방명록 목록화면 요청
	@RequestMapping("/list.bo")
	public String list(HttpSession session, @RequestParam(defaultValue = "1") int curPage
					, Model model, String search, String keyword
					, @RequestParam(defaultValue = "10") int pageList
					, @RequestParam(defaultValue = "list") String viewType) {
		session.setAttribute("category", "bo");
		//DB에서 방명록 정보를 조회해와 목록화면에 출력
		page.setCurPage(curPage); 	//현재 페이지를 담음
		page.setSearch(search);		//검색 조건
		page.setKeyword(keyword);	//검색어
		page.setPageList(pageList);
		page.setViewType(viewType);
		model.addAttribute("page",  service.board_list(page) );
		return "board/list";
	}
}
