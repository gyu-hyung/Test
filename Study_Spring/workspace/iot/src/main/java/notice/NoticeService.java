package notice;

import java.util.List;

public interface NoticeService {

	//공지글 신규 저장 (C)
	void notice_insert(NoticeVO vo);

	//공지글 목록조회 (R)
	List<NoticeVO> notice_list();
	
	//공지글 상세조회 (R)
	NoticeVO notice_detail(int id);
	
	//공지글 변경저장 (U)
	void notice_update(NoticeVO vo);
	
	//공지글 삭제 (D)
	void notice_delete(int id);
	
	//공지글 조회시 증가 처리 (U)
	void notice_read(int id);
}
