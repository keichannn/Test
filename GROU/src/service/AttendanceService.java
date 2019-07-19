package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.AttendanceDao;
import entity.Attendance;
import util.DbUtil;

public class AttendanceService {

	public AttendanceService() {}

    public List<Attendance> findAllByMonth(Integer user_id, Integer month) {

        List<Attendance> list = null;

        try (Connection conn = DbUtil.getConnection()) {

        	AttendanceDao attendanceDao = new AttendanceDao(conn);
            list = attendanceDao.findAllByMonth(user_id, month);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

	public Attendance findByYearAndMonth(Integer user_id, Integer year, Integer month) {

		Attendance attendance = null;

	    try (Connection conn = DbUtil.getConnection()) {

	    	AttendanceDao attendanceDao = new AttendanceDao(conn);
	        return attendance = attendanceDao.findByYearAndMonth(user_id, year, month);

	    } catch (Exception e) {
	       e.printStackTrace();
	    }

	    return attendance;

	}

	public List<Attendance> findByYearAndMonth2(Integer user_id, Integer christian, Integer month) {
		 List<Attendance> list = new ArrayList<>();
		 try (Connection conn = DbUtil.getConnection()) {

	        	AttendanceDao attendanceDao = new AttendanceDao(conn);
	            list = attendanceDao.findByYearAndMonth2(user_id,christian, month);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }

}
