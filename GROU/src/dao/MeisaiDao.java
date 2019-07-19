package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Salary;

public class MeisaiDao {

	private static final String SELECT_ALL="select * from salary s Join user_info ui ON s.user_id = ui.user_id WHERE ui.user_id=? AND s.month=? AND s.year=?";
	private static final String SELECT_YEAR_MONTH = "select * from salary where year = ? AND month = ? AND user_id = ?";

	private Connection connection;

	public MeisaiDao(Connection connection) {
		this.connection = connection;
	}

	public List<Salary> findAllBySalary(int user_id, int month, int year) {
		List<Salary> list = new ArrayList<Salary>();

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL)) {
			stmt.setInt(1, user_id);
			stmt.setInt(2, month);
			stmt.setInt(3, year);
			System.out.println(SELECT_ALL);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Salary r = new Salary(rs.getInt("user_id"), rs.getInt("year"), rs.getInt("month"),rs.getInt("basic"), rs.getInt("overtime_work"),rs.getInt("deduction_amount"),rs.getInt("payment_amount"));
				list.add(r);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;
	}

	public List<Salary> findYearMonth(int year,int month,int user_id){
		List<Salary> list = new ArrayList<Salary>();

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_YEAR_MONTH)) {
			stmt.setInt(1, year);
			stmt.setInt(2, month);
			stmt.setInt(3, user_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Salary r = new Salary(rs.getInt("user_id"), rs.getInt("year"), rs.getInt("month"),rs.getInt("basic"), rs.getInt("overtime_work"),rs.getInt("deduction_amount"),rs.getInt("payment_amount"));
				list.add(r);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;

	}
}
