package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Salary;
import util.ParamUtil;

public class SalaryDao {

    private final String SELECT_BY_USER_ID = "SELECT * FROM salary WHERE user_id = ?";
    private final String SELECT_BY_USER_ID_AND_YEAR_AND_MONTH = "SELECT * FROM salary WHERE user_id = ? AND year = ? AND month = ?";
    private final String INSERT_TO_PAYMENT_AMOUNT = "update salary set payment_amount = (SELECT (basic+overtime_work)-deduction_amount where user_id = ?) where user_id = ? and year = ? and month = ?";
    private final String INSERT_AUTO_BY_USER_ID_AND_YEAR_MONTH = "INSERT INTO salary(user_id, year, month, basic, overtime_work, deduction_amount) VALUES(?,?,?,?,?,?)";

    private Connection connection;

    public SalaryDao(Connection connection) {
        this.connection = connection;
    }

    public Salary findById(Integer userId) {

    	Salary salary = null;

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_USER_ID)) {

        	stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            salary = new Salary();

            if(rs.next()) {

            	 salary.setUser_id(rs.getInt("user_id"));
            	 salary.setYear(rs.getInt("year"));
            	 salary.setMonth(rs.getInt("month"));
            	 salary.setBasic(rs.getInt("basic"));
            	 salary.setOvertime_work(rs.getInt("overtime_work"));
            	 salary.setDeduction_amount(rs.getInt("deduction_amount"));
            	 salary.setPayment_amount(rs.getInt("basic")-(rs.getInt("overtime_work") + rs.getInt("deduction_amount")));

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return salary;

    }

    public boolean findByUserIdListAndYearAndMonth(List<Integer> userIdList, Integer year, Integer month) {

    	boolean error = false;

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_USER_ID_AND_YEAR_AND_MONTH)) {

            for(Integer salary : userIdList) {

            	stmt.setInt(1, salary);
            	stmt.setInt(2, year);
            	stmt.setInt(3, month);

                ResultSet rs = stmt.executeQuery();

            	if(!rs.next()) {

            		return error = true;

            	}

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    	return error;

    }

    public List<String> getSalaryResultByUserIdListAndYearAndMonth(List<Integer> userIdList, Integer year, Integer month) {

    	List<String> list = new ArrayList<>();;

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_USER_ID_AND_YEAR_AND_MONTH)) {

            for(Integer userId : userIdList) {

            	stmt.setInt(1, userId);
            	stmt.setInt(2, year);
            	stmt.setInt(3, month);

                ResultSet rs = stmt.executeQuery();

            	if(rs.next()) {

            		if(!ParamUtil.isNullOrEmpty(rs.getString("payment_amount"))) {

            			list.add("○");

            		} else {

            			list.add("×");
            		}

            	} else if(!rs.next()) {

            		list.add("×");

            	}

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    	return list;

    }

    public Salary findByIdAndYearAndMonth(Integer userId, Integer year, Integer month) {

    	Salary salary = null;

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_USER_ID_AND_YEAR_AND_MONTH)) {

        	stmt.setInt(1, userId);
        	stmt.setInt(2, year);
        	stmt.setInt(3, month);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {

            	salary = new Salary(rs.getInt("user_id"),rs.getInt("year"),rs.getInt("month"),rs.getInt("basic"),rs.getInt("overtime_work"),rs.getInt("deduction_amount"),rs.getInt("payment_amount"));

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return salary;

    }

    public void updateByUserId(Integer userId, Integer year, Integer month) {

        try (PreparedStatement stmt = connection.prepareStatement(INSERT_TO_PAYMENT_AMOUNT)) {

        	stmt.setInt(1, userId);
        	stmt.setInt(2, userId);
        	stmt.setInt(3, year);
        	stmt.setInt(4, month);

            stmt.executeUpdate();

        } catch (SQLException e) {

          throw new RuntimeException(e);

        }

    }

    public void updateByUserIdFromUserListAndYearAndMonth(List<Integer> userList, Integer year, Integer month) {

        try (PreparedStatement stmt = connection.prepareStatement(INSERT_TO_PAYMENT_AMOUNT)) {

        	for(Integer userId :userList) {

        	stmt.setInt(1, userId);
        	stmt.setInt(2, userId);
        	stmt.setInt(3, year);
        	stmt.setInt(4, month);

            stmt.executeUpdate();

        	}

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public void insertAutoByUserIdAndYearAndMonth(Integer userId,Integer year,Integer month) {

        try (PreparedStatement stmt = connection.prepareStatement(INSERT_AUTO_BY_USER_ID_AND_YEAR_MONTH)) {

        	if(ParamUtil.isNullOrEmptyForObject(findByIdAndYearAndMonth(userId,year,month))) {

        		stmt.setInt(1, userId);;
        		stmt.setInt(2, year);
        		stmt.setInt(3, month);
        		stmt.setInt(4, 200000);
        		stmt.setInt(5, 30000);
        		stmt.setInt(6, 20000);

        		stmt.executeUpdate();

        	}

        } catch (SQLException e) {

          throw new RuntimeException(e);

        }

    }

}
