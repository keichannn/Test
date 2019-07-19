package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class YukyuSinseiFormDao {

	private static final String INSERT_YUKYUSINSEI = "INSERT INTO paid_application (appli_date, user_id, preferred_date, remarks, appli_states) VALUES(TO_DATE(?, 'YYYY/MM/DD'),?,TO_DATE(?, 'YYYY/MM/DD'),?,1)";
	private static final String SELECT_SINSEI = "SELECT * FROM paid_application WHERE user_id = ? AND preferred_date = TO_DATE(?, 'YYYY/MM/DD')";
	private static final String UPDATE_BIKOU = "UPDATE paid_application SET remarks = ? WHERE user_id = ? AND preferred_date = TO_DATE(?, 'YYYY/MM/DD')";

	private Connection connection;

	public YukyuSinseiFormDao(Connection connection){
		this.connection = connection;
	}

	public void sinsei(String appli_date, Integer user_id, String preferred_date, String remarks) {
		try (PreparedStatement stmt = connection.prepareStatement(INSERT_YUKYUSINSEI)) {
			try (PreparedStatement stmtt = connection.prepareStatement(UPDATE_BIKOU)) {
				try (PreparedStatement stmttt = connection.prepareStatement(SELECT_SINSEI)) {

//					stmttt.setString(1, appli_date);
					stmttt.setInt(1, user_id);
					stmttt.setString(2, preferred_date);

					//既に申請しているかチェック
					ResultSet rs = stmttt.executeQuery();

					if (rs.next()) {
						stmtt.setString(1, remarks);
//						stmtt.setString(2, appli_date);
						stmtt.setInt(2, user_id);
						stmtt.setString(3, preferred_date);
						stmtt.executeUpdate();
						return;

					} else {
						stmt.setString(1, appli_date);
						stmt.setInt(2, user_id);
						stmt.setString(3, preferred_date);
						stmt.setString(4, remarks);
						stmt.executeUpdate();
						return;

					}
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
