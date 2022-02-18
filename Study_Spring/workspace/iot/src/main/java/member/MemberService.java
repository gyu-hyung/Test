package member;

import java.util.HashMap;

public interface MemberService {

	// 회원 가입 성공 여부를 boolean 타입으로 받음
	boolean member_join(MemberVO vo);
	
	// 아이디, 비번 일치하는 회원 정보 조회
	MemberVO member_login(HashMap<String, String> map);
	
	// 회원정보 변경저장
	boolean member_update(MemberVO vo);
	
	// 회원 탈퇴시 회원정보 삭제
	boolean member_delete(String id);
	
	// 아이디 중복확인
	boolean member_id_check(String id);
	
	// 소셜 회원 정보 존재여부 (R)
	boolean member_social_email(MemberVO vo);
	
	// 소셜 회원 정보 신규저장 (C)
	boolean member_social_insert(MemberVO vo);
	
	// 소셜 회원 정보 변경저장 (U)
	boolean member_social_update(MemberVO vo);
	
}










