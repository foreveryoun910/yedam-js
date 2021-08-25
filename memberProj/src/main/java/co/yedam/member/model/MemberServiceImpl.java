package co.yedam.member.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MemberServiceImpl implements MemberMapper {
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private MemberMapper map = sqlSession.getMapper(MemberMapper.class);
	
	@Override
	public List<MemberVO> memberList() {
		// TODO 회원목록
		return map.memberList();
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		// TODO 회원조회
		return map.memberSelect(vo);
	}

	@Override
	public int memberInsert(MemberVO vo) {
		// TODO 회원가입
		return map.memberInsert(vo);
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// TODO 회원수정
		return map.memberUpdate(vo);
	}

	@Override
	public int memberDelete(MemberVO vo) {
		// TODO 회원삭제
		return map.memberDelete(vo);
	}

}
