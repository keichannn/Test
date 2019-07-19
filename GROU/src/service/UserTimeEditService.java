package service;

import java.sql.Connection;

import dao.UserTimeEditDao;
import util.DbUtil;

public class UserTimeEditService {

	public UserTimeEditService() {

	}

	public void userTimeEdit(int userId, int christian, int month, int day, String week, String goingWork,
			String leavingWork, String breakStart) {
		try (Connection conn = DbUtil.getConnection()) {

			UserTimeEditDao uted = new UserTimeEditDao(conn);
			uted.userTimeEdit(userId, christian, month, day, week, goingWork, leavingWork, breakStart);

        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
