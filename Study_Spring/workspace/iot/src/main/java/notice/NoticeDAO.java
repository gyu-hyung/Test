package notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO implements NoticeService {

	@Autowired @Qualifier("hanul") private SqlSession sql;
	
	@Override
	public void notice_insert(NoticeVO vo) {
		sql.insert("notice.mapper.insert", vo);
	}

	@Override
	public List<NoticeVO> notice_list() {
		return sql.selectList("notice.mapper.list");
	}

	@Override
	public NoticeVO notice_detail(int id) {
		return sql.selectOne("notice.mapper.detail", id);
	}

	@Override
	public void notice_update(NoticeVO vo) {
		sql.update("notice.mapper.update", vo);
	}

	@Override
	public void notice_delete(int id) {
		sql.delete("notice.mapper.delete", id);
	}

	@Override
	public void notice_read(int id) {
		sql.update("notice.mapper.read", id);
	}

	@Override
	public NoticePage notice_list(NoticePage page) {
		// 총 글의 개수를 조회
		int pagecnt = sql.selectOne("notice.mapper.totalList", page);
		page.setTotalList(pagecnt);
		
		// 현재 페이지 정보를 조회하여 List<NoticeVO> 형태로 담음.
		List<NoticeVO> list = sql.selectList("notice.mapper.list", page);
		page.setList(list);
		return page;
	}

	@Override
	public void notice_reply_insert(NoticeVO vo) {		
		sql.insert("notice.mapper.reply_insert", vo);
	}

}






