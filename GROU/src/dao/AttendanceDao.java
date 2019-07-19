package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Attendance;

public class AttendanceDao {

	private static final String SELECT_ALL = "select * from attendance ad Join user_info ui ON ad.user_id = ui.user_id WHERE ui.user_id = ? AND ad.month=? ORDER BY day";

	private static final String SELECT_ALL_BY_ID_AND_YEAR_AND_MONTH_1 = "select ui.user_id, user_name, christian, month, day, week, going_work, leaving_work, break_start from attendance ad Join user_info ui ON ad.user_id = ui.user_id WHERE ui.user_id = ? AND ad.christian = ? AND ad.month = ? ORDER BY day";

	private static final String SELECT_ALL2 = "select * from attendance ad Join user_info ui ON ad.user_id = ui.user_id WHERE ui.user_id = ? AND ad.christian=? AND ad.month=? ORDER BY day";

	private static final String SELECT_ALL_BY_ID_AND_YEAR_AND_MONTH = "select * from attendance ad Join user_info ui ON ad.user_id = ui.user_id WHERE ui.user_id = ? AND ad.christian = ? AND ad.month=? ORDER BY day";

	private Connection connection;

	public AttendanceDao(Connection connection) {
		this.connection = connection;
	}

	public List<Attendance> findAllByMonth(Integer user_id, Integer month) {
		List<Attendance> list = new ArrayList<Attendance>();

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL)) {
			stmt.setInt(1, user_id);
			stmt.setInt(2, month);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Attendance r = new Attendance(rs.getInt("user_id"), rs.getInt("month"), rs.getInt("day"),
						rs.getString("week"), rs.getString("going_work"), rs.getString("leaving_work"),
						rs.getString("break_start"), rs.getInt("christian"));
				list.add(r);
			}

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}

		return list;
	}

	public Attendance findByYearAndMonth(Integer user_id, Integer year, Integer month) {

		//テスト
		System.out.print("/////");

		Attendance attendance = null;

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_BY_ID_AND_YEAR_AND_MONTH)) {
			stmt.setInt(1, user_id);
			stmt.setInt(2, year);
			stmt.setInt(3, month);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				attendance = new Attendance(rs.getInt("user_id"), rs.getInt("month"), rs.getInt("day"),
						rs.getString("week"), rs.getString("going_work"), rs.getString("leaving_work"),
						rs.getString("break_start"), rs.getInt("christian"));
			}

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}

		return attendance;

	}

	public List<Attendance> findByYearAndMonth2(Integer user_id, Integer christian, Integer month) {

		List<Attendance> list = new ArrayList<Attendance>();

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL2)) {
			stmt.setInt(1, user_id);
			stmt.setInt(2, christian);
			stmt.setInt(3, month);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Attendance r = new Attendance(rs.getInt("user_id"), rs.getInt("month"), rs.getInt("day"),
												rs.getString("week"), rs.getString("going_work"), rs.getString("leaving_work"),
												rs.getString("break_start"), rs.getInt("christian"));
				list.add(r);
			}



		} catch (SQLException e) {

			throw new RuntimeException(e);
		}

		return list;
	}

}
