package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Attendance;

public class SyukkinDao {

	private static final String SQL_SYUKKINZIKAN = "INSERT INTO attendance (user_id, christian, month, day, week, going_work, break_start) VALUES (?,?,?,?,?,?,0)";
	private static final String SQL_TAIKINZIKAN = "UPDATE attendance SET leaving_work= ? WHERE user_id = ? AND christian = ? AND month = ? AND day = ?";
	private static final String SQL_CHECK = "SELECT * FROM attendance WHERE user_id = ? AND christian = ? AND month = ? AND day = ?";
	private static final String SQL_KYUUKEIZIKAN = "UPDATE attendance SET break_start = ? WHERE user_id = ? AND christian = ? AND month = ? AND day = ?";
	private static final String SQL_SELECT_KYUUKEIZIKAN = "SELECT * FROM attendance WHERE user_id = ? AND christian = ? AND month = ? AND day = ?";

	private Connection connection;

	public SyukkinDao() {
	}

	public SyukkinDao(Connection connection) {
		this.connection = connection;
	}

	//出勤メソッド
	public String syukkin(int userId, int christian, int month, int day, String week, String goingWork) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SYUKKINZIKAN)) {
			try (PreparedStatement stmtt = connection.prepareStatement(SQL_CHECK)) {
				stmtt.setInt(1, userId);
				stmtt.setInt(2, christian);
				stmtt.setInt(3, month);
				stmtt.setInt(4, day);

				//既に出勤しているかチェック
				ResultSet rs = stmtt.executeQuery();
				if (rs.next()) {

					return "既に出勤しています。";

				} else {
					//出勤時間登録
					stmt.setInt(1, userId);
					stmt.setInt(2, christian);
					stmt.setInt(3, month);
					stmt.setInt(4, day);
					stmt.setString(5, week);
					stmt.setString(6, goingWork);

					stmt.executeUpdate();
					return "出勤しました。";

				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//退勤メソッド
	public String taikin(int userId, int christian, int month, int day, String leavingWork) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_TAIKINZIKAN);
				PreparedStatement stmtt = connection.prepareStatement(SQL_CHECK)) {
			stmtt.setInt(1, userId);
			stmtt.setInt(2, christian);
			stmtt.setInt(3, month);
			stmtt.setInt(4, day);

			//既に出勤しているかチェック
			ResultSet rs = stmtt.executeQuery();
			if (rs.next()) {

				stmt.setString(1, leavingWork);
				stmt.setInt(2, userId);
				stmt.setInt(3, christian);
				stmt.setInt(4, month);
				stmt.setInt(5, day);

				stmt.executeUpdate();

				return "退勤しました。";

			} else {

				return "出勤していません。";

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//休憩メソッド
	public String kyuukei(int userId, int christian, int month, int day, String breakStart) {
		try (PreparedStatement stmt = connection.prepareStatement(SQL_KYUUKEIZIKAN)) {
			Attendance atdance = new Attendance();
			try (PreparedStatement stmtt = connection.prepareStatement(SQL_SELECT_KYUUKEIZIKAN)) {
				//現在登録されている休憩時間を取得
				stmtt.setInt(1, userId);
				stmtt.setInt(2, christian);
				stmtt.setInt(3, month);
				stmtt.setInt(4, day);

				ResultSet rs = stmtt.executeQuery();
				rs.next();
				atdance.setBreak_start(rs.getString("break_start"));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			//DBに登録されている休憩時間にプラスしてDBに登録
			int num1 = Integer.parseInt(breakStart);
			System.out.println(atdance.getBreak_start());
			int num2 = Integer.parseInt(atdance.getBreak_start());
			int sum = num1 + num2;
			breakStart = Integer.toString(sum);
			stmt.setString(1, breakStart);
			stmt.setInt(2, userId);
			stmt.setInt(3, christian);
			stmt.setInt(4, month);
			stmt.setInt(5, day);

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return "休憩に入りました。";
	}
}
