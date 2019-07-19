package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.JoinUserApp;
import entity.Paid;
import entity.PaidApplication;

public class YukyuSinseiDao {

	//	private static final String SELECT_REMAINING_PAID = "select (select available_days from paid where usage_status = 2)  - (select available_days from paid where usage_status = 1) from paid where user_id = ?";
	private static final String SELECT_REMAINING_PAIDS = "select SUM(case when usage_status = 1 then available_days * (-1) when usage_status = 2 then available_days end) remaining from paid where user_id = ? group by user_id;";
	private static final String SELECT_PAID_USER_ALL = "select u.user_name,pa.appli_states, pa.remarks, pa.user_id from paid_application pa join user_info u on pa.user_id = u.user_id where pa.appli_states IN (1,2) AND pa.preferred_date = TO_DATE(?, 'YYYY/MM/DD')";
	private static final String SELECT_PAID_ALL = "select * from paid_application;";
	private static final String UPDATE_NINSYO = "UPDATE paid_application SET appli_states = '2' WHERE user_id = ? AND preferred_date = ?";
	private static final String UPDATE_HININSYO = "UPDATE paid_application SET appli_states = '3' WHERE user_id = ? AND preferred_date = ?";


	private Connection connection;

	public YukyuSinseiDao(Connection connection) {
		this.connection = connection;
	}

	public List<Paid> findPaid(int user_id) {
		List<Paid> list = new ArrayList<Paid>();

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_REMAINING_PAIDS)) {
			stmt.setInt(1, user_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Paid r = new Paid(rs.getInt("remaining"));
				list.add(r);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;
	}

	public List<JoinUserApp> findPaidUser(String paidDay) {
		List< JoinUserApp> list = new ArrayList< JoinUserApp>();


		try (PreparedStatement stmt = connection.prepareStatement(SELECT_PAID_USER_ALL)) {
			stmt.setString(1, paidDay);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				 JoinUserApp r = new  JoinUserApp(rs.getString("user_name"),rs.getInt("appli_states"),rs.getString("remarks"), rs.getInt("user_id"));

				list.add(r);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;
	}

	public List<PaidApplication> findAllPaid() {
		List<PaidApplication> list = new ArrayList<PaidApplication>();

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_PAID_ALL)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PaidApplication r = new PaidApplication(rs.getInt("application_id"), rs.getDate("appli_date"), rs.getInt("user_id"),
						rs.getDate("preferred_date"), rs.getString("remarks"),rs.getInt("appli_states"));
				list.add(r);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;
	}

	public void ninsyo(Integer user_id, java.sql.Date date) {
		//認証押されたときの処理
		try (PreparedStatement stmt = connection.prepareStatement(UPDATE_NINSYO)) {
			stmt.setInt(1, user_id);
			stmt.setDate(2, date);

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void hiNinsyo(Integer user_id, java.sql.Date date) {
		//却下されたときの処理
		try (PreparedStatement stmt = connection.prepareStatement(UPDATE_HININSYO)) {
			stmt.setInt(1, user_id);
			stmt.setDate(2, date);

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
