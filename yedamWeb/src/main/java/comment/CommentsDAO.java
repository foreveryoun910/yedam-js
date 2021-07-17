package comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentsDAO extends DAO {
	private static CommentsDAO instance; // Singleton
	
	public static CommentsDAO getInstance() {
		if(instance != null) {
			return instance;
		}
		
		return new CommentsDAO();
	}
	
	
	// 수정
	public HashMap<String, Object> updateComment(Comments comment) {
		connect();
		String sql = "update comments set name=?, content=? where id=?";
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, comment.getName());
			psmt.setString(2, comment.getContent());
			psmt.setString(3, comment.getId());
			psmt.executeUpdate();
			
			map.put("id", comment.getId());
			map.put("name", comment.getName());
			map.put("content", comment.getContent());
			map.put("code", "success");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return map;
	}
	
	
	// 입력
	public HashMap<String, Object> insertComment(Comments comment) {
		// 필요한 작업: 현재 시퀀스번호 로드 > 현재번호+1 > 입력 > 시퀀스+1
		connect();
		HashMap<String, Object> map = new HashMap<String, Object>();
		int currentId = 0;

		try {
			conn.setAutoCommit(false); // 사용자가 커밋을 할 때까지는 자동커밋x
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select value from id_repository where name='COMMENT'");
			if(rs.next()) {
				currentId = rs.getInt(1); // ???????
			}
			currentId++; // 새로운 시퀀스 번호(+1해줌)
			psmt = conn.prepareStatement("update id_repository set value=? where name='COMMENT'");
			psmt.setInt(1, currentId);
			psmt.executeUpdate();
			
			psmt = conn.prepareStatement("insert into comments(id, name, content) values(?, ?, ?)");
			psmt.setInt(1, currentId);
			psmt.setString(2, comment.getName());
			psmt.setString(3, comment.getContent());
			psmt.executeUpdate();
			conn.commit(); // 실제 커밋
			
			map.put("id", currentId);
			map.put("name", comment.getName());
			map.put("content", comment.getContent());
			map.put("code", "success");
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback(); // 예외 발생 시 commit 하면 안 되니까 rollback 처리 구문을 써준다.
			} catch (SQLException e1) {
				e1.printStackTrace();
				map.put("code", "error");
			} finally {
				disconnect();
			}
		}
		return map; // 처리 결과 반환
	} // end of insertComment()
	
	
	// 목록
	// Object 타입: 최상위 객체 -> 숫자, 문자 모두 아우를 수 있다 (?)
	public List<HashMap<String, Object>> selectAll() {
		connect();
		String sql = "select * from comments order by id";
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("id", rs.getInt("id"));
				map.put("name", rs.getString("name"));
				map.put("content", rs.getString("content"));
				list.add(map); // 리스트에 map 타입을 담는다
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	
}
