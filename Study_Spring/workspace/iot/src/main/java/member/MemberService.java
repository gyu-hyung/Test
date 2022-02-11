package member;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
	
	
	// 회원 가입 성공 여부를 boolean 타입으로 받자
	boolean member_join(MemberVO vo);
	
	
	//아이디 , 비번 일치하는 회원 정보 조회
	MemberVO member_login(HashMap<String,String> map);
	
	
	//회원정보 변경저장
	boolean member_update(MemberVO vo);
	
	
	//회원 탈퇴시 회원정보 삭제
	boolean member_delete(String id);
	
	
	//아이디 중복검사
	boolean member_id_cheack(String id);
		
	
	
}
