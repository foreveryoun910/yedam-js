package fullCalendar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comment.DAO;

public class CalDAO extends DAO {
	
	public String deleteSchedule(FullCalendar cal) {
		connect();
		String sql = "delete from full_calendar where title=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cal.getTitle());
			
			int r = psmt.executeUpdate();
			if (r > 0) {
				return "success";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return "fail";
	}
	
	
	public String insertSchedule(FullCalendar cal) {
		connect();
		String sql = "insert into full_calendar values(?, ?, ?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cal.getTitle());
			psmt.setString(2, cal.getStartDate());
			psmt.setString(3, cal.getEndDate());
			
			int r = psmt.executeUpdate();
			if (r > 0) {
				return "success";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return "fail";
	}
	
	
	public List<FullCalendar> getSchedules() {
		connect();
		String sql = "select * from full_calendar";
		List<FullCalendar> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				FullCalendar cal = new FullCalendar();
				cal.setTitle(rs.getString("title"));
				cal.setStartDate(rs.getString("start_date"));
				cal.setEndDate(rs.getString("end_date"));
				list.add(cal);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
		
	}
}
