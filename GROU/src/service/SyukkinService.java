package service;

import java.sql.Connection;

import dao.SyukkinDao;
import util.DbUtil;

public class SyukkinService {

	public SyukkinService() {

	}

	public String syukkin(int userId, int christian, int month, int day, String week, String goingWork) {
		String mes = null;
		try (Connection conn = DbUtil.getConnection()) {

        	SyukkinDao sd = new SyukkinDao(conn);
        	mes = sd.syukkin(userId, christian, month, day, week, goingWork);

        } catch (Exception e) {
            e.printStackTrace();
        }
		return mes;
	}

	public String taikin(int userId, int christian, int month, int day, String leavingWork) {
		String mes = null;
		try (Connection conn = DbUtil.getConnection()) {

        	SyukkinDao sd = new SyukkinDao(conn);
        	mes = sd.taikin(userId, christian, month, day, leavingWork);

        } catch (Exception e) {
            e.printStackTrace();
        }
		return mes;
	}

	public String kyuukei(int userId, int christian, int month, int day, String breakStart) {
		String mes = null;
		try (Connection conn = DbUtil.getConnection()) {

        	SyukkinDao sd = new SyukkinDao(conn);
        	mes = sd.kyuukei(userId, christian, month, day, breakStart);

        } catch (Exception e) {
            e.printStackTrace();
        }
		return mes;
	}

}
