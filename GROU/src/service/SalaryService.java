package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.MeisaiDao;
import dao.SalaryDao;
import entity.Salary;
import util.DbUtil;

public class SalaryService {

	public SalaryService() {}

	public Salary findById(Integer userId) {

		Salary salary = new Salary();

		try (Connection conn = DbUtil.getConnection()) {
			SalaryDao salaryDao = new SalaryDao(conn);
			salary = salaryDao.findById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return salary;
	}

	public Salary findByIdAndYearAndMonth(Integer userId, Integer year, Integer month) {

		Salary salary = null;

		try (Connection conn = DbUtil.getConnection()) {
			SalaryDao salaryDao = new SalaryDao(conn);
			salary = salaryDao.findByIdAndYearAndMonth(userId, year, month);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return salary;

	}

    public boolean findByUserIdListAndYearAndMonth(List<Integer> userIdList, Integer year, Integer month) {

		boolean error = false;

		try (Connection conn = DbUtil.getConnection()) {
			SalaryDao salaryDao = new SalaryDao(conn);
			error = salaryDao.findByUserIdListAndYearAndMonth(userIdList, year, month);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return error;

    }

	public void updateByUserId(Integer userId, Integer year, Integer month) {

		try (Connection conn = DbUtil.getConnection()) {
			SalaryDao salaryDao = new SalaryDao(conn);
			salaryDao.updateByUserId(userId, year, month);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateByUserIdFromUserListAndYearAndMonth(List<Integer> userList, Integer year, Integer month) {

		try (Connection conn = DbUtil.getConnection()) {
			SalaryDao salaryDao = new SalaryDao(conn);
			salaryDao.updateByUserIdFromUserListAndYearAndMonth(userList, year, month);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Salary> findYearMonth(int year, int month, int user_id) {
		List<Salary> list = new ArrayList<Salary>();

		try (Connection conn = DbUtil.getConnection()) {
			MeisaiDao meisaiDao = new MeisaiDao(conn);
			list = meisaiDao.findYearMonth(year, month, user_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Salary> findAllBySalary(int user_id, int month, int year) {
		List<Salary> list = new ArrayList<Salary>();

		try (Connection conn = DbUtil.getConnection()) {
			MeisaiDao meisaiDao = new MeisaiDao(conn);
			list = meisaiDao.findAllBySalary(user_id,month,year);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

    public List<String> getSalaryResultByUserIdListAndYearAndMonth(List<Integer> userIdList, Integer year, Integer month) {

		List<String> list = new ArrayList<>();

		try (Connection conn = DbUtil.getConnection()) {
			SalaryDao salaryDao = new SalaryDao(conn);
			list = salaryDao.getSalaryResultByUserIdListAndYearAndMonth(userIdList, year, month);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

    }

    public void insertAutoByUserIdAndYearAndMonth(Integer userId,Integer year,Integer month) {

		try (Connection conn = DbUtil.getConnection()) {
			SalaryDao salaryDao = new SalaryDao(conn);
			salaryDao.insertAutoByUserIdAndYearAndMonth(userId, year, month);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

}
