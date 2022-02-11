package member;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository		//dao생성시 저장소역할 한다는어노테 @Repository
public class MemberDAO implements MemberService {

	@Autowired @Qualifier("hanul") private SqlSession sql;	//@Qualifier : DB계정한정자 hanul
	
	
	@Override
	public boolean member_join(MemberVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		return sql.selectOne("member.mapper.login" , map);
	}

	@Override
	public boolean member_update(MemberVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean member_delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean member_id_cheack(String id) {
		// TODO Auto-generated method stub
		return false;
	}



	

}
