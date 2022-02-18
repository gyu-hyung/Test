package member;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Autowired @Qualifier("hanul") private SqlSession sql;
	
	
	public boolean member_join(MemberVO vo) {
		return sql.insert("member.mapper.join" , vo) == 1? true : false ;
		
	}

	
	public MemberVO member_login(HashMap<String, String> map) {
		return sql.selectOne("member.mapper.login", map);
	}

	
	public boolean member_update(MemberVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean member_delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean member_id_check(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public MemberVO  kakaoidcheck(String email) {
		
		return sql.selectOne("member.mapper.kakaoidcheck", email);
	}
	
	/*
	 * public MemberVO member_kakao(String id) { return
	 * sql.selectOne("member.mapper.kakao" id); }
	 */

}
