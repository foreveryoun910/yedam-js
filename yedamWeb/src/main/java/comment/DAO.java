package comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

	protected Connection conn;
	protected Statement stmt;
	protected PreparedStatement psmt;
	// stmt와 psmt의 차이점: stmt는 파라미터를 사용하지 못한다. ex) update ~ set aa=? <- 이런 ?를 못 씀.
	// select에만 쓸 수 있는 듯.
	protected ResultSet rs;

	
	public void connect() {
		try { // 접속할 주소, user, password
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			System.out.println("연결 성공!");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	} // end of connect()

	
	public void disconnect() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} // end of disconnect()
	
} // end of DAO
