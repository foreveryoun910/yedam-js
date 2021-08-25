package co.yedam.member.model;

import java.util.List;

public interface MemberMapper {
	List<MemberVO> memberList(); // 회원목록
	MemberVO memberSelect(MemberVO vo); // 회원조회
	int memberInsert(MemberVO vo); // 회원가입
	int memberUpdate(MemberVO vo); // 회원수정
	int memberDelete(MemberVO vo); // 회원삭제
}
