package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserTimeEditDao {

	private static final String SQL_DLETE_WORKTIME = "DELETE FROM attendance WHERE user_id = ? AND christian = ? AND month = ? AND day = ?";
	private static final String SQL_ZIKANSYUUSEI = "INSERT INTO attendance (user_id, christian, month, day, week, going_work, leaving_work, break_start) VALUES (?,?,?,?,?,?,?,?)";

	private Connection connection;

	public UserTimeEditDao(Connection connection) {
		this.connection = connection;
	}

	public UserTimeEditDao() {

	}

	//時刻編集メソッド
	public void userTimeEdit(int userId, int christian, int month, int day, String week, String goingWork,
			String leavingWork, String breakStart) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_ZIKANSYUUSEI)) {
			try (PreparedStatement stmtt = connection.prepareStatement(SQL_DLETE_WORKTIME)) {
				stmtt.setInt(1, userId);
				stmtt.setInt(2, christian);
				stmtt.setInt(3, month);
				stmtt.setInt(4, day);

				stmtt.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			stmt.setInt(1, userId);
			stmt.setInt(2, christian);
			stmt.setInt(3, month);
			stmt.setInt(4, day);
			stmt.setString(5, week);
			stmt.setString(6, goingWork);
			stmt.setString(7, leavingWork);
			stmt.setString(8, breakStart);

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
